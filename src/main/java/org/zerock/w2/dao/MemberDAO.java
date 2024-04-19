package org.zerock.w2.dao;

import lombok.Cleanup;
import org.zerock.w2.domain.MemberVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

//tbl_member 테이블 사용객체
public class MemberDAO {

    //아이디,패스워드를 입력해서 DB 검색하여 유저를 리턴함
    public MemberVO getWithPassword(String mid, String mpw) throws Exception {

        String query = "SELECT * FROM tbl_member WHERE mid = ? AND mpw = ?";

        MemberVO memberVO = null;
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, mid);
        preparedStatement.setString(2, mpw);
        @Cleanup ResultSet resultSet = preparedStatement.executeQuery();

        resultSet.next();
        memberVO = MemberVO.builder()
                .mid(resultSet.getString(1))
                .mpw(resultSet.getString(2))
                .mname(resultSet.getString(3))
                .build();

        return memberVO;
    }
}
