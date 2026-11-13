/**
 * 不含年份的月日日期。二月固定为 28 天，一年固定为 365 天。
 * 不使用 java.util.Date 或 java.util.GregorianCalendar。
 */
public class SimpleDate implements Comparable<SimpleDate> {
    private int month;
    private int day;

    /**
     * 用给定的月份和日期构造一个 SimpleDate。
     * 调用方保证参数有效：月份为 1–12，日期落在该月天数之内。
     */
    public SimpleDate(int month, int day) {
        this.month = month;
        this.day = day;
    }

    /** 返回当前日期的副本，后续修改互不影响。 */
    public SimpleDate copy() {
        return new SimpleDate(month, day);
    }

    /** 返回月份，范围为 1–12。 */
    public int getMonth() {
        return month;
    }

    /** 返回该月中的日期。 */
    public int getDay() {
        return day;
    }

    /** 以“月/日”形式返回日期，例如 3 月 24 日返回 "3/24"。 */
    public String toString() {
        return month + "/" + day;
    }

    /** 当参数也是 SimpleDate 且月、日都相同时返回 true。 */
    public boolean equals(Object d) {
        if (!(d instanceof SimpleDate)) {
            return false;
        }
        SimpleDate other = (SimpleDate) d;
        return month == other.month && day == other.day;
    }

    public int hashCode() {
        return 31 * month + day;
    }

    /** 返回当前月份的天数。二月始终为 28 天。 */
    public int daysInMonth() {
        switch (month) {
            case 2:
                return 28;
            case 4:
            case 6:
            case 9:
            case 11:
                return 30;
            default:
                return 31;
        }
    }

    /**
     * 将日期向前推进一天。月末会进入下个月，12 月 31 日之后进入 1 月 1 日。
     */
    public void nextDay() {
        day++;
        if (day > daysInMonth()) {
            day = 1;
            month++;
            if (month > 12) {
                month = 1;
            }
        }
    }

    /**
     * 返回从当前日期前进到 {@code to} 所需的天数。
     * 在副本上调用 nextDay，因此不会改变当前对象。
     * 若目标日期在年内更早，则跨过年末继续计数。
     */
    public int daysToDate(SimpleDate to) {
        SimpleDate current = copy();
        int days = 0;
        while (!current.equals(to)) {
            current.nextDay();
            days++;
        }
        return days;
    }

    /** 先比较月份，再比较日期。相等时返回 0。 */
    public int compareTo(SimpleDate date) {
        if (month != date.month) {
            return month - date.month;
        }
        return day - date.day;
    }
}
