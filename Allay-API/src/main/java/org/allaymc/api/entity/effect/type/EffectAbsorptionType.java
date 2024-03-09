package org.allaymc.api.entity.effect.type;

import org.allaymc.api.utils.Identifier;
import org.allaymc.api.entity.effect.AbstractEffectType;

import java.awt.*;

/**
 * Allay Project 2023/10/27
 *
 * @author daoge_cmd
 */
public class EffectAbsorptionType extends AbstractEffectType {

    public static final EffectAbsorptionType ABSORPTION_TYPE = new EffectAbsorptionType();
    private EffectAbsorptionType() {
        super(22, new Identifier("minecraft:absorption"), new Color(36, 107, 251));
    }
}
