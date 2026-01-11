package org.allaymc.server.entity.effect;

import org.allaymc.api.utils.identifier.Identifier;

import java.awt.*;

/**
 * @author daoge_cmd
 */
public class EffectFireResistanceType extends AbstractEffectType {
    public EffectFireResistanceType() {
        super(12, new Identifier("minecraft:fire_resistance"), new Color(0xff9900));
    }
}
