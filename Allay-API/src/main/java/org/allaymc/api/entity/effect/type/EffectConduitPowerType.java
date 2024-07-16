package org.allaymc.api.entity.effect.type;

import org.allaymc.api.entity.effect.AbstractEffectType;
import org.allaymc.api.utils.Identifier;

/**
 * Allay Project 2023/10/27
 *
 * @author daoge_cmd
 */
public class EffectConduitPowerType extends AbstractEffectType {
    public static final EffectConduitPowerType CONDUIT_POWER = new EffectConduitPowerType();

    private EffectConduitPowerType() {
        super(26, new Identifier("minecraft:conduit_power"));
    }
}
