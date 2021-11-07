package io.metadb.sql.tree;

import java.util.Optional;

/**
 * @author jinhai
 * @date 2021/11/01
 */
public abstract class Literal extends Expression {

    protected Literal(Optional<NodeLocation> location) {
        super(location);
    }
}
