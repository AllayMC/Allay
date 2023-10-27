package cn.allay.api.entity.effect.type;

import cn.allay.api.entity.effect.AbstractEffectType;
import cn.allay.api.identifier.Identifier;

import java.awt.*;

/**
 * Allay Project 2023/10/27
 *
 * @author daoge_cmd
 */
public class EffectRegenerationType extends AbstractEffectType {
    public static final EffectRegenerationType REGENERATION_TYPE = new EffectRegenerationType();
    private EffectRegenerationType() {
        super(10, new Identifier("minecraft:regeneration"), new Color(205, 92, 171));
    }
}
