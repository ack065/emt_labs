package mk.ukim.finki.emt.lab.Model.dto.create;

import mk.ukim.finki.emt.lab.Model.domain.Country;
import mk.ukim.finki.emt.lab.Model.enumerations.Currency;

public record CreateCountryDTO(
        String name,
        String continent,
        Currency currency
) {
    public Country toCountry() {
        return new Country(name, continent, currency);
    }
}