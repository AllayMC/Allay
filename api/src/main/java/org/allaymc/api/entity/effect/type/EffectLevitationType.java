package org.allaymc.api.entity.effect.type;

import org.allaymc.api.entity.effect.AbstractEffectType;
import org.allaymc.api.utils.Identifier;

import java.awt.*;

/**
 * @author daoge_cmd
 */
public class EffectLevitationType extends AbstractEffectType {
    EffectLevitationType() {
        super(24, new Identifier("minecraft:levitation"), new Color(0xceffff), true);
    }
}
