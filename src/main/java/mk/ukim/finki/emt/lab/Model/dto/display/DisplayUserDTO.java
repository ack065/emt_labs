package mk.ukim.finki.emt.lab.Model.dto.display;

import mk.ukim.finki.emt.lab.Model.domain.Booking;
import mk.ukim.finki.emt.lab.Model.domain.User;
import mk.ukim.finki.emt.lab.Model.enumerations.Role;

import java.util.List;

public record DisplayUserDTO (String username, String name, Role role, List<Long>temporaryBookings, List<Long>bookings){

    public static DisplayUserDTO from(User user){
        return new DisplayUserDTO(
                user.getUsername(),
                user.getName(),
                user.getRole(),
                user.getTemporaryBookings().stream().map(Booking::getID).toList(),
                user.getBookings().stream().map(Booking::getID).toList()
        );
    }
}