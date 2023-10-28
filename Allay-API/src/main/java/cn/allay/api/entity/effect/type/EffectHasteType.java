package cn.allay.api.entity.effect.type;

import cn.allay.api.entity.effect.AbstractEffectType;
import cn.allay.api.identifier.Identifier;

import java.awt.*;

/**
 * Allay Project 2023/10/27
 *
 * @author daoge_cmd
 */
public class EffectHasteType extends AbstractEffectType {
    public static final EffectHasteType HASTE_TYPE = new EffectHasteType();
    private EffectHasteType() {
        super(3, new Identifier("minecraft:haste"), new Color(217, 192, 67));
    }
}
