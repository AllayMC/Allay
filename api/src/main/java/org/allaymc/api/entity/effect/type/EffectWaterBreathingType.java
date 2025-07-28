package org.allaymc.api.entity.effect.type;

import org.allaymc.api.entity.effect.AbstractEffectType;
import org.allaymc.api.utils.Identifier;

import java.awt.*;

/**
 * @author daoge_cmd
 */
public class EffectWaterBreathingType extends AbstractEffectType {
    EffectWaterBreathingType() {
        super(13, new Identifier("minecraft:water_breathing"), new Color(0x98dac0));
    }
}
