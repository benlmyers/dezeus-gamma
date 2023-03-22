package com.benmyers.dezeus.core.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Combinator {

    /**
     * Get all combinations of the statements in each group.
     * 
     * @return List of all Cartesian products of each of the StatementGroups (sets
     *         of statements).
     */
    public static <E> List<List<E>> getCartesianProduct(List<? extends Collection<E>> groups) {
        List<List<E>> result = new ArrayList<>();
        if (groups.isEmpty()) {
            return result;
        }
        if (groups.size() == 1) {
            for (E statement : groups.get(0)) {
                List<E> list = new ArrayList<>();
                list.add(statement);
                result.add(list);
            }
            return result;
        }
        List<Collection<E>> subgroups = new ArrayList<>(groups);
        Collection<E> firstGroup = subgroups.remove(0);
        List<List<E>> subResult = getCartesianProduct(subgroups);
        for (E statement : firstGroup) {
            for (List<E> list : subResult) {
                List<E> newList = new ArrayList<>();
                newList.add(statement);
                newList.addAll(list);
                result.add(newList);
            }
        }
        return result;
    }
}
