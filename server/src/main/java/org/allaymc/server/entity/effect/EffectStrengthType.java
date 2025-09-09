package org.allaymc.server.entity.effect;

import org.allaymc.api.entity.effect.AbstractEffectType;
import org.allaymc.api.utils.Identifier;

import java.awt.*;

/**
 * @author daoge_cmd
 */
public class EffectStrengthType extends AbstractEffectType {
    public EffectStrengthType() {
        super(5, new Identifier("minecraft:strength"), new Color(0xffc700));
    }
}
