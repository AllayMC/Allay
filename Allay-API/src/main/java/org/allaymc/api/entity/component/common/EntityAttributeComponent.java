package org.allaymc.api.entity.component.common;

import com.google.common.collect.Lists;
import org.allaymc.api.entity.attribute.Attribute;
import org.allaymc.api.entity.attribute.AttributeType;
import org.allaymc.api.entity.component.EntityComponent;
import org.cloudburstmc.nbt.NbtList;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtType;

import java.util.Collection;
import java.util.List;

/**
 * Allay Project 2023/7/9
 *
 * @author daoge_cmd
 */
public interface EntityAttributeComponent extends EntityComponent {

    static List<AttributeType> basicPlayerAttributes() {
        var list = basicEntityAttributes();
        list.addAll(Lists.newArrayList(
                AttributeType.PLAYER_HUNGER,
                AttributeType.PLAYER_SATURATION,
                AttributeType.PLAYER_EXHAUSTION,
                AttributeType.PLAYER_LEVEL,
                AttributeType.PLAYER_EXPERIENCE
        ));
        return list;
    }

    static List<AttributeType> basicEntityAttributes() {
        return Lists.newArrayList(
                AttributeType.ABSORPTION,
                AttributeType.KNOCKBACK_RESISTANCE,
                AttributeType.HEALTH,
                AttributeType.MOVEMENT,
                AttributeType.FALL_DAMAGE
        );
    }

    void addAttribute(AttributeType attributeType);

    Collection<Attribute> getAttributes();

    Attribute getAttribute(AttributeType attributeType);

    void setAttribute(AttributeType attributeType, float value);

    void setAttribute(Attribute attribute);

    float getAttributeValue(AttributeType attributeType);

    default float getHealth() {
        return getAttributeValue(AttributeType.HEALTH);
    }

    void setHealth(float value);

    default float getMaxHealth() {
        return this.getAttribute(AttributeType.HEALTH).getMaxValue();
    }

    void setMaxHealth(float value);

    default NbtList<NbtMap> saveAttributes() {
        NbtList<NbtMap> list = new NbtList<>(NbtType.COMPOUND);
        this.getAttributes().stream().map(Attribute::toNBT).forEach(list::add);
        return list;
    }

    void sendAttributesIfIsPlayer();
}
