package org.allaymc.server.entity.component.common;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.allaymc.api.component.annotation.ComponentIdentifier;
import org.allaymc.api.component.annotation.ComponentedObject;
import org.allaymc.api.component.annotation.Dependency;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.attribute.Attribute;
import org.allaymc.api.entity.attribute.AttributeType;
import org.allaymc.api.entity.component.common.EntityAttributeComponent;
import org.allaymc.api.entity.component.event.EntityLoadNBTEvent;
import org.allaymc.api.entity.component.event.EntitySaveNBTEvent;
import org.allaymc.api.entity.component.player.EntityPlayerNetworkComponent;
import org.allaymc.api.eventbus.EventHandler;
import org.allaymc.api.utils.Identifier;
import org.cloudburstmc.nbt.NbtType;
import org.cloudburstmc.protocol.bedrock.packet.UpdateAttributesPacket;

import java.util.Collection;
import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import static java.lang.Math.max;
import static java.lang.Math.min;

/**
 * Allay Project 2023/7/9
 *
 * @author daoge_cmd
 */
@ToString
@EqualsAndHashCode
public class EntityAttributeComponentImpl implements EntityAttributeComponent {

    @ComponentIdentifier
    public static final Identifier IDENTIFIER = new Identifier("minecraft:entity_attribute_component");

    protected final Map<AttributeType, Attribute> attributes = new EnumMap<>(AttributeType.class);

    @ComponentedObject
    protected Entity entity;
    @Dependency(soft = true)
    protected EntityPlayerNetworkComponent networkComponent;

    public EntityAttributeComponentImpl(List<AttributeType> attributeTypes) {
        attributeTypes.forEach(this::addAttribute);
    }

    @EventHandler
    private void onLoadNBT(EntityLoadNBTEvent event) {
        var nbt = event.getNbt();
        nbt.listenForList("Attributes", NbtType.COMPOUND, attributesNbt -> {
            attributesNbt.forEach(compound -> {
                var attribute = Attribute.fromNBT(compound);
                attributes.put(AttributeType.byKey(attribute.getKey()), attribute);
            });
            sendAttributesToClient();
        });
    }

    @EventHandler
    private void onSaveNBT(EntitySaveNBTEvent event) {
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
        Attribute attribute = this.attributes.get(attributeType);
        if (attribute != null) attribute.setCurrentValue(value);
        sendAttributesToClient();
    }

    @Override
    public void setAttribute(Attribute attribute) {
        this.attributes.put(AttributeType.byKey(attribute.getKey()), attribute);
        sendAttributesToClient();
    }

    @Override
    public float getAttributeValue(AttributeType attributeType) {
        return this.getAttribute(attributeType).getCurrentValue();
    }

    @Override
    public void sendAttributesToClient() {
        if (networkComponent == null) return;
        var updateAttributesPacket = new UpdateAttributesPacket();
        updateAttributesPacket.setRuntimeEntityId(entity.getRuntimeId());
        attributes.values().forEach(attribute -> updateAttributesPacket.getAttributes().add(attribute.toNetwork()));
        updateAttributesPacket.setTick(entity.getWorld().getTick());
        networkComponent.sendPacket(updateAttributesPacket);
    }

    @Override
    public void setHealth(float value) {
        setAttribute(AttributeType.HEALTH, max(0, min(value, this.getMaxHealth())));
    }

    @Override
    public void setMaxHealth(float value) {
        var maxHealth = this.getAttribute(AttributeType.HEALTH);
        maxHealth.setMaxValue(value);
        this.setAttribute(maxHealth);
    }
}
