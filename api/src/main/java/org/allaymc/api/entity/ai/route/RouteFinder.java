package org.allaymc.api.entity.ai.route;

import org.allaymc.api.entity.interfaces.EntityIntelligent;
import org.joml.Vector3dc;

import java.util.List;

/**
 * {@code RouteFinder} searches for waypoints connecting an intelligent entity
 * to a target position. Implementations determine which positions are
 * traversable and may return a partial route when their search limit is
 * reached, so consumers follow the returned nodes rather than assuming arrival.
 *
 * @author daoge_cmd
 */
public interface RouteFinder {

    /**
     * Searches from the entity's current position toward the supplied target.
     * The result can be empty when no route is available, and bounded
     * implementations may return waypoints ending before the target.
     */
    List<Node> search(EntityIntelligent entity, Vector3dc target);
}
