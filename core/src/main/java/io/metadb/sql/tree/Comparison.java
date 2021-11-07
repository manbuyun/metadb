package io.metadb.sql.tree;

import com.google.common.base.MoreObjects;

import java.util.Objects;
import java.util.Optional;

/**
 * @author jinhai
 * @date 2021/11/01
 */
public class Comparison extends Expression {

    private Expression left;
    private Operator operator;
    private Expression right;

    public Comparison(NodeLocation location, Expression left, Operator operator, Expression right) {
        super(Optional.of(location));
        this.left = left;
        this.operator = operator;
        this.right = right;
    }

    @Override
    public int hashCode() {
        return Objects.hash(left, operator, right);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Comparison that = (Comparison) obj;
        return Objects.equals(left, that.left) &&
                Objects.equals(operator, that.operator) &&
                Objects.equals(right, that.right);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("left", left)
                .add("operator", operator)
                .add("right", right)
                .toString();
    }

    public enum Operator {
        EQUAL("="),
        NOT_EQUAL("<>"),
        LESS_THAN("<"),
        LESS_THAN_OR_EQUAL("<="),
        GREATER_THAN(">"),
        GREATER_THAN_OR_EQUAL(">=");

        private String value;

        Operator(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        @Override
        public String toString() {
            return value;
        }
    }
}
