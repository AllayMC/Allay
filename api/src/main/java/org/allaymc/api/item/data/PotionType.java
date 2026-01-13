package org.allaymc.api.item.data;

import org.allaymc.api.entity.effect.EffectInstance;
import org.allaymc.api.entity.effect.EffectTypes;
import org.allaymc.api.entity.interfaces.EntityLiving;

import java.awt.*;
import java.util.List;

/**
 * Represents the various potion types available in the game, including standard potions like
 * Water and Awkward, as well as enhanced potions with effects such as Night Vision, Strength,
 * and Turtle Master.
 * <p>
 * Each potion type can have associated effects, durations, and strengths, which are automatically
 * calculated or defined. Potions with effects also have a color representation that is derived
 * from the colors of their respective effects.
 * <p>
 * Enum constants with no associated effects (e.g., WATER, AWKWARD) represent base or transitional
 * potion states, which are commonly used as ingredients to create other potions.
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
    LONG_SLOW_FALLING(EffectTypes.SLOW_FALLING.createInstance(0, 4 * 60 * 20)),

    STRONG_SLOWNESS(EffectTypes.SLOWNESS.createInstance(3, 20 * 20));

    private static final PotionType[] VALUES = values();

    // This shouldn't be touchable by user code since effect instance is mutable
    private final List<EffectInstance> effects;
    private final Color color;

    PotionType(EffectInstance... effects) {
        this.effects = List.of(effects);
        this.color = computeColor(effects);
    }

    /**
     * Gets the potion type from id.
     *
     * @param id the id of potion
     * @return the potion type
     */
    public static PotionType fromId(int id) {
        return VALUES[id];
    }

    private static Color computeColor(EffectInstance... effects) {
        if (effects.length == 0) {
            // Don't extract it as a constant, because in an enum class the enum member is
            // initialized before the static member, at which point the static member is null
            return new Color(64, 64, 160);
        }

        int r = 0;
        int g = 0;
        int b = 0;
        int count = 0;
        for (var effect : effects) {
            r += effect.getType().getColor().getRed() * effect.getLevel();
            g += effect.getType().getColor().getGreen() * effect.getLevel();
            b += effect.getType().getColor().getBlue() * effect.getLevel();
            count += effect.getLevel();
        }

        return new Color(r / count, g / count, b / count);
    }

    /**
     * Apply this potion type to a specific entity.
     *
     * @param entity the entity that this potion type will be applied to
     */
    public void applyTo(EntityLiving entity) {
        getEffects().forEach(entity::addEffect);
    }

    /**
     * Gets the effects that this potion type has.
     *
     * @return the effects that this potion type has
     */
    public List<EffectInstance> getEffects() {
        return this.effects.stream().map(EffectInstance::new).toList();
    }

    /**
     * Gets the color of this potion type, which is a mix of all effect types that
     * this potion type has.
     *
     * @return the color of this potion type
     */
    public Color getColor() {
        return this.color;
    }
}
