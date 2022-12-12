package com.benmyers.dezeus.core.util;

import java.util.ArrayList;
import java.util.List;

import com.benmyers.dezeus.core.Statement;
import com.benmyers.dezeus.core.StatementGroup;

public class Combinator {

    /**
     * Get all combinations of the statements in each group.
     * 
     * @return List of all Cartesian products of each of the StatementGroups (sets
     *         of statements).
     */
    public static List<List<Statement>> getCartesianProduct(List<StatementGroup> groups) {
        List<List<Statement>> result = new ArrayList<>();
        if (groups.isEmpty()) {
            return result;
        }
        if (groups.size() == 1) {
            for (Statement statement : groups.get(0)) {
                List<Statement> list = new ArrayList<>();
                list.add(statement);
                result.add(list);
            }
            return result;
        }
        List<StatementGroup> subgroups = new ArrayList<>(groups);
        StatementGroup firstGroup = subgroups.remove(0);
        List<List<Statement>> subResult = getCartesianProduct(subgroups);
        for (Statement statement : firstGroup) {
            for (List<Statement> list : subResult) {
                List<Statement> newList = new ArrayList<>();
                newList.add(statement);
                newList.addAll(list);
                result.add(newList);
            }
        }
        return result;
    }
}
