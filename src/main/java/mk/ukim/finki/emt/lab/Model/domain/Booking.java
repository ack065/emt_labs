package mk.ukim.finki.emt.lab.Model.domain;

import jakarta.persistence.*;
import lombok.Data;
import mk.ukim.finki.emt.lab.Model.enumerations.Category;

@Entity
@Data
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;

    private String name;

    @Enumerated(EnumType.STRING)
    private Category category;

    @ManyToOne
    private Host host;

    private int numOfRooms;

    private boolean booked;

    private Double price;

    public Booking() {
    }
    public Booking(String name, Category category, Host host, int numOfRooms, Double price) {
        this.name = name;
        this.category = category;
        this.host = host;
        this.numOfRooms = numOfRooms;
        this.booked = false;
        this.price = price;
    }
}