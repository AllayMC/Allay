package org.allaymc.api.entity.action;

/**
 * {@code EnchantedHit} describes a client-visible enchanted-hit particle
 * action for an entity. The packet encoder passes {@code count} to the
 * corresponding animation packet as its particle data, while applying this
 * action alone does not calculate attack damage or change entity health.
 *
 * @param count requested particle count for the client animation
 *
 * @author daoge_cmd
 */
public record EnchantedHit(int count) implements EntityAction {
}
