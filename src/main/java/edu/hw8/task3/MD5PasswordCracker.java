package edu.hw8.task3;

import java.util.Map;

public interface MD5PasswordCracker {
    Map<String, String> crackPasswords(Map<String, String> hashPersonMap);
}
