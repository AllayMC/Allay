package org.allaymc.server.entity.component;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.block.property.enums.DripstoneThickness;
import org.allaymc.api.block.property.type.BlockPropertyTypes;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.container.ContainerHolder;
import org.allaymc.api.container.ContainerTypes;
import org.allaymc.api.container.interfaces.ArmorContainer;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.EntityState;
import org.allaymc.api.entity.action.CriticalHit;
import org.allaymc.api.entity.action.EnchantedHit;
import org.allaymc.api.entity.action.SimpleEntityAction;
import org.allaymc.api.entity.component.EntityBaseComponent;
import org.allaymc.api.entity.component.EntityContainerHolderComponent;
import org.allaymc.api.entity.component.EntityLivingComponent;
import org.allaymc.api.entity.component.EntityPhysicsComponent;
import org.allaymc.api.entity.damage.DamageContainer;
import org.allaymc.api.entity.damage.DamageType;
import org.allaymc.api.entity.effect.EffectInstance;
import org.allaymc.api.entity.effect.EffectType;
import org.allaymc.api.entity.effect.EffectTypes;
import org.allaymc.api.entity.interfaces.EntityLiving;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.EventHandler;
import org.allaymc.api.eventbus.event.entity.*;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.enchantment.EnchantmentTypes;
import org.allaymc.api.item.interfaces.ItemAirStack;
import org.allaymc.api.math.MathUtils;
import org.allaymc.api.utils.identifier.Identifier;
import org.allaymc.api.world.gamerule.GameRule;
import org.allaymc.api.world.particle.SimpleParticle;
import org.allaymc.server.component.ComponentClass;
import org.allaymc.server.component.ComponentManager;
import org.allaymc.server.component.annotation.ComponentObject;
import org.allaymc.server.component.annotation.Dependency;
import org.allaymc.server.component.annotation.Manager;
import org.allaymc.server.entity.component.event.*;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtType;
import org.joml.Vector3d;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Math.max;
import static java.lang.Math.min;

/**
 * {@code EntityLivingComponentImpl} manages living state and the ordered
 * damage, survival, effect, and death operations shared by entity types. An
 * attack first records cooldown state, then lets the public damage event
 * decide whether modifier and health processing continue. The damage container
 * remains mutable and may be replaced by the event, while the cooldown record
 * retains the original accepted container.
 *
 * <p> Ordinary ticks process environmental damage and effect expiry before
 * death detection. Death notification and drop-producing handlers run before
 * the base lifecycle enters {@code DEAD}; failures can therefore leave
 * preceding changes applied without rollback. NBT loading uses normal setters
 * and effect events in saved-field order, while saving emits the current
 * fields. Callers must respect the entity's ticking phases because the effect
 * map and living state have no independent synchronization.
 *
 * @author daoge_cmd
 */
public class EntityLivingComponentImpl implements EntityLivingComponent {

    /**
     * {@code IDENTIFIER} selects this living component during provider
     * assembly, allowing specialized implementations to replace the common
     * health and damage behavior.
     */
    @Identifier.Component
    public static final Identifier IDENTIFIER = new Identifier("minecraft:entity_living_component");

    // TAG_ACTIVE_EFFECTS, TAG_FIRE, TAG_ABSORPTION, TAG_HEALTH, and TAG_MAX_HEALTH
    // identify the living-state values shared by the load and save event handlers.

    /**
     * NBT key for the serialized active effect compounds.
     */
    protected static final String TAG_ACTIVE_EFFECTS = "ActiveEffects";
    /**
     * NBT key for the remaining burning duration.
     */
    protected static final String TAG_FIRE = "Fire";
    /**
     * NBT key for the stored absorption amount.
     */
    protected static final String TAG_ABSORPTION = "Absorption";
    /**
     * NBT key for the current health amount.
     */
    protected static final String TAG_HEALTH = "Health";
    /**
     * NBT key for the maximum health amount.
     */
    protected static final String TAG_MAX_HEALTH = "MaxHealth";

    /**
     * Injected manager dispatching this entity's internal component events.
     */
    @Manager
    protected ComponentManager manager;
    /**
     * Base component supplying location, lifecycle, and viewer broadcasts.
     */
    @Dependency
    protected EntityBaseComponent baseComponent;
    /**
     * Optional physics component used for knockback and landing queries.
     */
    @Dependency(optional = true)
    protected EntityPhysicsComponent physicsComponent;
    /**
     * Optional container holder supplying armor and inventory contents.
     */
    @Dependency(optional = true)
    protected EntityContainerHolderComponent containerHolderComponent;
    /**
     * Injected living entity owning this component's mutable state.
     */
    @ComponentObject
    protected EntityLiving thisEntity;

    // lastDamage and lastDamageTime describe the same cooldown-gate acceptance.
    // Recording happens before public event cancellation, so refused damage can
    // still establish the container and timestamp consulted by subsequent cooldown checks.

    /**
     * {@code lastDamage} retains the original container accepted by the
     * cooldown gate. Public damage-event cancellation or replacement does not
     * restore or replace that stored reference.
     */
    @Getter
    protected DamageContainer lastDamage;
    /**
     * {@code lastDamageTime} stores the entity tick of cooldown acceptance,
     * initially {@code -1}. It advances before damage-event dispatch, even
     * when that event later cancels the attempt.
     */
    @Getter
    protected long lastDamageTime = -1;

    /**
     * Stored burning duration measured in entity ticks.
     */
    @Getter
    protected int onFireTicks;
    /**
     * Accumulated freezing duration measured in entity ticks.
     */
    @Getter
    protected int freezeTicks;
    /**
     * {@code inPowderSnow} records powder-snow contact from block collision.
     * The next living tick consumes it for freezing and clears it before later
     * collision callbacks refresh it.
     */
    @Getter
    @Setter
    protected boolean inPowderSnow;
    // airSupplyTicks and airSupplyMaxTicks start at the same constructor default.
    // Breathing recovery compares them but adds four without clamping to the maximum.

