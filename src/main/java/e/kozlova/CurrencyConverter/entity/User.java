package e.kozlova.CurrencyConverter.entity;

import lombok.*;
import javax.persistence.*;

/**
 * Сущность пользователь
 */

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "USERS")
public class User {

    // Идентификатор пользователя
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Login пользователя
    @Column(name = "login")
    private String login;
}
