package mk.ukim.finki.emt.lab.Events;


import lombok.Getter;
import mk.ukim.finki.emt.lab.Model.domain.Host;
import org.springframework.context.ApplicationEvent;

import java.time.LocalDateTime;

@Getter
public class HostCreatedEvent extends ApplicationEvent {
    private final LocalDateTime creationTime;

    public HostCreatedEvent(Host src) {
        super(src);
        this.creationTime = LocalDateTime.now();
    }
}
