package org.allaymc.api.entity.effect.type;

import org.allaymc.api.utils.Identifier;
import org.allaymc.api.entity.effect.AbstractEffectType;

import java.awt.*;

/**
 * Allay Project 2023/10/27
 *
 * @author daoge_cmd
 */
public class EffectFatalPoisonType extends AbstractEffectType {
    public static final EffectFatalPoisonType FATAL_POISON_TYPE = new EffectFatalPoisonType();
    private EffectFatalPoisonType() {
        super(25, new Identifier("minecraft:fatal_poison"), new Color(78, 147, 49), true);
    }
}
