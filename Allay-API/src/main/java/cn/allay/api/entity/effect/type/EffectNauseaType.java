package cn.allay.api.entity.effect.type;

import cn.allay.api.entity.effect.AbstractEffectType;
import cn.allay.api.identifier.Identifier;

import java.awt.*;

/**
 * Allay Project 2023/10/27
 *
 * @author daoge_cmd
 */
public class EffectNauseaType extends AbstractEffectType {
    public static final EffectNauseaType NAUSEA_TYPE = new EffectNauseaType();
    private EffectNauseaType() {
        super(9, new Identifier("minecraft:nausea"), new Color(85, 29, 74), true);
    }
}
