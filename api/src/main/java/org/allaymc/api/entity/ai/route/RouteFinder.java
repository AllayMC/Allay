package org.allaymc.api.entity.ai.route;

import org.allaymc.api.entity.interfaces.EntityIntelligent;
import org.joml.Vector3dc;

import java.util.List;

/**
 * Finds a route from the entity's current position to a target position.
 *
 * @author daoge_cmd
 */
public interface RouteFinder {

    /**
     * Start searching for a route from the entity's position to the target.
     *
     * @param entity the entity
     * @param target the target position
     */
    void search(EntityIntelligent entity, Vector3dc target);

    /**
     * Check if a search is currently in progress.
     *
     * @return {@code true} if searching
     */
    boolean isSearching();

    /**
     * Check if the search has finished.
     *
     * @return {@code true} if the search is complete
     */
    boolean isFinished();

    /**
     * Get the current route. May be empty or incomplete if the search is not finished.
     *
     * @return the list of route nodes
     */
    List<Node> getRoute();

    /**
     * Check if there are more nodes in the route to visit.
     *
     * @return {@code true} if there is a next node
     */
    boolean hasNext();

    /**
     * Get the next node in the route.
     *
     * @return the next node
     */
    Node next();

    /**
     * Get the current node index in the route.
     *
     * @return the current index
     */
    int getNodeIndex();

    /**
     * Check if the route has been reached (the entity is close enough to the end).
     *
     * @return {@code true} if the route is reached
     */
    boolean hasReached();

    /**
     * Get the target position of this route finder.
     *
     * @return the target position
     */
    Vector3dc getTarget();

    /**
     * Set whether the route has been reached.
     *
     * @param reached whether the route has been reached
     */
    void setReached(boolean reached);

    /**
     * Set the current node index.
     *
     * @param index the node index
     */
    void setNodeIndex(int index);
}
