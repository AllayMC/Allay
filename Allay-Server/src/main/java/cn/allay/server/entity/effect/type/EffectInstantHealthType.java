package cn.allay.server.entity.effect.type;

import cn.allay.api.data.VanillaEffectIds;
import cn.allay.server.entity.effect.AbstractEffectType;

import java.awt.*;

/**
 * Allay Project 2023/10/27
 *
 * @author daoge_cmd
 */
public class EffectInstantHealthType extends AbstractEffectType {
    private EffectInstantHealthType() {
        super(6, VanillaEffectIds.INSTANT_HEALTH, new Color(248, 36, 35));
    }
}
