package org.allaymc.api.entity.effect.type;

import org.allaymc.api.entity.effect.AbstractEffectType;
import org.allaymc.api.utils.Identifier;

import java.awt.*;

/**
 * @author daoge_cmd
 */
public class EffectStrengthType extends AbstractEffectType {
    EffectStrengthType() {
        super(5, new Identifier("minecraft:strength"), new Color(0xffc700));
    }
}
