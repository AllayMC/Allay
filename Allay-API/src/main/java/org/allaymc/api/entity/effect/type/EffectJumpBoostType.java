package org.allaymc.api.entity.effect.type;

import org.allaymc.api.entity.effect.AbstractEffectType;
import org.allaymc.api.utils.Identifier;

import java.awt.*;

/**
 * Allay Project 2023/10/27
 *
 * @author daoge_cmd
 */
public class EffectJumpBoostType extends AbstractEffectType {
    public static final EffectJumpBoostType JUMP_BOOST_TYPE = new EffectJumpBoostType();

    private EffectJumpBoostType() {
        super(8, new Identifier("minecraft:jump_boost"), new Color(253, 255, 132));
    }
}
