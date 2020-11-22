package doctors;

import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Doctor {
	@Id
	//@GeneratedValue(strategy = GenerationType.AUTO)
	private String id;
	private String name;
	private String address;
	private String phone;
	private String email;	
}

