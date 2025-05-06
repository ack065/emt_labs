package mk.ukim.finki.emt.lab.Service.domain;

import mk.ukim.finki.emt.lab.Model.domain.Booking;
import mk.ukim.finki.emt.lab.Model.enumerations.Currency;
import mk.ukim.finki.emt.lab.Model.dto.standard.BookingDTO;

import java.util.List;
import java.util.Optional;

public interface BookingService {
    List<Booking> findAll();

    Booking create(BookingDTO bookingDTO) throws Exception;

    Booking update(Long ID, BookingDTO bookingDTO) throws Exception;

    void delete(Long ID);

    Booking reservation(Long ID) throws Exception;

    double convertPrices(Booking booking, Currency targetCurrency);

    Optional<Booking> findById(Long id);
}
