package edu.hw11.task2;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.agent.ByteBuddyAgent;
import net.bytebuddy.dynamic.loading.ClassReloadingStrategy;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Solution for second task
 */
class ClassModificationTest {
    @Test
    void testClassChanging() {
        ByteBuddyAgent.install();

        new ByteBuddy()
            .redefine(ArithmeticUtils.class)
            .method(ElementMatchers.named("sum"))
            .intercept(MethodDelegation.to(ArithmeticUtilsInterceptor.class))
            .make()
            .load(ClassLoader.getSystemClassLoader(), ClassReloadingStrategy.fromInstalledAgent())
            .getLoaded();

        assertThat(ArithmeticUtils.sum(2, 3)).isEqualTo(6);
    }

    public static class ArithmeticUtils {
        public static int sum(int a, int b) {
            return a + b;
        }
    }

    public static class ArithmeticUtilsInterceptor {
        public static int sum(int a, int b) {
            return a * b;
        }
    }
}
