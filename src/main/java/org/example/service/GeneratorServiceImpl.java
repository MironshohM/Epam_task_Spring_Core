package org.example.service;


import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class GeneratorServiceImpl implements GeneratorService {

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int PASSWORD_LENGTH = 10;
    private static final SecureRandom RANDOM = new SecureRandom();

    // Map to track unique usernames
    private final Map<String, Integer> usernameMap = new ConcurrentHashMap<>();

    @Override
    public String generatePassword() {
        StringBuilder password = new StringBuilder(PASSWORD_LENGTH);
        for (int i = 0; i < PASSWORD_LENGTH; i++) {
            int index = RANDOM.nextInt(CHARACTERS.length());
            password.append(CHARACTERS.charAt(index));
        }
        return password.toString();
    }

    @Override
    public String generateUsername(String firstName, String lastName) {
        String baseUsername = firstName + "." + lastName;

        int count = usernameMap.getOrDefault(baseUsername, 0);
        String uniqueUsername = count == 0 ? baseUsername : baseUsername + count;

        usernameMap.put(baseUsername, count + 1);

        return uniqueUsername;
    }
}