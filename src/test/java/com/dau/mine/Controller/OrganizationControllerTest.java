package com.dau.mine.Controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OrganizationControllerTest {

    @DisplayName("erroe code 테스트")
    @Test
    void ExceptionTest(){
        throw new RuntimeException("erroe code 테스트입니다.");
    }
}