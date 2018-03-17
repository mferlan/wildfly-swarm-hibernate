package hr.mferlan.example;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MyEntity {

	/** The id. */
	@Id
	public long id;

	/** The description. */
	public String description;
}