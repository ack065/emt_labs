package mk.ukim.finki.emt.lab.Service;

import mk.ukim.finki.emt.lab.Model.dto.create.CreateCountryDTO;
import mk.ukim.finki.emt.lab.Model.dto.display.DisplayCountryDTO;
import mk.ukim.finki.emt.lab.Model.views.HostsByCountry;

import java.util.List;
import java.util.Optional;

public interface CountryApplicationService {
    List<DisplayCountryDTO> findAll();
    DisplayCountryDTO create(CreateCountryDTO createCountryDto);
    DisplayCountryDTO update(Long id, CreateCountryDTO updateDto);
    void delete(Long id);
    Optional<DisplayCountryDTO> findById(Long id);

    List<HostsByCountry> findAllHostsByCountry();
    HostsByCountry findHostsByCountry(Long id);
    void refreshMaterializedView();
}