    /**
     * Current air supply and configured maximum, measured in ticks.
     */
    @Getter
    protected int airSupplyTicks, airSupplyMaxTicks;

    // health and maxHealth begin equal. setHealth clamps its initial proposal,
    // while setMaxHealth directly lowers health to the replacement maximum when needed.

    /**
     * Current and maximum health measured in damage points.
     */
    @Getter
    protected float health, maxHealth;
    /**
     * Absorption amount consulted by the victim damage pipeline.
     */
    @Getter
    @Setter
    protected float absorption;

    /**
     * {@code effects} owns the mutable active-effect index. Public views share
     * its mutable instances, and tick/removal paths snapshot entries before
     * operations that may alter membership.
     */
    protected Map<EffectType, EffectInstance> effects;
    /**
     * {@code deadTimer} counts ordinary ticks remaining before timed corpse
     * removal. Death processing initializes it only when {@code hasDeadTimer}
     * enables that removal path.
     */
    protected int deadTimer;

    /**
     * Creates an empty active-effect map and initializes both current and
     * maximum health to twenty points. Current and maximum air supply both
     * start at three hundred ticks, and construction does not dispatch setter
     * events or access injected owner components.
     */
    public EntityLivingComponentImpl() {
        this.effects = new HashMap<>();
        this.airSupplyTicks = this.airSupplyMaxTicks = DEFAULT_MAX_AIR_SUPPLY;
        this.health = this.maxHealth = DEFAULT_MAX_HEALTH;
    }

    /**
     * Prepares entity-sourced knockback before the public damage event
     * observes the container. Missing knockback sources use the attacker's
     * current location, and an inventory knockback enchantment halves the base
     * horizontal strength before adding its forward impulse.
     */
    protected void calculateKnockback(DamageContainer damage) {
        if (!damage.isHasKnockback()) {
            return;
        }

        if (!(damage.getAttacker() instanceof Entity attacker)) {
            return;
        }

        if (damage.getKnockbackSource() == null) {
            damage.setKnockbackSource(attacker.getLocation());
        }

        // Prepare the enchantment impulse before dispatch so damage listeners can
        // inspect or replace both the base strength and additional forward motion.
        if (attacker instanceof EntityContainerHolderComponent component &&
            component.hasContainer(ContainerTypes.INVENTORY)) {
            var kbEnchantmentLevel = component.getContainer(ContainerTypes.INVENTORY)
                .getItemInHand().getEnchantmentLevel(EnchantmentTypes.KNOCKBACK);
            if (kbEnchantmentLevel != 0) {
                damage.setKnockback(damage.getKnockback() / 2.0);
                var knockbackAdditional = MathUtils.normalizeIfNotZero(
                    MathUtils.getDirectionVector(attacker.getLocation()).setComponent(1, 0));
                knockbackAdditional.mul(kbEnchantmentLevel * 0.5);
                damage.setKnockbackAdditional(knockbackAdditional);
            }
        }
    }

    /**
     * Attempts an ordered attack against an alive, damage-eligible entity. The
     * cooldown gate records the supplied container and tick before the
     * cancellable damage event; cancellation therefore returns {@code false}
     * while leaving that cooldown record in place. An accepted event can
     * replace the container passed to attacker and victim modifiers.
     *
     * <p> Attacker changes precede armor and effect processing, followed by
     * the health proposal, hit actions, internal notifications, and optional
     * knockback. A {@code true} result means this pipeline completed, even
     * when the health-change event refused the actual health assignment.
     * Failures after a preceding phase do not undo cooldown, item durability,
     * or other already-applied changes.
     */
    @Override
    public boolean attack(DamageContainer damage, boolean ignoreCoolDown) {
        if (!thisEntity.isAlive() ||
            !canBeAttacked(damage) ||
            !checkAndUpdateCoolDown(damage, ignoreCoolDown)) {
            return false;
        }

        calculateKnockback(damage);

        var event = new EntityDamageEvent(thisEntity, damage);
        if (!event.call()) return false;

        damage = event.getDamageContainer();

        applyAttacker(damage);
        applyVictim(damage);
        applyDamage(damage);
        return true;
    }

    /**
     * Proposes health loss and publishes hit reactions for an accepted damage
     * container. Health-event cancellation does not stop actions or
     * notifications. Victim after-damage handlers run before attacker
     * notification, which casts an entity attacker to {@code ComponentClass};
     * an incompatible custom entity can therefore fail after those victim-side
     * effects.
     *
     * <p> Physics knockback uses an explicit container source or the
     * attacker's current location. Missing physics or source skips that motion
     * step after notifications, and the motion setter can independently reject
     * knockback. A later failure does not undo an earlier health assignment,
     * action, or callback, so the method does not make these reactions atomic.
     */
    protected void applyDamage(DamageContainer damage) {
        setHealth(this.health - damage.getFinalDamage());
        thisEntity.applyAction(SimpleEntityAction.HURT);
        if (damage.isCritical()) {
            thisEntity.applyAction(new CriticalHit(55));
        }
        if (damage.isEnchanted()) {
            thisEntity.applyAction(new EnchantedHit(15));
        }

        // Deliver victim and attacker notifications before optional knockback exits;
        // accepted hits still need these callbacks when no motion can be applied.
        this.manager.callEvent(CEntityAfterDamageEvent.INSTANCE);

        if (damage.getAttacker() instanceof Entity entity) {
            ((ComponentClass) entity).getManager().callEvent(CEntityAttackEvent.INSTANCE);
        }

        if (!damage.isHasKnockback()) {
            return;
        }

        if (physicsComponent != null) {
            var knockbackSource = damage.getKnockbackSource();
            if (knockbackSource == null && damage.getAttacker() instanceof Entity attacker) {
                // Listener replacement may omit the prepared source, so use the
                // attacker's current location when entity-sourced knockback still applies.
                knockbackSource = attacker.getLocation();
            }

            if (knockbackSource != null) {
                physicsComponent.knockback(
                    knockbackSource,
                    damage.getKnockback(),
                    damage.getKnockbackVertical(),
                    damage.getKnockbackAdditional(),
                    damage.isIgnoreKnockbackResistance()
                );
            }
        }
    }

