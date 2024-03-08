package org.allaymc.api.entity.effect.type;

import org.allaymc.api.common.data.Identifier;
import org.allaymc.api.entity.effect.AbstractEffectType;

import java.awt.*;

/**
 * Allay Project 2023/10/27
 *
 * @author daoge_cmd
 */
public class EffectSpeedType extends AbstractEffectType {
    public static final EffectSpeedType SPEED_TYPE = new EffectSpeedType();
    private EffectSpeedType() {
        super(1, new Identifier("minecraft:speed"), new Color(124, 175, 198));
    }
}
