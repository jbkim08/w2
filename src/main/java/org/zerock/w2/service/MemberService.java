package org.zerock.w2.service;

import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.zerock.w2.dao.MemberDAO;
import org.zerock.w2.util.MapperUtil;

@Log4j2
public enum MemberService {
    INSTANCE;

    //서비스는 db와 컨트롤러 사이에서 컨트롤러에서 오는 DTO<=>VO , dao 객체 필요
    private MemberDAO dao;
    private ModelMapper modelMapper;

    MemberService() {
        dao = new MemberDAO();
        modelMapper = MapperUtil.INSTANCE.get();
    }
}
