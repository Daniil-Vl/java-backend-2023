package edu.hw10.task1;

import edu.hw10.task1.utilityclasses.ClassWithFactory;
import edu.hw10.task1.utilityclasses.Rectangle;
import edu.hw10.task1.utilityclasses.StrRecord;
import java.lang.reflect.InvocationTargetException;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class RandomObjectGeneratorTest {

    private static final RandomObjectGenerator rog = new RandomObjectGenerator();

    @Test
    void createRecordRandomInstance() throws InvocationTargetException, InstantiationException, IllegalAccessException {
        var obj = rog.nextObject(StrRecord.class);
        StrRecord strRecord = (StrRecord) obj;
        assertThat(strRecord.first()).isNotNull();
        assertThat(strRecord.second()).isNotNull();
    }

    @Test
    void createRecordWithIntegers() throws InvocationTargetException, InstantiationException, IllegalAccessException {
        var obj = rog.nextObject(Rectangle.class);
        Rectangle rectangle = (Rectangle) obj;
        assertThat(rectangle).isNotNull();
    }

    @Test
    void createRandomInstanceUsingFactoryMethod()
        throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        var obj = rog.nextObject(ClassWithFactory.class, "create");
        ClassWithFactory classWithFactory = (ClassWithFactory) obj;
        assertThat(classWithFactory).isNotNull();
    }

    @Test
    void createRandomObjectUsingNonexistentFactoryMethod_ShouldThrowNoSuchMethodException() {
        assertThatThrownBy(() -> {
            rog.nextObject(ClassWithFactory.class, "nonExistentFactoryMethod");
        }).isInstanceOf(NoSuchMethodException.class);
    }

    @Test
    void createRectangleWithMinMaxAnnotations()
        throws InvocationTargetException, InstantiationException, IllegalAccessException {
        var obj = rog.nextObject(Rectangle.class);
        Rectangle rectangle = (Rectangle) obj;
        assertThat(rectangle.width()).isBetween(0, 10);
        assertThat(rectangle.height()).isBetween(0, 10);
    }
}
