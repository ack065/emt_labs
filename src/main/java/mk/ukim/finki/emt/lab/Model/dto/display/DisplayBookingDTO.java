package mk.ukim.finki.emt.lab.Model.dto.display;

import mk.ukim.finki.emt.lab.Model.domain.Booking;
import mk.ukim.finki.emt.lab.Model.enumerations.Category;

import java.util.List;
import java.util.stream.Collectors;

public record DisplayBookingDTO(
        Long id,
        String name,
        Category category,
        DisplayHostDTO host,
        int numOfRooms,
        boolean booked,
        Double price
) {
    public static DisplayBookingDTO from(Booking booking) {
        return new DisplayBookingDTO(
                booking.getID(),
                booking.getName(),
                booking.getCategory(),
                DisplayHostDTO.from(booking.getHost()),
                booking.getNumOfRooms(),
                booking.isBooked(),
                booking.getPrice()
        );
    }

    public static List<DisplayBookingDTO> from(List<Booking> bookings) {
        return bookings.stream().map(DisplayBookingDTO::from).collect(Collectors.toList());
    }
}