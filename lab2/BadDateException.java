/**
 * 非法日期。构造 FullDate 时，若年、月、日不能组成合法日期，则抛出此异常。
 */
public class BadDateException extends Exception {
    public BadDateException(String msg) {
        super(msg);
    }
}
