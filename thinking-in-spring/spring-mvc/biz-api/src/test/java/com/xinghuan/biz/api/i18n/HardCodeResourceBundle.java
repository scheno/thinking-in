package com.xinghuan.biz.api.i18n;

import java.util.ListResourceBundle;

/**
 * TODO Comment
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @since TODO
 */
public class HardCodeResourceBundle extends ListResourceBundle {

    private static final Object[][] contents = {
            {"my.name", "Mercy Ma"}
    };

    @Override
    protected Object[][] getContents() {
        return contents;
    }
}