    /**
     * Accepts and records an attack when forced or outside the previous
     * container's cooldown. The inclusive cooldown comparison rejects equal
     * deadlines, and successful recording occurs before public damage-event
     * cancellation is known.
     */
    protected boolean checkAndUpdateCoolDown(DamageContainer damage, boolean forceToUpdate) {
        var currentTime = baseComponent.getTick();
        if (!forceToUpdate && lastDamage != null && currentTime - lastDamageTime <= lastDamage.getCoolDown()) {
            return false;
        }

        lastDamage = damage;
        lastDamageTime = currentTime;
        return true;
    }

    /**
     * Applies armor reduction before victim absorption and effect damage
     * hooks.
     */
    protected void applyVictim(DamageContainer damage) {
        applyArmor(damage);
        applyEffects(damage);
    }

    /**
     * Updates armor durability and reduces eligible damage using current
     * equipment and attacker breach. Missing armor behavior or a missing armor
     * container leaves damage unchanged. Damageable equipment receives a
     * durability attempt before reduction, with a minimum increment of one
     * based on source damage.
     *
     * <p> Fall damage uses enchantment protection without ordinary armor
     * values. Other damage combines armor and toughness, then reduces that
     * protection by breach and applies capped enchantment protection
     * afterward. The piecewise calculation acts on the current final damage,
     * while durability uses original source damage, so those two quantities
     * need not match after listener or attacker modifiers.
     */
    protected void applyArmor(DamageContainer damage) {
        if (!damage.canBeReducedByArmor() || containerHolderComponent == null) {
            return;
        }

        var armorContainer = containerHolderComponent.getContainer(ContainerTypes.ARMOR);
        if (armorContainer == null) {
            return;
        }

        // Armor wear follows source damage, keeping earlier final-damage modifiers
        // from eliminating the minimum one-point durability attempt on damageable pieces.
        int durabilityIncreased = Math.max(1, (int) (damage.getSourceDamage() / 4f));
        var itemStackArray = armorContainer.getItemStackArray();
        for (int slot = 0; slot < itemStackArray.length; slot++) {
            var item = itemStackArray[slot];
            if (!item.getItemType().getItemData().isDamageable()) {
                continue;
            }
            item.tryIncreaseDamage(durabilityIncreased);
            armorContainer.notifySlotChange(slot);
        }

        if (damage.getDamageType() == DamageType.FALL) {
            // Use enchantment protection for fall damage before the ordinary armor
            // calculation, which would otherwise reduce damage through armor values too.
            applyArmorWhenFall(damage, armorContainer);
            return;
        }

        var totalArmorValue = 0f;
        var totalToughnessValue = 0f;
        var enchantmentProtectionFactor = 0;
        var breachReduction = 0f;

        if (damage.getAttacker() instanceof ContainerHolder holder && holder.hasContainer(ContainerTypes.INVENTORY)) {
            var item = holder.getContainer(ContainerTypes.INVENTORY).getItemInHand();
            var breachLevel = item.getEnchantmentLevel(EnchantmentTypes.BREACH);
            if (breachLevel > 0) {
                // Each breach level removes fifteen percentage points of armor-derived protection.
                breachReduction = 0.15f * breachLevel;
            }
        }

        for (var item : armorContainer.getItemStacks()) {
            if (item == ItemAirStack.AIR_STACK) {
                continue;
            }

            totalArmorValue += item.getItemType().getItemData().armorValue();
            totalToughnessValue += item.getItemType().getItemData().toughnessValue();
            enchantmentProtectionFactor += item.getEnchantmentProtectionFactor(damage.getDamageType());
        }
        // Cap enchantment protection at twenty, limiting this stage to eighty percent.
        enchantmentProtectionFactor = Math.min(20, enchantmentProtectionFactor);

        // Apply the armor-and-toughness branch before breach so breach subtracts
        // from the resulting protection fraction rather than from the armor points.
        final var v = totalArmorValue;
        final var t = totalToughnessValue;
        final var breach = breachReduction;
        damage.updateFinalDamage(d -> {
            if (d <= 0) {
                return d;
            }

            float reduced;
            if (d <= 1.6f * v + 0.2f * v * t) {
                reduced = (1f / (6.25f + 50f)) * d * d +
                          (1f - v / 25f) * d;
            } else {
                reduced = (1f - v / 125f) * d;
            }

            if (breach <= 0f) {
                return reduced;
            }

            var reduction = (d - reduced) / d;
            var adjustedReduction = Math.max(0f, reduction - breach);
            return d * (1f - adjustedReduction);
        });

        // Apply capped enchantment protection after the armor and breach result,
        // keeping the two reduction stages multiplicative rather than adding their factors.
        final var epf = enchantmentProtectionFactor;
        if (epf != 0) {
            damage.updateFinalDamage(d -> d * (1f - epf / 25f));
        }
    }

