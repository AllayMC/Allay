package cn.allay.api.entity.effect.type;

import cn.allay.api.entity.effect.AbstractEffectType;
import cn.allay.api.identifier.Identifier;

import java.awt.*;

/**
 * Allay Project 2023/10/27
 *
 * @author daoge_cmd
 */
public class EffectVillageHeroType extends AbstractEffectType {
    public static final EffectVillageHeroType VILLAGE_HERO_TYPE = new EffectVillageHeroType();
    private EffectVillageHeroType() {
        super(29, new Identifier("minecraft:village_hero"), new Color(68, 255, 68));
    }
}
