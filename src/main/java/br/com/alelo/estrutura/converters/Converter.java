package br.com.alelo.estrutura.converters;


/**
 * Converte um objeto origem "T" para um objeto de destino Z
 *
 * @param <T> Entidade da base de dados
 * @param <Z> Objeto de transferência de dados (VO, DTO)
 */

public interface Converter<T, Z> {
    Z toEntity(T o);

    T toVO(Z entity);
}
