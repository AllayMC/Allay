package org.allaymc.server.entity.component;

import lombok.Getter;
import org.allaymc.api.component.interfaces.ComponentManager;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.component.EntityBaseComponent;
import org.allaymc.api.entity.component.EntityDamageComponent;
import org.allaymc.api.entity.component.attribute.AttributeType;
import org.allaymc.api.entity.component.attribute.EntityAttributeComponent;
import org.allaymc.api.entity.damage.DamageContainer;
import org.allaymc.api.entity.effect.type.EffectTypes;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.EventHandler;
import org.allaymc.api.eventbus.event.entity.EntityDamageEvent;
import org.allaymc.api.item.enchantment.type.EnchantmentTypes;
import org.allaymc.api.utils.Identifier;
import org.allaymc.api.world.gamerule.GameRule;
import org.allaymc.server.component.ComponentClass;
import org.allaymc.server.component.annotation.ComponentObject;
import org.allaymc.server.component.annotation.Dependency;
import org.allaymc.server.component.annotation.Manager;
import org.allaymc.server.entity.component.event.*;
import org.cloudburstmc.protocol.bedrock.data.entity.EntityEventType;
import org.cloudburstmc.protocol.bedrock.data.entity.EntityFlag;
import org.cloudburstmc.protocol.bedrock.packet.AnimatePacket;

/**
 * @author daoge_cmd
 */
public class EntityDamageComponentImpl implements EntityDamageComponent {
    @Identifier.Component
    public static final Identifier IDENTIFIER = new Identifier("minecraft:entity_damage_component");

    @Dependency
    protected EntityBaseComponent baseComponent;
    @Dependency
    protected EntityAttributeComponent attributeComponent;
    @Manager
    protected ComponentManager manager;

    @ComponentObject
    protected Entity thisEntity;

    @Getter
    protected DamageContainer lastDamage;
    @Getter
    protected long lastDamageTime = 0;
    @Getter
    protected int onFireTicks;

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
        attributeComponent.setHealth(attributeComponent.getHealth() - damage.getFinalDamage());
        baseComponent.applyEntityEvent(EntityEventType.HURT, 2);

        if (damage.isCritical()) {
            baseComponent.applyAction(AnimatePacket.Action.CRITICAL_HIT);
        }

        manager.callEvent(CEntityAfterDamageEvent.INSTANCE);

        Entity attacker = damage.getAttacker();
        if (attacker == null) return;

        ((ComponentClass) attacker).getManager().callEvent(CEntityAttackEvent.INSTANCE);

        if (damage.hasCustomKnockback()) {
            baseComponent.knockback(attacker.getLocation(), damage.getCustomKnockback());
        } else {
            if (attacker instanceof EntityPlayer player) {
                // TODO: Zombies and other creatures that can hold weapons need to be considered
                var kb = EntityBaseComponent.DEFAULT_KNOCKBACK;
                var kbEnchantmentLevel = player.getItemInHand().getEnchantmentLevel(EnchantmentTypes.KNOCKBACK);
                if (kbEnchantmentLevel != 0) {
                    kb += kbEnchantmentLevel * 0.1f;
                }
                baseComponent.knockback(attacker.getLocation(), kb);
            } else {
                baseComponent.knockback(attacker.getLocation());
            }
        }
    }

    protected boolean checkAndUpdateCoolDown(DamageContainer damage, boolean forceToUpdate) {
        var currentTime = baseComponent.getWorld().getTick();
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

        thisEntity.getAllEffects().values().forEach(effect ->
                effect.getType().onEntityDamage(thisEntity, effect, lastDamage)
        );
    }

    protected void applyArmor(DamageContainer damage) {
        // Nothing here (But love by daoge)
    }

    protected void applyAttacker(DamageContainer damage) {
        if (damage.getAttacker() instanceof Entity attacker) {
            var strengthLevel = attacker.getEffectLevel(EffectTypes.STRENGTH);
            if (strengthLevel > 0) {
                damage.updateFinalDamage(d -> {
                    var pow = Math.pow(1.3, strengthLevel);
                    return (float) (d * pow + ((pow - 1) / 0.3));
                });
            }

            var weaknessLevel = attacker.getEffectLevel(EffectTypes.WEAKNESS);
            if (weaknessLevel > 0) {
                damage.updateFinalDamage(d -> {
                    var pow = Math.pow(0.8, weaknessLevel);
                    return (float) (d * pow + ((pow - 1) / 0.4));
                });
            }
        }

        // TODO: Sharpness enchantment

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
            (damage.getDamageType() == DamageContainer.DamageType.FIRE ||
             damage.getDamageType() == DamageContainer.DamageType.LAVA ||
             damage.getDamageType() == DamageContainer.DamageType.FIRE_TICK)
        ) return false;

        var event = new CEntityTryDamageEvent(damage, true);
        manager.callEvent(event);
        return event.isCanAttack();
    }

    @Override
    public boolean hasFallDamage() {
        return baseComponent.hasGravity() ||
               (!baseComponent.hasEffect(EffectTypes.LEVITATION) && !baseComponent.hasEffect(EffectTypes.SLOW_FALLING)) ||
               baseComponent.getWorld().getWorldData().<Boolean>getGameRuleValue(GameRule.FALL_DAMAGE);
    }

    @Override
    public boolean hasFireDamage() {
        return !thisEntity.hasEffect(EffectTypes.FIRE_RESISTANCE) &&
               !isFireproof() &&
               baseComponent.getWorld().getWorldData().<Boolean>getGameRuleValue(GameRule.FIRE_DAMAGE);
    }

    @Override
    public boolean hasDrowningDamage() {
        return !thisEntity.hasEffect(EffectTypes.WATER_BREATHING);
    }

    @Override
    public boolean setOnFireTicks(int newOnFireTicks) {
        if (!hasFireDamage()) {
            return false;
        }

        if (this.onFireTicks > 0 && newOnFireTicks <= 0) {
            baseComponent.setAndSendEntityFlag(EntityFlag.ON_FIRE, false);
        } else if (this.onFireTicks <= 0 && newOnFireTicks > 0) {
            baseComponent.setAndSendEntityFlag(EntityFlag.ON_FIRE, true);
            // The first tick of fire damage is applied immediately
            attack(DamageContainer.fireTick(1));
        }
        this.onFireTicks = newOnFireTicks;

        return true;
    }

    @EventHandler
    protected void onTick(CEntityTickEvent event) {
        tickFire();
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

    @EventHandler
    protected void onSaveNBT(CEntitySaveNBTEvent event) {
        event.getNbt().putShort("Fire", (short) onFireTicks);
    }

    @EventHandler
    protected void onLoadNBT(CEntityLoadNBTEvent event) {
        this.onFireTicks = event.getNbt().getShort("Fire");
    }

    @EventHandler
    protected void onFall(CEntityFallEvent event) {
        if (!hasFallDamage()) {
            return;
        }

        var blockStateStandingOn = thisEntity.getBlockStateStandingOn();
        double rawDamage = (event.getFallDistance() - 3) - baseComponent.getEffectLevel(EffectTypes.JUMP_BOOST);
        var damage = Math.round(rawDamage * (1 - blockStateStandingOn.getBehavior().getFallDamageReductionFactor()));
        if (damage > 0) {
            attack(DamageContainer.fall(damage));
        }
    }

    @EventHandler
    protected void onDrown(CEntityDrownEvent event) {
        if (!hasDrowningDamage()) {
            return;
        }

        attack(DamageContainer.drown(2));
    }

    @EventHandler
    protected void onDie(CEntityDieEvent event) {
        setOnFireTicks(0);
    }
}
