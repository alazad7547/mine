package com.dau.mine.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor // 자동으로 모든 매개변수를 받는 생성자를 생성
@NoArgsConstructor
@Data
public class DeptListDTO {
    private String id; //rowNum
    private String code; //부서코드
    private String dept_nm; //부서이름
    private String updept_cd; //상위부서코드
    private String type; //타입
    private String member_no; //사원번호
    private String name; //사원이름
    private String manager; //매니저 여부
    private List<DeptListDTO> children; //하위부서
}
