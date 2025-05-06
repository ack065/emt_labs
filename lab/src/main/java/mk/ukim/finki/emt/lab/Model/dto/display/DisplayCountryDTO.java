package mk.ukim.finki.emt.lab.Model.dto.display;

import mk.ukim.finki.emt.lab.Model.domain.Country;
import mk.ukim.finki.emt.lab.Model.enumerations.Currency;

import java.util.List;
import java.util.stream.Collectors;

public record DisplayCountryDTO(
        Long id,
        String name,
        String continent,
        Currency currency
) {
    public static DisplayCountryDTO from(Country country) {
        return new DisplayCountryDTO(
                country.getID(),
                country.getName(),
                country.getContinent(),
                country.getCurrency()
        );
    }

    public static List<DisplayCountryDTO> from(List<Country> countries) {
        return countries.stream().map(DisplayCountryDTO::from).collect(Collectors.toList());
    }
}