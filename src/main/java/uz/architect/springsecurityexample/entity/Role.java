package uz.architect.springsecurityexample.entity;

import jakarta.persistence.*;
import lombok.*;
import uz.architect.springsecurityexample.enums.RoleName;

import java.util.List;

@Getter
@Setter
@ToString(exclude = {"users"})
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "role")
public class Role {

    @Id
    @Column(name = "role_name")
    @Enumerated(EnumType.STRING)
    private RoleName roleName;

    @ManyToMany(mappedBy = "roles")
    private List<User> users;
}
