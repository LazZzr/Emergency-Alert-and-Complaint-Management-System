package util;

public class Validator {
    public static boolean isValid(String input) {
        return input != null && !input.trim().isEmpty();
    }
}