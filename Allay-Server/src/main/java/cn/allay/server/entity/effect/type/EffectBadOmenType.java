package cn.allay.server.entity.effect.type;

import cn.allay.api.data.VanillaEffectIds;
import cn.allay.server.entity.effect.AbstractEffectType;

import java.awt.*;

/**
 * Allay Project 2023/10/27
 *
 * @author daoge_cmd
 */
public class EffectBadOmenType extends AbstractEffectType {
    public static final EffectBadOmenType BAD_OMEN_TYPE = new EffectBadOmenType();
    private EffectBadOmenType() {
        super(28, VanillaEffectIds.BAD_OMEN, new Color(11, 97, 56), true);
    }
}
