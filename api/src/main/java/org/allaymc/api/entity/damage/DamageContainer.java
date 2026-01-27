package org.allaymc.api.entity.damage;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.action.EnchantedHit;
import org.allaymc.api.entity.component.EntityPhysicsComponent;
import org.joml.Vector3d;
import org.joml.Vector3dc;

import java.util.Objects;
import java.util.function.UnaryOperator;

import static org.allaymc.api.entity.damage.DamageType.*;

/**
 * DamageContainer is a container that stores the information of the damage, including
 * the attacker, the damage type, the source damage, the final damage, etc.
 *
 * @author daoge_cmd
 */
@Getter
public class DamageContainer {
    public static final int DEFAULT_DAMAGE_COOL_DOWN = 10;

    /**
     * The attacker of the damage. Attacker not only can be an entity, but also can be a block
     * or even {@code null}, it depends on the caller.
     */
    protected Object attacker;
    /**
     * The type of this damage.
     */
    protected DamageType damageType;
    /**
     * The initial amount of damage.
     */
    protected float sourceDamage;
    /**
     * The final amount of damage.
     */
    @Setter
    protected float finalDamage;
    /**
     * Whether the damage has knockback. Set this to {@code false} to apply custom knockback.
     */
    @Setter
    protected boolean hasKnockback;
    /**
     * The cooldown after this damage. When an entity is on cooldown, it cannot be damaged.
     */
    @Setter
    protected int coolDown;
    /**
     * Whether this damage is critical. If this value is {@code true}, the final damage will be multiplied by {@code 1.5},
     * and critical particle will be applied to the victim.
     */
    @Setter
    protected boolean critical;
    /**
     * Whether this damage is enchanted. If this value is {@code true}, action {@link EnchantedHit} will
     * be applied to the victim.
     */
    @Setter
    protected boolean enchanted;

    /**
     * Horizontal knockback strength. Defaults to {@link EntityPhysicsComponent#DEFAULT_KNOCKBACK}.
     */
    @Setter
    protected double knockback = EntityPhysicsComponent.DEFAULT_KNOCKBACK;

    /**
     * Vertical knockback strength. Defaults to {@link EntityPhysicsComponent#DEFAULT_KNOCKBACK}.
     */
    @Setter
    protected double knockbackVertical = EntityPhysicsComponent.DEFAULT_KNOCKBACK;

    /**
     * Additional knockback motion vector (e.g., for knockback enchantment effect).
     */
    @Setter
    protected Vector3dc knockbackAdditional = new Vector3d();

    /**
     * Source position for calculating knockback direction.
     * If {@code null}, uses the attacker's position (default behavior).
     * Projectiles can set this to hitPos - motion for accurate direction.
     */
    @Setter
    protected Vector3dc knockbackSource = null;

    /**
     * Whether to ignore knockback resistance. Defaults to {@code false}.
     */
    @Setter
    protected boolean ignoreKnockbackResistance = false;

    /**
     * Creates a new damage container.
     *
     * @param attacker     the attacker that cause the damage. Can be {@code null}
     * @param damageType   the type of this damage
     * @param sourceDamage the source amount of damage
     */
    public DamageContainer(Object attacker, DamageType damageType, float sourceDamage) {
        this.attacker = attacker;
        this.damageType = Objects.requireNonNull(damageType);
        this.sourceDamage = sourceDamage;
        this.finalDamage = sourceDamage;
        this.hasKnockback = true;
        this.coolDown = DEFAULT_DAMAGE_COOL_DOWN;
    }

    /**
     * Create a simple attack damage container.
     *
     * @param sourceDamage the source damage
     * @return the damage container
     */
    public static DamageContainer simpleAttack(float sourceDamage) {
        return new DamageContainer(null, API, sourceDamage);
    }

    /**
     * Create an entity attack damage container.
     *
     * @param attacker     the attacker
     * @param sourceDamage the source damage
     * @return the damage container
     */
    public static DamageContainer entityAttack(Entity attacker, float sourceDamage) {
        return new DamageContainer(attacker, ENTITY_ATTACK, sourceDamage);
    }

    /**
     * Create a starve damage container.
     *
     * @param sourceDamage the source damage
     * @return the damage container
     */
    public static DamageContainer starve(float sourceDamage) {
        return new DamageContainer(null, STARVE, sourceDamage);
    }

    /**
     * Create a fall damage container.
     *
     * @param sourceDamage the source damage
     * @return the damage container
     */
    public static DamageContainer fall(float sourceDamage) {
        return new DamageContainer(null, FALL, sourceDamage);
    }

    /**
     * Create a falling block damage container.
     *
     * @param sourceDamage the source damage
     * @return the damage container
     */
    public static DamageContainer fallingBlock(float sourceDamage) {
        return new DamageContainer(null, FALLING_BLOCK, sourceDamage);
    }

