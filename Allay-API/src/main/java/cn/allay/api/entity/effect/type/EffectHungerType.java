package cn.allay.api.entity.effect.type;

import cn.allay.api.entity.effect.AbstractEffectType;
import cn.allay.api.identifier.Identifier;

import java.awt.*;

/**
 * Allay Project 2023/10/27
 *
 * @author daoge_cmd
 */
public class EffectHungerType extends AbstractEffectType {
    public static final EffectHungerType HUNGER_TYPE = new EffectHungerType();
    private EffectHungerType() {
        super(17, new Identifier("minecraft:hunger"), new Color(46, 139, 87), true);
    }
}
