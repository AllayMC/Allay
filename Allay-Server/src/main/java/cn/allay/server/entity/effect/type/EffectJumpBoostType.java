package cn.allay.server.entity.effect.type;

import cn.allay.api.data.VanillaEffectIds;
import cn.allay.server.entity.effect.AbstractEffectType;

import java.awt.*;

/**
 * Allay Project 2023/10/27
 *
 * @author daoge_cmd
 */
public class EffectJumpBoostType extends AbstractEffectType {
    public static final EffectJumpBoostType JUMP_BOOST_TYPE = new EffectJumpBoostType();
    private EffectJumpBoostType() {
        super(8, VanillaEffectIds.JUMP_BOOST, new Color(34, 255, 76));
    }
}
