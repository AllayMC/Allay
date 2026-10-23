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
 * {@code DamageContainer} carries the source, mutable damage amount, and
 * knockback settings for one damage attempt. The living-entity damage pipeline
 * and event listeners update the same instance, so callers must create a
 * separate container when another attempt needs independent values.
 *
 * @author daoge_cmd
 */
@Getter
public class DamageContainer {
    /**
     * {@code DEFAULT_DAMAGE_COOL_DOWN} supplies the initial damage cooldown in
     * entity ticks; the constructor applies it to each new damage attempt.
     */
    public static final int DEFAULT_DAMAGE_COOL_DOWN = 10;

    /**
     * {@code attacker} identifies the damage source. The value can be an
     * entity, a block type, another caller-defined object, or {@code null}; the
     * selected {@code damageType} determines which source representations its
     * consumers accept.
     */
    protected Object attacker;
    /**
     * Damage category and death-message provider.
     */
    protected DamageType damageType;
    /**
     * Original damage amount supplied at construction.
     */
    protected float sourceDamage;
    /**
     * {@code finalDamage} holds the amount adjusted by listeners and the damage
     * pipeline. The constructor starts it at {@code sourceDamage}; setters and
     * {@code updateFinalDamage} replace it without range validation.
     */
    @Setter
    protected float finalDamage;
    /**
     * {@code hasKnockback} enables the living-entity pipeline's knockback
     * handling. Listeners can clear this flag when another mechanism supplies
     * the desired motion.
     */
    @Setter
    protected boolean hasKnockback;
    /**
     * {@code coolDown} is the cooldown in entity ticks following this attempt.
     * The living component retains the container and consults its current value
     * when rejecting later attacks, including when this attempt is cancelled by
     * listeners.
     */
    @Setter
    protected int coolDown;
    /**
     * {@code critical} requests critical-hit damage scaling and the victim's
     * hit action. The attacker phase can replace this flag from the attacker's
     * physics state before applying its {@code 1.5} multiplier.
     */
    @Setter
    protected boolean critical;
    /**
     * {@code enchanted} enables the enchanted-hit action on the victim. The
     * attacker phase also sets this flag when it applies supported weapon
     * enchantments.
     */
    @Setter
    protected boolean enchanted;

    /**
     * Horizontal knockback strength used by the physics component.
     */
    @Setter
    protected double knockback = EntityPhysicsComponent.DEFAULT_KNOCKBACK;

    /**
     * Vertical knockback strength used by the physics component.
     */
    @Setter
    protected double knockbackVertical = EntityPhysicsComponent.DEFAULT_KNOCKBACK;

    /**
     * {@code knockbackAdditional} holds extra motion passed to the physics
     * component. The generated setter retains the supplied vector, and the
     * getter exposes that same reference rather than a copy.
     */
    @Setter
    protected Vector3dc knockbackAdditional = new Vector3d();

    /**
     * {@code knockbackSource} specifies the position used to calculate
     * knockback direction. When this value is {@code null} and {@code attacker}
     * is an entity, the living component uses the attacker's location instead.
     */
    @Setter
    protected Vector3dc knockbackSource = null;

    /**
     * Whether physics bypasses the victim's knockback resistance.
     */
    @Setter
    protected boolean ignoreKnockbackResistance = false;

    /**
     * Creates mutable damage data with equal source and final damage amounts.
     * {@code attacker} is retained as supplied, knockback starts enabled, and
     * the cooldown starts at {@link #DEFAULT_DAMAGE_COOL_DOWN}; damage amounts
     * are not range-checked.
     *
     * @throws NullPointerException if {@code damageType} is {@code null}
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
     * Creates {@code API} damage with no attacker and the supplied amount. The
     * new container uses the constructor's default knockback and cooldown
     * settings. This factory has the same construction behavior as {@code api}.
     */
    public static DamageContainer simpleAttack(float sourceDamage) {
        return new DamageContainer(null, API, sourceDamage);
    }

