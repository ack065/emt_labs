package mk.ukim.finki.emt.lab.Service.impl;

import mk.ukim.finki.emt.lab.Model.domain.Booking;
import mk.ukim.finki.emt.lab.Model.domain.Host;
import mk.ukim.finki.emt.lab.Model.dto.create.CreateBookingDTO;
import mk.ukim.finki.emt.lab.Model.dto.display.DisplayBookingDTO;
import mk.ukim.finki.emt.lab.Model.enumerations.Currency;
import mk.ukim.finki.emt.lab.Repository.BookingRepository;
import mk.ukim.finki.emt.lab.Repository.HostRepository;
import mk.ukim.finki.emt.lab.Service.BookingApplicationService;
import mk.ukim.finki.emt.lab.Service.domain.BookingService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookingApplicationServiceImpl implements BookingApplicationService {
    private final BookingRepository bookingRepository;
    private final HostRepository hostRepository;
    private final BookingService bookingService;

    public BookingApplicationServiceImpl(BookingRepository bookingRepository,
                                         HostRepository hostRepository, BookingService bookingService) {
        this.bookingRepository = bookingRepository;
        this.hostRepository = hostRepository;
        this.bookingService = bookingService;
    }

    @Override
    public List<DisplayBookingDTO> findAll() {
        return bookingService.findAll().stream().map(DisplayBookingDTO::from).collect(Collectors.toList());
    }

    @Override
    public DisplayBookingDTO create(CreateBookingDTO createBookingDto) throws Exception {
        Host host = hostRepository.findById(createBookingDto.hostId())
                .orElseThrow(() -> new Exception("Host not found"));

        Booking booking = new Booking(
                createBookingDto.name(),
                createBookingDto.category(),
                host,
                createBookingDto.numOfRooms(),
                createBookingDto.price()
        );

        Booking savedBooking = bookingRepository.save(booking);
        return DisplayBookingDTO.from(savedBooking);
    }

    @Override
    public DisplayBookingDTO update(Long id, CreateBookingDTO createBookingDto) throws Exception {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new Exception("Booking not found"));

        Host host = hostRepository.findById(createBookingDto.hostId())
                .orElseThrow(() -> new Exception("Host not found"));

        booking.setName(createBookingDto.name());
        booking.setCategory(createBookingDto.category());
        booking.setHost(host);
        booking.setNumOfRooms(createBookingDto.numOfRooms());
        booking.setPrice(createBookingDto.price());

        Booking updatedBooking = bookingRepository.save(booking);
        return DisplayBookingDTO.from(updatedBooking);
    }

    @Override
    public void delete(Long id) {
        bookingRepository.deleteById(id);
    }

    @Override
    public DisplayBookingDTO reservation(Long id) throws Exception {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new Exception("Booking not found"));

        if (!booking.isBooked()) {
            int remainingRooms = booking.getNumOfRooms() - 1;
            booking.setNumOfRooms(remainingRooms);

            if (remainingRooms == 0) {
                booking.setBooked(true);
            }

            booking = bookingRepository.save(booking);
        }

        return DisplayBookingDTO.from(booking);
    }

    @Override
    public double convertPrices(Optional<Booking> booking, Currency targetCurrency) {
        if (booking.isEmpty()) {
            throw new IllegalArgumentException("Booking cannot be null");
        }
        Currency bookingCurrency = booking.get().getHost().getCountry().getCurrency();
        return Currency.convert(bookingCurrency, targetCurrency, booking.get().getPrice());
    }

    @Override
    public Optional<DisplayBookingDTO> findById(Long id) {
        return bookingService.findById(id).map(DisplayBookingDTO::from);
    }

}