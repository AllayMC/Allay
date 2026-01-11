package org.allaymc.server.entity.effect;

import org.allaymc.api.utils.identifier.Identifier;

import java.awt.*;

/**
 * @author daoge_cmd
 */
public class EffectBadOmenType extends AbstractEffectType {
    public EffectBadOmenType() {
        super(28, new Identifier("minecraft:bad_omen"), new Color(0x0b6138), true);
    }
}
