package mk.ukim.finki.emt.lab.Repository;

import jakarta.transaction.Transactional;
import mk.ukim.finki.emt.lab.Model.views.AccommodationsByHost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AccommodationsByHostRepository extends JpaRepository<AccommodationsByHost, Long> {
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "REFRESH MATERIALIZED VIEW public.accommodations_by_host", nativeQuery = true)
    void refreshMaterializedView();



}
