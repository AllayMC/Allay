package org.allaymc.api.entity.damage;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.entity.Entity;

import java.util.Objects;
import java.util.function.UnaryOperator;

/**
 * Allay Project 2024/1/12
 *
 * @author daoge_cmd
 */
@Getter
public class DamageContainer {
    public static final int DEFAULT_DAMAGE_COOL_DOWN = 10;

    protected Entity attacker;
    protected DamageType damageType;
    protected float sourceDamage;
    @Setter
    protected float finalDamage;
    @Setter
    protected float customKnockback = -1;
    protected float knockbackResistance = 0;
    @Setter
    protected int coolDown = DEFAULT_DAMAGE_COOL_DOWN;
    @Setter
    protected boolean critical;

    public DamageContainer(Entity attacker, DamageType damageType, float sourceDamage) {
        // attacker is nullable
        this.attacker = attacker;
        if (attacker != null) this.critical = attacker.canCriticalAttack();
        this.damageType = Objects.requireNonNull(damageType);
        this.sourceDamage = sourceDamage;
        this.finalDamage = sourceDamage;
    }

    public static DamageContainer simpleAttack(float sourceDamage) {
        return new DamageContainer(null, DamageType.API, sourceDamage);
    }

    public static DamageContainer entityAttack(Entity attacker, float sourceDamage) {
        return new DamageContainer(attacker, DamageType.ENTITY_ATTACK, sourceDamage);
    }

    public static DamageContainer starve(float sourceDamage) {
        return new DamageContainer(null, DamageType.STARVE, sourceDamage);
    }

    public static DamageContainer fall(float sourceDamage) {
        return new DamageContainer(null, DamageType.FALL, sourceDamage);
    }

    public static DamageContainer magicEffect(float sourceDamage) {
        return new DamageContainer(null, DamageType.MAGIC, sourceDamage);
    }

    public boolean hasCustomKnockback() {
        return customKnockback != -1;
    }

    public void updateFinalDamage(UnaryOperator<Float> updater) {
        this.finalDamage = updater.apply(this.finalDamage);
    }

    public boolean canBeReducedByArmor() {
        return switch (this.damageType) {
            case FIRE_TICK, SUFFOCATION, DROWNING,
                 STARVE, VOID, MAGIC, COMMAND,
                 FLY_INTO_WALL, FREEZING, SONIC_BOOM -> false;
            default -> true;
        };
    }

    public enum DamageType {
        ANVIL,
        /**
         * Block explosion damage
         */
        BLOCK_EXPLOSION,
        CHARGING,
        /**
         * Damage caused by contact with a block such as a Cactus
         */
        CONTACT,
        /**
         * Damage caused by running out of air underwater
         */
        DROWNING,
        /**
         * Damage caused by being attacked by another entity
         */
        ENTITY_ATTACK,
        /**
         * Entity explosion damage
         */
        ENTITY_EXPLOSION,
        /**
         * Fall damage
         */
        FALL,
        FALLING_BLOCK,
        /**
         * Damage caused by standing in fire
         */
        FIRE,
        FIREBALL,
        /**
         * Burn damage
         */
        FIRE_TICK,
        FIREWORKS,
        FLY_INTO_WALL,
        FREEZING,
        /**
         * Damage caused by standing in lava
         */
        LAVA,
        /**
         * Damage caused by being struck by lightning
         */
        LIGHTNING,
        /**
         * Potion or spell damage
         */
        MAGIC,
        /**
         * Damage caused by standing on magma block
         */
        MAGMA,
        PISTON,
        /**
         * Damage caused by being hit by a projectile such as an Arrow
         */
        PROJECTILE,
        RAM_ATTACK,
        STALACTITE,
        /**
         * Damage caused by hunger
         */
        STARVE,
        /**
         * Damage caused by being put in a block
         */
        SUFFOCATION,
        /**
         * Damage caused by submitting /kill command
         */
        COMMAND,
        SONIC_BOOM,
        TEMPERATURE,
        /**
         * Damage caused by thorns enchantment
         */
        THORNS,
        /**
         * Damage caused by falling into the void
         */
        VOID,
        WITHER,
        /**
         * Plugins
         */
        API,
    }
}
