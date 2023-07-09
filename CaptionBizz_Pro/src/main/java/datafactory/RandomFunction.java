package datafactory;

import java.util.Random;

public class RandomFunction {
    public static String AlphabeticString() {
        int number = 5;
        String AlphabeticString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "abcdefghijklmnopqrstuvxyz";
        StringBuilder sb = new StringBuilder(number);
        for (int i = 0; i < number; i++) {
            int index = (int) (AlphabeticString.length() * Math.random());
            sb.append(AlphabeticString.charAt(index));
        }
        return sb.toString();
    }

    public static String getPhoneNumber() {
        Random random = new Random();
        int num1 = random.nextInt(900) + 100;
        int num2 = random.nextInt(643) + 100;
        int num3 = random.nextInt(9000) + 1000;
        return num1 + String.valueOf(num2) + num3;
    }
    public static String getEmail() {
        return ("test" + System.nanoTime() + "@yopmail.com");
    }
}
