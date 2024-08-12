package org.allaymc.api.entity.component.player;

import com.google.common.collect.Lists;
import org.allaymc.api.entity.attribute.AttributeType;
import org.allaymc.api.entity.component.common.EntityAttributeComponent;

import java.util.Arrays;
import java.util.List;

/**
 * Allay Project 2023/10/14
 *
 * @author daoge_cmd
 */
public interface EntityPlayerAttributeComponent extends EntityAttributeComponent {

    static AttributeType[] basicPlayerAttributes() {
        var list = Lists.newArrayList(
                AttributeType.PLAYER_HUNGER,
                AttributeType.PLAYER_SATURATION,
                AttributeType.PLAYER_EXHAUSTION,
                AttributeType.PLAYER_LEVEL,
                AttributeType.PLAYER_EXPERIENCE
        );
        list.addAll(Arrays.asList(EntityAttributeComponent.basicEntityAttributes()));
        return list.toArray(AttributeType[]::new);
    }

    int getExperienceLevel();

    void setExperienceLevel(int value);

    float getExperience();

    void setExperience(float value);

    int getHunger();

    void setHunger(int value);

    float getSaturation();

    void setSaturation(float value);

    float getExhaustion();

    void setExhaustion(float value);
}
