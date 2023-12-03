package edu.hw8.task3;

import java.util.HashMap;
import java.util.Map;

public class SingleThreadCracker implements MD5PasswordCracker {
    //    private final PasswordGenerator passwordGenerator = new PasswordGenerator();
    private final NewPasswordGenerator newPasswordGenerator = new NewPasswordGenerator();

    @Override
    public Map<String, String> crackPasswords(Map<String, String> hashPersonMap) {
        HashMap<String, String> tempHashPersonMap = new HashMap<>(hashPersonMap);
        HashMap<String, String> resultPersonPasswordMap = new HashMap<>();

        while (!tempHashPersonMap.isEmpty()) {
            String password = newPasswordGenerator.getNextPassword();

            if (password == null) {
                break;
            }

            String hash = MD5HashConverter.getHexPasswordString(password);
            if (tempHashPersonMap.containsKey(hash)) {
                resultPersonPasswordMap.put(tempHashPersonMap.get(hash), password);
                tempHashPersonMap.remove(hash);
            }
        }

        return resultPersonPasswordMap;
    }

//    @Override
//    public Map<String, String> crackPasswords(Map<String, String> hashPersonMap) throws NoSuchAlgorithmException {
//        Map<String, String> tempHashPersonMap = new HashMap<>(hashPersonMap);
//        Map<String, String> resultPersonPasswordMap = new HashMap<>();
//        MessageDigest md = MessageDigest.getInstance("MD5");
//        int currentLength = 0;
//
//        while (!tempHashPersonMap.isEmpty() && currentLength < PasswordGenerator.MAX_PASSWORD_LENGTH) {
//
//            List<String> passwords = passwordGenerator.generateNextPasswordsBatch();
//            for (String password : passwords) {
//                String hash = MD5HashConverter.getHexPasswordString(password);
//                if (tempHashPersonMap.containsKey(hash)) {
//                    resultPersonPasswordMap.put(tempHashPersonMap.get(hash), password);
//                }
//            }
//
//            currentLength++;
//        }
//
//        return resultPersonPasswordMap;
//    }
}
