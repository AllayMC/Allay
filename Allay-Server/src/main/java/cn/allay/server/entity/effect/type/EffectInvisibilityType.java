package cn.allay.server.entity.effect.type;

import cn.allay.api.data.VanillaEffectIds;
import cn.allay.server.entity.effect.AbstractEffectType;

import java.awt.*;

/**
 * Allay Project 2023/10/27
 *
 * @author daoge_cmd
 */
public class EffectInvisibilityType extends AbstractEffectType {
    public static final EffectInvisibilityType INVISIBILITY_TYPE = new EffectInvisibilityType();
    private EffectInvisibilityType() {
        super(14, VanillaEffectIds.INVISIBILITY, new Color(127, 131, 146));
    }
}
