package edu.hw4;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Solutions for 4 homework
 */
public class AnimalUtilities {

    private AnimalUtilities() {
    }

    /**
     * 1
     */
    public static List<Animal> sortByHeight(List<Animal> animals) {
        return animals.stream().sorted(Comparator.comparingInt(Animal::height)).toList();
    }

    /**
     * 2
     */
    public static List<Animal> sortByWeightTopK(List<Animal> animals, int k) {
        return animals.stream().sorted(Comparator.comparingInt(Animal::weight).reversed()).limit(k).toList();
    }

    /**
     * 3
     */
    public static Map<Animal.Type, Integer> countTypes(List<Animal> animals) {
        return animals.stream().collect(Collectors.toMap(
            Animal::type,
            animal -> 1,
            Integer::sum
        ));
    }

    /**
     * 4
     */
    public static Animal getAnimalWithLongestName(List<Animal> animals) {
        return animals.stream().max(Comparator.comparingInt(o -> o.name().length()))
            .orElseThrow(() -> new IllegalArgumentException(
                "Cannot find animal with longest name, when list is empty"
            ));
    }

    /**
     * 5
     */
    public static Animal.Sex getGenderPrevalence(List<Animal> animals) {
        if (animals.isEmpty()) {
            throw new IllegalArgumentException("Cannot get gender prevalence from empty list");
        }

        Map<Animal.Sex, Integer> map =
            animals.stream().collect(Collectors.toMap(Animal::sex, animal -> 1, Integer::sum));

        return map.getOrDefault(Animal.Sex.M, 0) > map.getOrDefault(Animal.Sex.F, 0)
            ? Animal.Sex.M
            : Animal.Sex.F;
    }

    /**
     * 6
     */
    public static Map<Animal.Type, Animal> getHeaviestOfEachType(List<Animal> animals) {
        return animals.stream().collect(Collectors.toMap(
            Animal::type,
            Function.identity(),
            (a1, a2) -> a1.weight() > a2.weight() ? a1 : a2
        ));
    }

    /**
     * 7
     */
    public static Animal getOldest(List<Animal> animals, int k) throws IllegalArgumentException {
        return animals.stream().sorted(Comparator.comparingInt(Animal::age).reversed()).skip(k - 1).findFirst()
            .orElseThrow(() -> new IllegalArgumentException("Cannot get oldest animal from empty list"));
    }

    /**
     * 8
     */
    public static Optional<Animal> getHeaviestLowerThan(List<Animal> animals, int k) {
        return animals.stream().filter(an -> an.height() < k).max(Comparator.comparingInt(Animal::weight));
    }

    /**
     * 9
     */
    public static Integer getNumberOfPaws(List<Animal> animals) {
        return animals.stream().mapToInt(Animal::paws).sum();
    }

    /**
     * 10
     */
    public static List<Animal> getAgeNotNumberOfPaws(List<Animal> animals) {
        return animals.stream().filter(an -> an.age() != an.paws()).toList();
    }

    /**
     * 11
     */
    @SuppressWarnings("MagicNumber")
    public static List<Animal> getBigBitingAnimals(List<Animal> animals) {
        return animals.stream().filter(an -> an.bites() && an.height() > 100).toList();
    }

    /**
     * 12
     */
    public static Integer countWeightMoreThanHeight(List<Animal> animals) {
        return Long.valueOf(
                animals.stream().filter(an -> an.weight() > an.height()).count())
            .intValue();
    }

    /**
     * 13
     */
    public static List<Animal> getAnimalsWithLongNames(List<Animal> animals) {
        return animals.stream()
            .filter(an -> an.name().trim().split(" ").length > 2)
            .toList();
    }

    /**
     * 14
     */
    public static Boolean hasBigDog(List<Animal> animals, int k) {
        return animals.stream()
            .anyMatch(an -> an.type().equals(Animal.Type.DOG) && an.height() > k);
    }

    /**
     * 15
     */
    public static Map<Animal.Type, Integer> getSumWeight(List<Animal> animals, int k, int i) {
        return animals.stream()
            .filter(an -> an.age() >= k && an.age() <= i)
            .collect(Collectors.toMap(
                Animal::type,
                Animal::weight,
                Integer::sum
            ));
    }

    /**
     * 16
     */
    public static List<Animal> getSortedByTypeSexName(List<Animal> animals) {
        return animals.stream()
            .sorted(Comparator.comparing(Animal::type)
                .thenComparing(Animal::sex)
                .thenComparing(Animal::name))
            .toList();
    }

    /**
     * 17
     */
    public static Boolean isSpiderMoreBitingThanDogs(List<Animal> animals) {
        return animals.stream().filter(an -> an.type().equals(Animal.Type.SPIDER) && an.bites()).count()
            > animals.stream().filter(an -> an.type().equals(Animal.Type.DOG) && an.bites()).count();
    }

    /**
     * 18
     */
    public static Animal getHeaviestFish(List<List<Animal>> listsOfFishes) {
        return listsOfFishes.stream()
            .flatMap(Collection::stream)
            .filter(an -> an.type().equals(Animal.Type.FISH))
            .max(Comparator.comparingInt(Animal::weight))
            .orElseThrow(() -> new IllegalArgumentException("Cannot get heaviest fish from empty list"));
    }

    /**
     * 19
     */
    public static Map<String, Set<ValidationError>> getIncorrectAnimals(List<Animal> animals) {
        return animals.stream()
            .filter(an -> !AnimalValidator.validate(an).isEmpty())
            .collect(Collectors.toMap(
                Animal::name,
                AnimalValidator::validate
            ));
    }

    /**
     * 20
     */
    public static Map<String, String> getIncorrectAnimalsStrings(List<Animal> animals) {
        return getIncorrectAnimals(animals).entrySet().stream()
            .collect(Collectors.toMap(
                Map.Entry::getKey,
                set -> String.join(", ", set.getValue().stream().map(ValidationError::toString).toList())
            ));
    }

}
