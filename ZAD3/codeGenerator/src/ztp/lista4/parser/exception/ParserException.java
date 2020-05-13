package ztp.lista4.parser.exception;

import java.io.IOException;

public class ParserException extends IOException {
    public ParserException() {
        super();
    }

    public ParserException(String message) {
        super(message);
    }
}
