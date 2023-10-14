package cn.allay.api.entity.component.attribute;

import cn.allay.api.entity.attribute.Attribute;
import cn.allay.api.entity.attribute.AttributeType;
import cn.allay.api.entity.component.EntityComponent;
import org.cloudburstmc.nbt.NbtList;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtType;
import org.jetbrains.annotations.Range;

import java.util.Collection;

/**
 * Allay Project 2023/7/9
 *
 * @author daoge_cmd
 */
public interface EntityAttributeComponent extends EntityComponent {
    void addAttribute(AttributeType attributeType);

    Collection<Attribute> getAttributes();

    Attribute getAttribute(AttributeType attributeType);

    void setAttribute(AttributeType attributeType, float value);

    void setAttribute(Attribute attribute);

    float getAttributeValue(AttributeType attributeType);

    default void setHealth(@Range(from = 0, to = Integer.MAX_VALUE) float value) {
        setAttribute(AttributeType.HEALTH, value);
    }

    default float getHealth() {
        return getAttributeValue(AttributeType.HEALTH);
    }

    default NbtList<NbtMap> saveAttributes() {
        NbtList<NbtMap> list = new NbtList<>(NbtType.COMPOUND);
        for (Attribute attribute : this.getAttributes()) {
            list.add(attribute.toNBT());
        }
        return list;
    }
}
