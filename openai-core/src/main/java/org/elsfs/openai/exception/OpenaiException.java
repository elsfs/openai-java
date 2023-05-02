package org.elsfs.openai.exception;

/**
 * @author zeng
 * @since 0.0.1
 */
public class OpenaiException extends Exception {
    public OpenaiException() {
        super();
    }

    public OpenaiException(String message) {
        super(message);
    }

    public OpenaiException(String message, Throwable cause) {
        super(message, cause);
    }

    public OpenaiException(Throwable cause) {
        super(cause);
    }

    protected OpenaiException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
