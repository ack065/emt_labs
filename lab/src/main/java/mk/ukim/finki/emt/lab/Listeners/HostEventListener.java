package mk.ukim.finki.emt.lab.Listeners;


import mk.ukim.finki.emt.lab.Events.HostCreatedEvent;
import mk.ukim.finki.emt.lab.Events.HostDeletedEvent;
import mk.ukim.finki.emt.lab.Events.HostEditedEvent;
import mk.ukim.finki.emt.lab.Service.CountryApplicationService;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class HostEventListener {
    private final CountryApplicationService countryApplicationService;

    public HostEventListener(CountryApplicationService countryApplicationService) {
        this.countryApplicationService = countryApplicationService;
    }
    @EventListener
    public void onHostCreated(HostCreatedEvent event) {
        this.countryApplicationService.refreshMaterializedView();
    }
    @EventListener
    public void onHostDeleted(HostDeletedEvent event) {
        this.countryApplicationService.refreshMaterializedView();
    }
    @EventListener
    public void onHostEdited(HostEditedEvent event) {
        this.countryApplicationService.refreshMaterializedView();
    }
}
