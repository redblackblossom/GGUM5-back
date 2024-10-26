package com.catspot.excel;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.catspot.exceptionhandler.CommonErrorCode;
import com.catspot.exceptionhandler.CustomException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ExcelDataInitializerTest {

    @Mock
    private ExcelReader excelReader;

    @InjectMocks
    private ExcelDataInitializer excelDataInitializer;

    @Test
    void 잘_읽고_다니냐(){
        // given
        doNothing().when(excelReader).importClassrooms(any());

        // when
        assertDoesNotThrow(() -> excelDataInitializer.run());

        // then
        verify(excelReader, times(1)).importClassrooms(any());
    }

    @Test
    void 이걸_못읽네(){
        // given
        doThrow(new RuntimeException()).when(excelReader).importClassrooms(any());

        // when & then
        CustomException exception = assertThrows(CustomException.class, () -> excelDataInitializer.run());
        assertEquals(CommonErrorCode.INTERNAL_SERVER_ERROR, exception.getErrorCode());
    }
}