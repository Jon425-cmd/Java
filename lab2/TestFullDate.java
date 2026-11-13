import java.util.Scanner;

/**
 * FullDate 的客户端测试程序：读入两个年月日，并打印其间的天数。
 * 非法日期由 FullDate 构造器抛出，在 getDate 中捕获后重新输入。
 */
public class TestFullDate {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        System.out.println("日数计数器（FullDate）");
        System.out.println();

        String response;
        do {
            FullDate first = getDate(console, "输入第一次日期（y m d）：");
            FullDate second = getDate(console, "输入第二个日期（y m d）：");
            int days = first.daysToDate(second);
            System.out.println(first + "到" + second + "之间有" + days + "天");
            System.out.println();
            System.out.print("更多？");
            response = console.next();
        } while (isYes(response));
    }

    public static FullDate getDate(Scanner console, String prompt) {
        while (true) {
            System.out.print(prompt);
            int year = console.nextInt();
            int month = console.nextInt();
            int day = console.nextInt();
            try {
                return new FullDate(year, month, day);
            } catch (BadDateException e) {
                System.out.println("糟糕的日期：" + e.getMessage());
            }
        }
    }

    private static boolean isYes(String response) {
        return response.equals("是的")
                || response.equals("是")
                || response.equalsIgnoreCase("yes")
                || response.equalsIgnoreCase("y");
    }
}
