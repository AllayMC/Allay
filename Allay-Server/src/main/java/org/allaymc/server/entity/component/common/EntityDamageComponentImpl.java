package org.allaymc.server.entity.component.common;

import lombok.Getter;
import org.allaymc.api.component.annotation.ComponentEventListener;
import org.allaymc.api.component.annotation.ComponentIdentifier;
import org.allaymc.api.component.annotation.ComponentedObject;
import org.allaymc.api.component.annotation.Dependency;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.component.common.EntityAttributeComponent;
import org.allaymc.api.entity.component.common.EntityBaseComponent;
import org.allaymc.api.entity.component.common.EntityDamageComponent;
import org.allaymc.api.entity.component.event.EntityFallEvent;
import org.allaymc.api.entity.damage.DamageContainer;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.identifier.Identifier;
import org.allaymc.api.world.gamerule.GameRule;
import org.cloudburstmc.protocol.bedrock.data.GameType;
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
    @ComponentedObject
    protected Entity entity;
    @Getter
    protected DamageContainer lastDamage;
    @Getter
    protected long lastDamageTime = 0;

    @Override
    public boolean attack(DamageContainer damage) {
        var currentTime = baseComponent.getWorld().getTick();
        if (lastDamage != null && currentTime - lastDamageTime <= lastDamage.getCoolDown()) return false;

        lastDamage = damage;
        lastDamageTime = currentTime;

        damage.updateFinalDamage(d -> d * (damage.isCritical() ? 1.5f : 1f));

        attributeComponent.setHealth(attributeComponent.getHealth() - damage.getFinalDamage());
        baseComponent.sendEntityEvent(EntityEventType.HURT, 2);

        if (damage.isCritical()) baseComponent.sendAnimation(AnimatePacket.Action.CRITICAL_HIT);
        if (damage.getAttacker() != null) baseComponent.knockback(damage.getAttacker().getLocation());
        return true;
    }

    @Override
    public boolean hasFallDamage() {
        return baseComponent.hasGravity();
    }

    @ComponentEventListener
    private void onEntityFall(EntityFallEvent event) {
        if (!hasFallDamage()) return;
        if (!((boolean) baseComponent.getWorld().getWorldData().getGameRule(GameRule.FALL_DAMAGE))) return;

        var damage = event.fallDistance() - 3;
        if (damage > 0) this.attack(new DamageContainer(entity, DamageContainer.DamageType.FALL, damage));
    }
}
