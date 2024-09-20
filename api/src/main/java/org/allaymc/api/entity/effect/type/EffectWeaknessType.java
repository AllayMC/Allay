package org.allaymc.api.entity.effect.type;

import org.allaymc.api.entity.effect.AbstractEffectType;
import org.allaymc.api.utils.Identifier;

/**
 * @author daoge_cmd
 */
public class EffectWeaknessType extends AbstractEffectType {
    public EffectWeaknessType() {
        super(18, new Identifier("minecraft:weakness"), true);
    }
}
