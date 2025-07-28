package org.allaymc.api.entity.effect.type;

import org.allaymc.api.entity.effect.AbstractEffectType;
import org.allaymc.api.utils.Identifier;

import java.awt.*;

/**
 * @author daoge_cmd
 */
public class EffectHasteType extends AbstractEffectType {
    EffectHasteType() {
        super(3, new Identifier("minecraft:haste"), new Color(0xd9c043));
    }
}
