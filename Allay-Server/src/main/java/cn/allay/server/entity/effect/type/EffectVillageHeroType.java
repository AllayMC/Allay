package cn.allay.server.entity.effect.type;

import cn.allay.api.data.VanillaEffectIds;
import cn.allay.server.entity.effect.AbstractEffectType;

import java.awt.*;

/**
 * Allay Project 2023/10/27
 *
 * @author daoge_cmd
 */
public class EffectVillageHeroType extends AbstractEffectType {
    private EffectVillageHeroType() {
        super(29, VanillaEffectIds.VILLAGE_HERO, new Color(68, 255, 68));
    }
}
