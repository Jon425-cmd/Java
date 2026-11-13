import java.util.Scanner;

/**
 * SimpleDate 的客户端测试程序：反复读入两个月日，并打印其间的天数。
 */
public class TestSimpleDate {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        System.out.println("日数计数器（SimpleDate）");
        System.out.println();

        String response;
        do {
            System.out.print("输入第一次日期（月日）：");
            int month1 = console.nextInt();
            int day1 = console.nextInt();

            System.out.print("进入第二个日期（月日）：");
            int month2 = console.nextInt();
            int day2 = console.nextInt();

            SimpleDate first = new SimpleDate(month1, day1);
            SimpleDate second = new SimpleDate(month2, day2);
            int days = first.daysToDate(second);
            System.out.println(first + "到" + second + "之间有" + days + "天");
            System.out.println();

            System.out.print("更多？");
            response = console.next();
        } while (isYes(response));
    }

    private static boolean isYes(String response) {
        return response.equals("是的")
                || response.equals("是")
                || response.equalsIgnoreCase("yes")
                || response.equalsIgnoreCase("y");
    }
}
