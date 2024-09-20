package org.allaymc.server.entity.component;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.component.interfaces.ComponentManager;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.component.attribute.Attribute;
import org.allaymc.api.entity.component.attribute.AttributeType;
import org.allaymc.api.entity.component.attribute.EntityAttributeComponent;
import org.allaymc.api.eventbus.EventHandler;
import org.allaymc.api.eventbus.event.entity.EntityHealthChangeEvent;
import org.allaymc.server.component.annotation.Identifier;
import org.allaymc.server.component.annotation.ComponentedObject;
import org.allaymc.server.component.annotation.Manager;
import org.allaymc.server.entity.component.event.CEntityAttributeChangeEvent;
import org.allaymc.server.entity.component.event.CEntityLoadNBTEvent;
import org.allaymc.server.entity.component.event.CEntitySaveNBTEvent;
import org.cloudburstmc.nbt.NbtType;

import java.util.Collection;
import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;

import static java.lang.Math.max;
import static java.lang.Math.min;

/**
 * @author daoge_cmd
 */
@Slf4j
public class EntityAttributeComponentImpl implements EntityAttributeComponent {

    @Identifier
    public static final org.allaymc.api.utils.Identifier IDENTIFIER = new org.allaymc.api.utils.Identifier("minecraft:entity_attribute_component");

    protected final Map<AttributeType, Attribute> attributes = new EnumMap<>(AttributeType.class);

    @ComponentedObject
    protected Entity thisEntity;
    @Manager
    protected ComponentManager manager;

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

    public EntityAttributeComponentImpl(AttributeType[] attributeTypes, Attribute... attributes) {
        for (AttributeType attributeType : attributeTypes) {
            addAttribute(attributeType);
        }
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
            // NOTICE: Caller should call sendAttributesToClient() to send attributes to client if he want
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
    public void setAttributeValue(AttributeType attributeType, float value) {
        var attribute = this.attributes.get(attributeType);
        if (attribute == null) {
            throw unsupportAttributeTypeException(attributeType);
        }
        attribute.setCurrentValue(value);
        manager.callEvent(CEntityAttributeChangeEvent.INSTANCE);
    }

    @Override
    public void setAttribute(Attribute attribute) {
        var attributeType = AttributeType.byKey(attribute.getKey());
        if (!this.attributes.containsKey(attributeType)) {
            throw unsupportAttributeTypeException(attributeType);
        }
        this.attributes.put(AttributeType.byKey(attribute.getKey()), attribute);
        manager.callEvent(CEntityAttributeChangeEvent.INSTANCE);
    }

    @Override
    public float getAttributeValue(AttributeType attributeType) {
        var attribute = this.getAttribute(attributeType);
        if (attribute == null) {
            throw unsupportAttributeTypeException(attributeType);
        }
        return attribute.getCurrentValue();
    }

    @Override
    public void setHealth(float value) {
        if (!supportHealth()) {
            // Check if health is supported by the entity here
            // To make sure that if health is not supported, event won't be called
            throw unsupportAttributeTypeException(AttributeType.HEALTH);
        }
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

        setAttributeValue(AttributeType.HEALTH, event.getNewHealth());
    }

    protected IllegalArgumentException unsupportAttributeTypeException(AttributeType attributeType) {
        return new IllegalArgumentException("Attribute type " + attributeType + " is not found in entity " + thisEntity.getEntityType().getIdentifier());
    }
}
