package hashing.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
public class Users {

    @Id
    @Column(name = "USER_ID")
    private String email;

    @Column(name = "PASSWORD")
    private String password;
}
