package io.metadb.sql.tree;

import java.util.Objects;
import java.util.Optional;

/**
 * @author jinhai
 * @date 2022/05/22
 */
public class LongLiteral extends Literal {

    private long value;

    public LongLiteral(NodeLocation location, String value) {
        super(Optional.of(location));
        this.value = Long.parseLong(value);
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
        LongLiteral that = (LongLiteral) obj;
        return Objects.equals(value, that.value);
    }

    @Override
    public String toString() {
        return Long.toString(value);
    }
}
