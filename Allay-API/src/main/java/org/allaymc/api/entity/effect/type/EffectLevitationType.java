package org.allaymc.api.entity.effect.type;

import org.allaymc.api.entity.effect.AbstractEffectType;
import org.allaymc.api.identifier.Identifier;

import java.awt.*;

/**
 * Allay Project 2023/10/27
 *
 * @author daoge_cmd
 */
public class EffectLevitationType extends AbstractEffectType {
    public static final EffectLevitationType LEVITATION_TYPE = new EffectLevitationType();
    private EffectLevitationType() {
        super(24, new Identifier("minecraft:levitation"), new Color(206, 255, 255), true);
    }
}
