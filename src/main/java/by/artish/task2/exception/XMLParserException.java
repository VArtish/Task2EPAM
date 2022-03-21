package by.artish.task2.exception;

public class XMLParserException extends Exception{
    public XMLParserException() {
        super();
    }

    public XMLParserException(String message) {
        super(message);
    }

    public XMLParserException(String message, Exception exception) {
        super(message, exception);
    }

    public XMLParserException(Exception exception) {
        super(exception);
    }
}
