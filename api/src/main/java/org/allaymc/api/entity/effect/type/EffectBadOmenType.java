package org.allaymc.api.entity.effect.type;

import org.allaymc.api.entity.effect.AbstractEffectType;
import org.allaymc.api.utils.Identifier;

/**
 * @author daoge_cmd
 */
public class EffectBadOmenType extends AbstractEffectType {
    EffectBadOmenType() {
        super(28, new Identifier("minecraft:bad_omen"), true);
    }
}
