package edu.hw11.task2;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.agent.ByteBuddyAgent;
import net.bytebuddy.dynamic.loading.ClassReloadingStrategy;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Solution for second task
 */
class ClassModificationTest {
    @Test
    void testClassChanging() {
        ByteBuddyAgent.install();

        ArithmeticUtils utils = new ArithmeticUtils();
        new ByteBuddy()
            .redefine(ArithmeticUtilsInterceptor.class)
            .name(ArithmeticUtils.class.getName())
            .make()
            .load(ArithmeticUtils.class.getClassLoader(), ClassReloadingStrategy.fromInstalledAgent());

        assertThat(utils.sum(2, 3)).isEqualTo(6);
    }

    static class ArithmeticUtils {
        public int sum(int a, int b) {
            return a + b;
        }
    }

    static class ArithmeticUtilsInterceptor {
        public int sum(int a, int b) {
            return a * b;
        }
    }
}
