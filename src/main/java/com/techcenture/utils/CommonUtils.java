package com.techcenture.utils;

import com.github.javafaker.Faker;

import java.time.YearMonth;
import java.util.Random;

public class CommonUtils {

    private CommonUtils() {
    }


    public static String randomDigits(int quantity) {
        String digits = "1234567890";
        String result = "";
        for (int i = 0; i < quantity; i++) {
            int randomIndex = (int) (Math.random() * digits.length());
            result += digits.charAt(randomIndex);
        }

        return result;
    }
    public static String generateRandomExpirationDate() {

        // Generate a random month (between 1 and 12)
        int randomMonth = new Random().nextInt(12) + 1;

        // Generate a random year between current year and 10 years from now
        int currentYear = YearMonth.now().getYear();
        int maxYear = currentYear + 10;
        int randomYear = getRandomNumberInRange(currentYear, maxYear);

        // Format the month and year into MM/YY format
        String formattedDate = String.format("%02d/%02d", randomMonth, randomYear % 100);

        return formattedDate;
    }

    // Helper method to generate a random number within a range
    private static int getRandomNumberInRange(int min, int max) {
        if (min > max) {
            throw new IllegalArgumentException("Max must be greater than min");
        }
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }
}
