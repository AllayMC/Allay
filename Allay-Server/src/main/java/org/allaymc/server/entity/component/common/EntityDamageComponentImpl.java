package org.allaymc.server.entity.component.common;

import lombok.Getter;
import org.allaymc.api.component.annotation.ComponentIdentifier;
import org.allaymc.api.component.annotation.ComponentedObject;
import org.allaymc.api.component.annotation.Dependency;
import org.allaymc.api.component.annotation.Manager;
import org.allaymc.api.component.interfaces.ComponentManager;
import org.allaymc.api.data.VanillaEffectTypes;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.component.common.EntityAttributeComponent;
import org.allaymc.api.entity.component.common.EntityBaseComponent;
import org.allaymc.api.entity.component.common.EntityDamageComponent;
import org.allaymc.api.entity.component.event.CEntityDamageEvent;
import org.allaymc.api.entity.component.event.CEntityFallEvent;
import org.allaymc.api.entity.component.player.EntityPlayerHungerComponent;
import org.allaymc.api.entity.damage.DamageContainer;
import org.allaymc.api.eventbus.EventHandler;
import org.allaymc.api.utils.Identifier;
import org.allaymc.api.world.gamerule.GameRule;
import org.cloudburstmc.protocol.bedrock.data.entity.EntityEventType;
import org.cloudburstmc.protocol.bedrock.packet.AnimatePacket;

/**
 * Allay Project 2024/1/12
 *
 * @author daoge_cmd
 */
public class EntityDamageComponentImpl implements EntityDamageComponent {
    @ComponentIdentifier
    public static final Identifier IDENTIFIER = new Identifier("minecraft:entity_damage_component");

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
        if (checkCoolDown(damage)) return false;

        applyAttacker(damage);
        applyVictim(damage);
        applyDamage(damage);

        return true;
    }

    protected void applyDamage(DamageContainer damage) {
        attributeComponent.setHealth(attributeComponent.getHealth() - damage.getFinalDamage());
        baseComponent.applyEntityEvent(EntityEventType.HURT, 2);
        if (damage.isCritical()) baseComponent.applyAnimation(AnimatePacket.Action.CRITICAL_HIT);

        if (thisEntity instanceof EntityPlayerHungerComponent hungerComponent) {
            hungerComponent.exhaust(0.1f);
        }

        var attacker = damage.getAttacker();
        if (attacker == null) return;

        if (attacker instanceof EntityPlayerHungerComponent hungerComponent) {
            hungerComponent.exhaust(0.1f);
        }

        if (damage.hasCustomKnockback()) {
            baseComponent.knockback(attacker.getLocation(), damage.getCustomKnockback());
        } else {
            baseComponent.knockback(attacker.getLocation());
        }
    }

    protected boolean checkCoolDown(DamageContainer damage) {
        var currentTime = baseComponent.getWorld().getTick();
        if (lastDamage != null && currentTime - lastDamageTime <= lastDamage.getCoolDown()) return true;

        lastDamage = damage;
        lastDamageTime = currentTime;
        return false;
    }

    protected void applyVictim(DamageContainer damage) {
        applyEffects(damage);
        applyArmor(damage);
    }

    protected void applyEffects(DamageContainer damage) {
        // Damage absorption
        var absorption = baseComponent.getAbsorption();
        if (absorption > 0) {
            baseComponent.setAbsorption(Math.max(0, absorption - damage.getFinalDamage()));
            damage.updateFinalDamage(d -> Math.max(0, d - absorption));
        }
    }

    protected void applyArmor(DamageContainer damage) {
        // Nothing here
    }

    protected void applyAttacker(DamageContainer damage) {
        var attacker = damage.getAttacker();

        if (attacker != null) {
            var strengthLevel = attacker.getEffectLevel(VanillaEffectTypes.STRENGTH);
            if (strengthLevel > 0) {
                damage.updateFinalDamage(d -> {
                    var pow = Math.pow(1.3, strengthLevel);
                    return (float) (d * pow + ((pow - 1) / 0.3));
                });
            }

            var weaknessLevel = attacker.getEffectLevel(VanillaEffectTypes.WEAKNESS);
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
        var event = new CEntityDamageEvent(damage, true);
        manager.callEvent(event);
        return event.isCanAttack();
    }

    @Override
    public boolean hasFallDamage() {
        return baseComponent.hasGravity() ||
               !baseComponent.hasEffect(VanillaEffectTypes.SLOW_FALLING) ||
               (boolean) baseComponent.getWorld().getWorldData().getGameRule(GameRule.FALL_DAMAGE);
    }

    @EventHandler
    private void onEntityFall(CEntityFallEvent event) {
        if (!hasFallDamage()) return;

        var damage = Math.round((event.getFallDistance() - 3) - baseComponent.getEffectLevel(VanillaEffectTypes.JUMP_BOOST));
        if (damage > 0) this.attack(DamageContainer.fall(damage));
    }
}
