package mk.ukim.finki.emt.lab.Events;

import lombok.Getter;
import mk.ukim.finki.emt.lab.Model.domain.Host;
import org.springframework.context.ApplicationEvent;

import java.time.LocalDateTime;

@Getter
public class HostDeletedEvent extends ApplicationEvent {
    private LocalDateTime deletedAt;

    public HostDeletedEvent(Host src){
        super(src);
        this.deletedAt = LocalDateTime.now();
    }
}
