package io.metadb.sql.tree;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author jinhai
 * @date 2021/11/01
 */
public class QualifiedName extends Expression {

    private List<Identifier> identifiers;

    public QualifiedName(NodeLocation location, List<Identifier> identifiers) {
        super(Optional.of(location));
        this.identifiers = identifiers;
    }

    @Override
    public int hashCode() {
        return Objects.hash(identifiers);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        QualifiedName that = (QualifiedName) obj;
        return Objects.equals(identifiers, that.identifiers);
    }

    @Override
    public String toString() {
        return identifiers.stream().map(identifier -> identifier.getValue()
                .toLowerCase(Locale.ENGLISH))
                .collect(Collectors.joining("."));
    }
}
