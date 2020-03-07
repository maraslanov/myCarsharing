package app.persistance.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Data
@Getter
@Setter
@EqualsAndHashCode
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullname;

    @NotNull(message = "Поле не может быть пустым")
    @Pattern(regexp = ".+@.+", message = "e-mail должен соответствовать шаблону ИМЯ@ДОМЕН")
    @Column(name = "login")
    private String email;

    @NotNull(message = "Поле не может быть пустым")
    @Size(min = 6, message = "Не менее 6 символов")
    @Column(name = "password")
    private String password;

    @Transient
    private String passwordConfirm;

    @Column(name = "access")
    private String access;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    private Date blockDate;

    @Column(name = "block_info")
    private String blockInfo;
}
