package utils;

public class Utils {
    public static boolean checkSuccess(int chance) {
        return Math.random() * 100 < chance;
    }
}
