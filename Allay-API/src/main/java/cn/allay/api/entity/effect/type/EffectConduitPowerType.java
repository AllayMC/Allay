package cn.allay.api.entity.effect.type;

import cn.allay.api.entity.effect.AbstractEffectType;
import cn.allay.api.identifier.Identifier;

import java.awt.*;

/**
 * Allay Project 2023/10/27
 *
 * @author daoge_cmd
 */
public class EffectConduitPowerType extends AbstractEffectType {
    public static final EffectConduitPowerType CONDUIT_POWER_TYPE = new EffectConduitPowerType();
    private EffectConduitPowerType() {
        super(26, new Identifier("minecraft:conduit_power"), new Color(29, 194, 209));
    }
}
