package br.com.alelo.estrutura.converters;


/**
 * Converte um objeto origem "T" para um objeto de destino Z
 *
 * @param <T>
 * @param <Z>
 */

public interface Converter<T, Z> {
    Z toEntity(T o);

    T toVO(Z entity);
}
