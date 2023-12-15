package edu.hw10.task1;

import edu.hw10.task1.myannotations.Max;
import edu.hw10.task1.myannotations.Min;
import edu.hw10.task1.myannotations.NotNull;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Random;

/**
 * Generate random objects, invoking constructors or factory methods
 * Works only with record and pojo
 */
public class RandomObjectGenerator {
    private static final int RANDOM_STRING_MAX_LENGTH = 100;
    private static final int ALPHABETIC_LEFT_LIMIT = 'a';
    private static final int ALPHABETIC_RIGHT_LIMIT = 'z';

    /**
     * Create instance of cls class with randomly generated fields
     *
     * @param cls               - class of returned instance
     * @param factoryMethodName - name of factory method, which will be used to create instance
     * @return instance of cls class
     * @throws NoSuchMethodException if cls hasn't factory method with factoryMethodName name
     */
    public Object nextObject(Class<?> cls, String factoryMethodName)
        throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Method factoryMethod = null;

        for (Method method : cls.getMethods()) {
            if (method.getName().equals(factoryMethodName)) {
                factoryMethod = method;
                break;
            }
        }

        if (factoryMethod == null) {
            throw new NoSuchMethodException("Class %s hasn't factory method %s".formatted(
                cls.getName(),
                factoryMethodName
            ));
        }

        return factoryMethod.invoke(cls, generateRandomParams(factoryMethod.getParameters()));
    }

    /**
     * Generates random object, using constructor with max number of parameters
     */
    public Object nextObject(Class<?> cls)
        throws InvocationTargetException, InstantiationException, IllegalAccessException {
        Constructor<?>[] constructors = cls.getConstructors();

        if (constructors.length == 0) {
            throw new IllegalArgumentException(
                "It is not possible to create an instance of a class that does not have a constructor");
        }

        Constructor<?> maxConstructor = constructors[0];
        int maxCount = maxConstructor.getParameterCount();

        for (Constructor<?> constructor : constructors) {
            if (constructor.getParameterCount() > maxCount) {
                maxCount = constructor.getParameterCount();
                maxConstructor = constructor;
            }
        }

        return maxConstructor.newInstance(
            generateRandomParams(maxConstructor.getParameters())
        );
    }

    private Object[] generateRandomParams(Parameter[] parameters) {
        Object[] randomParameters = new Object[parameters.length];
        Random random = new Random();

        for (int pos = 0; pos < parameters.length; pos++) {
            Parameter parameter = parameters[pos];
            Class<?> parameterType = parameter.getType();

            if (parameterType.equals(String.class)) {
                double nullStringProbability = parameter.isAnnotationPresent(NotNull.class) ? -1.0 : 0.5;
                randomParameters[pos] = generateRandomAlphabeticString(
                    random.nextInt(0, RANDOM_STRING_MAX_LENGTH),
                    nullStringProbability
                );
            } else {
                double minValue = -Double.MAX_VALUE;
                double maxValue = Double.MAX_VALUE;

                if (parameter.isAnnotationPresent(Min.class)) {
                    minValue = parameter.getAnnotation(Min.class).value();
                }

                if (parameter.isAnnotationPresent(Max.class)) {
                    maxValue = parameter.getAnnotation(Max.class).value();
                }

                if (parameterType.equals(int.class)) {
                    randomParameters[pos] = random.nextInt((int) minValue, (int) maxValue);
                } else if (parameterType.equals(double.class)) {
                    randomParameters[pos] = random.nextDouble(minValue, maxValue);
                } else if (parameterType.equals(float.class)) {
                    randomParameters[pos] = random.nextFloat((float) minValue, (float) maxValue);
                } else if (parameterType.equals(boolean.class)) {
                    randomParameters[pos] = random.nextBoolean();
                } else if (parameterType.equals(long.class)) {
                    randomParameters[pos] = random.nextLong((long) minValue, (long) maxValue);
                }
            }
        }

        return randomParameters;
    }

    private String generateRandomAlphabeticString(int length, double nullStringProbability) {
        Random random = new Random();

        if (random.nextDouble() <= nullStringProbability) {
            return null;
        }

        return random.ints(ALPHABETIC_LEFT_LIMIT, ALPHABETIC_RIGHT_LIMIT + 1)
            .limit(length)
            .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
            .toString();
    }
}
