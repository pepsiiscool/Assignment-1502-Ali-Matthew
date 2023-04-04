package controller;

public class CustomException extends Throwable {

    /**
     * custom exception for negative price
     * @param s
     */

    public CustomException(String s) {
        super(s);
    }
}
