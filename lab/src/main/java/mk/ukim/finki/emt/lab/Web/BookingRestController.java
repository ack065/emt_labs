package mk.ukim.finki.emt.lab.Web;


import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import mk.ukim.finki.emt.lab.Model.domain.Booking;
import mk.ukim.finki.emt.lab.Model.dto.create.CreateBookingDTO;
import mk.ukim.finki.emt.lab.Model.dto.display.DisplayBookingDTO;
import mk.ukim.finki.emt.lab.Model.enumerations.Currency;
import mk.ukim.finki.emt.lab.Service.BookingApplicationService;
import mk.ukim.finki.emt.lab.Service.domain.BookingService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/booking")
@SecurityRequirement(name = "Authentication")
public class BookingRestController {
    private final BookingApplicationService bookingApplicationService;
    private final BookingService bookingService;

    public BookingRestController(BookingApplicationService bookingApplicationService, BookingService bookingService) {
        this.bookingApplicationService = bookingApplicationService;
        this.bookingService = bookingService;
    }

    @GetMapping
    public List<DisplayBookingDTO> findAll() {
        return bookingApplicationService.findAll();
    }

    @PostMapping("/add-booking")
    @PreAuthorize("hasRole('User')")
    public ResponseEntity<DisplayBookingDTO> addBooking(@RequestBody CreateBookingDTO createBookingDto) throws Exception {
        return ResponseEntity.ok(bookingApplicationService.create(createBookingDto));
    }

    @PutMapping("/edit-booking/{id}")
    @PreAuthorize("hasRole('User')")
    public ResponseEntity<DisplayBookingDTO> editBooking(
            @PathVariable Long id,
            @RequestBody CreateBookingDTO updateDto) throws Exception {
        return ResponseEntity.ok(bookingApplicationService.update(id, updateDto));
    }

    @DeleteMapping("/delete-booking/{id}")
    @PreAuthorize("hasRole('User')")
    public ResponseEntity<Void> deleteBooking(@PathVariable Long id) {
        bookingApplicationService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/reservation/{id}")
    @PreAuthorize("hasRole('User')")
    public ResponseEntity<DisplayBookingDTO> reservation(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(bookingApplicationService.reservation(id));
    }

    @PostMapping("/convert-currency/{id}/{targetCurrency}")
    @PreAuthorize("hasRole('User')")
    public ResponseEntity<Double> convertBookingPrice(
            @PathVariable Long id,
            @PathVariable Currency targetCurrency) {
            Optional<Booking> booking = bookingService.findById(id);
            return ResponseEntity.ok(bookingApplicationService.convertPrices(booking, targetCurrency));

    }

    @GetMapping("/{id}")
    public ResponseEntity<DisplayBookingDTO> getBookingById(@PathVariable Long id) {
        return bookingApplicationService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
