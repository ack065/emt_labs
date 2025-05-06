package mk.ukim.finki.emt.lab.Model.views;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

@Data
@Entity
@Subselect("SELECT * from public.hosts_by_country")
@Immutable
public class HostsByCountry {
    @Id
    @Column(name = "country_id")
    private Long id;
    @Column(name = "country_name")
    private String country;
    @Column(name = "hosts_count")
    private int hostsCount;

}
