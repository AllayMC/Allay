package org.allaymc.server.entity.effect;

import org.allaymc.api.entity.effect.AbstractEffectType;
import org.allaymc.api.utils.Identifier;

import java.awt.*;

/**
 * @author daoge_cmd
 */
public class EffectSlowFallingType extends AbstractEffectType {
    public EffectSlowFallingType() {
        super(27, new Identifier("minecraft:slow_falling"), new Color(0xf3cfb9));
    }
}
