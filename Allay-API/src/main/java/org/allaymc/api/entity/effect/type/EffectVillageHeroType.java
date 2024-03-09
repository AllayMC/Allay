package org.allaymc.api.entity.effect.type;

import org.allaymc.api.utils.Identifier;
import org.allaymc.api.entity.effect.AbstractEffectType;

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
