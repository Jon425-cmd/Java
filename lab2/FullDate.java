/**
 * 带年份的日期。闰年二月为 29 天。
 * 月日的推进、每月天数和正向天数统计沿用 SimpleDate，这里只补充年份和闰年。
 */
public class FullDate extends SimpleDate {
    private int year;

    /**
     * 用年份、月份和日期构造 FullDate。
     * 年份小于 1、月份不在 1–12、或日期超出该月天数时抛出 BadDateException。
     */
    public FullDate(int year, int month, int day) throws BadDateException {
        super(month, day);
        this.year = year;
        if (year < 1 || month < 1 || month > 12 || day < 1 || day > daysInMonth()) {
            throw new BadDateException(year + "/" + month + "/" + day);
        }
    }

    /** 返回年份。 */
    public int getYear() {
        return year;
    }

    /** 闰年二月返回 29，其余月份沿用父类。 */
    public int daysInMonth() {
        if (getMonth() == 2 && isLeapYear()) {
            return 29;
        }
        return super.daysInMonth();
    }

    /** 跨过 12 月 31 日时年份加一。 */
    public void nextDay() {
        int previousMonth = getMonth();
        super.nextDay();
        if (previousMonth == 12 && getMonth() == 1) {
            year++;
        }
    }

    /** 返回包含年份的副本。 */
    public SimpleDate copy() {
        try {
            return new FullDate(year, getMonth(), getDay());
        } catch (BadDateException e) {
            throw new IllegalStateException(e);
        }
    }

    /** 以“年/月/日”形式返回日期，例如 "2016/10/25"。 */
    public String toString() {
        return year + "/" + super.toString();
    }

    /** 年份、月份和日期都相同时返回 true。 */
    public boolean equals(Object d) {
        if (!(d instanceof FullDate)) {
            return false;
        }
        FullDate other = (FullDate) d;
        return year == other.year && super.equals(other);
    }

    public int hashCode() {
        return 31 * super.hashCode() + year;
    }

    /** 先比较年份，再比较月日。 */
    public int compareTo(SimpleDate date) {
        if (date instanceof FullDate) {
            int yearDiff = year - ((FullDate) date).year;
            if (yearDiff != 0) {
                return yearDiff;
            }
        }
        return super.compareTo(date);
    }

    /**
     * 返回两个日期之间的天数，较晚的日期在前时结果同样为正。
     * 正向统计交给父类，父类会通过副本上的 nextDay 越过闰日和年末。
     */
    public int daysToDate(SimpleDate to) {
        if (to instanceof FullDate && compareTo(to) > 0) {
            return to.daysToDate(this);
        }
        if (!(to instanceof FullDate)) {
            return new SimpleDate(getMonth(), getDay()).daysToDate(to);
        }
        return super.daysToDate(to);
    }

    private boolean isLeapYear() {
        if (year % 400 == 0) {
            return true;
        }
        if (year % 100 == 0) {
            return false;
        }
        return year % 4 == 0;
    }
}
