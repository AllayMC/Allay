package org.allaymc.api.item.data;

import org.allaymc.api.entity.effect.EffectInstance;
import org.allaymc.api.entity.effect.EffectTypes;
import org.allaymc.api.entity.interfaces.EntityLiving;

import java.awt.*;
import java.util.List;

/**
 * {@code PotionType} identifies a metadata-encoded potion and supplies its
 * effect prototypes and display color. Enum order defines the numeric
 * identifier used by potion stacks, so {@code fromId} performs an ordinal
 * lookup rather than searching a separate identifier field. Effect durations
 * use game ticks, and amplifiers are one less than displayed levels.
 *
 * <p>
 * Each potion retains its prototype effects internally because effect
 * instances are mutable. {@code getEffects} creates new instances for callers,
 * while {@code applyTo} offers those copies to an entity. Display color
 * weights effect-type RGB values by effect level rather than duration; potions
 * without effects use a fixed blue color.
 *
 * @author daoge_cmd
 */
public enum PotionType {
    // These constants define metadata order and effect prototypes. Durations convert
    // seconds at twenty ticks per second, with zero-based amplifiers selecting levels.
    /**
     * Effect-free water potion.
     */
    WATER,
    /**
     * Effect-free mundane potion.
     */
    MUNDANE,
    /**
     * Effect-free extended mundane potion.
     */
    LONG_MUNDANE,
    /**
     * Effect-free thick potion.
     */
    THICK,
    /**
     * Effect-free awkward potion.
     */
    AWKWARD,

    /**
     * Three-minute night-vision potion.
     */
    NIGHT_VISION(EffectTypes.NIGHT_VISION.createInstance(0, 3 * 60 * 20)),
    /**
     * Eight-minute night-vision potion.
     */
    LONG_NIGHT_VISION(EffectTypes.NIGHT_VISION.createInstance(0, 8 * 60 * 20)),

    /**
     * Three-minute invisibility potion.
     */
    INVISIBILITY(EffectTypes.INVISIBILITY.createInstance(0, 3 * 60 * 20)),
    /**
     * Eight-minute invisibility potion.
     */
    LONG_INVISIBILITY(EffectTypes.INVISIBILITY.createInstance(0, 8 * 60 * 20)),

    /**
     * Three-minute level-one jump-boost potion.
     */
    LEAPING(EffectTypes.JUMP_BOOST.createInstance(0, 3 * 60 * 20)),
    /**
     * Eight-minute level-one jump-boost potion.
     */
    LONG_LEAPING(EffectTypes.JUMP_BOOST.createInstance(0, 8 * 60 * 20)),
    /**
     * Ninety-second level-two jump-boost potion.
     */
    STRONG_LEAPING(EffectTypes.JUMP_BOOST.createInstance(1, 90 * 20)),

    /**
     * Three-minute fire-resistance potion.
     */
    FIRE_RESISTANCE(EffectTypes.FIRE_RESISTANCE.createInstance(0, 3 * 60 * 20)),
    /**
     * Eight-minute fire-resistance potion.
     */
    LONG_FIRE_RESISTANCE(EffectTypes.FIRE_RESISTANCE.createInstance(0, 8 * 60 * 20)),

    /**
     * Three-minute level-one speed potion.
     */
    SWIFTNESS(EffectTypes.SPEED.createInstance(0, 3 * 60 * 20)),
    /**
     * Eight-minute level-one speed potion.
     */
    LONG_SWIFTNESS(EffectTypes.SPEED.createInstance(0, 8 * 60 * 20)),
    /**
     * Ninety-second level-two speed potion.
     */
    STRONG_SWIFTNESS(EffectTypes.SPEED.createInstance(1, 90 * 20)),

    /**
     * Ninety-second level-one slowness potion.
     */
    SLOWNESS(EffectTypes.SLOWNESS.createInstance(0, 90 * 20)),
    /**
     * Four-minute level-one slowness potion.
     */
    LONG_SLOWNESS(EffectTypes.SLOWNESS.createInstance(0, 4 * 60 * 20)),

