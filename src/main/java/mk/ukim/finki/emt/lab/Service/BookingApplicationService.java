package mk.ukim.finki.emt.lab.Service;

import mk.ukim.finki.emt.lab.Model.domain.Booking;
import mk.ukim.finki.emt.lab.Model.dto.create.CreateBookingDTO;
import mk.ukim.finki.emt.lab.Model.dto.display.DisplayBookingDTO;
import mk.ukim.finki.emt.lab.Model.dto.standard.BookingDTO;
import mk.ukim.finki.emt.lab.Model.enumerations.Currency;

import java.util.List;
import java.util.Optional;

public interface BookingApplicationService {
    List<DisplayBookingDTO> findAll();
    DisplayBookingDTO create(CreateBookingDTO createBookingDto) throws Exception;
    DisplayBookingDTO update(Long id, CreateBookingDTO createBookingDto) throws Exception;
    void delete(Long id);
    DisplayBookingDTO reservation(Long id) throws Exception;
    double convertPrices(Optional<Booking> booking, Currency targetCurrency);
    Optional<DisplayBookingDTO> findById(Long id);
}