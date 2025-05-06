package mk.ukim.finki.emt.lab.Service.impl;

import mk.ukim.finki.emt.lab.Events.HostCreatedEvent;
import mk.ukim.finki.emt.lab.Events.HostDeletedEvent;
import mk.ukim.finki.emt.lab.Events.HostEditedEvent;
import mk.ukim.finki.emt.lab.Model.domain.Country;
import mk.ukim.finki.emt.lab.Model.domain.Host;
import mk.ukim.finki.emt.lab.Model.dto.create.CreateHostDTO;
import mk.ukim.finki.emt.lab.Model.dto.display.DisplayHostDTO;
import mk.ukim.finki.emt.lab.Model.views.AccommodationsByHost;
import mk.ukim.finki.emt.lab.Repository.AccommodationsByHostRepository;
import mk.ukim.finki.emt.lab.Repository.CountryRepository;
import mk.ukim.finki.emt.lab.Repository.HostRepository;
import mk.ukim.finki.emt.lab.Service.HostApplicationService;
import mk.ukim.finki.emt.lab.Service.domain.HostService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HostApplicationServiceImpl implements HostApplicationService {
    private final HostRepository hostRepository;
    private final CountryRepository countryRepository;
    private final HostService hostService;
    private final AccommodationsByHostRepository accommodationsByHostRepository;
    private final ApplicationEventPublisher eventPublisher;

    public HostApplicationServiceImpl(HostRepository hostRepository, CountryRepository countryRepository, HostService hostService, AccommodationsByHostRepository accommodationsByHostRepository, ApplicationEventPublisher eventPublisher) {
        this.hostRepository = hostRepository;
        this.countryRepository = countryRepository;
        this.hostService = hostService;
        this.accommodationsByHostRepository = accommodationsByHostRepository;
        this.eventPublisher = eventPublisher;
    }

    @Override
    public List<DisplayHostDTO> findAll() {
        return hostService.findAll().stream().map(DisplayHostDTO::from).collect(Collectors.toList());
    }

    @Override
    public DisplayHostDTO create(CreateHostDTO createHostDto) {
        Host host = new Host(
                createHostDto.name(),
                createHostDto.surname(),
                createHostDto.toHost().getCountry()
        );

        Host createdHost = hostRepository.save(host);
        this.eventPublisher.publishEvent(new HostCreatedEvent(createdHost));
        return DisplayHostDTO.from(createdHost);
    }

    @Override
    public DisplayHostDTO update(Long id, CreateHostDTO updateDto) {
        Host host = hostRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Host not found"));

        Country country = countryRepository.findById(updateDto.countryId())
                .orElseThrow(() -> new RuntimeException("Country not found"));

        host.setName(updateDto.name());
        host.setSurname(updateDto.surname());
        host.setCountry(country);

        Host updatedHost = hostRepository.save(host);
        this.eventPublisher.publishEvent(new HostEditedEvent(updatedHost));
        return DisplayHostDTO.from(updatedHost);
    }

    @Override
    public void delete(Long id) {
        hostRepository.deleteById(id);
        this.eventPublisher.publishEvent(new HostDeletedEvent(hostService.findById(id).orElseThrow()));
    }

    @Override
    public Optional<DisplayHostDTO> findById(Long id) {
        return hostService.findById(id).map(DisplayHostDTO::from);
    }

    @Override
    public List<AccommodationsByHost> findAllAccommodationsByHost() {
        return accommodationsByHostRepository.findAll();
    }

    @Override
    public AccommodationsByHost findAccommodationsByHost(Long id) {
        return accommodationsByHostRepository.findById(id).orElseThrow(() -> new RuntimeException("Accommodation not found"));
    }

    @Override
    public void refreshMaterializedView() {
        accommodationsByHostRepository.refreshMaterializedView();
    }
}
