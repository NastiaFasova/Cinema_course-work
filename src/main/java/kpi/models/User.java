package kpi.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;
import javax.persistence.*;

@Entity
@Table(name = "user")
@Data
@EqualsAndHashCode
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToMany
    private Set<Role> roles;
    @Column(unique = true)
    private String email;
    private String password;
    private String firstname;
    private String lastname;
    private String avatarUrl;
    @OneToOne(cascade =  CascadeType.ALL,
             fetch = FetchType.LAZY)
    private Bill bill;
    private boolean blocked;

}
