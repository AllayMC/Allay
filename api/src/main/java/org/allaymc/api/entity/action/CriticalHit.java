package org.allaymc.api.entity.action;

/**
 * {@code CriticalHit} describes a client-visible critical-hit particle action
 * for an entity. The packet encoder passes {@code count} to the corresponding
 * animation packet as its particle data, while applying this action alone does
 * not calculate attack damage or change entity health.
 *
 * @param count requested particle count for the client animation
 *
 * @author daoge_cmd
 */
public record CriticalHit(int count) implements EntityAction {
}
