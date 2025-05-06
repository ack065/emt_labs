package mk.ukim.finki.emt.lab.Model.domain;

import jakarta.persistence.*;
import lombok.Data;
import mk.ukim.finki.emt.lab.Model.enumerations.Currency;

@Entity
@Data
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;

    private String name;

    private String continent;

    private Currency currency;

    public Country() {
    }

    public Country(String name, String continent, Currency currency) {
        this.name = name;
        this.continent = continent;
        this.currency = currency;
    }
}