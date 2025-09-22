package org.allaymc.server.entity.component;

import lombok.Getter;
import org.allaymc.api.container.ContainerHolder;
import org.allaymc.api.container.ContainerType;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.action.SimpleEntityAction;
import org.allaymc.api.entity.component.EntityContainerHolderComponent;
import org.allaymc.api.entity.component.EntityLivingComponent;
import org.allaymc.api.entity.component.EntityPhysicsComponent;
import org.allaymc.api.entity.component.attribute.AttributeType;
import org.allaymc.api.entity.component.attribute.EntityAttributeComponent;
import org.allaymc.api.entity.damage.DamageContainer;
import org.allaymc.api.entity.damage.DamageType;
import org.allaymc.api.entity.data.EntityData;
import org.allaymc.api.entity.data.EntityFlag;
import org.allaymc.api.entity.effect.EffectInstance;
import org.allaymc.api.entity.effect.EffectType;
import org.allaymc.api.entity.effect.EffectTypes;
import org.allaymc.api.entity.interfaces.EntityLiving;
import org.allaymc.api.eventbus.EventHandler;
import org.allaymc.api.eventbus.event.entity.EntityDamageEvent;
import org.allaymc.api.eventbus.event.entity.EntityEffectAddEvent;
import org.allaymc.api.eventbus.event.entity.EntityEffectRemoveEvent;
import org.allaymc.api.item.enchantment.EnchantmentTypes;
import org.allaymc.api.math.MathUtils;
import org.allaymc.api.utils.identifier.Identifier;
import org.allaymc.api.world.gamerule.GameRule;
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
import java.util.Map;

/**
 * @author daoge_cmd
 */
public class EntityLivingComponentImpl implements EntityLivingComponent {

    @Identifier.Component
    public static final Identifier IDENTIFIER = new Identifier("minecraft:entity_living_component");

    protected static final String TAG_ACTIVE_EFFECTS = "ActiveEffects";

    @Manager
    protected ComponentManager manager;
    @Dependency
    protected EntityAttributeComponent attributeComponent;
    @Dependency(optional = true)
    protected EntityPhysicsComponent physicsComponent;
    @ComponentObject
    protected EntityLiving thisEntity;

    @Getter
    protected DamageContainer lastDamage;
    @Getter
    protected long lastDamageTime;
    @Getter
    protected int onFireTicks;
    protected Map<EffectType, EffectInstance> effects;

    public EntityLivingComponentImpl() {
        this.effects = new HashMap<>();
    }

    @Override
    public boolean attack(DamageContainer damage, boolean ignoreCoolDown) {
        if (!canBeAttacked(damage) || !checkAndUpdateCoolDown(damage, ignoreCoolDown)) {
            return false;
        }

        var event = new EntityDamageEvent(thisEntity, damage);
        if (!event.call()) return false;

        damage = event.getDamageContainer();

        applyAttacker(damage);
        applyVictim(damage);
        applyDamage(damage);
        return true;
    }

    protected void applyDamage(DamageContainer damage) {
        this.attributeComponent.setHealth(this.attributeComponent.getHealth() - damage.getFinalDamage());
        thisEntity.applyAction(SimpleEntityAction.HURT);
        if (damage.isCritical()) {
            thisEntity.applyAction(SimpleEntityAction.CRITICAL_HIT);
        }
        if (damage.isEnchanted()) {
            thisEntity.applyAction(SimpleEntityAction.ENCHANTED_HIT);
        }
        this.manager.callEvent(CEntityAfterDamageEvent.INSTANCE);

        if (!(damage.getAttacker() instanceof Entity entity)) {
            return;
        }

        ((ComponentClass) entity).getManager().callEvent(CEntityAttackEvent.INSTANCE);
        if (!damage.isHasKnockback()) {
            return;
        }

        if (physicsComponent != null) {
            var kb = EntityPhysicsComponent.DEFAULT_KNOCKBACK;
            var kby = EntityPhysicsComponent.DEFAULT_KNOCKBACK;
            var additionalMotion = new Vector3d();
            if (entity instanceof EntityContainerHolderComponent component && component.hasContainer(ContainerType.INVENTORY)) {
                var kbEnchantmentLevel = component.getContainer(ContainerType.INVENTORY).getItemInHand().getEnchantmentLevel(EnchantmentTypes.KNOCKBACK);
                if (kbEnchantmentLevel != 0) {
                    kb /= 2.0;
                    additionalMotion = MathUtils.normalizeIfNotZero(MathUtils.getDirectionVector(entity.getLocation()).setComponent(1, 0));
                    additionalMotion.mul(kbEnchantmentLevel * 0.5);
                }
            }

            physicsComponent.knockback(entity.getLocation(), kb, kby, additionalMotion);
        }
    }

    protected boolean checkAndUpdateCoolDown(DamageContainer damage, boolean forceToUpdate) {
        var currentTime = thisEntity.getWorld().getTick();
        if (!forceToUpdate && lastDamage != null && currentTime - lastDamageTime <= lastDamage.getCoolDown()) {
            return false;
        }

        lastDamage = damage;
        lastDamageTime = currentTime;
        return true;
    }

