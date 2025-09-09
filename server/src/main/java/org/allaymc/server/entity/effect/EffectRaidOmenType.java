package org.allaymc.server.entity.effect;

import org.allaymc.api.entity.effect.AbstractEffectType;
import org.allaymc.api.utils.Identifier;

import java.awt.*;

/**
 * @author IWareQ
 */
public class EffectRaidOmenType extends AbstractEffectType {
    public EffectRaidOmenType() {
        super(36, new Identifier("minecraft:raid_omen"), new Color(0xde4058));
    }
}
