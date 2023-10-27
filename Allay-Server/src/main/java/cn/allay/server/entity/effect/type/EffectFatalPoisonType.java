package cn.allay.server.entity.effect.type;

import cn.allay.api.data.VanillaEffectIds;
import cn.allay.server.entity.effect.AbstractEffectType;

import java.awt.*;

/**
 * Allay Project 2023/10/27
 *
 * @author daoge_cmd
 */
public class EffectFatalPoisonType extends AbstractEffectType {
    public static final EffectFatalPoisonType FATAL_POISON_TYPE = new EffectFatalPoisonType();
    private EffectFatalPoisonType() {
        super(25, VanillaEffectIds.FATAL_POISON, new Color(78, 147, 49), true);
    }
}
