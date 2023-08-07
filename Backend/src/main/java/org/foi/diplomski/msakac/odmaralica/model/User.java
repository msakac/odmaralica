package org.foi.diplomski.msakac.odmaralica.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "USERS")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	private String surname;

	private String password;

	@Column(unique = true)
	private String email;

	private Boolean active;

	private String description;

	private String profilePicture;

	private String phoneNumber;

	@Enumerated(EnumType.STRING)
	private UserRole userRole;

}