    protected void applyVictim(DamageContainer damage) {
        applyArmor(damage);
        applyEffects(damage);
    }

    protected void applyEffects(DamageContainer damage) {
        // Damage absorption
        if (attributeComponent.supportAttribute(AttributeType.ABSORPTION)) {
            var absorption = attributeComponent.getAbsorption();
            if (absorption > 0) {
                attributeComponent.setAbsorption(Math.max(0, absorption - damage.getFinalDamage()));
                damage.updateFinalDamage(d -> Math.max(0, d - absorption));
            }
        }

        this.effects.values().forEach(effect ->
                effect.getType().onEntityDamage(thisEntity, effect, lastDamage)
        );
    }

    protected void applyArmor(DamageContainer damage) {
        // Nothing here (But love by daoge)
    }

    protected void applyAttacker(DamageContainer damage) {
        if (damage.getAttacker() instanceof Entity attacker) {
            if (attacker instanceof EntityLivingComponent living) {
                var strengthLevel = living.getEffectLevel(EffectTypes.STRENGTH);
                if (strengthLevel > 0) {
                    damage.updateFinalDamage(d -> {
                        var pow = Math.pow(1.3, strengthLevel);
                        return (float) (d * pow + ((pow - 1) / 0.3));
                    });
                }

                var weaknessLevel = living.getEffectLevel(EffectTypes.WEAKNESS);
                if (weaknessLevel > 0) {
                    damage.updateFinalDamage(d -> {
                        var pow = Math.pow(0.8, weaknessLevel);
                        return (float) (d * pow + ((pow - 1) / 0.4));
                    });
                }
            }

            if (attacker instanceof ContainerHolder holder && holder.hasContainer(ContainerType.INVENTORY)) {
                var item = holder.getContainer(ContainerType.INVENTORY).getItemInHand();
                var sharpnessLevel = item.getEnchantmentLevel(EnchantmentTypes.SHARPNESS);
                if (sharpnessLevel > 0) {
                    damage.updateFinalDamage(d -> d + sharpnessLevel * 1.25f);
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

    @Override
    public boolean canBeAttacked(DamageContainer damage) {
        if (!thisEntity.isAlive()) {
            return false;
        }

        // Fire resistance effect
        if (hasFireDamage() &&
            (damage.getDamageType() == DamageType.FIRE ||
             damage.getDamageType() == DamageType.LAVA ||
             damage.getDamageType() == DamageType.FIRE_TICK)
        ) return false;

        var event = new CEntityTryDamageEvent(damage, true);
        manager.callEvent(event);
        return event.isCanAttack();
    }

    @Override
    public boolean hasFallDamage() {
        return (physicsComponent != null && physicsComponent.hasGravity()) ||
               (!hasEffect(EffectTypes.LEVITATION) && !hasEffect(EffectTypes.SLOW_FALLING)) ||
               thisEntity.getWorld().getWorldData().<Boolean>getGameRuleValue(GameRule.FALL_DAMAGE);
    }

    @Override
    public boolean hasFireDamage() {
        return !hasEffect(EffectTypes.FIRE_RESISTANCE) &&
               !isFireproof() &&
               thisEntity.getWorld().getWorldData().<Boolean>getGameRuleValue(GameRule.FIRE_DAMAGE);
    }

    @Override
    public boolean hasDrowningDamage() {
        return !hasEffect(EffectTypes.WATER_BREATHING);
    }

    @Override
    public boolean setOnFireTicks(int newOnFireTicks) {
        if (this.isFireproof()) {
            return false;
        }

        if (this.onFireTicks > 0 && newOnFireTicks <= 0) {
            thisEntity.setFlag(EntityFlag.ON_FIRE, false);
        } else if (this.onFireTicks <= 0 && newOnFireTicks > 0) {
            thisEntity.setFlag(EntityFlag.ON_FIRE, true);
            // The first tick of fire damage is applied immediately
            attack(DamageContainer.fireTick(1));
        }
        this.onFireTicks = newOnFireTicks;

        return true;
    }

    @Override
    public Map<EffectType, EffectInstance> getAllEffects() {
        return Collections.unmodifiableMap(effects);
    }

    @Override
    public boolean hasEffect(EffectType effectType) {
        return effects.containsKey(effectType);
    }

    @Override
    public int getEffectLevel(EffectType effectType) {
        var effect = effects.get(effectType);
        return effect == null ? 0 : effect.getLevel();
    }

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
        if (old != null && old.getType() != effectInstance.getType()) {
            old.getType().onRemove(thisEntity, old);
            effectInstance.getType().onAdd(thisEntity, effectInstance);
        }

        sendEffects(effectInstance, old);
        if (old == null) {
            syncVisibleEffects();
        }

        return true;
    }

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
        syncVisibleEffects();
    }

    protected void sendEffects(EffectInstance newEffect, EffectInstance oldEffect) {
        thisEntity.forEachViewers(viewer -> viewer.viewEntityEffectChange(thisEntity, newEffect, oldEffect));
    }

    protected void syncVisibleEffects() {
        long visibleEffects = 0;
        for (var effect : this.effects.values()) {
            if (!effect.isVisible()) {
                continue;
            }

            visibleEffects = (visibleEffects << 7) | ((long) effect.getType().getId() << 1) | (effect.isAmbient() ? 1 : 0);
        }

        thisEntity.setData(EntityData.VISIBLE_MOB_EFFECTS, visibleEffects);
    }

    @Override
    public void removeAllEffects() {
        // Prevent ConcurrentModificationException
        for (EffectType effectType : this.effects.keySet().toArray(EffectType[]::new)) {
            removeEffect(effectType);
        }
    }

    @EventHandler
    protected void onTick(CEntityTickEvent event) {
        tickFire();
        tickBreathe();
        tickEffects();
    }

    protected void tickFire() {
        if (this.onFireTicks <= 0) {
            return;
        }

        // Do not do onFireTicks-- directly, because we also
        // need to update the ON_FIRE flag of the entity, and
        // this method will update the flag.
        this.setOnFireTicks(onFireTicks - 1);
        if (this.onFireTicks % 20 == 0) {
            attack(DamageContainer.fireTick(1));
        }
    }

    protected void tickBreathe() {
        if (!thisEntity.hasData(EntityData.AIR_SUPPLY)) {
            return;
        }

        short airSupply = thisEntity.getData(EntityData.AIR_SUPPLY);
        short airSupplyMax = thisEntity.getData(EntityData.AIR_SUPPLY_MAX);
        short newAirSupply = airSupply;
        if (!canBreathe()) {
            thisEntity.setFlag(EntityFlag.BREATHING, false);
            newAirSupply = (short) (airSupply - 1);
            if (newAirSupply <= -20) {
                if (hasDrowningDamage()) {
                    attack(DamageContainer.drown(2));
                }
                newAirSupply = 0;
            }
        } else if (airSupply < airSupplyMax) {
            thisEntity.setFlag(EntityFlag.BREATHING, true);
            newAirSupply = (short) (airSupply + 4);
        }
        if (airSupply != newAirSupply) {
            thisEntity.setData(EntityData.AIR_SUPPLY, newAirSupply);
        }
    }

    protected boolean canBreathe() {
        return hasEffect(EffectTypes.WATER_BREATHING) || hasEffect(EffectTypes.CONDUIT_POWER) || !thisEntity.isEyesInWater();
    }

    protected void tickEffects() {
        if (this.effects.isEmpty()) {
            return;
        }

        for (var effect : this.effects.values().toArray(EffectInstance[]::new)) {
            effect.setDuration(effect.getDuration() - 1);
            effect.getType().onTick(thisEntity, effect);
            if (effect.getDuration() <= 0) {
                removeEffect(effect.getType());
            }
        }
    }

    @EventHandler
    protected void onSaveNBT(CEntitySaveNBTEvent event) {
        var nbt = event.getNbt();
        nbt.putShort("Fire", (short) onFireTicks);
        if (!effects.isEmpty()) {
            nbt.putList(TAG_ACTIVE_EFFECTS, NbtType.COMPOUND, effects.values().stream().map(EffectInstance::saveNBT).toList());
        }
    }

    @EventHandler
    protected void onLoadNBT(CEntityLoadNBTEvent event) {
        var nbt = event.getNbt();
        nbt.listenForShort("Fire", s -> this.onFireTicks = s);
        nbt.listenForList(TAG_ACTIVE_EFFECTS, NbtType.COMPOUND, activeEffects -> {
            for (NbtMap activeEffect : activeEffects) {
                var effectInstance = EffectInstance.fromNBT(activeEffect);
                addEffect(effectInstance);
            }
        });
    }

    @EventHandler
    protected void onFall(CEntityFallEvent event) {
        if (!hasFallDamage()) {
            return;
        }

        // physics component won't be null here, because CEntityFallEvent is called in physics component
        var blockStateStandingOn = physicsComponent.getBlockStateStandingOn();
        double rawDamage = (event.getFallDistance() - 3) - getEffectLevel(EffectTypes.JUMP_BOOST);
        var damage = Math.round(rawDamage * (1 - blockStateStandingOn.getBehavior().getFallDamageReductionFactor()));
        if (damage > 0) {
            attack(DamageContainer.fall(damage));
        }
    }

    @EventHandler
    protected void onDie(CEntityDieEvent event) {
        setOnFireTicks(0);
        this.effects.values().forEach(effect -> effect.getType().onEntityDies(thisEntity, effect));
        removeAllEffects();
    }

    @EventHandler
    protected void onInitMetadata(CEntityInitMetadataEvent $) {
        thisEntity.setFlag(EntityFlag.BREATHING, true);
        thisEntity.setData(EntityData.AIR_SUPPLY, (short) 300);
        thisEntity.setData(EntityData.AIR_SUPPLY_MAX, (short) 300);
    }
}
