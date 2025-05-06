package mk.ukim.finki.emt.lab.Model.dto.create;

import mk.ukim.finki.emt.lab.Model.domain.Booking;
import mk.ukim.finki.emt.lab.Model.enumerations.Category;

public record CreateBookingDTO(
        String name,
        Category category,
        Long hostId,
        int numOfRooms,
        Double price
) {
    public Booking toBooking() {
        return new Booking(name, category, null, numOfRooms, price);
    }
}