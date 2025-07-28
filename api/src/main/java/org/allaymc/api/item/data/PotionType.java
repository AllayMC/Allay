package org.allaymc.api.item.data;

import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.effect.EffectInstance;
import org.allaymc.api.entity.effect.type.EffectTypes;

import java.util.List;

/**
 * PotionType holds the effects given by a specific potion
 *
 * @author daoge_cmd
 */
public enum PotionType {
    WATER,
    MUNDANE,
    LONG_MUNDANE,
    THICK,
    AWKWARD,

    NIGHT_VISION(EffectTypes.NIGHT_VISION.createInstance(0, 3 * 60 * 20)),
    LONG_NIGHT_VISION(EffectTypes.NIGHT_VISION.createInstance(0, 8 * 60 * 20)),

    INVISIBILITY(EffectTypes.INVISIBILITY.createInstance(0, 3 * 60 * 20)),
    LONG_INVISIBILITY(EffectTypes.INVISIBILITY.createInstance(0, 8 * 60 * 20)),

    LEAPING(EffectTypes.JUMP_BOOST.createInstance(0, 3 * 60 * 20)),
    LONG_LEAPING(EffectTypes.JUMP_BOOST.createInstance(0, 8 * 60 * 20)),
    STRONG_LEAPING(EffectTypes.JUMP_BOOST.createInstance(1, 90 * 20)),

    FIRE_RESISTANCE(EffectTypes.FIRE_RESISTANCE.createInstance(0, 3 * 60 * 20)),
    LONG_FIRE_RESISTANCE(EffectTypes.FIRE_RESISTANCE.createInstance(0, 8 * 60 * 20)),

    SWIFTNESS(EffectTypes.SPEED.createInstance(0, 3 * 60 * 20)),
    LONG_SWIFTNESS(EffectTypes.SPEED.createInstance(0, 8 * 60 * 20)),
    STRONG_SWIFTNESS(EffectTypes.SPEED.createInstance(1, 90 * 20)),

    SLOWNESS(EffectTypes.SLOWNESS.createInstance(0, 90 * 20)),
    LONG_SLOWNESS(EffectTypes.SLOWNESS.createInstance(0, 4 * 60 * 20)),
    STRONG_SLOWNESS(EffectTypes.SLOWNESS.createInstance(3, 20 * 20)),

    WATER_BREATHING(EffectTypes.WATER_BREATHING.createInstance(0, 3 * 60 * 20)),
    LONG_WATER_BREATHING(EffectTypes.WATER_BREATHING.createInstance(0, 8 * 60 * 20)),

    HEALING(EffectTypes.INSTANT_HEALTH.createInstance(0, 1)),
    STRONG_HEALING(EffectTypes.INSTANT_HEALTH.createInstance(1, 1)),

    HARMING(EffectTypes.INSTANT_DAMAGE.createInstance(0, 1)),
    STRONG_HARMING(EffectTypes.INSTANT_DAMAGE.createInstance(1, 1)),

    POISON(EffectTypes.POISON.createInstance(0, 45 * 20)),
    LONG_POISON(EffectTypes.POISON.createInstance(0, 2 * 60 * 20)),
    STRONG_POISON(EffectTypes.POISON.createInstance(1, 22 * 20 + 10)), // 22.5s ≈ 22*20+10 ticks

    REGENERATION(EffectTypes.REGENERATION.createInstance(0, 45 * 20)),
    LONG_REGENERATION(EffectTypes.REGENERATION.createInstance(0, 2 * 60 * 20)),
    STRONG_REGENERATION(EffectTypes.REGENERATION.createInstance(1, 22 * 20 + 10)),

    STRENGTH(EffectTypes.STRENGTH.createInstance(0, 3 * 60 * 20)),
    LONG_STRENGTH(EffectTypes.STRENGTH.createInstance(0, 8 * 60 * 20)),
    STRONG_STRENGTH(EffectTypes.STRENGTH.createInstance(1, 90 * 20)),

    WEAKNESS(EffectTypes.WEAKNESS.createInstance(0, 90 * 20)),
    LONG_WEAKNESS(EffectTypes.WEAKNESS.createInstance(0, 4 * 60 * 20)),

    WITHER(EffectTypes.WITHER.createInstance(0, 40 * 20)),

    TURTLE_MASTER(
            EffectTypes.RESISTANCE.createInstance(2, 20 * 20),
            EffectTypes.SLOWNESS.createInstance(3, 20 * 20)
    ),
    LONG_TURTLE_MASTER(
            EffectTypes.RESISTANCE.createInstance(2, 40 * 20),
            EffectTypes.SLOWNESS.createInstance(3, 40 * 20)
    ),
    STRONG_TURTLE_MASTER(
            EffectTypes.RESISTANCE.createInstance(4, 20 * 20),
            EffectTypes.SLOWNESS.createInstance(5, 20 * 20)
    ),

    SLOW_FALLING(EffectTypes.SLOW_FALLING.createInstance(0, 90 * 20)),
    LONG_SLOW_FALLING(EffectTypes.SLOW_FALLING.createInstance(0, 4 * 60 * 20));;

    private static final PotionType[] VALUES = values();

    // This shouldn't be touchable by user code since effect instance is mutable
    private final List<EffectInstance> effects;

    PotionType(EffectInstance... effects) {
        this.effects = List.of(effects);
    }

    /**
     * Gets the potion type from potion item's meta.
     *
     * @param meta the meta of potion item.
     * @return the potion type from potion item's meta.
     */
    public static PotionType fromItemMeta(int meta) {
        return VALUES[meta];
    }

    /**
     * Apply this potion type to a specific entity.
     *
     * @param entity the entity that this potion type will be applied to.
     */
    public void applyTo(Entity entity) {
        effects.stream().map(EffectInstance::new).forEach(entity::addEffect);
    }
}
