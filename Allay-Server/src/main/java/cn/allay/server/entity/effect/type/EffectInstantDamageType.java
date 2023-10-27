package cn.allay.server.entity.effect.type;

import cn.allay.api.data.VanillaEffectIds;
import cn.allay.server.entity.effect.AbstractEffectType;

import java.awt.*;

/**
 * Allay Project 2023/10/27
 *
 * @author daoge_cmd
 */
public class EffectInstantDamageType extends AbstractEffectType {
    public static final EffectInstantDamageType INSTANT_DAMAGE_TYPE = new EffectInstantDamageType();
    private EffectInstantDamageType() {
        super(7, VanillaEffectIds.INSTANT_DAMAGE, new Color(67, 10, 9), true);
    }
}
