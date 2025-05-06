package mk.ukim.finki.emt.lab.Service.impl;

import mk.ukim.finki.emt.lab.Model.domain.Country;
import mk.ukim.finki.emt.lab.Model.dto.create.CreateCountryDTO;
import mk.ukim.finki.emt.lab.Model.dto.display.DisplayCountryDTO;
import mk.ukim.finki.emt.lab.Model.views.HostsByCountry;
import mk.ukim.finki.emt.lab.Repository.CountryRepository;
import mk.ukim.finki.emt.lab.Repository.HostsByCountryRepository;
import mk.ukim.finki.emt.lab.Service.CountryApplicationService;
import mk.ukim.finki.emt.lab.Service.domain.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CountryApplicationServiceImpl implements CountryApplicationService {

    private final CountryRepository countryRepository;
    private final CountryService countryService;
    private final HostsByCountryRepository hostsByCountryRepository;

    public CountryApplicationServiceImpl(CountryRepository countryRepository, CountryService countryService, HostsByCountryRepository hostsByCountryRepository) {
        this.countryRepository = countryRepository;
        this.countryService = countryService;
        this.hostsByCountryRepository = hostsByCountryRepository;
    }

    @Override
    public List<DisplayCountryDTO> findAll() {
        return countryService.findAll().stream().map(DisplayCountryDTO::from).collect(Collectors.toList());
    }
    @Override
    public DisplayCountryDTO create(CreateCountryDTO createCountryDto) {
        Country country = new Country(
                createCountryDto.name(),
                createCountryDto.continent(),
                createCountryDto.currency()
        );
        Country savedCountry = countryRepository.save(country);
        return DisplayCountryDTO.from(savedCountry);
    }

    @Override
    public DisplayCountryDTO update(Long id, CreateCountryDTO updateDto) {
        Country country = countryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Country not found"));

        country.setName(updateDto.name());
        country.setContinent(updateDto.continent());
        country.setCurrency(updateDto.currency());

        Country updatedCountry = countryRepository.save(country);
        return DisplayCountryDTO.from(updatedCountry);
    }

    @Override
    public void delete(Long id) {
        countryRepository.deleteById(id);
    }

    @Override
    public Optional<DisplayCountryDTO> findById(Long id) {
        return countryService.findById(id).map(DisplayCountryDTO::from);
    }

    @Override
    public List<HostsByCountry> findAllHostsByCountry() {
        return hostsByCountryRepository.findAll();
    }

    @Override
    public HostsByCountry findHostsByCountry(Long id) {
        return hostsByCountryRepository.findById(id).orElseThrow(() -> new RuntimeException("Host not found"));
    }

    @Override
    public void refreshMaterializedView() {
        hostsByCountryRepository.refreshMaterializedView();
    }
}
