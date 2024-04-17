package com.xinghuan.biz.api.enums;

public enum StatusCode {

    OK(0, "OK") {
        @Override
        public String getMessage() {
            return super.message;
        }
    },


    FAILED(-1, "Failed"),

    CONTINUE(1, "{status-code.continue}");

    private final int code;

    private final String message; // 可能需要支持国际化

    StatusCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return getLocalizedMessage();
    }

    public String getLocalizedMessage() {
        // FIXME 增加国际化支持
        // 如果 message 是占位符，翻译成当前 message text
        // 否则，直接返回 message
        return message;
    }

}
