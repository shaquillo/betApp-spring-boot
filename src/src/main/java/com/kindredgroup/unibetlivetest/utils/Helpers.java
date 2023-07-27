package com.kindredgroup.unibetlivetest.utils;

import com.kindredgroup.unibetlivetest.types.SelectionResult;
import lombok.extern.log4j.Log4j2;

import java.math.BigDecimal;

@Log4j2
public final class Helpers {

    private Helpers() {
        // Ne peut pas être instanciée.
    }

    /**
     * Get random index from range
     *
     * @param min
     * @param max
     * @return the number choosed
     */
    public static int getRandomIndex(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    /**
     * Selon le nombre alétoire 0, 1 ou 2, nous allons :
     * 0 : Garder la cote comme telle
     * 1 : Augmenter la cote de 0.1
     * 2 : Diminuer la cote de 0.1 (si possible)
     */
    public static BigDecimal updateOddRandomly(BigDecimal originalOdd) {
        switch (getRandomIndex(0, 3)) {
            case 0:
                return originalOdd;
            case 1:
                return originalOdd.add(new BigDecimal("0.1"));
            default:
                /** Une cote ne peut pas être en dessous de 1 **/
                return originalOdd.subtract(new BigDecimal("0.1")).compareTo(BigDecimal.ONE) < 0 ? BigDecimal.ONE : originalOdd.subtract(new BigDecimal("0.1"));
        }
    }

    /**
     * Met le résultat aléatoirement pour une sélection : gagnate ou perdante.
     */
    public static SelectionResult setResultRandomly() {
        return getRandomIndex(0, 2) == 0 ? SelectionResult.WON : SelectionResult.LOST;
    }
}