    /**
     * Three-minute water-breathing potion.
     */
    WATER_BREATHING(EffectTypes.WATER_BREATHING.createInstance(0, 3 * 60 * 20)),
    /**
     * Eight-minute water-breathing potion.
     */
    LONG_WATER_BREATHING(EffectTypes.WATER_BREATHING.createInstance(0, 8 * 60 * 20)),

    /**
     * Level-one instant-health potion.
     */
    HEALING(EffectTypes.INSTANT_HEALTH.createInstance(0, 1)),
    /**
     * Level-two instant-health potion.
     */
    STRONG_HEALING(EffectTypes.INSTANT_HEALTH.createInstance(1, 1)),

    /**
     * Level-one instant-damage potion.
     */
    HARMING(EffectTypes.INSTANT_DAMAGE.createInstance(0, 1)),
    /**
     * Level-two instant-damage potion.
     */
    STRONG_HARMING(EffectTypes.INSTANT_DAMAGE.createInstance(1, 1)),

    /**
     * Forty-five-second level-one poison potion.
     */
    POISON(EffectTypes.POISON.createInstance(0, 45 * 20)),
    /**
     * Two-minute level-one poison potion.
     */
    LONG_POISON(EffectTypes.POISON.createInstance(0, 2 * 60 * 20)),
    /**
     * Twenty-two-and-a-half-second level-two poison potion.
     */
    STRONG_POISON(EffectTypes.POISON.createInstance(1, 22 * 20 + 10)),

    /**
     * Forty-five-second level-one regeneration potion.
     */
    REGENERATION(EffectTypes.REGENERATION.createInstance(0, 45 * 20)),
    /**
     * Two-minute level-one regeneration potion.
     */
    LONG_REGENERATION(EffectTypes.REGENERATION.createInstance(0, 2 * 60 * 20)),
    /**
     * Twenty-two-and-a-half-second level-two regeneration potion.
     */
    STRONG_REGENERATION(EffectTypes.REGENERATION.createInstance(1, 22 * 20 + 10)),

    /**
     * Three-minute level-one strength potion.
     */
    STRENGTH(EffectTypes.STRENGTH.createInstance(0, 3 * 60 * 20)),
    /**
     * Eight-minute level-one strength potion.
     */
    LONG_STRENGTH(EffectTypes.STRENGTH.createInstance(0, 8 * 60 * 20)),
    /**
     * Ninety-second level-two strength potion.
     */
    STRONG_STRENGTH(EffectTypes.STRENGTH.createInstance(1, 90 * 20)),

    /**
     * Ninety-second level-one weakness potion.
     */
    WEAKNESS(EffectTypes.WEAKNESS.createInstance(0, 90 * 20)),
    /**
     * Four-minute level-one weakness potion.
     */
    LONG_WEAKNESS(EffectTypes.WEAKNESS.createInstance(0, 4 * 60 * 20)),

    /**
     * Forty-second level-one wither potion.
     */
    WITHER(EffectTypes.WITHER.createInstance(0, 40 * 20)),

    /**
     * Twenty-second resistance-three and slowness-four potion.
     */
    TURTLE_MASTER(
            EffectTypes.RESISTANCE.createInstance(2, 20 * 20),
            EffectTypes.SLOWNESS.createInstance(3, 20 * 20)
    ),
    /**
     * Forty-second resistance-three and slowness-four potion.
     */
    LONG_TURTLE_MASTER(
            EffectTypes.RESISTANCE.createInstance(2, 40 * 20),
            EffectTypes.SLOWNESS.createInstance(3, 40 * 20)
    ),
    /**
     * Twenty-second resistance-five and slowness-six potion.
     */
    STRONG_TURTLE_MASTER(
            EffectTypes.RESISTANCE.createInstance(4, 20 * 20),
            EffectTypes.SLOWNESS.createInstance(5, 20 * 20)
    ),

