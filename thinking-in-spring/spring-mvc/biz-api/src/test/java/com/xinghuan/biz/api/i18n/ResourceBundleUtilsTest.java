package com.xinghuan.biz.api.i18n;

import org.junit.jupiter.api.Test;

import java.util.ResourceBundle;

/**
 * {@link ResourceBundleUtils} Test
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @since 1.0.0
 */
public class ResourceBundleUtilsTest {

    @Test
    public void testJavaPropertiesResourceBundle() {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("META-INF.Messages");
        System.out.println(resourceBundle.getString("my.name"));
    }

    @Test
    public void testJavaClassResourceBundle() {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("com.acme.biz.api.i18n.HardCodeResourceBundle");
        System.out.println(resourceBundle.getString("my.name"));
    }
}
