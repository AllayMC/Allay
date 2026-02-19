package org.allaymc.server.entity.ai.route;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.entity.ai.route.Node;
import org.allaymc.api.entity.ai.route.RouteFinder;
import org.allaymc.api.entity.interfaces.EntityIntelligent;
import org.joml.Vector3d;
import org.joml.Vector3dc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Base implementation of {@link RouteFinder} that stores and iterates over a list of nodes.
 *
 * @author daoge_cmd
 */
public abstract class SimpleRouteFinder implements RouteFinder {

    @Getter
    protected final List<Node> route = new ArrayList<>();
    @Getter
    @Setter
    protected int nodeIndex;
    protected boolean reached;
    @Getter
    protected Vector3dc target;

    protected EntityIntelligent entity;

    @Override
    public void search(EntityIntelligent entity, Vector3dc target) {
        this.entity = entity;
        this.target = new Vector3d(target);
        this.nodeIndex = 0;
        this.reached = false;
        this.route.clear();
    }

    @Override
    public boolean hasNext() {
        return nodeIndex < route.size();
    }

    @Override
    public Node next() {
        if (!hasNext()) {
            return null;
        }
        return route.get(nodeIndex++);
    }

    /**
     * Get an unmodifiable view of the route.
     *
     * @return unmodifiable route list
     */
    public List<Node> getUnmodifiableRoute() {
        return Collections.unmodifiableList(route);
    }

    @Override
    public boolean hasReached() {
        return reached;
    }

    @Override
    public void setReached(boolean reached) {
        this.reached = reached;
    }
}
