package com.catspot.exceptionhandler;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class GlobalExceptionHandlerTest {

    private final GlobalExceptionHandler globalExceptionHandler = new GlobalExceptionHandler();

    @Test
    public void testGlobalException(){
        // Given
        Exception exception = new Exception("General Exception occurred");

        // When
        ResponseEntity<ErrorResponse> responseEntity = globalExceptionHandler.AllExceptionHandler(exception);

        // Then
        assertThat(responseEntity).isNotNull();
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        assertThat(responseEntity.getBody()).isNotNull();
        assertThat(responseEntity.getBody().getStatus()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR.value());
        assertThat(responseEntity.getBody().getMessage()).isEqualTo("General Exception occurred");
    }

    @Test
    public void test_INVALID_PARAMETER_Exception() {
        // Given
        CustomException customException = new CustomException(CommonErrorCode.INVALID_PARAMETER);

        // When
        ResponseEntity<ErrorResponse> responseEntity = globalExceptionHandler.CustomExceptionHandler(customException);

        // Then
        assertThat(responseEntity).isNotNull();
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(responseEntity.getBody()).isNotNull();
        assertThat(responseEntity.getBody().getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
        assertThat(responseEntity.getBody().getMessage()).isEqualTo("Invalid parameter included");
    }

    @Test
    public void test_RESOURCE_NOT_FOUND_Exception() {
        // Given
        CustomException customException = new CustomException(CommonErrorCode.RESOURCE_NOT_FOUND);

        // When
        ResponseEntity<ErrorResponse> responseEntity = globalExceptionHandler.CustomExceptionHandler(customException);

        // Then
        assertThat(responseEntity).isNotNull();
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(responseEntity.getBody()).isNotNull();
        assertThat(responseEntity.getBody().getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
        assertThat(responseEntity.getBody().getMessage()).isEqualTo("Resource not exists");
    }

}
