package org.allaymc.server.entity.effect;

import org.allaymc.api.entity.effect.AbstractEffectType;
import org.allaymc.api.utils.Identifier;

import java.awt.*;

/**
 * @author daoge_cmd
 */
public class EffectConduitPowerType extends AbstractEffectType {
    public EffectConduitPowerType() {
        super(26, new Identifier("minecraft:conduit_power"), new Color(0x1dc2d1));
    }
}
