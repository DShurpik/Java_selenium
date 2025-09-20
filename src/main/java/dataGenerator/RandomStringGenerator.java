package dataGenerator;

import java.util.Random;

public class RandomStringGenerator {

    public String generateRandomString() {
        Random random = new Random();
        int length = random.nextInt(1, 25);
        StringBuilder result = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
            int index = (int) (Math.random() * ALPHABET.length());
            result.append(ALPHABET.charAt(index));
        }
        return result.toString();
    }
}
