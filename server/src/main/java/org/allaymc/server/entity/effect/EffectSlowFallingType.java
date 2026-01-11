package org.allaymc.server.entity.effect;

import org.allaymc.api.utils.identifier.Identifier;

import java.awt.*;

/**
 * @author daoge_cmd
 */
public class EffectSlowFallingType extends AbstractEffectType {
    public EffectSlowFallingType() {
        super(27, new Identifier("minecraft:slow_falling"), new Color(0xf3cfb9));
    }
}