    /**
     * Reduces fall damage using only the armor items' enchantment protection
     * factors. Air entries contribute nothing, the summed factor is capped at
     * twenty, and zero protection leaves the current final damage unchanged.
     * This path deliberately bypasses armor-value and toughness calculations
     * used for other damage categories.
     *
     * <p> The selected protection factor scales the damage already present in
     * the mutable container. Callers invoke this helper after armor durability
     * handling, so this reduction does not restore equipment wear or inspect
     * attacker breach again.
     */
    protected void applyArmorWhenFall(DamageContainer damage, ArmorContainer armorContainer) {
        var enchantmentProtectionFactor = 0;

        for (var item : armorContainer.getItemStacks()) {
            if (item == ItemAirStack.AIR_STACK) {
                continue;
            }

            enchantmentProtectionFactor += item.getEnchantmentProtectionFactor(damage.getDamageType());
        }
        // Cap enchantment protection at twenty, limiting this stage to eighty percent.
        enchantmentProtectionFactor = Math.min(20, enchantmentProtectionFactor);

        if (enchantmentProtectionFactor == 0) {
            return;
        }

        final var epf = enchantmentProtectionFactor;
        damage.updateFinalDamage(d -> d * (1f - epf / 25f));
    }

    /**
     * Updates absorption and invokes the victim's active effect damage hooks.
     * Positive absorption is first reduced by the current final damage, and
     * the subsequent damage adjustment subtracts that remaining absorption
     * value. This order means the subtracted amount is not the absorption that
     * was consumed.
     *
     * <p> Effect hooks receive {@code lastDamage}, the container recorded
     * before public damage-event replacement, rather than necessarily
     * receiving the supplied {@code damage} reference. Callers therefore
     * cannot assume a listener-replaced container is the same object seen by
     * every active effect hook.
     */
    protected void applyEffects(DamageContainer damage) {
        // The updater subtracts the remaining absorption rather than the amount
        // consumed, so a depleted reserve removes no further damage in this pipeline.
        if (this.absorption > 0) {
            setAbsorption(Math.max(0, this.absorption - damage.getFinalDamage()));
            damage.updateFinalDamage(d -> Math.max(0, d - this.absorption));
        }

        this.effects.values().forEach(effect ->
                effect.getType().onEntityDamage(thisEntity, effect, lastDamage)
        );
    }

    /**
     * Applies the attacker's active strength and weakness modifiers, held
     * weapon enchantments, and critical state. Living attackers contribute
     * effect levels before inventory sharpness and fire aspect are read. Fire
     * aspect attempts to ignite this victim during this phase, before victim
     * armor and health processing.
     *
     * <p> An attacker with physics replaces the container's critical flag
     * using its current critical-attack eligibility. The final critical
     * multiplier follows the other attacker damage changes; enchanted flags
     * also control later hit particles, so these mutations influence both
     * damage and viewer presentation.
     */
    protected void applyAttacker(DamageContainer damage) {
        if (damage.getAttacker() instanceof Entity attacker) {
            if (attacker instanceof EntityLivingComponent living) {
                var strengthLevel = living.getEffectLevel(EffectTypes.STRENGTH);
                if (strengthLevel > 0) {
                    damage.updateFinalDamage(d -> {
                        // Strength scales both the current damage and its level-derived bonus.
                        var pow = Math.pow(1.3, strengthLevel);
                        return (float) (d * pow + ((pow - 1) / 0.3));
                    });
                }

                var weaknessLevel = living.getEffectLevel(EffectTypes.WEAKNESS);
                if (weaknessLevel > 0) {
                    damage.updateFinalDamage(d -> {
                        // Weakness applies its own level-dependent scale after strength.
                        var pow = Math.pow(0.8, weaknessLevel);
                        return (float) (d * pow + ((pow - 1) / 0.4));
                    });
                }
            }

            if (attacker instanceof ContainerHolder holder && holder.hasContainer(ContainerTypes.INVENTORY)) {
                var item = holder.getContainer(ContainerTypes.INVENTORY).getItemInHand();
                var sharpnessLevel = item.getEnchantmentLevel(EnchantmentTypes.SHARPNESS);
                if (sharpnessLevel > 0) {
                    // Apply the sharpness bonus before victim armor and absorption process it.
                    damage.updateFinalDamage(d -> d + sharpnessLevel * 1.25f);
                    damage.setEnchanted(true);
                }
                var fireAspectLevel = item.getEnchantmentLevel(EnchantmentTypes.FIRE_ASPECT);
                if (fireAspectLevel > 0) {
                    // Each fire-aspect level requests four seconds of burning in ticks.
                    setOnFireTicks(fireAspectLevel * 80);
                    damage.setEnchanted(true);
                }
            }

            if (attacker instanceof EntityPhysicsComponent component) {
                damage.setCritical(component.canCriticalAttack());
            }
        }

        if (damage.isCritical()) {
            damage.updateFinalDamage(d -> d * 1.5f);
        }
    }

    /**
     * Returns whether the damage category passes this entity's current damage
     * capability checks.
     */
    @Override
    public boolean canBeAttacked(DamageContainer damage) {
        if (!hasFallDamage() && damage.getDamageType() == DamageType.FALL) {
            return false;
        }

        if (!hasFireDamage() &&
            (damage.getDamageType() == DamageType.FIRE ||
             damage.getDamageType() == DamageType.LAVA ||
             damage.getDamageType() == DamageType.FIRE_TICK)
        ) {
            return false;
        }

        if (!hasDrowningDamage() && damage.getDamageType() == DamageType.DROWN) {
            return false;
        }

        if (!hasVoidDamage() && damage.getDamageType() == DamageType.VOID) {
            return false;
        }

        return true;
    }

