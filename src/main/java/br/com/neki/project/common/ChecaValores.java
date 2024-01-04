package br.com.neki.project.common;

import br.com.neki.project.model.exceptions.ResourceBadRequest;

public class ChecaValores {

    public static void verificaValorInt(Integer numero) {
        if (numero <= 0) {
            throw new ResourceBadRequest("Não pode ter valor zero ou negativo");
        }
    }

    // Para Doubles inteiros que precisam ser maior que zero, como estoque
    public static void verificaValorDouble(Double numero) {
        if (numero <= 0) {
            throw new ResourceBadRequest("Não pode ter valor zero ou negativo");
        }
    }

    // Necessário para valores que podem ser zero, como acréscimo ou desconto de
    // pedidoItem
    public static void verificaValorDoubleMenorQueZero(Double numero) {
        if (numero < 0) {
            throw new ResourceBadRequest("Não pode ter valor negativo");
        }
    }

    public static void verificaValorLong(Long numero) {
        if (numero <= 0) {
            throw new ResourceBadRequest("Não pode ter valor zero ou negativo");
        }
    }
}
