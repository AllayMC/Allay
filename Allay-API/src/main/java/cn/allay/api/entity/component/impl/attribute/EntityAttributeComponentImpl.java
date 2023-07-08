package cn.allay.api.entity.component.impl.attribute;

import cn.allay.api.component.annotation.Impl;
import cn.allay.api.entity.attribute.Attribute;
import cn.allay.api.entity.attribute.AttributeType;
import cn.allay.api.entity.component.EntityComponentImpl;
import cn.allay.api.identifier.Identifier;
import com.google.common.collect.Lists;

import java.util.*;

/**
 * Allay Project 2023/7/9
 *
 * @author daoge_cmd
 */
public class EntityAttributeComponentImpl implements EntityAttributeComponent, EntityComponentImpl {

    public static final Identifier IDENTIFIER = new Identifier("minecraft:entity_attribute_component");

    protected final Map<AttributeType, Attribute> attributes = new EnumMap<>(AttributeType.class);

    public EntityAttributeComponentImpl(List<AttributeType> attributeTypes) {
        attributeTypes.forEach(this::addAttribute);
    }

    public static List<AttributeType> basicAttributes() {
        return Lists.newArrayList(
                AttributeType.HEALTH,
                AttributeType.ABSORPTION,
                AttributeType.ATTACK_DAMAGE,
                AttributeType.FOLLOW_RANGE,
                AttributeType.MOVEMENT,
                AttributeType.KNOCKBACK_RESISTENCE
        );
    }

    @Override
    public Identifier getIdentifier() {
        return IDENTIFIER;
    }

    @Override
    @Impl
    public void addAttribute(AttributeType attributeType) {
        this.attributes.put(attributeType, attributeType.getAttribute());
    }

    @Override
    @Impl
    public Collection<Attribute> getAttributes() {
        return Collections.unmodifiableCollection(attributes.values());
    }

    @Override
    @Impl
    public Attribute getAttribute(AttributeType attributeType) {
        return attributes.get(attributeType);
    }

    @Override
    @Impl
    public void setAttributes(AttributeType attributes, float value) {
        Attribute attribute = this.attributes.get(attributes);
        if (attribute != null)
            attribute.setCurrentValue(value);
    }

    @Override
    @Impl
    public float getAttributeValue(AttributeType attributeType) {
        return this.getAttribute(attributeType).getCurrentValue();
    }
}