    /**
     * Create a lightning damage container.
     *
     * @param attacker     the lightning bolt entity
     * @param sourceDamage the source damage
     * @return the damage container
     */
    public static DamageContainer lightning(Entity attacker, float sourceDamage) {
        return new DamageContainer(attacker, LIGHTNING, sourceDamage);
    }

    /**
     * Create a magic effect damage container.
     *
     * @param sourceDamage the source damage
     * @return the damage container
     */
    public static DamageContainer magicEffect(float sourceDamage) {
        return new DamageContainer(null, MAGIC, sourceDamage);
    }

    /**
     * Create a magma damage container.
     *
     * @param sourceDamage the source damage
     * @return the damage container
     */
    public static DamageContainer magma(float sourceDamage) {
        return new DamageContainer(null, MAGMA, sourceDamage);
    }

    /**
     * Create a campfire damage container.
     *
     * @param sourceDamage the source damage
     * @return the damage container
     */
    public static DamageContainer campfire(float sourceDamage) {
        return new DamageContainer(null, CAMPFIRE, sourceDamage);
    }

    /**
     * Create a drown damage container.
     *
     * @param sourceDamage the source damage
     * @return the damage container
     */
    public static DamageContainer drown(float sourceDamage) {
        return new DamageContainer(null, DROWN, sourceDamage);
    }

    /**
     * Create a fire tick damage container.
     *
     * @param sourceDamage the source damage
     * @return the damage container
     */
    public static DamageContainer fireTick(float sourceDamage) {
        return new DamageContainer(null, FIRE_TICK, sourceDamage);
    }

    /**
     * Create a lava damage container.
     *
     * @param sourceDamage the source damage
     * @return the damage container
     */
    public static DamageContainer lava(float sourceDamage) {
        return new DamageContainer(null, LAVA, sourceDamage);
    }

    /**
     * Create a block explosion damage container with specific block type.
     * Used for blocks that need custom death messages (e.g., bed, respawn anchor).
     *
     * @param blockType    the block type that caused the explosion
     * @param sourceDamage the source damage
     * @return the damage container
     */
    public static DamageContainer blockExplosion(BlockType<?> blockType, float sourceDamage) {
        return new DamageContainer(blockType, BLOCK_EXPLOSION, sourceDamage);
    }

    /**
     * Create an entity explosion damage container.
     *
     * @param attacker     the entity that exploded
     * @param sourceDamage the source damage
     * @return the damage container
     */
    public static DamageContainer entityExplosion(Entity attacker, float sourceDamage) {
        return new DamageContainer(attacker, ENTITY_EXPLOSION, sourceDamage);
    }

    /**
     * Create a contact damage container.
     *
     * @param sourceDamage the source damage
     * @return the damage container
     */
    public static DamageContainer contact(float sourceDamage) {
        return new DamageContainer(null, CONTACT, sourceDamage);
    }

    /**
     * Create a projectile damage container.
     *
     * @param sourceDamage the source damage
     * @return the damage container
     */
    public static DamageContainer projectile(Entity projectile, float sourceDamage) {
        return new DamageContainer(projectile, PROJECTILE, sourceDamage);
    }

    /**
     * Creates a void damage container.
     *
     * @param sourceDamage the source damage
     * @return the damage container
     */
    public static DamageContainer voidDamage(float sourceDamage) {
        return new DamageContainer(null, VOID, sourceDamage);
    }

    /**
     * Creates an API damage container with the specified source damage.
     *
     * @param sourceDamage the initial damage value from the source
     * @return a new {@code DamageContainer} instance with the API damage type
     */
    public static DamageContainer api(float sourceDamage) {
        return new DamageContainer(null, API, sourceDamage);
    }

    /**
     * Create a stalactite damage container (for falling dripstone hitting entities).
     *
     * @param sourceDamage the source damage
     * @return the damage container
     */
    public static DamageContainer stalactite(float sourceDamage) {
        return new DamageContainer(null, STALACTITE, sourceDamage);
    }

    /**
     * Create a stalagmite damage container (for entities falling onto pointed dripstone).
     *
     * @param sourceDamage the source damage
     * @return the damage container
     */
    public static DamageContainer stalagmite(float sourceDamage) {
        return new DamageContainer(null, STALAGMITE, sourceDamage);
    }

    /**
     * Get the attacker.
     *
     * @param <T> the type of the attacker
     * @return the attacker, or {@code null} if the attacker is not present
     */
    public <T> T getAttacker() {
        // noinspection unchecked
        return (T) attacker;
    }

    /**
     * Update the final damage using the given updater.
     *
     * @param updater the updater
     */
    public void updateFinalDamage(UnaryOperator<Float> updater) {
        this.finalDamage = updater.apply(this.finalDamage);
    }

    /**
     * Check if the damage can be reduced by armor.
     *
     * @return {@code true} if the damage can be reduced by armor, otherwise {@code false}.
     */
    public boolean canBeReducedByArmor() {
        return !CANNOT_BE_REDUCED_BY_ARMOR_DAMAGE_TYPES.contains(damageType);
    }
}
