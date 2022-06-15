package study.springrestapi.events;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import java.net.URI;

@Controller
@ComponentScan
public class EventController {

    @PostMapping("/api/events")
    public ResponseEntity createEvent() {

        URI createdUri = linkTo(
                methodOn(EventController.class)
                        .createEvent()
        )
                .slash("{id}")
                .toUri();
        return ResponseEntity.created(createdUri).build();
    }

}
