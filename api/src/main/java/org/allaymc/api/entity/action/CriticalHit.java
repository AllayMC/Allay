package org.allaymc.api.entity.action;

/**
 * CriticalHit makes an entity display critical hit particles. This will show stars around the entity.
 *
 * @param count the count of particles around the entity
 * @author daoge_cmd
 */
public record CriticalHit(int count) implements EntityAction {
}
