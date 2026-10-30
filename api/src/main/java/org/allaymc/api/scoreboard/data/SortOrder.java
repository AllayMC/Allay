package org.allaymc.api.scoreboard.data;

/**
 * {@code SortOrder} identifies the ordering requested for scoreboard scores on the client. The
 * server sends this value with an objective rather than sorting its line map, so the same scorer
 * data can be presented in ascending or descending score order.
 *
 * @author daoge_cmd
 */
public enum SortOrder {
    /**
     * Increasing numeric score order.
     */
    ASCENDING,
    /**
     * Decreasing numeric score order.
     */
    DESCENDING
}
