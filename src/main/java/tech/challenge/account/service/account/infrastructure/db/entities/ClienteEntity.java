package tech.challenge.account.service.account.infrastructure.db.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Entity
@Data
@Table(name = "clientes")
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class ClienteEntity extends BaseEntity {
    @Column(nullable = false)
    @NotEmpty(message = "O nome não pode estar vazio")
    private String nome;

    @Column(nullable = false)
    @NotEmpty(message = "O e-mail não pode estar vazio")
    private String email;

    @Column(nullable = false)
    @NotEmpty(message = "O CPF não pode estar vazio")
    private String cpf;
}

