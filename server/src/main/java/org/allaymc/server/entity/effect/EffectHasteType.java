package org.allaymc.server.entity.effect;

import org.allaymc.api.utils.identifier.Identifier;

import java.awt.*;

/**
 * @author daoge_cmd
 */
public class EffectHasteType extends AbstractEffectType {
    public EffectHasteType() {
        super(3, new Identifier("minecraft:haste"), new Color(0xd9c043));
    }
}
