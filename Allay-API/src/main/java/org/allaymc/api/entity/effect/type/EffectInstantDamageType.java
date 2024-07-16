package org.allaymc.api.entity.effect.type;

import org.allaymc.api.entity.effect.AbstractEffectType;
import org.allaymc.api.utils.Identifier;

/**
 * Allay Project 2023/10/27
 *
 * @author daoge_cmd
 */
public class EffectInstantDamageType extends AbstractEffectType {
    public static final EffectInstantDamageType INSTANT_DAMAGE = new EffectInstantDamageType();

    private EffectInstantDamageType() {
        super(7, new Identifier("minecraft:instant_damage"), true);
    }
}
