package com.xinghuan.biz.api.model;

import com.xinghuan.biz.api.BaseTest;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import java.util.Set;

/**
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @since
 */
public class UserTest extends BaseTest {

    @Test
    public void testValidateUser() {

        User user = new User();

        Set<ConstraintViolation<User>> constraintViolations = validate(user);

        constraintViolations.forEach(cv -> System.out.println(cv.getMessage()));

        // UnexpectedTypeException <- ConstraintDeclarationException <- ValidationException
    }
}