    /**
     * Ninety-second slow-falling potion.
     */
    SLOW_FALLING(EffectTypes.SLOW_FALLING.createInstance(0, 90 * 20)),
    /**
     * Four-minute slow-falling potion.
     */
    LONG_SLOW_FALLING(EffectTypes.SLOW_FALLING.createInstance(0, 4 * 60 * 20)),

    /**
     * Twenty-second level-four slowness potion.
     */
    STRONG_SLOWNESS(EffectTypes.SLOWNESS.createInstance(3, 20 * 20)),

    /**
     * Three-minute wind-charged effect potion.
     */
    WIND_CHARGED(EffectTypes.WIND_CHARGED.createInstance(0, 3 * 60 * 20)),
    /**
     * Three-minute weaving effect potion.
     */
    WEAVING(EffectTypes.WEAVING.createInstance(0, 3 * 60 * 20)),
    /**
     * Three-minute oozing effect potion.
     */
    OOZING(EffectTypes.OOZING.createInstance(0, 3 * 60 * 20)),
    /**
     * Three-minute infested effect potion.
     */
    INFESTED(EffectTypes.INFESTED.createInstance(0, 3 * 60 * 20));

    /**
     * {@code VALUES} caches declaration order for metadata lookups, so potion
     * identifiers remain tied to the order of enum constants.
     */
    private static final PotionType[] VALUES = values();

    /**
     * {@code effects} holds unmodifiable storage of mutable effect prototypes.
     * Only copied {@code EffectInstance} values leave through
     * {@code getEffects}, while their effect-type references remain shared.
     */
    private final List<EffectInstance> effects;
    /**
     * Precomputed potion display color.
     */
    private final Color color;

    /**
     * Creates a potion definition from its supplied effect prototypes and
     * computes its display color. The constructor retains the effect instances
     * in an unmodifiable list; callers obtain copies through
     * {@code getEffects}.
     */
    PotionType(EffectInstance... effects) {
        this.effects = List.of(effects);
        this.color = computeColor(effects);
    }

    /**
     * Returns the potion at the supplied metadata ordinal. {@code id} must
     * identify an available enum value; negative or excessive ordinals cause
     * an array-index exception instead of a fallback potion.
     */
    public static PotionType fromId(int id) {
        return VALUES[id];
    }

    /**
     * Returns an effect-level-weighted RGB color, or the base blue for an
     * empty effect list. Each effect contributes its type's channel values
     * multiplied by its displayed level, and total levels supply the divisor.
     * Effect duration does not change that weight.
     *
     * <p>
     * The channel averages use integer division, so fractional channel values
     * are discarded. The empty-list color is allocated directly because enum
     * constructors run before later static fields would be initialized; the
     * current enum effects all supply positive levels for the nonempty
     * average.
     */
    private static Color computeColor(EffectInstance... effects) {
        if (effects.length == 0) {
            // Allocate the base color during enum construction; a later static color
            // field would still be null while these enum constants are initialized.
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

        // Integer RGB channels discard fractional weighted averages, keeping level
        // weights independent from the effects' durations and mutable remaining ticks.
        return new Color(r / count, g / count, b / count);
    }

    /**
     * Offers a fresh copy of every potion effect to the supplied entity. The
     * entity controls effect eligibility and addition events, so this method
     * does not guarantee that every offered effect is installed.
     */
    public void applyTo(EntityLiving entity) {
        getEffects().forEach(entity::addEffect);
    }

    /**
     * Returns an unmodifiable list containing newly copied effect instances.
     * Mutating a returned instance does not change the retained prototype,
     * while each copy continues to refer to its original effect type.
     */
    public List<EffectInstance> getEffects() {
        return this.effects.stream().map(EffectInstance::new).toList();
    }

    /**
     * Returns the display color precomputed from this potion's effects.
     */
    public Color getColor() {
        return this.color;
    }
}
