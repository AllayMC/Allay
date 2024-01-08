package org.allaymc.api.entity.effect.type;

import org.allaymc.api.entity.effect.AbstractEffectType;
import org.allaymc.api.identifier.Identifier;

import java.awt.Color;

/**
 * Allay Project 2023/10/27
 *
 * @author daoge_cmd
 */
public class EffectSpeedType extends AbstractEffectType {
    public static final EffectSpeedType SPEED_TYPE = new EffectSpeedType();
    private EffectSpeedType() {
        super(1, new Identifier("minecraft:speed"), new Color(124, 175, 198));
    }
}
