package org.allaymc.server.entity.effect;

import org.allaymc.api.utils.identifier.Identifier;

import java.awt.*;

/**
 * @author daoge_cmd
 */
public class EffectNauseaType extends AbstractEffectType {
    public EffectNauseaType() {
        super(9, new Identifier("minecraft:nausea"), new Color(0x551d4a), true);
    }
}
