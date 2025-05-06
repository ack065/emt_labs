package mk.ukim.finki.emt.lab.Model.dto.display;

import mk.ukim.finki.emt.lab.Model.domain.Host;

import java.util.List;
import java.util.stream.Collectors;

public record DisplayHostDTO(
        Long id,
        String name,
        String surname,
        DisplayCountryDTO country
) {
    public static DisplayHostDTO from(Host host) {
        return new DisplayHostDTO(
                host.getID(),
                host.getName(),
                host.getSurname(),
                DisplayCountryDTO.from(host.getCountry())
        );
    }

    public static List<DisplayHostDTO> from(List<Host> hosts) {
        return hosts.stream().map(DisplayHostDTO::from).collect(Collectors.toList());
    }
}