    /**
     * Returns whether any configured fall-damage enabling condition currently
     * holds. The calculation uses a disjunction: physics gravity, absence of
     * both levitation and slow falling, or the fall-damage game rule can
     * independently enable the result. A disabled game rule therefore does not
     * by itself suppress fall damage here.
     *
     * <p> Callers use this result as a damage-category gate and before
     * querying landing blocks. The method reads current component and effect
     * state each time, so changes to gravity, effects, or the world rule can
     * change eligibility between attempts.
     */
    @Override
    public boolean hasFallDamage() {
        return (physicsComponent != null && physicsComponent.hasGravity()) ||
               (!hasEffect(EffectTypes.LEVITATION) && !hasEffect(EffectTypes.SLOW_FALLING)) ||
               thisEntity.getWorld().getWorldData().<Boolean>getGameRuleValue(GameRule.FALL_DAMAGE);
    }

    /**
     * Returns whether fire resistance, fireproof status, and the world
     * fire-damage rule permit burning damage.
     */
    @Override
    public boolean hasFireDamage() {
        return !hasEffect(EffectTypes.FIRE_RESISTANCE) &&
               !isFireproof() &&
               thisEntity.getWorld().getWorldData().<Boolean>getGameRuleValue(GameRule.FIRE_DAMAGE);
    }

    /**
     * Returns whether the entity lacks the water-breathing effect for drowning
     * eligibility.
     */
    @Override
    public boolean hasDrowningDamage() {
        return !hasEffect(EffectTypes.WATER_BREATHING);
    }

    /**
     * Returns {@code true} to enable the ordinary void damage category.
     */
    @Override
    public boolean hasVoidDamage() {
        return true;
    }

    /**
     * Returns {@code false} so ordinary living components permit fire-duration
     * assignment.
     */
    @Override
    public boolean isFireproof() {
        return false;
    }

    /**
     * Attempts to store the requested burning duration unless this entity is
     * fireproof. Entering a positive duration attempts immediate fire-tick
     * damage before the new duration is stored; crossing between burning and
     * nonburning also broadcasts state.
     */
    @Override
    public boolean setOnFireTicks(int newOnFireTicks) {
        if (this.isFireproof()) {
            return false;
        }

        var updateState = false;
        if (this.onFireTicks > 0 && newOnFireTicks <= 0) {
            updateState = true;
        } else if (this.onFireTicks <= 0 && newOnFireTicks > 0) {
            updateState = true;
            // Attempt the initial burn before storing its duration so damage reactions
            // occur when the entity first transitions into the burning state.
            attack(DamageContainer.fireTick(1));
        }

        this.onFireTicks = newOnFireTicks;
        if (updateState) {
            this.baseComponent.broadcastState();
        }

        return true;
    }

    /**
     * Clamps freeze duration to its supported range before broadcasting entity
     * state. Every call publishes the resulting value, even when unchanged,
     * and a viewer failure does not restore the previous duration.
     */
    @Override
    public void setFreezeTicks(int freezeTicks) {
        this.freezeTicks = Math.clamp(freezeTicks, 0, MAX_FREEZE_TICKS);
        this.baseComponent.broadcastState();
    }

    /**
     * Stores the requested air supply before broadcasting entity state without
     * range normalization. A viewer failure leaves the new supply stored, and
     * the maximum supply is not changed by this setter.
     */
    @Override
    public void setAirSupplyTicks(int ticks) {
        this.airSupplyTicks = ticks;
        this.baseComponent.broadcastState();
    }

    /**
     * Stores the maximum air supply before broadcasting entity state without
     * normalizing current air. A viewer failure leaves the new maximum stored,
     * and current air can remain above it until later gameplay changes it.
     */
    @Override
    public void setAirSupplyMaxTicks(int ticks) {
        this.airSupplyMaxTicks = ticks;
        this.baseComponent.broadcastState();
    }

    /**
     * Returns an unmodifiable live view over the active effect map. Its
     * entries retain their mutable effect objects, so callers can observe
     * later duration changes and must not treat the view as an independent
     * snapshot.
     */
    @Override
    public Map<EffectType, EffectInstance> getEffects() {
        return Collections.unmodifiableMap(effects);
    }

    /**
     * Returns whether the active effect map contains the supplied effect type.
     */
    @Override
    public boolean hasEffect(EffectType effectType) {
        return effects.containsKey(effectType);
    }

    /**
     * Returns the stored effect's level, or zero when the type is absent. The
     * returned level comes from the current effect instance and reflects
     * mutations to that retained object.
     */
    @Override
    public int getEffectLevel(EffectType effectType) {
        var effect = effects.get(effectType);
        return effect == null ? 0 : effect.getLevel();
    }

    /**
     * Attempts to install an effect after type eligibility and the cancellable
     * addition event. The event's final instance is stored directly, replacing
     * an old entry of that type; only a previously absent type receives
     * {@code onAdd} and a state broadcast.
     */
    @Override
    public boolean addEffect(EffectInstance effectInstance) {
        if (!canApplyEffect(effectInstance.getType())) {
            return false;
        }

        var event = new EntityEffectAddEvent(thisEntity, effectInstance);
        if (!event.call()) {
            return false;
        }

        effectInstance = event.getEffect();
        var old = effects.put(effectInstance.getType(), effectInstance);
        if (old == null) {
            effectInstance.getType().onAdd(thisEntity, effectInstance);
            this.baseComponent.broadcastState();
        }
        sendEffects(effectInstance, old);

        return true;
    }

