package cn.allay.server.entity.effect.type;

import cn.allay.api.data.VanillaEffectIds;
import cn.allay.server.entity.effect.AbstractEffectType;

import java.awt.*;

/**
 * Allay Project 2023/10/27
 *
 * @author daoge_cmd
 */
public class EffectWeaknessType extends AbstractEffectType {
    private EffectWeaknessType() {
        super(18, VanillaEffectIds.WEAKNESS, new Color(72, 77, 72), true);
    }
}
