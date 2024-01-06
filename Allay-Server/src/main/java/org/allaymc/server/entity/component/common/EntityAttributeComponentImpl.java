package org.allaymc.server.entity.component.common;

import org.allaymc.api.component.annotation.ComponentIdentifier;
import org.allaymc.api.component.annotation.ComponentedObject;
import org.allaymc.api.entity.attribute.Attribute;
import org.allaymc.api.entity.attribute.AttributeType;
import org.allaymc.api.entity.component.common.EntityAttributeComponent;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.identifier.Identifier;
import com.google.common.collect.Lists;
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
    protected EntityPlayer player;
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
    }

    @Override
    public void setAttribute(Attribute attribute) {
        this.attributes.put(AttributeType.valueOf(attribute.getKey()), attribute);
    }

    @Override
    public float getAttributeValue(AttributeType attributeType) {
        return this.getAttribute(attributeType).getCurrentValue();
    }

    @Override
    public void sendAttributesToClient() {
        var updateAttributesPacket = new UpdateAttributesPacket();
        updateAttributesPacket.setRuntimeEntityId(player.getUniqueId());
        for (Attribute attribute : player.getAttributes()) {
            updateAttributesPacket.getAttributes().add(attribute.toNetwork());
        }
        updateAttributesPacket.setTick(player.getWorld().getTick());
        player.sendPacket(updateAttributesPacket);
    }
}
