package org.allaymc.api.entity.effect.type;

import org.allaymc.api.entity.effect.AbstractEffectType;
import org.allaymc.api.utils.Identifier;

/**
 * Allay Project 2023/10/27
 *
 * @author daoge_cmd
 */
public class EffectStrengthType extends AbstractEffectType {
    public EffectStrengthType() {
        super(5, new Identifier("minecraft:strength"));
    }
}
