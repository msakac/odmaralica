package org.foi.diplomski.msakac.odmaralica.utils;


import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QueryBuilder<T> {
    private final CriteriaBuilder criteriaBuilder;
    private final CriteriaQuery<T> criteriaQuery;
    private final Root<T> root;

    public QueryBuilder(CriteriaBuilder criteriaBuilder, Class<T> entityClass) {
        this.criteriaBuilder = criteriaBuilder;
        this.criteriaQuery = criteriaBuilder.createQuery(entityClass);
        this.root = criteriaQuery.from(entityClass);
        criteriaQuery.select(root);
    }

    // FIXME: Osim q parametara trebam jos sort=, offset, limit
    public CriteriaQuery<T> buildQuery(String query) {
        Map<String, String> parsedQuery = parseQuery(query);
        List<Predicate> predicates = new ArrayList<>();

        for (String key : parsedQuery.keySet()) {
            String value = parsedQuery.get(key);

            if (key.contains("!=")) {
                String[] keyValue = key.split("!=");
                String attributeName = keyValue[0];
                Path<Object> attributePath = root.get(attributeName);
                predicates.add(criteriaBuilder.notEqual(attributePath, (Comparable<? super Comparable<?>>) castToComparable(value)));
            }  else if (key.contains(">")) {
                String[] keyValue = key.split(">");
                String attributeName = keyValue[0];
                Path<Comparable<? super Comparable<?>>> attributePath = root.get(attributeName);
                predicates.add(criteriaBuilder.greaterThan(attributePath, (Comparable<? super Comparable<?>>) castToComparable(value)));
            } else if (key.contains("<")) {
                String[] keyValue = key.split("<");
                String attributeName = keyValue[0];
                Path<Comparable<? super Comparable<?>>> attributePath = root.get(attributeName);
                predicates.add(criteriaBuilder.lessThan(attributePath, (Comparable<? super Comparable<?>>) castToComparable(value)));
            } else if (key.contains("(in)=")) {
                String[] keyValue = key.split("\\(in\\)=");
                String attributeName = keyValue[0];
                Path<Object> attributePath = root.get(attributeName);
                List<String> values = Arrays.asList(value.split(","));
                predicates.add(attributePath.in(values));
            } else if (key.contains("=")) {
                String[] keyValue = key.split("=");
                String attributeName = keyValue[0];
                Path<Object> attributePath = root.get(attributeName);
                predicates.add(criteriaBuilder.equal(attributePath, value));
            }
        }

        criteriaQuery.where(predicates.toArray(new Predicate[0]));
        return criteriaQuery;
    }

    //FIXME: Compares only integers
    private Comparable<?> castToComparable(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return value;
        }
    }

    public static Map<String, String> parseQuery(String query) {
        Map<String, String> result = new HashMap<>();
        List<String> operators = Arrays.asList("<", ">", "=", "!=", "(in)=");

        String[] queryParams = query.split("&");
        for (String param : queryParams) {
            String operator = null;
            for (String op : operators) {
                if (param.contains(op)) {
                    operator = op;
                    break;
                }
            }

            if (operator != null) {
                String[] parts = param.split(operator, 2);
                String key = parts[0] + operator;
                String value = parts[1];
                result.put(key, value);
            }
        }

        return result;
    }
}
