package cn.allay.server.entity.effect.type;

import cn.allay.api.data.VanillaEffectIds;
import cn.allay.server.entity.effect.AbstractEffectType;

import java.awt.*;

/**
 * Allay Project 2023/10/27
 *
 * @author daoge_cmd
 */
public class EffectNauseaType extends AbstractEffectType {
    private EffectNauseaType() {
        super(9, VanillaEffectIds.NAUSEA, new Color(85, 29, 74), true);
    }
}
