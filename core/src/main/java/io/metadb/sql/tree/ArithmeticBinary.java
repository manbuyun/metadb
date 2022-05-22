package io.metadb.sql.tree;

import com.google.common.base.MoreObjects;

import java.util.Objects;
import java.util.Optional;

/**
 * @author jinhai
 * @date 2022/05/22
 */
public class ArithmeticBinary extends Expression {

    private Expression left;
    private ArithmeticBinary.Operator operator;
    private Expression right;

    public ArithmeticBinary(NodeLocation location, Expression left, ArithmeticBinary.Operator operator, Expression right) {
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
        ArithmeticBinary that = (ArithmeticBinary) obj;
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
        ADD("+"),
        SUBTRACT("-"),
        MULTIPLY("*"),
        DIVIDE("/");

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
