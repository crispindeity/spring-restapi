package study.events;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EventTest {

    @Test
    @DisplayName("event 클래스에 빌더가 존재하는 경우 테스트가 통과해야 한다.")
    void eventBuilderTest() {
        //given
        //when
        Event event = Event.builder()
                .name("Spring REST API")
                .description("Study")
                .build();
        //then
        assertThat(event).isNotNull();
    }

    @Test
    @DisplayName("빌더를 사용하지 않고 클래스 생성이 가능한 경우 테스트가 통과해야 한다.")
    void javaBeanTest() {
        //given
        String name = "Event";
        String description = "Spring";

        //when
        Event event = new Event();
        event.setName(name);
        event.setDescription(description);

        //then
        assertSoftly(softAssertions -> {
            softAssertions.assertThat(event.getName()).as("이벤트 클래스의 이름은 Event 여야 한다.").isEqualTo(name);
            softAssertions.assertThat(event.getDescription()).as("이벤트 클래스의 설명은 Spring 이여야 한다.").isEqualTo(description);
        });
    }
}
