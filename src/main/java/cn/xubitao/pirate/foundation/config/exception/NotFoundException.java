package cn.xubitao.pirate.foundation.config.exception;

/**
 * Created by 15031046 on 2016/1/22.
 */
public class NotFoundException extends RuntimeException {
    public NotFoundException() {
    }

    public NotFoundException(String message) {
        super(message);
    }
}
