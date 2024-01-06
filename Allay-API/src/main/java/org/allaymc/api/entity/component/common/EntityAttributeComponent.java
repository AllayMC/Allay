package org.allaymc.api.entity.component.common;

import com.google.common.collect.Lists;
import org.allaymc.api.entity.attribute.Attribute;
import org.allaymc.api.entity.attribute.AttributeType;
import org.allaymc.api.entity.component.EntityComponent;
import org.cloudburstmc.nbt.NbtList;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtType;
import org.jetbrains.annotations.Range;

import java.util.Collection;
import java.util.List;

/**
 * Allay Project 2023/7/9
 *
 * @author daoge_cmd
 */
public interface EntityAttributeComponent extends EntityComponent {
    static List<AttributeType> basicPlayerAttributes() {
        return Lists.newArrayList(
                AttributeType.HEALTH,
                AttributeType.PLAYER_HUNGER,
                AttributeType.MOVEMENT,
                AttributeType.PLAYER_LEVEL,
                AttributeType.PLAYER_EXPERIENCE
        );
    }

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

    void sendAttributesToClient();
}
