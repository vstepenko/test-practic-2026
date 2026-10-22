package ua.edu.duan.test_practic.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "user")
public class UserEntity {

    @Id
    @Column(name = "login")
    private String login;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "email")
    private String email;
}

