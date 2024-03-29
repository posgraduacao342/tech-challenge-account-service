package tech.challenge.account.service.account.infrastructure.db.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.LastModifiedDate;
import java.time.LocalDateTime;
import java.util.UUID;

@MappedSuperclass
@Getter
@Setter
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime dataCriacao;

    @CreationTimestamp
    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime dataAtualizacao;

    @Column()
    private LocalDateTime dataDelecao;

    @PrePersist
    public void prePersist() {
        var timestamp = LocalDateTime.now();
        dataCriacao = timestamp;
        dataAtualizacao = timestamp;
    }
}
