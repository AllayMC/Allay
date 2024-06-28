package org.allaymc.api.entity.effect;

import org.allaymc.api.entity.Entity;
import org.allaymc.api.utils.Identifier;

import java.awt.*;

/**
 * Allay Project 2023/10/27
 *
 * @author daoge_cmd
 */
public interface EffectType {
    int getId();

    Identifier getIdentifier();

    Color getColor();

    EffectInstance createInstance(int amplifier, int duration, boolean visible);

    default EffectInstance createInstance(int amplifier) {
        return createInstance(amplifier, 15);
    }

    default EffectInstance createInstance(int amplifier, int duration) {
        return createInstance(amplifier, duration, true);
    }

    default boolean isBad() {
        return false;
    }

    default void onTick(Entity entity, EffectInstance effectInstance) {}

    default void onAdd(Entity entity, EffectInstance effectInstance) {}

    default void onRemove(Entity entity, EffectInstance effectInstance) {}
}
