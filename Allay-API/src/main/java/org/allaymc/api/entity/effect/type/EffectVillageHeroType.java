package org.allaymc.api.entity.effect.type;

import org.allaymc.api.entity.effect.AbstractEffectType;
import org.allaymc.api.utils.Identifier;

/**
 * Allay Project 2023/10/27
 *
 * @author daoge_cmd
 */
public class EffectVillageHeroType extends AbstractEffectType {
    public static final EffectVillageHeroType VILLAGE_HERO_TYPE = new EffectVillageHeroType();

    private EffectVillageHeroType() {
        super(29, new Identifier("minecraft:village_hero"));
    }
}
