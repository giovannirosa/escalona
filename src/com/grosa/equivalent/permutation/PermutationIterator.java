package com.grosa.equivalent.permutation;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Classe para permutar a visão de todas as formas.
 * Iterator permite que as permutações sejam geradas em tempo real,
 * assim não é necessário fazer todas as permutações previamente.
 * <p>Inspirado na permutação do Apache:
 * https://commons.apache.org/proper/commons-collections/apidocs/org/apache/commons/collections4/iterators/PermutationIterator.html
 */
public class PermutationIterator<T> implements Iterator<List<T>> {

    private List<T> base;

    private PermutationResolver<T> permutationResolver;
    private List<T> next;

    public PermutationIterator(List<T> base, PermutationResolver<T> resolver) {
        this.base = base;
        this.next = new ArrayList<>(base);
        this.permutationResolver = resolver;
    }

    private List<T> generateNextPermutation(boolean isLast) {

        List<T> result = null;

        if (!isLast) {
            result = getPermutationResolver().resolvePermutation(base);
        }

        return result;
    }

    @Override
    public boolean hasNext() {
        return next != null;
    }

    @Override
    public List<T> next() {

        if (!hasNext()) {
            throw new NoSuchElementException();
        }

        List<T> current = next;

        this.next = generateNextPermutation(!getPermutationResolver().nextStep());

        return current;
    }


    private PermutationResolver<T> getPermutationResolver() {
        return permutationResolver;
    }

}
