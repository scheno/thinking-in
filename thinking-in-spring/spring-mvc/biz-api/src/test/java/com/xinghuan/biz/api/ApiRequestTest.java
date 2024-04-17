package com.xinghuan.biz.api;

import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import java.util.Set;

/**
 * {@link ApiRequest}
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @since
 */
public class ApiRequestTest extends BaseTest {

    @Test
    public void testValidateBody() {
        ApiRequest request = new ApiRequest();

        Set<ConstraintViolation<ApiRequest>> constraintViolations = validate(request);

        constraintViolations.forEach(cv -> System.out.println(cv.getMessage()));
    }
}
