package edu.hw2;

import edu.hw2.task4.CallStackInspector;
import edu.hw2.task4.CallingInfo;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CallingInfoTest {

    @Test
    void testCallingInfoMethod() {
        class TestClass {
            public CallingInfo methodA() {
                return methodB();
            }

            public CallingInfo methodB() {
                return CallStackInspector.callingInfo();
            }
        }

        TestClass temp = new TestClass();
        assertThat(temp.methodA()).isEqualTo(new CallingInfo("edu.hw2.CallingInfoTest$1TestClass", "methodB"));
    }
}
