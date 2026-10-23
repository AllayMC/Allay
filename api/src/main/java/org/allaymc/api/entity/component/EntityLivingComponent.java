package org.allaymc.api.entity.component;

import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.damage.DamageContainer;
import org.allaymc.api.entity.effect.EffectInstance;
import org.allaymc.api.entity.effect.EffectType;
import org.allaymc.api.item.ItemStack;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.List;
import java.util.Map;

/**
 * {@code EntityLivingComponent} exposes health, damage eligibility, effects,
 * environmental survival, and death rewards for an entity. Mutable damage and
 * effect objects participate in event-driven updates, while the server's
 * ordinary tick advances fire, freezing, breathing, effect duration, and death
 * processing.
 *
 * @author daoge_cmd
 */
public interface EntityLivingComponent extends EntityComponent {

    /**
     * {@code DEFAULT_MAX_AIR_SUPPLY} initializes new living components with
     * three hundred air ticks for both the current supply and maximum supply.
     */
    int DEFAULT_MAX_AIR_SUPPLY = 300;
    /**
     * {@code DEFAULT_MAX_HEALTH} initializes both current and maximum health
     * to twenty points before specialized components or NBT loading replace
     * them.
     */
    int DEFAULT_MAX_HEALTH = 20;
    /**
     * {@code MAX_FREEZE_TICKS} caps accumulated freezing at one hundred forty
     * ticks and marks the threshold at which periodic freezing damage becomes
     * eligible.
     */
    int MAX_FREEZE_TICKS = 140;

    /**
     * Attempts the damage pipeline using the supplied mutable damage
     * container. A {@code true} result means eligibility, cooldown, and
     * damage-event checks accepted the attempt, although health-event
     * cancellation can still prevent health assignment. The common server
     * requires component-backed entity attackers for notification and can fail
     * after victim effects when an incompatible custom attacker is supplied.
     */
    boolean attack(DamageContainer damage, boolean ignoreCoolDown);

    /**
     * Attempts damage using this entity's ordinary attack cooldown rules. The
     * supplied container is shared with the server pipeline and listeners, so
     * callers must use another container when a later attempt needs
     * independent mutable values.
     */
    default boolean attack(DamageContainer damage) {
        return attack(damage, false);
    }

    /**
     * Attempts a source-free API attack with the supplied damage amount. The
     * new container follows ordinary cooldown and event handling, so this
     * helper does not guarantee that the entity loses health.
     */
    default boolean attack(float damage) {
        return attack(DamageContainer.simpleAttack(damage));
    }

    /**
     * Attempts an entity-sourced attack with the supplied attacker and amount.
     * The created damage container follows ordinary cooldown, attacker
     * modifiers, and listener decisions; a successful pipeline result does not
     * bypass health-event cancellation.
     */
    default boolean attack(Entity attacker, float damage) {
        return attack(DamageContainer.entityAttack(attacker, damage));
    }

    /**
     * Returns whether the damage category is permitted for this entity's
     * current capabilities. This check does not establish that the entity is
     * alive or outside cooldown; {@code attack} checks those conditions
     * separately.
     */
    boolean canBeAttacked(DamageContainer damage);

    /**
     * Returns the last damage container accepted by the cooldown gate, or
     * {@code null} before any acceptance. The reference remains shared, and
     * even a later-cancelled damage event can leave this recorded container in
     * place.
     */
    DamageContainer getLastDamage();

    /**
     * Returns the entity tick recorded at the last cooldown-gate acceptance.
     * The initial value is {@code -1}; a subsequently cancelled damage event
     * does not restore the earlier timestamp in the server implementation.
     */
    long getLastDamageTime();

    /**
     * Returns whether this entity's current rules permit fall damage attempts.
     */
    boolean hasFallDamage();

    /**
     * Returns whether current rules permit fire, lava, and burning damage.
     */
    boolean hasFireDamage();

    /**
     * Returns whether this entity's current effects permit drowning damage
     * attempts.
     */
    boolean hasDrowningDamage();

    /**
     * Returns whether this entity permits damage from the void category.
     */
    boolean hasVoidDamage();

    /**
     * Returns whether fire duration assignment is disabled independently of
     * fire resistance.
     */
    boolean isFireproof();

    /**
     * Returns the stored burning duration measured in entity ticks.
     */
    int getOnFireTicks();

    /**
     * Attempts to replace the burning duration and returns whether assignment
     * is allowed. Fireproof entities reject the request; entering a positive
     * duration can immediately attempt fire-tick damage and broadcast the
     * changed burning state.
     */
    boolean setOnFireTicks(int onFireTicks);

    /**
     * Requests a burning duration of zero through the normal fire setter. This
     * helper discards the setter's result, so a fireproof implementation may
     * reject the request rather than changing its stored duration.
     */
    default void extinguish() {
        setOnFireTicks(0);
    }

    /**
     * Returns whether the stored burning duration is greater than zero.
     */
    default boolean isOnFire() {
        return getOnFireTicks() > 0;
    }

