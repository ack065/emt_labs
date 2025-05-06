package mk.ukim.finki.emt.lab.Service.domain.impl;

import mk.ukim.finki.emt.lab.Model.domain.Booking;
import mk.ukim.finki.emt.lab.Model.enumerations.Currency;
import mk.ukim.finki.emt.lab.Model.domain.Host;
import mk.ukim.finki.emt.lab.Model.dto.standard.BookingDTO;
import mk.ukim.finki.emt.lab.Repository.BookingRepository;
import mk.ukim.finki.emt.lab.Repository.HostRepository;
import mk.ukim.finki.emt.lab.Service.domain.BookingService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingServiceImpl implements BookingService {
    private final BookingRepository bookingRepository;
    private final HostRepository hostRepository;

    public BookingServiceImpl(BookingRepository bookingRepository, HostRepository hostRepository) {
        this.bookingRepository = bookingRepository;
        this.hostRepository = hostRepository;
    }


    @Override
    public List<Booking> findAll() {
        return this.bookingRepository.findAll();
    }

    @Override
    public Booking create(BookingDTO bookingDTO) throws Exception {
        Host host_obj = this.hostRepository.findById(bookingDTO.getHostID()).orElseThrow(Exception::new);
        Booking booking_obj = new Booking(bookingDTO.getName(), bookingDTO.getCategory(), host_obj, bookingDTO.getNumOfRooms(), bookingDTO.getPrice());

        return this.bookingRepository.save(booking_obj);
    }

    @Override
    public Booking update(Long ID, BookingDTO bookingDTO) throws Exception {
        Booking booking_obj = this.bookingRepository.findById(ID).orElseThrow(Exception::new);
        Host host_obj = this.hostRepository.findById(bookingDTO.getHostID()).orElseThrow(Exception::new);

        booking_obj.setName(bookingDTO.getName());
        booking_obj.setCategory(bookingDTO.getCategory());
        booking_obj.setHost(host_obj);
        booking_obj.setNumOfRooms(bookingDTO.getNumOfRooms());
        booking_obj.setPrice(bookingDTO.getPrice());

        return this.bookingRepository.save(booking_obj);
    }

    @Override
    public void delete(Long ID) {
        this.bookingRepository.deleteById(ID);
    }

    @Override
    public Booking reservation(Long ID) throws Exception {
        Booking booking_obj = this.bookingRepository.findById(ID).orElseThrow(Exception::new);

        if (booking_obj.isBooked()) {
            return booking_obj;
        }

        if (booking_obj.getNumOfRooms() - 1 == 0) {
            booking_obj.setNumOfRooms(0);
            booking_obj.setBooked(true);

            return this.bookingRepository.save(booking_obj);
        } else {
            booking_obj.setNumOfRooms(booking_obj.getNumOfRooms() - 1);

            return this.bookingRepository.save(booking_obj);
        }
    }
    @Override
    public double convertPrices(Booking booking, Currency targetCurrency) {
        Currency bookingCurrency = booking.getHost().getCountry().getCurrency();
        return Currency.convert(bookingCurrency, targetCurrency, booking.getPrice());
    }

    @Override
    public Optional<Booking> findById(Long id) {
        return this.bookingRepository.findById(id);
    }


}




