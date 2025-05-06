package mk.ukim.finki.emt.lab.Model.views;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

@Data
@Entity
@Subselect("SELECT * from public.accommodations_by_host")
@Immutable
public class AccommodationsByHost  {

    @Id
    @Column(name = "host_id")
    private Long id;
    @Column(name = "accommodations_count")
    private int accommodationId;
}
