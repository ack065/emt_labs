package mk.ukim.finki.emt.lab.Model.dto.standard;
import lombok.Data;
import mk.ukim.finki.emt.lab.Model.enumerations.Category;

@Data
public class BookingDTO {

    private String name;

    private Category category;

    private Long hostID;

    private int numOfRooms;
    private Double price;

    public BookingDTO(String name, Category category, Long hostID, int numOfRooms, Double price) {
        this.name = name;
        this.category = category;
        this.hostID = hostID;
        this.numOfRooms = numOfRooms;
        this.price = price;
    }

}