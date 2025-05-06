package mk.ukim.finki.emt.lab.Model.projections;

public interface UserProjection {
    String getName();
    String getSurname();

    default String getFullName() {
        return getName() + " " + getSurname();
    }
}
