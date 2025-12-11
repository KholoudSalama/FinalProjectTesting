package utils;

import com.github.javafaker.Faker;
import java.util.Random;

public class TestDataGenerator {
    private static Faker faker = new Faker();
    private static Random random = new Random();

    public static String generateFirstName() {
        return faker.name().firstName();
    }

    public static String generateLastName() {
        return faker.name().lastName();
    }

    public static String generateEmail() {
        return "test" + System.currentTimeMillis() + "@test.com";
    }

    public static String generatePhoneNumber() {
        return faker.phoneNumber().cellPhone();
    }

    public static String generatePassword() {
        return "Test@" + random.nextInt(10000);
    }

    public static String generateAddress() {
        return faker.address().streetAddress();
    }

    public static String generateCity() {
        return faker.address().city();
    }

    public static String generatePostCode() {
        return faker.address().zipCode();
    }

    public static String generateCompany() {
        return faker.company().name();
    }
}