package cn.allay.api.entity.effect.type;

import cn.allay.api.entity.effect.AbstractEffectType;
import cn.allay.api.identifier.Identifier;

import java.awt.*;

/**
 * Allay Project 2023/10/27
 *
 * @author daoge_cmd
 */
public class EffectBadOmenType extends AbstractEffectType {
    public static final EffectBadOmenType BAD_OMEN_TYPE = new EffectBadOmenType();
    private EffectBadOmenType() {
        super(28, new Identifier("minecraft:bad_omen"), new Color(11, 97, 56), true);
    }
}
