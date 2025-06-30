package org.allaymc.api.entity.effect.type;

import org.allaymc.api.entity.effect.AbstractEffectType;
import org.allaymc.api.utils.Identifier;

/**
 * @author daoge_cmd
 */
public class EffectConduitPowerType extends AbstractEffectType {
    EffectConduitPowerType() {
        super(26, new Identifier("minecraft:conduit_power"));
    }
}
