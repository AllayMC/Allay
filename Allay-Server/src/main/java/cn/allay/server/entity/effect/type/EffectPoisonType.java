package cn.allay.server.entity.effect.type;

import cn.allay.api.data.VanillaEffectIds;
import cn.allay.server.entity.effect.AbstractEffectType;

import java.awt.*;

/**
 * Allay Project 2023/10/27
 *
 * @author daoge_cmd
 */
public class EffectPoisonType extends AbstractEffectType {
    private EffectPoisonType() {
        super(19, VanillaEffectIds.POISON, new Color(78, 147, 49), true);
    }
}
