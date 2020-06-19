package br.com.alelo.estrutura.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "people")
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Person {

    // Colocar IDENTITY
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private Integer age;

    @Column(name = "document")
    private String document;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

}