    /**
     * Returns the accumulated freezing duration measured in entity ticks.
     */
    int getFreezeTicks();

    /**
     * Sets accumulated freezing after clamping the supplied ticks to
     * {@code [0, MAX_FREEZE_TICKS]}. The server broadcasts the resulting
     * state, including when the clamped value equals the current value.
     */
    void setFreezeTicks(int freezeTicks);

    /**
     * Returns whether the previous block-collision pass observed powder-snow
     * contact. The living tick consumes and clears this flag before the next
     * block-collision pass can set it for a later tick.
     */
    boolean isInPowderSnow();

    /**
     * Records powder-snow contact for the next living tick's freezing update.
     */
    void setInPowderSnow(boolean inPowderSnow);

    /**
     * Returns whether freezing has reached the maximum accumulated tick
     * threshold.
     */
    default boolean isFrozen() {
        return getFreezeTicks() >= MAX_FREEZE_TICKS;
    }

    /**
     * Returns the stored air supply measured in remaining entity ticks.
     */
    int getAirSupplyTicks();

    /**
     * Sets the current air supply and broadcasts entity state. The server
     * stores the value without clamping; a nonbreathing entity loses one tick
     * at a time and takes drowning damage at the negative damage threshold.
     */
    void setAirSupplyTicks(int ticks);

    /**
     * Returns the configured maximum air supply measured in entity ticks.
     */
    int getAirSupplyMaxTicks();

    /**
     * Sets the maximum air supply used by breathing regeneration and
     * broadcasts state. The server does not clamp the current supply to this
     * value, so changing the maximum does not immediately normalize existing
     * air.
     */
    void setAirSupplyMaxTicks(int ticks);

    /**
     * Returns whether effects or eye position currently permit breathing.
     */
    boolean canBreathe();

    /**
     * Returns an unmodifiable live view of the active effect map. The effect
     * instances themselves remain shared and mutable, so this view prevents
     * structural edits through the map rather than creating independent effect
     * copies.
     */
    @UnmodifiableView
    Map<EffectType, EffectInstance> getEffects();

    /**
     * Returns whether an active effect entry exists for the supplied type.
     */
    boolean hasEffect(EffectType effectType);

    /**
     * Returns the active effect's level, or zero when no effect is stored for
     * that type. The result uses the current mutable effect instance rather
     * than a separately cached level.
     */
    int getEffectLevel(EffectType effectType);

    /**
     * Attempts to add or replace an active effect after its addition event.
     * Accepted instances are retained without copying; listeners may replace
     * the proposed instance, and cancellation leaves the map entry unchanged.
     */
    boolean addEffect(EffectInstance effectInstance);

    /**
     * Attempts to remove an active effect after its removal event. Missing
     * types are ignored, and a cancelled event leaves the instance active;
     * accepted removal invokes the effect hook and updates viewers.
     */
    void removeEffect(EffectType effectType);

    /**
     * Attempts removal for every type present at the start of the operation.
     * Each individual removal still dispatches its cancellable event, so
     * listeners can leave some initially active effects installed.
     */
    void removeAllEffects();

    /**
     * Returns whether this effect type is accepted, with the default accepting
     * every type.
     */
    default boolean canApplyEffect(EffectType effectType) {
        return true;
    }

    /**
     * Collects item drops for the supplied looting level through this entity's
     * drop rules. The server returns the mutable event list with its
     * contributed item references; callers own any subsequent spawning or
     * inventory transfer.
     */
    List<ItemStack> getDrops(int lootingLevel);

    /**
     * Returns the experience-point amount contributed by this entity's death
     * reward rules.
     */
    int getDropXpAmount();

    /**
     * Returns the stored absorption amount used by the victim damage pipeline.
     */
    float getAbsorption();

    /**
     * Sets the absorption amount consulted by subsequent victim damage
     * processing.
     */
    void setAbsorption(float absorption);

    /**
     * Returns the currently stored health amount for this entity.
     */
    float getHealth();

    /**
     * Proposes replacement health through the cancellable health-change event.
     * The server prepares a range-clamped proposal and converts positive
     * values below one to zero, then stores the listener-adjusted value
     * without applying those checks again.
     */
    void setHealth(float health);

    /**
     * Requests the current maximum as the entity's health through
     * {@code setHealth}. The normal health event can cancel or replace that
     * value, so this helper does not force an unconditional reset.
     */
    default void resetHealth() {
        setHealth(getMaxHealth());
    }

    /**
     * Attempts a source-free API attack for one million damage points. Normal
     * cooldown, eligibility, damage modifiers, and event decisions still
     * apply, so this helper does not guarantee immediate death.
     */
    default void kill() {
        attack(DamageContainer.api(1_000_000));
    }

    /**
     * Returns the current maximum health used for ordinary health assignments.
     */
    float getMaxHealth();

    /**
     * Replaces maximum health and lowers current health when it exceeds that
     * maximum. The server does not validate positivity or dispatch a
     * health-change event for this direct normalization, so callers must
     * choose a suitable maximum.
     */
    void setMaxHealth(float maxHealth);
}
