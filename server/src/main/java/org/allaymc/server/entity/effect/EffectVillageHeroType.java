package org.allaymc.server.entity.effect;

import org.allaymc.api.utils.identifier.Identifier;

import java.awt.*;

/**
 * @author daoge_cmd
 */
public class EffectVillageHeroType extends AbstractEffectType {
    public EffectVillageHeroType() {
        super(29, new Identifier("minecraft:village_hero"), new Color(0x44ff44));
    }
}