    /**
     * Creates {@code ENTITY_ATTACK} damage with the supplied entity as attacker
     * and the supplied amount. The new container uses the constructor's default
     * knockback and cooldown settings. Death-message generation expects a
     * non-{@code null} attacker for this category.
     */
    public static DamageContainer entityAttack(Entity attacker, float sourceDamage) {
        return new DamageContainer(attacker, ENTITY_ATTACK, sourceDamage);
    }

    /**
     * Creates {@code STARVE} damage with no attacker and the supplied amount.
     * The new container uses the constructor's default knockback and cooldown
     * settings. The source amount is retained without range validation.
     */
    public static DamageContainer starve(float sourceDamage) {
        return new DamageContainer(null, STARVE, sourceDamage);
    }

    /**
     * Creates {@code FALL} damage with no attacker and the supplied amount. The
     * new container uses the constructor's default knockback and cooldown
     * settings. The source amount is retained without range validation.
     */
    public static DamageContainer fall(float sourceDamage) {
        return new DamageContainer(null, FALL, sourceDamage);
    }

    /**
     * Creates {@code FALLING_BLOCK} damage with no attacker and the supplied
     * amount. The new container uses the constructor's default knockback and
     * cooldown settings. The source amount is retained without range
     * validation.
     */
    public static DamageContainer fallingBlock(float sourceDamage) {
        return new DamageContainer(null, FALLING_BLOCK, sourceDamage);
    }

    /**
     * Creates {@code LIGHTNING} damage with the supplied lightning entity as
     * attacker and the supplied amount. The new container uses the
     * constructor's default knockback and cooldown settings. The attacker
     * reference is retained without copying or validation.
     */
    public static DamageContainer lightning(Entity attacker, float sourceDamage) {
        return new DamageContainer(attacker, LIGHTNING, sourceDamage);
    }

    /**
     * Creates {@code MAGIC} damage with no attacker and the supplied amount.
     * The new container uses the constructor's default knockback and cooldown
     * settings. The source amount is retained without range validation.
     */
    public static DamageContainer magicEffect(float sourceDamage) {
        return new DamageContainer(null, MAGIC, sourceDamage);
    }

    /**
     * Creates {@code MAGMA} damage with no attacker and the supplied amount.
     * The new container uses the constructor's default knockback and cooldown
     * settings. The source amount is retained without range validation.
     */
    public static DamageContainer magma(float sourceDamage) {
        return new DamageContainer(null, MAGMA, sourceDamage);
    }

    /**
     * Creates {@code CAMPFIRE} damage with no attacker and the supplied amount.
     * The new container uses the constructor's default knockback and cooldown
     * settings. The source amount is retained without range validation.
     */
    public static DamageContainer campfire(float sourceDamage) {
        return new DamageContainer(null, CAMPFIRE, sourceDamage);
    }

    /**
     * Creates {@code DROWN} damage with no attacker and the supplied amount.
     * The new container uses the constructor's default knockback and cooldown
     * settings. The source amount is retained without range validation.
     */
    public static DamageContainer drown(float sourceDamage) {
        return new DamageContainer(null, DROWN, sourceDamage);
    }

    /**
     * Creates {@code FIRE_TICK} damage with no attacker and the supplied
     * amount. The new container uses the constructor's default knockback and
     * cooldown settings. The source amount is retained without range
     * validation.
     */
    public static DamageContainer fireTick(float sourceDamage) {
        return new DamageContainer(null, FIRE_TICK, sourceDamage);
    }

    /**
     * Creates {@code FREEZING} damage with no attacker and the supplied amount.
     * The new container uses the constructor's default knockback and cooldown
     * settings. The source amount is retained without range validation.
     */
    public static DamageContainer freezing(float sourceDamage) {
        return new DamageContainer(null, FREEZING, sourceDamage);
    }

    /**
     * Creates {@code LAVA} damage with no attacker and the supplied amount. The
     * new container uses the constructor's default knockback and cooldown
     * settings. The source amount is retained without range validation.
     */
    public static DamageContainer lava(float sourceDamage) {
        return new DamageContainer(null, LAVA, sourceDamage);
    }

