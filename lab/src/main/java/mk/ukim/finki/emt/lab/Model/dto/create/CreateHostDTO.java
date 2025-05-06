package mk.ukim.finki.emt.lab.Model.dto.create;

import mk.ukim.finki.emt.lab.Model.domain.Host;

public record CreateHostDTO(
        String name,
        String surname,
        Long countryId
) {
    public Host toHost() {
        return new Host(name, surname, null);
    }
}