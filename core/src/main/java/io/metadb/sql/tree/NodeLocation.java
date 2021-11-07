package io.metadb.sql.tree;

import com.google.common.base.MoreObjects;

import java.util.Objects;

/**
 * @author jinhai
 * @date 2021/11/01
 */
public class NodeLocation {

    private int line;
    private int position;

    public NodeLocation(int line, int position) {
        this.line = line;
        this.position = position;
    }

    @Override
    public int hashCode() {
        return Objects.hash(line, position);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        NodeLocation that = (NodeLocation) obj;
        return Objects.equals(line, that.line) &&
                Objects.equals(position, that.position);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("line", line)
                .add("position", position)
                .toString();
    }
}