    /**
     * Creates {@code BLOCK_EXPLOSION} damage with the supplied block type as
     * attacker and the supplied amount. The new container uses the
     * constructor's default knockback and cooldown settings. A non-{@code null} block
     * type selects block-specific explosion death wording.
     */
    public static DamageContainer blockExplosion(BlockType<?> blockType, float sourceDamage) {
        return new DamageContainer(blockType, BLOCK_EXPLOSION, sourceDamage);
    }

    /**
     * Creates {@code ENTITY_EXPLOSION} damage with the supplied entity as
     * attacker and the supplied amount. The new container uses the
     * constructor's default knockback and cooldown settings. Death-message
     * generation expects a non-{@code null} attacker for this category.
     */
    public static DamageContainer entityExplosion(Entity attacker, float sourceDamage) {
        return new DamageContainer(attacker, ENTITY_EXPLOSION, sourceDamage);
    }

    /**
     * Creates {@code CONTACT} damage with no attacker and the supplied amount.
     * The new container uses the constructor's default knockback and cooldown
     * settings. Callers needing a block-specific death message must supply that
     * source separately.
     */
    public static DamageContainer contact(float sourceDamage) {
        return new DamageContainer(null, CONTACT, sourceDamage);
    }

    /**
     * Creates {@code PROJECTILE} damage with the supplied projectile as
     * attacker and the supplied amount. The new container uses the
     * constructor's default knockback and cooldown settings. Death-message
     * generation expects an {@code EntityProjectile} with a non-{@code null} shooter.
     */
    public static DamageContainer projectile(Entity projectile, float sourceDamage) {
        return new DamageContainer(projectile, PROJECTILE, sourceDamage);
    }

    /**
     * Creates {@code VOID} damage with no attacker and the supplied amount. The
     * new container uses the constructor's default knockback and cooldown
     * settings. The source amount is retained without range validation.
     */
    public static DamageContainer voidDamage(float sourceDamage) {
        return new DamageContainer(null, VOID, sourceDamage);
    }

    /**
     * Creates {@code API} damage with no attacker and the supplied amount. The
     * new container uses the constructor's default knockback and cooldown
     * settings. This factory has the same construction behavior as
     * {@code simpleAttack}.
     */
    public static DamageContainer api(float sourceDamage) {
        return new DamageContainer(null, API, sourceDamage);
    }

    /**
     * Creates {@code STALACTITE} damage with no attacker and the supplied
     * amount. The new container uses the constructor's default knockback and
     * cooldown settings. The source amount is retained without range
     * validation.
     */
    public static DamageContainer stalactite(float sourceDamage) {
        return new DamageContainer(null, STALACTITE, sourceDamage);
    }

    /**
     * Creates {@code STALAGMITE} damage with no attacker and the supplied
     * amount. The new container uses the constructor's default knockback and
     * cooldown settings. The source amount is retained without range
     * validation.
     */
    public static DamageContainer stalagmite(float sourceDamage) {
        return new DamageContainer(null, STALAGMITE, sourceDamage);
    }

    /**
     * Returns the retained attacker reference using the caller's requested
     * type. The unchecked conversion does not verify the source category;
     * callers must choose {@code T} consistently with the object supplied when
     * this damage was created.
     *
     * @return the same attacker object, or {@code null} when no source was supplied
     */
    public <T> T getAttacker() {
        // noinspection unchecked
        return (T) attacker;
    }

    /**
     * Replaces final damage with the result of applying {@code updater} to its
     * current value. The updater runs immediately, and a {@code null} updater
     * or result fails before a replacement value can be stored.
     */
    public void updateFinalDamage(UnaryOperator<Float> updater) {
        this.finalDamage = updater.apply(this.finalDamage);
    }

    /**
     * Returns whether the current damage category permits armor reduction. The
     * result consults the current mutable
     * {@link DamageType#CANNOT_BE_REDUCED_BY_ARMOR_DAMAGE_TYPES} set on each
     * call, so changes to that set affect existing containers as well as new
     * ones.
     */
    public boolean canBeReducedByArmor() {
        return !CANNOT_BE_REDUCED_BY_ARMOR_DAMAGE_TYPES.contains(damageType);
    }
}
