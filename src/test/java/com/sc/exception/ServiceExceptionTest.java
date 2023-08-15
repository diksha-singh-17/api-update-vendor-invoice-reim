package com.sc.exception;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class ServiceExceptionTest {

    @Test
    void testServiceException(){
        ServiceException exception = new ServiceException("Service Exception");
        Assertions.assertNotNull(exception);
    }

}
