package io.metadb.sql.tree;

import java.util.Optional;

/**
 * @author jinhai
 * @date 2021/11/01
 */
public abstract class Expression extends Node {

    protected Expression(Optional<NodeLocation> location) {
        super(location);
    }
}
