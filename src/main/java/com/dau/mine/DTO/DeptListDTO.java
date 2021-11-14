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
    private String id;
    private String code;
    private String dept_nm;
    private String updept_cd;
    private String type;
    private String member_no;
    private String name;
    private String manager;
    private List<DeptListDTO> children;
}
