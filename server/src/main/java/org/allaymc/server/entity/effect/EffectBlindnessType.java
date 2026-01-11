package org.allaymc.server.entity.effect;

import org.allaymc.api.utils.identifier.Identifier;

import java.awt.*;

/**
 * @author daoge_cmd
 */
public class EffectBlindnessType extends AbstractEffectType {
    public EffectBlindnessType() {
        super(15, new Identifier("minecraft:blindness"), new Color(0x1f1f23), true);
    }
}
