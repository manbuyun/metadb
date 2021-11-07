package io.metadb.sql.tree;

import java.util.Optional;

/**
 * @author jinhai
 * @date 2021/11/01
 */
public abstract class Node {

    private Optional<NodeLocation> location;

    protected Node(Optional<NodeLocation> location) {
        this.location = location;
    }

    @Override
    public abstract int hashCode();

    @Override
    public abstract boolean equals(Object obj);

    @Override
    public abstract String toString();
}
