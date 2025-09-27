package org.allaymc.server.entity.effect;

import org.allaymc.api.entity.effect.AbstractEffectType;
import org.allaymc.api.utils.identifier.Identifier;

import java.awt.*;

/**
 * @author daoge_cmd
 */
public class EffectLevitationType extends AbstractEffectType {
    public EffectLevitationType() {
        super(24, new Identifier("minecraft:levitation"), new Color(0xceffff), true);
    }
}
