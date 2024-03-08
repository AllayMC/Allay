package org.allaymc.api.entity.effect.type;

import org.allaymc.api.common.data.Identifier;
import org.allaymc.api.entity.effect.AbstractEffectType;

import java.awt.*;

/**
 * Allay Project 2023/10/27
 *
 * @author daoge_cmd
 */
public class EffectNightVisionType extends AbstractEffectType {
    public static final EffectNightVisionType NIGHT_VISION_TYPE = new EffectNightVisionType();
    private EffectNightVisionType() {
        super(16, new Identifier("minecraft:night_vision"), new Color(0, 0, 139));
    }
}
