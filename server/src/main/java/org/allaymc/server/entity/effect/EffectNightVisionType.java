package org.allaymc.server.entity.effect;

import org.allaymc.api.entity.effect.AbstractEffectType;
import org.allaymc.api.utils.Identifier;

import java.awt.*;

/**
 * @author daoge_cmd
 */
public class EffectNightVisionType extends AbstractEffectType {
    public EffectNightVisionType() {
        super(16, new Identifier("minecraft:night_vision"), new Color(0xc2ff66));
    }
}
