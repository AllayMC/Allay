package org.allaymc.api.entity.effect.type;

import org.allaymc.api.entity.effect.AbstractEffectType;
import org.allaymc.api.utils.Identifier;

import java.awt.*;

/**
 * @author daoge_cmd
 */
public class EffectBlindnessType extends AbstractEffectType {
    EffectBlindnessType() {
        super(15, new Identifier("minecraft:blindness"), new Color(0x1f1f23), true);
    }
}
