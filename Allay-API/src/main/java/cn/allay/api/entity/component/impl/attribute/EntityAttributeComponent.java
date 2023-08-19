package cn.allay.api.entity.component.impl.attribute;

import cn.allay.api.component.annotation.Inject;
import cn.allay.api.entity.attribute.Attribute;
import cn.allay.api.entity.attribute.AttributeType;
import org.cloudburstmc.nbt.NbtList;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtType;

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
    void setAttribute(AttributeType attributeType, float value);

    @Inject
    void setAttribute(Attribute attribute);

    @Inject
    float getAttributeValue(AttributeType attributeType);

    default NbtList<NbtMap> saveAttributes() {
        NbtList<NbtMap> list = new NbtList<>(NbtType.COMPOUND);
        for (Attribute attribute : this.getAttributes()) {
            list.add(attribute.toNBT());
        }
        return list;
    }
}
