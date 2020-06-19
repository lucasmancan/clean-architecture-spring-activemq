package br.com.alelo.estrutura.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.checkerframework.checker.interning.qual.InternedDistinct;
import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "people")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Person {

    // Colocar IDENTITY
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private String age;

    @Column(name = "document")
    private String document;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

}
