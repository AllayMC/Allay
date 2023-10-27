package cn.allay.api.entity.effect.type;

import cn.allay.api.entity.effect.AbstractEffectType;
import cn.allay.api.identifier.Identifier;

import java.awt.*;

/**
 * Allay Project 2023/10/27
 *
 * @author daoge_cmd
 */
public class EffectJumpBoostType extends AbstractEffectType {
    public static final EffectJumpBoostType JUMP_BOOST_TYPE = new EffectJumpBoostType();
    private EffectJumpBoostType() {
        super(8, new Identifier("minecraft:jump_boost"), new Color(34, 255, 76));
    }
}
