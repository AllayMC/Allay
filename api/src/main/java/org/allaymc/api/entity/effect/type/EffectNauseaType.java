package org.allaymc.api.entity.effect.type;

import org.allaymc.api.entity.effect.AbstractEffectType;
import org.allaymc.api.utils.Identifier;

/**
 * @author daoge_cmd
 */
public class EffectNauseaType extends AbstractEffectType {
    EffectNauseaType() {
        super(9, new Identifier("minecraft:nausea"), true);
    }
}
