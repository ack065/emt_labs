package mk.ukim.finki.emt.lab.Repository;


import jakarta.transaction.Transactional;
import mk.ukim.finki.emt.lab.Model.views.HostsByCountry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface HostsByCountryRepository extends JpaRepository<HostsByCountry, Long> {

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "REFRESH MATERIALIZED VIEW public.hosts_by_country", nativeQuery = true)
    void refreshMaterializedView();
}
