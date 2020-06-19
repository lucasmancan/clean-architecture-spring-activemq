package br.com.alelo.estrutura.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PersonVO implements Serializable {
    private Long id;
    private String name;
    private String age;
    private String document;
}
