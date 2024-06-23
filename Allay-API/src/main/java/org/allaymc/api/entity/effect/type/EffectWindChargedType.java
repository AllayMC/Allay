package org.allaymc.api.entity.effect.type;

import org.allaymc.api.entity.effect.AbstractEffectType;
import org.allaymc.api.utils.Identifier;

import java.awt.*;

/**
 * Allay Project 23/06/2024
 *
 * @author IWareQ
 */
public class EffectWindChargedType extends AbstractEffectType {
    public static final EffectWindChargedType WIND_CHARGED_TYPE = new EffectWindChargedType();

    private EffectWindChargedType() {
        super(32, new Identifier("minecraft:wind_charged"), new Color(189, 201, 255), true);
    }
}
