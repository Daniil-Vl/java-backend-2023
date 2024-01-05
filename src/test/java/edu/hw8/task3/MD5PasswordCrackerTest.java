package edu.hw8.task3;

import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static java.util.Map.entry;
import static org.assertj.core.api.Assertions.assertThat;

abstract class MD5PasswordCrackerTest {

    private static final Map<String, String> database = Map.ofEntries(
        entry("1a1dc91c907325c69271ddf0c944bc72", "user with password 'pass'"),
        entry("836a4218fb09b4a0ab0412e64de74315", "user with password 'simp'"),
        entry("202cb962ac59075b964b07152d234b70", "user with password '123'"),
        entry("fa246d0262c3925617b0c72bb20eeb1d", "user with password '9999'"),
        entry("cf79ae6addba60ad018347359bd144d2", "user with password '8888'"),
        entry("8b04d5e3775d298e78455efc5ca404d5", "user with password 'first'")
    );

    private static final Map<String, String> expectedCrackerPasswords = Map.ofEntries(
        entry("user with password 'pass'", "pass"),
        entry("user with password 'simp'", "simp"),
        entry("user with password '123'", "123"),
        entry("user with password '9999'", "9999"),
        entry("user with password '8888'", "8888"),
        entry("user with password 'first'", "first")
    );

    private MD5PasswordCracker passwordCracker;

    @BeforeEach
    void init() {
        passwordCracker = getInstance();
    }

    abstract MD5PasswordCracker getInstance();

    @Test
    void crackPasswords() {
        Map<String, String> actualCrackedPasswords = passwordCracker.crackPasswords(database);
        System.out.println("Expected: " + expectedCrackerPasswords);
        System.out.println("Actual: " + actualCrackedPasswords);
        assertThat(actualCrackedPasswords).isEqualTo(expectedCrackerPasswords);
    }
}
