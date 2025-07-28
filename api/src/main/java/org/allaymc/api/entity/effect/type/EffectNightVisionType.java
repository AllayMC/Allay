package org.allaymc.api.entity.effect.type;

import org.allaymc.api.entity.effect.AbstractEffectType;
import org.allaymc.api.utils.Identifier;

import java.awt.*;

/**
 * @author daoge_cmd
 */
public class EffectNightVisionType extends AbstractEffectType {
    EffectNightVisionType() {
        super(16, new Identifier("minecraft:night_vision"), new Color(0xc2ff66));
    }
}
