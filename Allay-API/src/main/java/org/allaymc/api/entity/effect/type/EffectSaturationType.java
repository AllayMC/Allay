package org.allaymc.api.entity.effect.type;

import org.allaymc.api.entity.effect.AbstractEffectType;
import org.allaymc.api.identifier.Identifier;

import java.awt.Color;

/**
 * Allay Project 2023/10/27
 *
 * @author daoge_cmd
 */
public class EffectSaturationType extends AbstractEffectType {
    public static final EffectSaturationType SATURATION_TYPE = new EffectSaturationType();
    private EffectSaturationType() {
        super(23, new Identifier("minecraft:saturation"), new Color(255, 0, 255));
    }
}
