package org.allaymc.api.entity.effect.type;

import org.allaymc.api.entity.effect.AbstractEffectType;
import org.allaymc.api.identifier.Identifier;

import java.awt.Color;

/**
 * Allay Project 2023/10/27
 *
 * @author daoge_cmd
 */
public class EffectInvisibilityType extends AbstractEffectType {
    public static final EffectInvisibilityType INVISIBILITY_TYPE = new EffectInvisibilityType();
    private EffectInvisibilityType() {
        super(14, new Identifier("minecraft:invisibility"), new Color(127, 131, 146));
    }
}
