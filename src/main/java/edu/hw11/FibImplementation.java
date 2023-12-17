package edu.hw11;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.bytecode.ByteCodeAppender;
import net.bytebuddy.jar.asm.Label;
import net.bytebuddy.jar.asm.MethodVisitor;
import net.bytebuddy.jar.asm.Opcodes;

public class FibImplementation {

    private static final String fibClassName = "FibonacciCalculator";

    public static long fib(int n) {
        if (n < 3) {
            return 1;
        }

        return fib(n - 2) + fib(n - 1);
    }

    public static Class<?> buildClass() {
        return new ByteBuddy()
            .subclass(Object.class)
            .name(fibClassName)
            .defineMethod("fib", long.class, Opcodes.ACC_PUBLIC | Opcodes.ACC_STATIC)
            .withParameter(int.class, "number")
            .intercept(new Implementation.Simple(new FibonacciByteCodeAppender()))
            .make()
            .load(ClassLoader.getSystemClassLoader())
            .getLoaded();
    }

    /**
     * Bytecode of fib recursive method
     */
    static class FibonacciByteCodeAppender implements ByteCodeAppender {

        @Override
        public Size apply(
            MethodVisitor methodVisitor,
            Implementation.Context context,
            MethodDescription methodDescription
        ) {
            var lessThenOrEqualTwo = new Label();

            methodVisitor.visitVarInsn(Opcodes.ILOAD, 0); // Load to stack N
            methodVisitor.visitInsn(Opcodes.ICONST_3); // Load to stack 3

            // if N >= 3
            methodVisitor.visitJumpInsn(Opcodes.IF_ICMPGE, lessThenOrEqualTwo);

            // If N < 3 then return 1
            methodVisitor.visitInsn(Opcodes.LCONST_1);
            methodVisitor.visitInsn(Opcodes.LRETURN);

            // Visit this label if N >= 3, then continue executing
            methodVisitor.visitLabel(lessThenOrEqualTwo);
            methodVisitor.visitFrame(Opcodes.F_SAME, 0, null, 0, null);

            // Invoke fib(n - 2)
            methodVisitor.visitVarInsn(Opcodes.ILOAD, 0);
            methodVisitor.visitInsn(Opcodes.ICONST_2);
            methodVisitor.visitInsn(Opcodes.ISUB);
            methodVisitor.visitMethodInsn(Opcodes.INVOKESTATIC, fibClassName, "fib", "(I)J", false);

            // Invoke fib(n - 1)
            methodVisitor.visitVarInsn(Opcodes.ILOAD, 0);
            methodVisitor.visitInsn(Opcodes.ICONST_1);
            methodVisitor.visitInsn(Opcodes.ISUB);
            methodVisitor.visitMethodInsn(Opcodes.INVOKESTATIC, fibClassName, "fib", "(I)J", false);

            // Return fib(n - 2) + fib(n - 1) of previously calculated values
            methodVisitor.visitInsn(Opcodes.LADD);
            methodVisitor.visitInsn(Opcodes.LRETURN);

            return new Size(4, 1);
        }
    }
}
