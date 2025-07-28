package org.allaymc.api.entity.effect.type;

import org.allaymc.api.entity.effect.AbstractEffectType;
import org.allaymc.api.utils.Identifier;

import java.awt.*;

/**
 * @author IWareQ
 */
public class EffectRaidOmenType extends AbstractEffectType {
    EffectRaidOmenType() {
        super(36, new Identifier("minecraft:raid_omen"), new Color(0xde4058));
    }
}
