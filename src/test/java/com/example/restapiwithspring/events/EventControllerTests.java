package com.example.restapiwithspring.events;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * packageName    : com.example.restapiwithspring.events
 * fileName       : EventControllerTests
 * author         : user
 * date           : 2024-02-27
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-02-27        user       최초 생성
 */
@RunWith(SpringRunner.class)
@WebMvcTest
public class EventControllerTests {

    @Autowired
    MockMvc mockMvc;
    // MVC 테스트에 주로 사용함.
    // Mock으로 만든 가짜 요청을 디스패쳐 서블릿한테 보낼 수 있다
    // 웹과 관련된 테스트만 하기 때문에 슬라이싱 테스트라고 부른다.

    @Test
    public void createEvent() throws Exception {
        // perform은 에러 처리 필요
        // 201 = created
        mockMvc.perform(post("/api/events")
                        .contentType(MediaType.APPLICATION_JSON) // 요청 본문 내 컨텐츠 타입
                        .accept(MediaTypes.HAL_JSON)) // 어떠한 응답을 원하는지 명시
                .andExpect(status().isCreated());
    }
}
