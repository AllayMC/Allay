package org.allaymc.api.entity.effect.type;

import org.allaymc.api.entity.effect.AbstractEffectType;
import org.allaymc.api.utils.Identifier;

/**
 * Allay Project 2023/10/27
 *
 * @author daoge_cmd
 */
public class EffectInstantHealthType extends AbstractEffectType {
    public EffectInstantHealthType() {
        super(6, new Identifier("minecraft:instant_health"));
    }
}
