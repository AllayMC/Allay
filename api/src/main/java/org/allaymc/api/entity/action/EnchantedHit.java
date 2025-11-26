package org.allaymc.api.entity.action;

/**
 * EnchantedHit makes an entity display enchanted hit particles. This will show circles around the entity.
 *
 * @param count the count of particles around the entity
 * @author daoge_cmd
 */
public record EnchantedHit(int count) implements EntityAction {
}
