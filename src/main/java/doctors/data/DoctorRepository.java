package doctors.data;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import doctors.Doctor;

public interface DoctorRepository extends CrudRepository<Doctor, String> {
	@Query(value="SELECT p FROM Doctor p WHERE p.name LIKE %?1%")
    public List<Doctor> search(@Param("keyword") String keyword);
}
