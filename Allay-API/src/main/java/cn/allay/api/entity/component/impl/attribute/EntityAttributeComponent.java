package cn.allay.api.entity.component.impl.attribute;

import cn.allay.api.component.annotation.Inject;
import cn.allay.api.entity.attribute.Attribute;
import cn.allay.api.entity.attribute.AttributeType;

import java.util.Collection;

/**
 * Allay Project 2023/7/9
 *
 * @author daoge_cmd
 */
public interface EntityAttributeComponent {
    @Inject
    void addAttribute(AttributeType attributeType);

    @Inject
    Collection<Attribute> getAttributes();

    @Inject
    Attribute getAttribute(AttributeType attributeType);

    @Inject
    void setAttributes(AttributeType attributes, float value);

    @Inject
    float getAttributeValue(AttributeType attributeType);
}
