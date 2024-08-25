package org.allaymc.server.entity.component;

import org.allaymc.api.component.annotation.ComponentIdentifier;
import org.allaymc.api.component.annotation.ComponentedObject;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.attribute.Attribute;
import org.allaymc.api.entity.attribute.AttributeType;
import org.allaymc.api.entity.component.common.EntityAttributeComponent;
import org.allaymc.api.entity.component.event.CEntityLoadNBTEvent;
import org.allaymc.api.entity.component.event.CEntitySaveNBTEvent;
import org.allaymc.api.eventbus.EventHandler;
import org.allaymc.api.eventbus.event.entity.EntityHealthChangeEvent;
import org.allaymc.api.utils.Identifier;
import org.cloudburstmc.nbt.NbtType;

import java.util.Collection;
import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;

import static java.lang.Math.max;
import static java.lang.Math.min;

/**
 * Allay Project 2023/7/9
 *
 * @author daoge_cmd
 */
public class EntityAttributeComponentImpl implements EntityAttributeComponent {

    @ComponentIdentifier
    public static final Identifier IDENTIFIER = new Identifier("minecraft:entity_attribute_component");

    protected final Map<AttributeType, Attribute> attributes = new EnumMap<>(AttributeType.class);

    @ComponentedObject
    protected Entity thisEntity;

    public EntityAttributeComponentImpl(AttributeType... attributeTypes) {
        for (AttributeType attributeType : attributeTypes) {
            addAttribute(attributeType);
        }
    }

    public EntityAttributeComponentImpl(Attribute... attributes) {
        for (Attribute attribute : attributes) {
            this.attributes.put(AttributeType.byKey(attribute.getKey()), attribute);
        }
    }

    @EventHandler
    protected void onLoadNBT(CEntityLoadNBTEvent event) {
        var nbt = event.getNbt();
        nbt.listenForList("Attributes", NbtType.COMPOUND, attributesNbt -> {
            attributesNbt.forEach(attributeNbt -> {
                var attribute = Attribute.fromNBT(attributeNbt);
                attributes.put(AttributeType.byKey(attribute.getKey()), attribute);
            });

            sendAttributesToClient();
        });
    }

    @EventHandler
    protected void onSaveNBT(CEntitySaveNBTEvent event) {
        event.getNbt().putList(
                "Attributes",
                NbtType.COMPOUND,
                saveAttributes()
        );
    }

    @Override
    public void addAttribute(AttributeType attributeType) {
        this.attributes.put(attributeType, attributeType.newAttributeInstance());
    }

    @Override
    public Collection<Attribute> getAttributes() {
        return Collections.unmodifiableCollection(attributes.values());
    }

    @Override
    public Attribute getAttribute(AttributeType attributeType) {
        return attributes.get(attributeType);
    }

    @Override
    public void setAttribute(AttributeType attributeType, float value) {
        var attribute = this.attributes.get(attributeType);
        if (attribute != null) attribute.setCurrentValue(value);
        sendAttributesToClient();
    }

    @Override
    public void setAttribute(Attribute attribute) {
        this.attributes.put(AttributeType.byKey(attribute.getKey()), attribute);
        sendAttributesToClient();
    }

    @Override
    public void setHealth(float value) {
        if (value > 0 && value < 1) {
            // Client will think he is dead if the health is less than 1
            // But server doesn't think so, which would causes bug
            // So we need to set the health to 1 if it's less than 1 and bigger than 0
            value = 1;
        } else {
            value = max(0, min(value, this.getMaxHealth()));
        }

        var event = new EntityHealthChangeEvent(thisEntity, getHealth(), value);
        event.call();
        if (event.isCancelled()) return;

        setAttribute(AttributeType.HEALTH, event.getNewHealth());
    }

}
