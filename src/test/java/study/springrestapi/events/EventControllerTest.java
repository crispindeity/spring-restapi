package study.springrestapi.events;

import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

@SpringBootTest()
@AutoConfigureMockMvc
class EventControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    EventRepository eventRepository;

    @Test
    @DisplayName("이벤트가 정상적으로 생성되면 테스트를 통과해야 한다.")
    void createEventTest() throws Exception {
        //given
        Event event = Event.builder()
                .name("Spring")
                .description("Spring Study")
                .beginEnrollmentDateTime(LocalDateTime.of(2022, 6, 14, 14, 34))
                .closeEnrollmentDateTime(LocalDateTime.of(2022, 6, 15, 14, 35))
                .beginEnrollmentDateTime(LocalDateTime.of(2022, 6, 16, 14, 37))
                .endEventDateTime(LocalDateTime.of(2022, 6, 17, 14, 38))
                .basePrice(100)
                .maxPrice(200)
                .limitOfEnrollment(100)
                .location("강남역 D2 스타트업 팩토리")
                .build();

        //when
        event.setId(10);
        Mockito.when(eventRepository.save(event)).thenReturn(event);

        //then
        mockMvc.perform(post("/api/events")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaTypes.HAL_JSON)
                        .content(objectMapper.writeValueAsString(event)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("id")
                        .exists()
                );
    }
}
