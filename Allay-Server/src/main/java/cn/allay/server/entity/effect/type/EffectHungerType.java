package cn.allay.server.entity.effect.type;

import cn.allay.api.data.VanillaEffectIds;
import cn.allay.server.entity.effect.AbstractEffectType;

import java.awt.*;

/**
 * Allay Project 2023/10/27
 *
 * @author daoge_cmd
 */
public class EffectHungerType extends AbstractEffectType {
    public static final EffectHungerType HUNGER_TYPE = new EffectHungerType();
    private EffectHungerType() {
        super(17, VanillaEffectIds.HUNGER, new Color(46, 139, 87), true);
    }
}