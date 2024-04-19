package org.zerock.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.zerock.w2.dao.TodoDAO;
import org.zerock.w2.domain.TodoVO;

import java.time.LocalDate;
import java.util.List;

public class TodoDAOTests {

    private TodoDAO todoDAO;

    //미리 테스트전에 todoDAO 객체를 생성함
    @BeforeEach
    public void read(){
        todoDAO = new TodoDAO();
    }

    @Test
    public void testTime() throws Exception {
        System.out.println(todoDAO.getTime2());
    }

    @Test
    public void testInsert() throws Exception {

        TodoVO todoVO = TodoVO.builder()
                .title("샘플 타이틀...")
                .dueDate(LocalDate.of(2024,4,17))
                .build();

        todoDAO.insert(todoVO);
    }

    @Test
    public void testList() throws Exception {
        List<TodoVO> list = todoDAO.selectAll();
        for (TodoVO todoVO : list) {
            System.out.println(todoVO);
        }
    }

    @Test
    public void testSelectOne() throws Exception {
        Long tno = 1L;
        TodoVO todoVO = todoDAO.selectOne(tno);
        System.out.println(todoVO);
    }

    @Test
    public void testDelete() throws Exception {
        todoDAO.deleteOne(3); //3번 삭제
    }

    @Test
    public void testUpdate() throws Exception {
        TodoVO vo = TodoVO.builder()
                .tno(1L)
                .title("수정 제목...")
                .dueDate(LocalDate.of(2024,4,18))
                .finished(true)
                .build();

        todoDAO.updateOne(vo);
    }
}
