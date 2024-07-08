package com.schening.java.delegate;

/**
 * @author shenchen
 * @since 2024/7/4 19:26
 */
public class DelegateDemo {
    public static void main(String[] args) {
        Son son = new Son(null);
        son.test();
    }
}

interface Human {
    void display();
}

class Grandpa implements Human {
    public void display() {
        System.out.println("Grandpa class");
    }
}

class Father extends Grandpa {
    public void display() {
        System.out.println("Father class");
    }
}

class Son extends Father {

    Human delegate;

    public Son(Human Human) {
        this.delegate = Human;
    }

    public void display() {
        System.out.println("Son class");
    }

    void test() {
        if (delegate != null) {
            delegate.display();
            return;
        }
        super.display(); // 在调试中，这行可能输出 true
    }
}