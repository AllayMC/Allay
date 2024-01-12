package org.allaymc.server.entity.component.common;

import com.google.common.base.Preconditions;
import org.allaymc.api.component.annotation.ComponentIdentifier;
import org.allaymc.api.component.annotation.ComponentedObject;
import org.allaymc.api.component.annotation.Dependency;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.attribute.Attribute;
import org.allaymc.api.entity.attribute.AttributeType;
import org.allaymc.api.entity.component.common.EntityAttributeComponent;
import org.allaymc.api.entity.component.player.EntityPlayerNetworkComponent;
import org.allaymc.api.identifier.Identifier;
import org.cloudburstmc.protocol.bedrock.packet.UpdateAttributesPacket;

import java.util.*;

/**
 * Allay Project 2023/7/9
 *
 * @author daoge_cmd
 */
public class EntityAttributeComponentImpl implements EntityAttributeComponent {

    @ComponentIdentifier
    public static final Identifier IDENTIFIER = new Identifier("minecraft:entity_attribute_component");
    @ComponentedObject
    protected Entity entity;
    @Dependency(soft = true)
    protected EntityPlayerNetworkComponent networkComponent;
    protected final Map<AttributeType, Attribute> attributes = new EnumMap<>(AttributeType.class);

    public EntityAttributeComponentImpl(List<AttributeType> attributeTypes) {
        attributeTypes.forEach(this::addAttribute);
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
        Attribute attribute = this.attributes.get(attributeType);
        if (attribute != null)
            attribute.setCurrentValue(value);
        sendAttributesIfIsPlayer();
    }

    @Override
    public void setAttribute(Attribute attribute) {
        this.attributes.put(AttributeType.valueOf(attribute.getKey()), attribute);
        sendAttributesIfIsPlayer();
    }

    @Override
    public float getAttributeValue(AttributeType attributeType) {
        return this.getAttribute(attributeType).getCurrentValue();
    }

    @Override
    public void sendAttributesIfIsPlayer() {
        if (networkComponent == null) return;
        var updateAttributesPacket = new UpdateAttributesPacket();
        updateAttributesPacket.setRuntimeEntityId(entity.getUniqueId());
        for (Attribute attribute : attributes.values()) {
            updateAttributesPacket.getAttributes().add(attribute.toNetwork());
        }
        updateAttributesPacket.setTick(entity.getWorld().getTick());
        networkComponent.sendPacket(updateAttributesPacket);
    }

    @Override
    public void setHealth(float value) {
        Preconditions.checkArgument(value >= 0);
        setAttribute(AttributeType.HEALTH, value);
    }
}
