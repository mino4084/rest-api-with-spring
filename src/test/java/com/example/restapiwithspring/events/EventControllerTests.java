package com.example.restapiwithspring.events;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    EventRepository eventRepository;

    @Test
    public void createEvent() throws Exception {
        Event event = Event.builder()
                .name("rest api event")
                .description("REST API development")
                .beginEnrollmentDateTime(LocalDateTime.of(2024,2,27,12,0,0))
                .closeEnrollmentDateTime(LocalDateTime.of(2024,2,28,12,0,0))
                .beginEventDateTime(LocalDateTime.of(2024,2,29,12,0,0))
                .endEventDateTime(LocalDateTime.of(2024,3,1,12,0,0))
                .basePrice(100)
                .maxPrice(200)
                .limitOfEnrollment(100)
                .location("강남역 D2 스타트업 팩토리")
                .build();

        event.setID(10);
        Mockito.when(eventRepository.save(event)).thenReturn(event);


        // perform은 에러 처리 필요
        // 201 = created
        mockMvc.perform(post("/api/events")
                        .contentType(MediaType.APPLICATION_JSON) // 요청 본문 내 컨텐츠 타입
                        .accept(MediaTypes.HAL_JSON)
                        .content(objectMapper.writeValueAsString(event))) // 어떠한 응답을 원하는지 명시
                .andDo(print())
                .andExpect(jsonPath("id").exists())
                .andExpect(status().isCreated())
                .andExpect(header().exists(HttpHeaders.LOCATION))
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaTypes.HAL_JSON_VALUE));
    }
}