    /**
     * Attempts removal of an existing effect after its cancellable removal
     * event. Accepted removal detaches the map entry before invoking the
     * removal hook and notifying viewers; cancellation leaves the current
     * entry installed.
     */
    @Override
    public void removeEffect(EffectType effectType) {
        var removed = effects.get(effectType);
        if (removed == null) {
            return;
        }

        var event = new EntityEffectRemoveEvent(thisEntity, removed);
        if (!event.call()) {
            return;
        }

        effects.remove(effectType);
        effectType.onRemove(thisEntity, removed);
        sendEffects(null, removed);
        this.baseComponent.broadcastState();
    }

    /**
     * Sends an effect change to the entity's current viewers. Either the new
     * or old instance can be {@code null} to describe removal or addition, and
     * the viewer reads those references directly.
     */
    protected void sendEffects(EffectInstance newEffect, EffectInstance oldEffect) {
        thisEntity.forEachViewers(viewer -> viewer.viewEntityEffectChange(thisEntity, newEffect, oldEffect));
    }

    /**
     * Attempts removal of a snapshot of currently active effect types. Each
     * removal still follows its own event decision, so cancelled removals
     * remain active and newly added types are outside this initial snapshot.
     */
    @Override
    public void removeAllEffects() {
        // Snapshot keys because removeEffect changes map membership and dispatches
        // listeners that can alter other active effects during the same removal pass.
        for (EffectType effectType : this.effects.keySet().toArray(EffectType[]::new)) {
            removeEffect(effectType);
        }
    }

    /**
     * Proposes clamped health through a cancellable health-change event.
     * Positive values below one become zero before dispatch so the initial
     * proposal does not retain the client's fractional-death mismatch. The
     * accepted listener value is stored directly without another clamp.
     */
    @Override
    public void setHealth(float value) {
        if (value > 0 && value < 1) {
            // Treat positive sub-one health as zero because client death presentation
            // would otherwise disagree with the server's zero-health death check.
            value = 0;
        } else {
            value = max(0, min(value, this.maxHealth));
        }

        var event = new EntityHealthChangeEvent(thisEntity, this.health, value);
        if (!event.call()) {
            return;
        }

        this.health = event.getNewHealth();
    }

    /**
     * Replaces maximum health and directly lowers current health to that value
     * when needed. This setter does not dispatch the health-change event or
     * validate positivity, unlike the normal current-health proposal path.
     */
    @Override
    public void setMaxHealth(float maxHealth) {
        this.maxHealth = maxHealth;
        this.health = Math.min(this.health, this.maxHealth);
    }

    /**
     * Processes environmental survival, active effects, and death in their
     * required tick order.
     */
    @EventHandler
    protected void onTick(CEntityTickEvent event) {
        var currentTick = event.getCurrentTick();
        tickVoid(currentTick);
        tickFire(currentTick);
        tickFreeze(currentTick);
        tickBreathe();
        tickEffects();
        tickDead();
    }

    /**
     * Attempts ten void damage points every twenty dimension ticks when the
     * entity is at least eighteen blocks below minimum height. Ordinary attack
     * eligibility and cooldown handling still decide whether each scheduled
     * attempt proceeds.
     */
    protected void tickVoid(long currentTick) {
        if (currentTick % 20 != 0) {
            // Spread void damage attempts one second apart at twenty ticks per second.
            return;
        }

        // Keep the void threshold eighteen blocks below the dimension floor.
        int minY = thisEntity.getDimension().getDimensionType().getMinHeight() - 18;
        if (thisEntity.getLocation().y() <= minY) {
            attack(DamageContainer.voidDamage(10));
        }
    }

    /**
     * Decreases a positive burning duration through the fire setter and
     * attempts periodic fire damage. The setter handles burning-state
     * broadcasts at expiry, while damage attempts align with every twentieth
     * dimension tick.
     */
    protected void tickFire(long currentTick) {
        if (!isOnFire()) {
            return;
        }

        // Use the setter at expiry so viewers receive the burning-state transition
        // instead of retaining the fire flag after the countdown reaches zero.
        this.setOnFireTicks(onFireTicks - 1);
        if (currentTick % 20 == 0) {
            attack(DamageContainer.fireTick(1));
        }
    }

    /**
     * Consumes the powder-snow contact observed during the previous
     * block-collision pass. Contact increases freezing to
     * {@code MAX_FREEZE_TICKS}, while absence reduces a positive duration by
     * one; the fully frozen threshold enables damage only on each fortieth
     * dimension tick with the freeze-damage rule enabled.
     *
     * <p> The flag is cleared after processing, before the base component runs
     * its next block-collision pass. Multiple powder-snow callbacks can then
     * mark contact again without increasing freezing themselves, keeping the
     * duration update associated with one living tick rather than with the
     * number of intersected snow blocks.
     */
    protected void tickFreeze(long currentTick) {
        if (inPowderSnow) {
            if (freezeTicks < MAX_FREEZE_TICKS) {
                setFreezeTicks(freezeTicks + 1);
            }
            // Fully frozen damage attempts align with two-second dimension-tick intervals.
            if (freezeTicks >= MAX_FREEZE_TICKS && currentTick % 40 == 0 &&
                    thisEntity.getWorld().getWorldData().<Boolean>getGameRuleValue(GameRule.FREEZE_DAMAGE)) {
                attack(DamageContainer.freezing(1f));
            }
        } else if (freezeTicks > 0) {
            setFreezeTicks(freezeTicks - 1);
        }
        // Consume only the previous collision pass; the next pass must establish
        // fresh powder-snow contact instead of carrying this tick's observation forward.
        inPowderSnow = false;
    }

