package data;

import com.github.javafaker.Faker;

import java.util.Locale;

public class DataHelper {

    private static final Faker faker = new Faker(Locale.ENGLISH);

    public static String generateRandomLogin() {
        return faker.name().firstName();
    }

    public static String generateRandomPassword() {
        return faker.name().firstName();
    }

    public static AuthInfo generateRandomAuthInfo() {
        return new AuthInfo(generateRandomLogin(), generateRandomPassword());
    }

    public static AuthInfo getValidAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    public static String getRandomVerificationCode() {
        return faker.numerify("######");
    }
}
