package com.dau.mine.Service;

import com.dau.mine.DTO.DeptListDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class OganizationServiceTest {

    @DisplayName("하위부서 생성 테스트")
    @Test
    void MakeGroupDataTest() throws Exception {
        //given
        final OrganizationService organizationService = new OrganizationService();
        List<DeptListDTO> deptList = new ArrayList<>();
        deptList.add(new DeptListDTO("1","1","ABC회사",null,"Company","1","사장1","Y",null));
        deptList.add(new DeptListDTO("2","2","경영지원본부","1","Division","1","본부장1","Y",null));
        deptList.add(new DeptListDTO("3","5","회계본부","1","Division","1","회계본부장1","Y",null));
        deptList.add(new DeptListDTO("4","6","회계팀","5","Departmeent","1","개발1","N",null));


        //when
        List<DeptListDTO> result = organizationService.MakeGroupData(deptList);

        //then
        System.out.println(result.toString());
    }



}