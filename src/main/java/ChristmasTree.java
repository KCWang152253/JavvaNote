import java.util.Scanner;

public class ChristmasTree {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 获取用户输入的树高度
        System.out.print("请输入树的高度: ");
        int height = scanner.nextInt();

        // 获取用户输入的构造符号
        System.out.print("请输入构造树的符号: ");
        char symbol = scanner.next().charAt(0);

        // 打印圣诞树
        printTree(height, symbol);

        scanner.close();
    }

    public static void printTree(int height, char symbol) {
        for (int i = 1; i <= height; i++) {
            // 打印空格
            for (int j = i; j < height; j++) {
                System.out.print(" ");
            }
            // 打印符号
            for (int k = 1; k <= (2 * i - 1); k++) {
                System.out.print(symbol);
            }
            // 换行
            System.out.println();
        }
        // 打印树干
        for (int i = 1; i <= height / 3; i++) {
            for (int j = 1; j < height; j++) {
                System.out.print(" ");
            }
            System.out.println("|");
        }
    }
}