package br.com.alelo.estrutura.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class HttpResponseVO {
    private final String message;
}
