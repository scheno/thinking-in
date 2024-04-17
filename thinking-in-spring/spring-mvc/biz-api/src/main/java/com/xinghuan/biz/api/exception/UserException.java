package com.xinghuan.biz.api.exception;

/**
 * TODO Comment
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @since TODO
 */
public class UserException extends RuntimeException {

    public UserException() {
        // 序列化使用
    }

    public UserException(String message, Throwable cause) {
        super(message, cause, false, false);
    }
}
