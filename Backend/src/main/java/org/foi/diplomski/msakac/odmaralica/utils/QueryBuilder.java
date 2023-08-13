package org.foi.diplomski.msakac.odmaralica.utils;

import javax.persistence.criteria.*;
import java.util.*;

@SuppressWarnings("unchecked")
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

    private Path<?> getAttributePath(String key, Operator op) {
        String[] keyValue = key.split(op.getSymbol());
        String attributeName = keyValue[0];

        Path<?> attributePath;
        if (attributeName.contains(".")) {
            String[] attributeNames = attributeName.split("\\.");
            attributePath = root;
            for (String attrName : attributeNames) {
                attributePath = attributePath.get(attrName);
            }
        } else {
            attributePath = root.get(attributeName);
        }
        return attributePath;
    }
    //FIXME: Add support for sort, limit and offset
    public CriteriaQuery<T> buildQuery(String query) {
        Map<String, String> parsedQuery = parseQuery(query);
        List<Predicate> predicates = new ArrayList<>();

        for (String key : parsedQuery.keySet()) {
            String value = parsedQuery.get(key);

            Operator operator = Operator.findOperator(key);

            if (operator != null) {
                Path<?> attributePath = getAttributePath(key, operator);

                switch (operator) {
                    case NOT_EQUAL:
                        predicates.add(criteriaBuilder.notEqual(attributePath, castToComparable(value)));
                        break;
                    case GREATER_THAN:
                        predicates.add(criteriaBuilder.greaterThan((Path<Comparable<? super Comparable<?>>>) attributePath,
                                (Comparable<? super Comparable<?>>) castToComparable(value)));
                        break;
                    case LESS_THAN:
                        predicates.add(criteriaBuilder.lessThan((Path<Comparable<? super Comparable<?>>>) attributePath,
                                (Comparable<? super Comparable<?>>) castToComparable(value)));
                        break;
                    case IN:
                        List<String> values = Arrays.asList(value.split(","));
                        predicates.add(attributePath.in(values));
                        break;
                    case EQUAL:
                        predicates.add(criteriaBuilder.equal(attributePath, value));
                        break;
                }
            }
        }

        criteriaQuery.where(predicates.toArray(new Predicate[0]));
        return criteriaQuery;
    }

    //FIXME: Compares only integers
    //FIXME: Retard more linka poslati i zbrejkati sve zivo
    private Comparable<?> castToComparable(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return value;
        }
    }

    private static Map<String, String> parseQuery(String query) {
        Map<String, String> result = new HashMap<>();
        List<String> operators = Arrays.asList("<", ">", "=", "!=", "(in)=");

        String[] queryParams = query.split(";");
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
