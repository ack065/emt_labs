package mk.ukim.finki.emt.lab.Repository;


import io.micrometer.common.lang.NonNull;
import mk.ukim.finki.emt.lab.Model.domain.User;
import mk.ukim.finki.emt.lab.Model.projections.UserProjection;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    @NonNull
    @Override
    @EntityGraph(
            type = EntityGraph.EntityGraphType.FETCH,
            attributePaths = {"temporaryBookings"}
    )
    List<User> findAll();

    User findUserByUsername(String username);

    List<UserProjection> findAllProjectedBy();
}
