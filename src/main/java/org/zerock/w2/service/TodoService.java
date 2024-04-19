package org.zerock.w2.service;

import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.zerock.w2.dao.TodoDAO;
import org.zerock.w2.domain.TodoVO;
import org.zerock.w2.dto.TodoDTO;
import org.zerock.w2.util.MapperUtil;

import java.util.ArrayList;
import java.util.List;

@Log4j2
public enum TodoService {
    INSTANCE;

    private TodoDAO dao;  //DB사용객체(CRUD)
    private ModelMapper modelMapper; //DTO <=> VO 데이터 변환

    TodoService() {
        //생성시 필요한 객체들을 만듬 (싱글턴)
        dao = new TodoDAO();
        modelMapper = MapperUtil.INSTANCE.get();
    }

    //새로운 할일을 등록하는 서비스
    //화면(유저,브라우저)로 받은 데이터(DTO)를 VO로 변환해서 DB에 저장
    public void register(TodoDTO todoDTO) throws Exception {
        TodoVO todoVO = modelMapper.map(todoDTO, TodoVO.class);
        //System.out.println("todoVO = " + todoVO);
        log.info("todoVO: " + todoVO);
        dao.insert(todoVO);
    }

    //화면에 할일 리스트를 보여주기 위한 서비스
    public List<TodoDTO> listAll() throws Exception {

        List<TodoVO> voList = dao.selectAll();
        log.info("voList 를 DB 에서 받음....");
        log.info("voList: " + voList);
        // VO => DTO 변환
        List<TodoDTO> dtoList = new ArrayList<TodoDTO>();
        for (TodoVO vo : voList) {
            TodoDTO dto = modelMapper.map(vo, TodoDTO.class);
            dtoList.add(dto);
        }

        return dtoList;
    }


    //특정 할일을 가져오는 서비스
    public TodoDTO get(Long tno) throws Exception {
        log.info("tno: " + tno);
        TodoVO todoVO = dao.selectOne(tno);
        TodoDTO dto = modelMapper.map(todoVO, TodoDTO.class);
        return dto;
    }

    //삭제 서비스
    public void remove(Long tno) throws Exception {
        log.info("tno: " + tno);
        dao.deleteOne(tno);
    }

    //수정 서비스 (todoDTO 에는 수정할 데이터와 번호 포함)
    public void update(TodoDTO todoDTO) throws Exception {
        log.info("todoDTO: " + todoDTO);
        TodoVO todoVO = modelMapper.map(todoDTO, TodoVO.class);
        dao.updateOne(todoVO);
    }
}










