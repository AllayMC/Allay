package org.allaymc.server.entity.component;

import lombok.Getter;
import org.allaymc.api.component.interfaces.ComponentManager;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.component.EntityBaseComponent;
import org.allaymc.api.entity.component.EntityDamageComponent;
import org.allaymc.api.entity.component.attribute.EntityAttributeComponent;
import org.allaymc.api.entity.damage.DamageContainer;
import org.allaymc.api.entity.effect.type.EffectTypes;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.EventHandler;
import org.allaymc.api.eventbus.event.entity.EntityDamageEvent;
import org.allaymc.api.item.enchantment.type.EnchantmentTypes;
import org.allaymc.api.world.gamerule.GameRule;
import org.allaymc.server.component.annotation.Identifier;
import org.allaymc.server.component.annotation.ComponentedObject;
import org.allaymc.server.component.annotation.Dependency;
import org.allaymc.server.component.annotation.Manager;
import org.allaymc.server.entity.component.event.*;
import org.cloudburstmc.protocol.bedrock.data.entity.EntityEventType;
import org.cloudburstmc.protocol.bedrock.packet.AnimatePacket;

/**
 * @author daoge_cmd
 */
public class EntityDamageComponentImpl implements EntityDamageComponent {
    @Identifier
    public static final org.allaymc.api.utils.Identifier IDENTIFIER = new org.allaymc.api.utils.Identifier("minecraft:entity_damage_component");

    @Dependency
    protected EntityBaseComponent baseComponent;
    @Dependency
    protected EntityAttributeComponent attributeComponent;
    @Manager
    protected ComponentManager manager;

    @ComponentedObject
    protected Entity thisEntity;

    @Getter
    protected DamageContainer lastDamage;
    @Getter
    protected long lastDamageTime = 0;

    @Override
    public boolean attack(DamageContainer damage) {
        if (!canBeAttacked(damage)) return false;
        if (!checkAndUpdateCoolDown(damage)) return false;

        var event = new EntityDamageEvent(thisEntity, damage);
        event.call();
        if (event.isCancelled()) {
            return false;
        }

        applyAttacker(damage);
        applyVictim(damage);
        applyDamage(damage);

        return true;
    }

    protected void applyDamage(DamageContainer damage) {
        attributeComponent.setHealth(attributeComponent.getHealth() - damage.getFinalDamage());
        baseComponent.applyEntityEvent(EntityEventType.HURT, 2);
        if (damage.isCritical()) baseComponent.applyAction(AnimatePacket.Action.CRITICAL_HIT);

        manager.callEvent(CEntityAfterDamageEvent.INSTANCE);

        Entity attacker = damage.getAttacker();
        if (attacker == null) return;

        attacker.getManager().callEvent(CEntityAttackEvent.INSTANCE);

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

    protected boolean checkAndUpdateCoolDown(DamageContainer damage) {
        var currentTime = baseComponent.getWorld().getTick();
        if (lastDamage != null && currentTime - lastDamageTime <= lastDamage.getCoolDown()) return false;

        lastDamage = damage;
        lastDamageTime = currentTime;
        return true;
    }

    protected void applyVictim(DamageContainer damage) {
        applyEffects(damage);
        applyArmor(damage);
    }

    protected void applyEffects(DamageContainer damage) {
        // Damage absorption
        if (attributeComponent.supportAbsorption()) {
            var absorption = attributeComponent.getAbsorption();
            if (absorption > 0) {
                attributeComponent.setAbsorption(Math.max(0, absorption - damage.getFinalDamage()));
                damage.updateFinalDamage(d -> Math.max(0, d - absorption));
            }
        }
    }

    protected void applyArmor(DamageContainer damage) {
        // Nothing here
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

        if (damage.isCritical()) damage.updateFinalDamage(d -> d * 1.5f);
    }

    @Override
    public boolean canBeAttacked(DamageContainer damage) {
        var event = new CEntityTryDamageEvent(damage, true);
        manager.callEvent(event);
        return event.isCanAttack();
    }

    @Override
    public boolean hasFallDamage() {
        return baseComponent.hasGravity() ||
               !baseComponent.hasEffect(EffectTypes.SLOW_FALLING) ||
               (boolean) baseComponent.getWorld().getWorldData().getGameRule(GameRule.FALL_DAMAGE);
    }

    @EventHandler
    protected void onFall(CEntityFallEvent event) {
        if (!hasFallDamage()) return;

        var blockStateStandingOn = thisEntity.getBlockStateStandingOn();
        float rawDamage = (event.getFallDistance() - 3) - baseComponent.getEffectLevel(EffectTypes.JUMP_BOOST);
        var damage = Math.round(rawDamage * (1 - blockStateStandingOn.getBehavior().getFallDamageReductionFactor()));
        if (damage > 0) attack(DamageContainer.fall(damage));
    }

    @EventHandler
    protected void onDrown(CEntityDrownEvent event) {
        attack(DamageContainer.drown(2));
    }
}
