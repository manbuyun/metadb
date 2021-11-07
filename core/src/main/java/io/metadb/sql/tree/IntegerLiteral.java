package io.metadb.sql.tree;

import java.util.Objects;
import java.util.Optional;

/**
 * @author jinhai
 * @date 2021/11/01
 */
public class IntegerLiteral extends Literal {

    private int value;

    public IntegerLiteral(NodeLocation location, String value) {
        super(Optional.of(location));
        this.value = Integer.parseInt(value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        IntegerLiteral that = (IntegerLiteral) obj;
        return Objects.equals(value, that.value);
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
