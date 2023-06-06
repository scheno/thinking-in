package com.xinghuan.thinking.in.spring.aop;

/**
 * @author shenchen
 * @since 2023/6/6 17:50
 */
public class ClassLoaderDemo {

    public static void main(String[] args) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        System.out.println(classLoader);

        ClassLoader parentClassLoader = classLoader;
        while (parentClassLoader != null) {
            parentClassLoader = parentClassLoader.getParent();
            if (parentClassLoader != null) {
                System.out.println(parentClassLoader);
            }
        }
    }

}
