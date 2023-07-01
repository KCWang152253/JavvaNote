package utils;

import java.util.Random;
import java.util.stream.IntStream;

/**
 * @author KCWang
 * @version 1.0
 * @date 2023/7/1 上午11:28
 */
public class RandomUtils {
    public static void main(String[] args) {
        Random random = new Random();
        IntStream ints = random.ints();
        int i = random.nextInt(5);
        double v = Math.random();
//                new int[]{};
    }
}
