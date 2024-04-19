package org.zerock.w2.domain;

import lombok.*;

import java.time.LocalDate;

//get 메소드, 빌더패턴, toString 메소드 자동생성됨
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TodoVO {
    private Long tno;
    private String title;
    private LocalDate dueDate;
    private boolean finished;
}