    /**
     * Updates air from breathing eligibility and applies drowning attempts
     * after twenty negative supply ticks. A nonbreathing entity loses one air
     * tick per update, then resets the supply to zero after each drowning
     * attempt, even if damage is refused.
     *
     * <p> Breathing restores four ticks whenever the current supply is below
     * its maximum. That addition is not clamped, so it can exceed the
     * configured maximum by up to three ticks. A changed supply broadcasts
     * entity state, while unchanged air avoids another broadcast.
     */
    protected void tickBreathe() {
        var newAirSupplyTicks = this.airSupplyTicks;
        if (!canBreathe()) {
            newAirSupplyTicks = this.airSupplyTicks - 1;
            // Twenty depleted-air ticks space repeated drowning attempts one second
            // apart; resetting to zero keeps the next interval independent of damage success.
            if (newAirSupplyTicks <= -20) {
                attack(DamageContainer.drown(2));
                newAirSupplyTicks = 0;
            }
        } else if (this.airSupplyTicks < this.airSupplyMaxTicks) {
            newAirSupplyTicks = this.airSupplyTicks + 4;
        }
        if (this.airSupplyTicks != newAirSupplyTicks) {
            this.airSupplyTicks = newAirSupplyTicks;
            this.baseComponent.broadcastState();
        }
    }

    /**
     * Returns whether breathing effects apply or the entity's eyes are outside
     * water.
     */
    @Override
    public boolean canBreathe() {
        return hasEffect(EffectTypes.WATER_BREATHING) || hasEffect(EffectTypes.CONDUIT_POWER) || !thisEntity.isEyesInWater();
    }

    /**
     * Advances a snapshot of active effects by one tick and calls each
     * effect's tick hook. Expired instances request normal cancellable
     * removal, so an effect may remain installed with a nonpositive duration
     * when a listener refuses removal.
     */
    protected void tickEffects() {
        if (this.effects.isEmpty()) {
            return;
        }

        // Snapshot instances because tick hooks and expiry removal can change map
        // membership while this pass still needs to visit its original effect set.
        for (var effect : this.effects.values().toArray(EffectInstance[]::new)) {
            effect.setDuration(effect.getDuration() - 1);
            effect.getType().onTick(thisEntity, effect);
            if (effect.getDuration() <= 0) {
                removeEffect(effect.getType());
            }
        }
    }

    /**
     * Starts death processing when health is exactly zero and the base state
     * is not already dead. A dead entity either counts down its enabled corpse
     * timer or immediately queues removal when that timer is disabled.
     *
     * <p> The death timer is decremented in the same update that starts death,
     * and reaching zero emits particles before removal is requested. Removal
     * remains an entity-manager operation rather than an immediate deletion
     * here, and failures in death or particle handling leave any earlier state
     * changes applied.
     */
    protected void tickDead() {
        if (this.health == 0 && !this.baseComponent.isDead()) {
            onDie();
        }

        if (this.baseComponent.isDead()) {
            var manager = this.baseComponent.getDimension().getEntityManager();
            if (hasDeadTimer()) {
                if (deadTimer > 0) deadTimer--;
                if (deadTimer == 0) {
                    spawnDeadParticle();
                    manager.removeEntity(thisEntity);
                }
            } else {
                manager.removeEntity(thisEntity);
            }
        }
    }

    /**
     * Returns whether a corpse persists briefly and emits removal particles,
     * enabled by default. Specialized living components can disable this timer
     * to request removal immediately after their base lifecycle enters the
     * dead state.
     */
    protected boolean hasDeadTimer() {
        return true;
    }

    /**
     * Collects item references from internal drop handlers using the supplied
     * looting level. The returned list is the event's mutable accumulator, and
     * inventory handlers may already have cleared their source slots during
     * collection.
     */
    @Override
    public List<ItemStack> getDrops(int lootingLevel) {
        var event = new CEntityGetDropEvent(lootingLevel);
        manager.callEvent(event);
        return event.getDrops();
    }

    /**
     * Collects the experience reward from internal handlers, starting with
     * zero points.
     */
    @Override
    public int getDropXpAmount() {
        var event = new CEntityGetDropXpEvent(0);
        manager.callEvent(event);
        return event.getXp();
    }

    /**
     * Publishes death and collects its drops before transitioning the base
     * lifecycle to {@code DEAD}. The public notification precedes internal
     * death handlers, which can replace the item drop position and motion
     * supplier. Looting comes from the recorded attacker's current held item,
     * and mob-loot rules gate item collection.
     *
     * <p> Experience requires the recorded attacker itself to be a player and
     * uses the entity position separately from the editable item-drop
     * position. Effect death hooks run before the dead-state transition, timer
     * initialization, and death action. This sequence has no rollback, so a
     * handler or drop failure can leave earlier notifications, inventory
     * changes, or spawned drops applied.
     */
    protected void onDie() {
        new EntityDieEvent(thisEntity).call();

        var pos = baseComponent.getLocation();
        var dieEvent = new CEntityDieEvent();
        dieEvent.setDropPosition(new Vector3d(pos.x(), pos.y(), pos.z()));
        manager.callEvent(dieEvent);

        int lootingLevel = 0;
        if (lastDamage != null &&
            lastDamage.getAttacker() instanceof ContainerHolder holder &&
            holder.hasContainer(ContainerTypes.INVENTORY)) {
            lootingLevel = holder.getContainer(ContainerTypes.INVENTORY).getItemInHand().getEnchantmentLevel(EnchantmentTypes.LOOTING);
        }

        // Collect inventory-backed drops only after the mob-loot gate succeeds,
        // because component collection can clear source slots before items are spawned.
        if (thisEntity.getWorld().getWorldData().<Boolean>getGameRuleValue(GameRule.DO_MOB_LOOT)) {
            var drops = getDrops(lootingLevel);
            if (!drops.isEmpty()) {
                var dimension = baseComponent.getDimension();
                var dropPos = dieEvent.getDropPosition();
                var motionFactory = dieEvent.getDropMotionFactory();
                for (var drop : drops) {
                    // Forty ticks prevent pickup during the first two seconds of death loot.
                    dimension.dropItem(drop, dropPos, motionFactory.get(), 40);
                }
            }
        }

        if (lastDamage != null && lastDamage.getAttacker() instanceof EntityPlayer) {
            var xpAmount = getDropXpAmount();
            if (xpAmount > 0) {
                baseComponent.getDimension().splitAndDropXpOrb(
                    new Vector3d(pos.x(), pos.y(), pos.z()), xpAmount
                );
            }
        }

        this.effects.values().forEach(effect -> effect.getType().onEntityDies(thisEntity, effect));
        ((EntityBaseComponentImpl) this.baseComponent).setState(EntityState.DEAD);
        if (hasDeadTimer()) {
            this.deadTimer = 20;
        }

        this.baseComponent.applyAction(SimpleEntityAction.DEATH);
    }

