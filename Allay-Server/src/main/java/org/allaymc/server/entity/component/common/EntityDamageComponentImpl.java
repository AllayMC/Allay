package org.allaymc.server.entity.component.common;

import org.allaymc.api.component.annotation.ComponentIdentifier;
import org.allaymc.api.component.annotation.Dependency;
import org.allaymc.api.entity.component.common.EntityAttributeComponent;
import org.allaymc.api.entity.component.common.EntityBaseComponent;
import org.allaymc.api.entity.component.common.EntityDamageComponent;
import org.allaymc.api.entity.damage.DamageContainer;
import org.allaymc.api.identifier.Identifier;

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

    @Override
    public void attack(DamageContainer damage) {
        attributeComponent.setHealth(attributeComponent.getHealth() - damage.getFinalDamage());
    }
}
