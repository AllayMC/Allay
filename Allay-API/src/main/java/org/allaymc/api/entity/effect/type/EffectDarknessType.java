package org.allaymc.api.entity.effect.type;

import org.allaymc.api.entity.effect.AbstractEffectType;
import org.allaymc.api.identifier.Identifier;

import java.awt.*;

/**
 * Allay Project 2023/10/27
 *
 * @author daoge_cmd
 */
public class EffectDarknessType extends AbstractEffectType {
    public static final EffectDarknessType DARKNESS_TYPE = new EffectDarknessType();
    private EffectDarknessType() {
        super(30, new Identifier("minecraft:darkness"), new Color(41, 39, 33), true);
    }
}
