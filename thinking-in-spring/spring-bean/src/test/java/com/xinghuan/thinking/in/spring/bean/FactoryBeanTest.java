package com.xinghuan.thinking.in.spring.bean;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

class FactoryBeanTest {

    @Test
    void testFactoryBean() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(PrinterFactoryBean.class);
        context.register(InkCartridge.class);
        context.refresh();

        Printer printerOne = context.getBean(Printer.class);
        Printer printerTwo = context.getBean(Printer.class);

        printerOne.print("Printer One");
        printerTwo.print("Printer Two");

        System.out.println(printerOne);
        System.out.println(printerTwo);

        assert(printerOne == printerTwo);

        context.close();
    }

    @Component
    static class PrinterFactoryBean implements FactoryBean<Printer> {

        @Autowired
        private InkCartridge inkCartridge;

        @Override
        public Printer getObject() throws Exception {
            return new Printer();
        }

        @Override
        public Class<?> getObjectType() {
            return Printer.class;
        }

    }

    static class Printer {

        public void print(String message) {
            System.out.println(message);
        }

    }

    @Component
    static class InkCartridge {

    }

}
