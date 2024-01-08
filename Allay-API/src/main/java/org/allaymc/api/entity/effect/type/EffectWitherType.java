package org.allaymc.api.entity.effect.type;

import org.allaymc.api.entity.effect.AbstractEffectType;
import org.allaymc.api.identifier.Identifier;

import java.awt.Color;

/**
 * Allay Project 2023/10/27
 *
 * @author daoge_cmd
 */
public class EffectWitherType extends AbstractEffectType {
    public static final EffectWitherType WITHER_TYPE = new EffectWitherType();
    private EffectWitherType() {
        super(20, new Identifier("minecraft:wither"), new Color(53, 42, 39), true);
    }
}
