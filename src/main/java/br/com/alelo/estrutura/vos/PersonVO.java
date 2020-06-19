package br.com.alelo.estrutura.vos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PersonVO implements Serializable {


    private Long id;

    @NotNull
    @NotEmpty
    private String name;

    @NotNull
    @Min(1)
    @Max(120)
    private Integer age;

    @NotNull
    @NotEmpty
    private String document;
}
