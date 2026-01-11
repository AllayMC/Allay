package org.allaymc.server.entity.effect;

import org.allaymc.api.utils.identifier.Identifier;

import java.awt.*;

/**
 * @author daoge_cmd
 */
public class EffectDarknessType extends AbstractEffectType {
    public EffectDarknessType() {
        super(30, new Identifier("minecraft:darkness"), new Color(0x292721), true);
    }
}
