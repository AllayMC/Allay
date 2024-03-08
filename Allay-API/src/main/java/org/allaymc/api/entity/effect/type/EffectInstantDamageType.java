package org.allaymc.api.entity.effect.type;

import org.allaymc.api.common.data.Identifier;
import org.allaymc.api.entity.effect.AbstractEffectType;

import java.awt.*;

/**
 * Allay Project 2023/10/27
 *
 * @author daoge_cmd
 */
public class EffectInstantDamageType extends AbstractEffectType {
    public static final EffectInstantDamageType INSTANT_DAMAGE_TYPE = new EffectInstantDamageType();
    private EffectInstantDamageType() {
        super(7, new Identifier("minecraft:instant_damage"), new Color(67, 10, 9), true);
    }
}
