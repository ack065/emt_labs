package mk.ukim.finki.emt.lab.Jobs;

import mk.ukim.finki.emt.lab.Service.HostApplicationService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;



@Component
public class ScheduledTask {
    private final HostApplicationService hostApplicationService;

    public ScheduledTask(HostApplicationService hostApplicationService) {
        this.hostApplicationService = hostApplicationService;
    }

    @Scheduled(cron = "0 0 0 * * *")
    public void refreshMaterializedView(){
        this.hostApplicationService.refreshMaterializedView();
    }
}
