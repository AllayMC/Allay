package org.allaymc.api.entity.effect.type;

import org.allaymc.api.entity.effect.AbstractEffectType;
import org.allaymc.api.utils.Identifier;

import java.awt.*;

/**
 * Allay Project 23/06/2024
 *
 * @author IWareQ
 */
public class EffectRaidOmenType extends AbstractEffectType {
    public static final EffectRaidOmenType RAID_OMEN_TYPE = new EffectRaidOmenType();

    private EffectRaidOmenType() {
        super(36, new Identifier("minecraft:raid_omen"), new Color(222, 64, 88));
    }
}
