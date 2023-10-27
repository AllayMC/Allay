package cn.allay.server.entity.effect.type;

import cn.allay.api.data.VanillaEffectIds;
import cn.allay.server.entity.effect.AbstractEffectType;

import java.awt.*;

/**
 * Allay Project 2023/10/27
 *
 * @author daoge_cmd
 */
public class EffectConduitPowerType extends AbstractEffectType {
    public static final EffectConduitPowerType CONDUIT_POWER_TYPE = new EffectConduitPowerType();
    private EffectConduitPowerType() {
        super(26, VanillaEffectIds.CONDUIT_POWER, new Color(29, 194, 209));
    }
}
