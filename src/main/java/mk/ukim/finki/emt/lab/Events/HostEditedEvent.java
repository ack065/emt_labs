package mk.ukim.finki.emt.lab.Events;


import lombok.Getter;
import mk.ukim.finki.emt.lab.Model.domain.Host;
import org.springframework.context.ApplicationEvent;

import java.time.LocalDateTime;

@Getter
public class HostEditedEvent  extends ApplicationEvent {
    private final LocalDateTime editedAt;

    public HostEditedEvent(Host src) {
        super(src);
        this.editedAt = LocalDateTime.now();
    }

}
