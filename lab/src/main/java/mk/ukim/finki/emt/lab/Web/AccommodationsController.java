package mk.ukim.finki.emt.lab.Web;


import mk.ukim.finki.emt.lab.Service.HostApplicationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/accommodations")
public class AccommodationsController {
    private final HostApplicationService hostApplicationService;

    public AccommodationsController(HostApplicationService hostApplicationService) {
        this.hostApplicationService = hostApplicationService;
    }

    @GetMapping("/by-host")
    public ResponseEntity<?> findAllHostsByAccommodation() {
        return ResponseEntity.status(HttpStatus.OK).body(hostApplicationService.findAll());
    }
}
