package com.dau.mine.DAO;

import com.dau.mine.DTO.MemberDTO;
import org.apache.ibatis.annotations.Param;

public interface MemberDAO {
    int MemberInsert(@Param("dto") MemberDTO dto) throws Exception;
    int MemberUpdate(@Param("dto") MemberDTO dto, @Param("member_no") String member_no) throws Exception;
    int MemberDelete(@Param("dto") MemberDTO dto, @Param("member_no") String member_no) throws Exception;
    int MemberAllDelete(@Param("dept_cd") String dept_cd) throws Exception;
}
