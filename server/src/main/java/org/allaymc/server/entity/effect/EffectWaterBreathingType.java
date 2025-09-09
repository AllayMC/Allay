package org.allaymc.server.entity.effect;

import org.allaymc.api.entity.effect.AbstractEffectType;
import org.allaymc.api.utils.Identifier;

import java.awt.*;

/**
 * @author daoge_cmd
 */
public class EffectWaterBreathingType extends AbstractEffectType {
    public EffectWaterBreathingType() {
        super(13, new Identifier("minecraft:water_breathing"), new Color(0x98dac0));
    }
}
