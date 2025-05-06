package mk.ukim.finki.emt.lab.Web;

import mk.ukim.finki.emt.lab.Model.dto.create.CreateCountryDTO;
import mk.ukim.finki.emt.lab.Model.dto.create.CreateHostDTO;
import mk.ukim.finki.emt.lab.Model.dto.display.DisplayBookingDTO;
import mk.ukim.finki.emt.lab.Model.dto.display.DisplayCountryDTO;
import mk.ukim.finki.emt.lab.Model.dto.display.DisplayHostDTO;
import mk.ukim.finki.emt.lab.Service.CountryApplicationService;
import mk.ukim.finki.emt.lab.Service.HostApplicationService;
import mk.ukim.finki.emt.lab.Service.domain.HostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hosts")
public class HostRestController {

    private final HostApplicationService hostApplicationService;
    private final CountryApplicationService countryApplicationService;

    public HostRestController(HostApplicationService hostApplicationService, CountryApplicationService countryApplicationService) {
        this.hostApplicationService = hostApplicationService;
        this.countryApplicationService = countryApplicationService;
    }

    @GetMapping
    public List<DisplayHostDTO> findAll() {
        return this.hostApplicationService.findAll();
    }
    @PostMapping("/add-country")
    @PreAuthorize("hasRole('User')")
    public ResponseEntity<DisplayHostDTO> addHost(@RequestBody CreateHostDTO createHostDTO) {
        return ResponseEntity.ok(hostApplicationService.create(createHostDTO));
    }

    @PutMapping("/edit-country/{id}")
    @PreAuthorize("hasRole('User')")
    public ResponseEntity<DisplayHostDTO> editHost(
            @PathVariable Long id,
            @RequestBody CreateHostDTO updateDto) {
        return ResponseEntity.ok(hostApplicationService.update(id, updateDto));
    }

    @DeleteMapping("/delete-country/{id}")
    @PreAuthorize("hasRole('User')")
    public ResponseEntity<Void> deleteHost(@PathVariable Long id) {
        hostApplicationService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DisplayHostDTO> getHostById(@PathVariable Long id) {
        return hostApplicationService.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/by-country")
    public ResponseEntity<?> findAllHostsByCountry(){
        return ResponseEntity.status(HttpStatus.OK).body(countryApplicationService.findAll());
    }

}