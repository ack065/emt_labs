package mk.ukim.finki.emt.lab.Web;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import mk.ukim.finki.emt.lab.Model.domain.Country;
import mk.ukim.finki.emt.lab.Model.dto.create.CreateCountryDTO;
import mk.ukim.finki.emt.lab.Model.dto.display.DisplayCountryDTO;
import mk.ukim.finki.emt.lab.Service.CountryApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/countries")
@SecurityRequirement(name = "Authentication")
public class CountryRestController {

    private final CountryApplicationService countryApplicationService;

    public CountryRestController(CountryApplicationService countryApplicationService) {
        this.countryApplicationService = countryApplicationService;
    }

    @GetMapping
    public List<DisplayCountryDTO> findAll() {
        return countryApplicationService.findAll();
    }

    @PostMapping("/add-country")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<DisplayCountryDTO> addCountry(@RequestBody CreateCountryDTO createCountryDto) {
        return ResponseEntity.ok(countryApplicationService.create(createCountryDto));
    }

    @PutMapping("/edit-country/{id}")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<DisplayCountryDTO> editCountry(
            @PathVariable Long id,
            @RequestBody CreateCountryDTO updateDto) {
        return ResponseEntity.ok(countryApplicationService.update(id, updateDto));
    }

    @DeleteMapping("/delete-country/{id}")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<Void> deleteCountry(@PathVariable Long id) {
        countryApplicationService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DisplayCountryDTO> getCountryById(@PathVariable Long id) {
        return countryApplicationService.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
}
