package com.xinghuan.biz.api;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.validation.beanvalidation.LocaleContextMessageInterpolator;

import javax.validation.*;
import javax.validation.bootstrap.GenericBootstrap;
import java.util.Set;

/**
 * 公用的 Test 类
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @since
 */
public abstract class BaseTest {

    private Validator validator;

    @BeforeEach
    public void init() {
        GenericBootstrap bootstrap = Validation.byDefaultProvider();
        Configuration<?> configuration = bootstrap.configure();
        MessageInterpolator targetInterpolator = configuration.getDefaultMessageInterpolator();
        configuration.messageInterpolator(new LocaleContextMessageInterpolator(targetInterpolator));
        ValidatorFactory validatorFactory = configuration.buildValidatorFactory();
        this.validator = validatorFactory.getValidator();
    }

    protected <T> Set<ConstraintViolation<T>> validate(T object, Class<?>... groups) {
        return validator.validate(object, groups);
    }
}
