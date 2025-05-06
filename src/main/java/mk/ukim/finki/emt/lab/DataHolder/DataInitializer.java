package mk.ukim.finki.emt.lab.DataHolder;

import jakarta.annotation.PostConstruct;

import mk.ukim.finki.emt.lab.Model.domain.Country;
import mk.ukim.finki.emt.lab.Model.enumerations.Currency;
import mk.ukim.finki.emt.lab.Model.domain.Host;
import mk.ukim.finki.emt.lab.Repository.CountryRepository;
import mk.ukim.finki.emt.lab.Repository.HostRepository;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {

    private final CountryRepository countryRepository;
    private final HostRepository hostRepository;

    public DataInitializer(CountryRepository countryRepository, HostRepository hostRepository) {
        this.countryRepository = countryRepository;
        this.hostRepository = hostRepository;
    }

    @PostConstruct
    public void initializeData() {
        Country country1 = new Country("Germany", "Europe", Currency.EUR);
        Country country2 = new Country("USA", "North America", Currency.USD);
        Country country3 = new Country("Japan", "Asia", Currency.YEN);

        this.countryRepository.save(country1);
        this.countryRepository.save(country2);
        this.countryRepository.save(country3);

        Host host1 = new Host("Oleg", "Gelovski", country1);
        Host host2 = new Host("Luka", "Akul", country2);
        Host host3 = new Host("Lee", "Sin", country3);

        this.hostRepository.save(host1);
        this.hostRepository.save(host2);
        this.hostRepository.save(host3);

    }
}