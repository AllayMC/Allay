package org.allaymc.api.entity.effect.type;

import org.allaymc.api.entity.effect.AbstractEffectType;
import org.allaymc.api.utils.Identifier;

import java.awt.*;

/**
 * Allay Project 2023/10/27
 *
 * @author daoge_cmd
 */
public class EffectPoisonType extends AbstractEffectType {
    public static final EffectPoisonType POISON_TYPE = new EffectPoisonType();

    private EffectPoisonType() {
        super(19, new Identifier("minecraft:poison"), new Color(135, 163, 99), true);
    }
}
