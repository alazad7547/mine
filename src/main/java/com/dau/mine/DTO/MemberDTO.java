package com.dau.mine.DTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor // 자동으로 모든 매개변수를 받는 생성자를 생성
@NoArgsConstructor
@Data
public class MemberDTO {
    private String dept_cd;
    private String member_no;
    private String member_nm;
    private String manager_yn;
}
