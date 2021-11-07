package io.metadb.sql.tree;

import com.google.common.base.MoreObjects;

import java.util.Objects;
import java.util.Optional;

/**
 * @author jinhai
 * @date 2021/11/01
 */
public class Identifier extends Expression {

    private String value;

    public Identifier(NodeLocation location, String value) {
        super(Optional.of(location));
        this.value = value;
    }

    public String getValue() {
        return value;
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
        Identifier that = (Identifier) obj;
        return Objects.equals(value, that.value);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("value", value)
                .toString();
    }
}
