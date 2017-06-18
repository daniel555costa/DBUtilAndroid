package st.domain.support.android.exception;

/**
 * Created by xdaniel on 6/18/17.
 */

public class JsonMapperException extends RuntimeException {
    public JsonMapperException() {
    }

    public JsonMapperException(String message) {
        super(message);
    }

    public JsonMapperException(String message, Throwable cause) {
        super(message, cause);
    }

    public JsonMapperException(Throwable cause) {
        super(cause);
    }

    public JsonMapperException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}