package study.springrestapi.events;

import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

@SpringBootTest()
@AutoConfigureMockMvc
class EventControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    @DisplayName("이벤트가 정상적으로 생성되면 테스트를 통과해야 한다.")
    void createEventTest() throws Exception {
        //given
        Event event = Event.builder()
                .name("Spring")
                .description("Spring Study")
                .beginEnrollMentDateTime(LocalDateTime.of(2022, 6, 14, 14, 34))
                .closeEnrollmentDateTime(LocalDateTime.of(2022, 6, 15, 14, 35))
                .beginEnrollMentDateTime(LocalDateTime.of(2022, 6, 16, 14, 37))
                .endEventDateTime(LocalDateTime.of(2022, 6, 17, 14, 38))
                .basePrice(100)
                .maxPrice(200)
                .limitOfEnrollment(100)
                .location("강남역 D2 스타트업 팩토리")
                .build();
        //when
        //then
        mockMvc.perform(post("/api/events")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaTypes.HAL_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("id")
                        .exists()
                );
    }
}
