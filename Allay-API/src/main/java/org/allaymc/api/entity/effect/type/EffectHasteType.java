package org.allaymc.api.entity.effect.type;

import org.allaymc.api.entity.effect.AbstractEffectType;
import org.allaymc.api.utils.Identifier;

/**
 * @author daoge_cmd
 */
public class EffectHasteType extends AbstractEffectType {
    public EffectHasteType() {
        super(3, new Identifier("minecraft:haste"));
    }
}
