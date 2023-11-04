package edu.hw4;

import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AnimalUtilitiesTest {

    Animal cat1 = new Animal("First cat", Animal.Type.CAT, Animal.Sex.M, 4, 60, 8, true);
    Animal dog = new Animal("Dog", Animal.Type.DOG, Animal.Sex.F, 13, 300, 40, true);
    Animal cat2 = new Animal("Second cat", Animal.Type.CAT, Animal.Sex.M, 2, 50, 10, true);
    Animal spider1 = new Animal("Spider", Animal.Type.SPIDER, Animal.Sex.F, 1, 5, 2, true);
    Animal spider2 = new Animal("Spider2", Animal.Type.SPIDER, Animal.Sex.F, 2, 6, 1, true);
    Animal fish1 = new Animal("First fish", Animal.Type.FISH, Animal.Sex.F, 7, 20, 10, false);
    Animal bird = new Animal("Bird a b", Animal.Type.BIRD, Animal.Sex.F, 6, 30, 4, false);
    Animal fish2 = new Animal("Second fish", Animal.Type.FISH, Animal.Sex.M, 2, 10, 15, false);

    // Fishes for task 18
    // They are not supposed to be in animals list
    Animal fish3 = new Animal("Third fist", Animal.Type.FISH, Animal.Sex.M, 2, 10, 5, false);
    Animal fish4 = new Animal("Fourth fist", Animal.Type.FISH, Animal.Sex.M, 3, 12, 10, false);

    public List<Animal> animalList() {
        return List.of(
            cat2, dog, cat1, spider1, spider2, fish1, bird, fish2
        );
    }

    /**
     * Incorrect animals for task 19-20
     */
    public List<Animal> incorrectAnimals() {
        return List.of(
            new Animal("", Animal.Type.FISH, Animal.Sex.F, 1, 1, 1, false),
            new Animal("Null type", null, Animal.Sex.F, 1, 1, 1, false),
            new Animal("Null sex", Animal.Type.FISH, null, 1, 1, 1, false),
            new Animal("Negative age", Animal.Type.FISH, Animal.Sex.F, -1, 1, 1, false),
            new Animal("Negative height", Animal.Type.FISH, Animal.Sex.F, 1, -1, 1, false),
            new Animal("Negative weight", Animal.Type.FISH, Animal.Sex.F, 1, 1, -1, false),
            new Animal("Negative height and weight", Animal.Type.FISH, Animal.Sex.F, 1, -1, -1, false),

            // Correct (without errors)
            // It should not be included in list of animals with mistakes in AnimalUtilities::getIncorrectAnimals method
            new Animal("Fish", Animal.Type.FISH, Animal.Sex.F, 1, 1, 1, false)
        );
    }

    // 1
    @Test
    void testSortByHeight() {
        assertThat(
            AnimalUtilities.sortByHeight(animalList()))
            .isEqualTo(
                List.of(
                    spider1, spider2, fish2, fish1, bird, cat2, cat1, dog
                ));
    }

    // 2
    @Test
    void testSortByWeightTopK() {
        assertThat(
            AnimalUtilities.sortByWeightTopK(animalList(), 2))
            .isEqualTo(
                List.of(
                    dog, fish2
                ));
    }

    // 3
    @Test
    void testCountTypes() {
        assertThat(
            AnimalUtilities.countTypes(animalList()))
            .isEqualTo(
                Map.of(
                    Animal.Type.CAT, 2,
                    Animal.Type.DOG, 1,
                    Animal.Type.FISH, 2,
                    Animal.Type.BIRD, 1,
                    Animal.Type.SPIDER, 2
                ));
    }

    // 4
    @Test
    void testGetAnimalWithLongestName() {
        assertThat(
            AnimalUtilities.getAnimalWithLongestName(animalList()))
            .isEqualTo(fish2);
        assertThrows(
            IllegalArgumentException.class,
            () -> AnimalUtilities.getAnimalWithLongestName(List.of())
        );
    }

    // 5
    @Test
    void testGetGenderPrevalence() {
        assertThat(
            AnimalUtilities.getGenderPrevalence(animalList()))
            .isEqualTo(Animal.Sex.F);
    }

    // 6
    @Test
    void testGetHeaviestOfEachType() {
        assertThat(
            AnimalUtilities.getHeaviestOfEachType(animalList()))
            .isEqualTo(
                Map.of(
                    Animal.Type.CAT, cat2,
                    Animal.Type.DOG, dog,
                    Animal.Type.FISH, fish2,
                    Animal.Type.BIRD, bird,
                    Animal.Type.SPIDER, spider1
                ));
    }

    // 7
    @Test
    void testGetOldest() {
        assertThat(
            AnimalUtilities.getOldest(animalList(), 2))
            .isEqualTo(fish1);
        assertThrows(
            IllegalArgumentException.class,
            () -> AnimalUtilities.getOldest(List.of(), 2)
        );
    }

    // 8
    @Test
    void testGetHeaviestLowerThan() {
        assertThat(
            AnimalUtilities.getHeaviestLowerThan(animalList(), 290))
            .isEqualTo(Optional.of(fish2));

        assertThat(
            AnimalUtilities.getHeaviestLowerThan(animalList(), 1).isPresent())
            .isFalse();
    }

    // 9
    @Test
    void testGetNumberOfPaws() {
        assertThat(
            AnimalUtilities.getNumberOfPaws(animalList()))
            .isEqualTo(4 + 4 + 4 + 8 + 8 + 2);
    }

    // 10
    @Test
    void testGetAgeNotNumberOfPaws() {
        assertThat(
            AnimalUtilities.getAgeNotNumberOfPaws(animalList()))
            .isEqualTo(
                List.of(
                    cat2, dog, spider1, spider2, fish1, bird, fish2
                ));
    }

    // 11
    @Test
    void testGetBigBitingAnimals() {
        assertThat(
            AnimalUtilities.getBigBitingAnimals(animalList()))
            .isEqualTo(
                List.of(
                    dog
                ));
    }

    // 12
    @Test
    void testCountWeightMoreThanHeight() {
        assertThat(
            AnimalUtilities.countWeightMoreThanHeight(animalList()))
            .isEqualTo(1);
    }

    // 13
    @Test
    void testGetAnimalsWithLongNames() {
        assertThat(
            AnimalUtilities.getAnimalsWithLongNames(animalList()))
            .isEqualTo(
                List.of(
                    bird
                ));
    }

    // 14
    @Test
    void testHasBigDog() {
        assertThat(
            AnimalUtilities.hasBigDog(animalList(), 200))
            .isTrue();
    }

    // 15
    @Test
    void testGetSumWeight() {
        assertThat(
            AnimalUtilities.getSumWeight(animalList(), 2, 10))
            .isEqualTo(Map.of(
                Animal.Type.CAT, cat1.weight() + cat2.weight(),
                Animal.Type.FISH, fish1.weight() + fish2.weight(),
                Animal.Type.SPIDER, spider2.weight(),
                Animal.Type.BIRD, bird.weight()
            ));
    }

    // 16
    @Test
    void testGetSortedByTypeSexName() {
        assertThat(
            AnimalUtilities.getSortedByTypeSexName(animalList()))
            .isEqualTo(
                List.of(
                    // Type: CAT < DOG < BIRD < FISH < SPIDER
                    // Sex: M < F
                    // Name: lexicographical order
                    cat1, cat2, dog, bird, fish2, fish1, spider1, spider2
                ));
    }

    // 17
    @Test
    void testIsSpiderMoreBitingThanDogs() {
        assertThat(
            AnimalUtilities.isSpiderMoreBitingThanDogs(animalList()))
            .isTrue();

        assertThat(
            AnimalUtilities.isSpiderMoreBitingThanDogs(List.of()))
            .isFalse();
    }

    // 18
    @Test
    void testGetHeaviestFish() {
        assertThat(
            AnimalUtilities.getHeaviestFish(List.of(animalList(), List.of(fish3, fish4))))
            .isEqualTo(fish2);

        assertThrows(
            IllegalArgumentException.class,
            () -> AnimalUtilities.getHeaviestFish(List.of())
        );
    }

    // 19
    @Test
    void testGetIncorrectAnimals() {
        Map<String, Set<ValidationError>> expected = Map.of(

            "",
            Set.of(new ValidationError("Name cannot be blank", ValidationError.Field.name)),

            "Negative age",
            Set.of(new ValidationError("Age must be non-negative", ValidationError.Field.age)),

            "Null type",
            Set.of(new ValidationError("Type cannot be null", ValidationError.Field.type)),

            "Negative height",
            Set.of(new ValidationError("Height must be non-negative", ValidationError.Field.height)),

            "Negative weight",
            Set.of(new ValidationError("Weight must be non-negative", ValidationError.Field.weight)),

            "Null sex",
            Set.of(new ValidationError("Sex cannot be null", ValidationError.Field.sex)),

            "Negative height and weight",
            Set.of(
                new ValidationError("Height must be non-negative", ValidationError.Field.height),
                new ValidationError("Weight must be non-negative", ValidationError.Field.weight)
            )

        );

        assertThat(
            AnimalUtilities.getIncorrectAnimals(incorrectAnimals()))
            .isEqualTo(expected);
    }

    // 20
    @Test
    void testGetIncorrectAnimalsStrings() {
        Map<String, String> expected = Map.of(

            "",
            "ValidationError{field=name, errorMessage='Name cannot be blank'}",

            "Negative age",
            "ValidationError{field=age, errorMessage='Age must be non-negative'}",

            "Negative height",
            "ValidationError{field=height, errorMessage='Height must be non-negative'}",

            "Null type",
            "ValidationError{field=type, errorMessage='Type cannot be null'}",

            "Negative weight",
            "ValidationError{field=weight, errorMessage='Weight must be non-negative'}",

            "Null sex",
            "ValidationError{field=sex, errorMessage='Sex cannot be null'}",

            "Negative height and weight",
            "ValidationError{field=height, errorMessage='Height must be non-negative'}, ValidationError{field=weight, errorMessage='Weight must be non-negative'}"

        );

        assertThat(
            AnimalUtilities.getIncorrectAnimalsStrings(incorrectAnimals()))
            .isEqualTo(expected);
    }
}