    /**
     * Emits smoke-like explosion particles on a half-block grid within the
     * entity box.
     */
    protected void spawnDeadParticle() {
        var offsetAABB = this.baseComponent.getOffsetAABB();
        // Half-block sampling distributes removal particles throughout the corpse's box.
        for (double x = offsetAABB.minX(); x <= offsetAABB.maxX(); x += 0.5) {
            for (double z = offsetAABB.minZ(); z <= offsetAABB.maxZ(); z += 0.5) {
                for (double y = offsetAABB.minY(); y <= offsetAABB.maxY(); y += 0.5) {
                    this.baseComponent.getDimension().addParticle(x, y, z, SimpleParticle.EXPLODE);
                }
            }
        }
    }

    /**
     * Writes living health, absorption, fire, and active effects to the shared
     * builder. Fire duration is narrowed to a {@code short}, while freeze and air
     * state are omitted by this component's persistence handlers.
     */
    @EventHandler
    protected void onSaveNBT(CEntitySaveNBTEvent event) {
        var nbt = event.getNbt();
        // The fire tag uses the short encoding expected by onLoadNBT; values
        // outside that representation narrow before the compound is saved.
        nbt.putShort(TAG_FIRE, (short) onFireTicks);
        if (!effects.isEmpty()) {
            nbt.putList(TAG_ACTIVE_EFFECTS, NbtType.COMPOUND, effects.values().stream().map(EffectInstance::saveNBT).toList());
        }
        nbt.putFloat(TAG_ABSORPTION, this.absorption);
        nbt.putFloat(TAG_HEALTH, this.health);
        nbt.putFloat(TAG_MAX_HEALTH, this.maxHealth);
    }

    /**
     * Loads living fields through their normal setters and active-effect
     * events. Fire and effects are processed before health, and current health
     * is loaded before maximum health, so the initial health proposal can be
     * clamped using the previous maximum.
     *
     * <p> Missing tags preserve current values. This handler does not clear
     * existing effects first or restore omitted freeze and air fields;
     * cancellation and exceptions from setters or effect construction can
     * leave earlier loaded values applied without rollback.
     */
    @EventHandler
    protected void onLoadNBT(CEntityLoadNBTEvent event) {
        var nbt = event.getNbt();
        nbt.listenForShort(TAG_FIRE, this::setOnFireTicks);
        nbt.listenForList(TAG_ACTIVE_EFFECTS, NbtType.COMPOUND, activeEffects -> {
            for (NbtMap activeEffect : activeEffects) {
                var effectInstance = EffectInstance.fromNBT(activeEffect);
                addEffect(effectInstance);
            }
        });
        nbt.listenForFloat(TAG_ABSORPTION, this::setAbsorption);
        nbt.listenForFloat(TAG_HEALTH, this::setHealth);
        nbt.listenForFloat(TAG_MAX_HEALTH, this::setMaxHealth);
    }

    /**
     * Calculates landing damage from the accepted fall distance and current
     * supporting block. Upward dripstone tips use the stalagmite formula and
     * skip ordinary fall reduction; other blocks apply the three-block
     * allowance, jump-boost level, and the block's fall-reduction factor.
     *
     * <p> Damage attempts occur only when the computed amount is positive and
     * still follow the normal attack pipeline. The internal physics event
     * establishes the expected physics component for the standing-block query,
     * while {@code hasFallDamage} can skip that query entirely for entities
     * that reject fall damage.
     */
    @EventHandler
    protected void onFall(CEntityFallEvent event) {
        if (!hasFallDamage()) {
            return;
        }

        // This event originates in physics landing handling, which supplies the
        // standing-block query needed to distinguish ordinary surfaces from dripstone tips.
        var blockStateStandingOn = physicsComponent.getBlockStateStandingOn();
        var fallDistance = event.getFallDistance();

        if (blockStateStandingOn.getBlockType() == BlockTypes.POINTED_DRIPSTONE &&
            blockStateStandingOn.getPropertyValue(BlockPropertyTypes.DRIPSTONE_THICKNESS) == DripstoneThickness.TIP &&
            !blockStateStandingOn.getPropertyValue(BlockPropertyTypes.HANGING)) {
            // Upward dripstone tips double fall distance before the two-point allowance.
            var damage = (long) Math.ceil(fallDistance * 2 - 2);
            if (damage > 0) {
                attack(DamageContainer.stalagmite(damage));
            }
            return;
        }

        // Ordinary falls receive a three-block allowance before jump boost and
        // the supporting block's reduction determine the rounded positive damage amount.
        double rawDamage = (fallDistance - 3) - getEffectLevel(EffectTypes.JUMP_BOOST);
        var damage = Math.round(rawDamage * (1 - blockStateStandingOn.getBehavior().getFallDamageReductionFactor()));
        if (damage > 0) {
            attack(DamageContainer.fall(damage));
        }
    }
}
