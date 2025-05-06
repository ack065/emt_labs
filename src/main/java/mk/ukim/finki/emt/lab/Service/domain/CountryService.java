package mk.ukim.finki.emt.lab.Service.domain;

import mk.ukim.finki.emt.lab.Model.domain.Booking;
import mk.ukim.finki.emt.lab.Model.domain.Country;

import java.util.List;
import java.util.Optional;

public interface CountryService {
    List<Country> findAll();
    Optional<Country> findById(Long id);
}
