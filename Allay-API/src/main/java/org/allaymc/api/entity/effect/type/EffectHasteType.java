package org.allaymc.api.entity.effect.type;

import org.allaymc.api.common.data.Identifier;
import org.allaymc.api.entity.effect.AbstractEffectType;

import java.awt.*;

/**
 * Allay Project 2023/10/27
 *
 * @author daoge_cmd
 */
public class EffectHasteType extends AbstractEffectType {
    public static final EffectHasteType HASTE_TYPE = new EffectHasteType();
    private EffectHasteType() {
        super(3, new Identifier("minecraft:haste"), new Color(217, 192, 67));
    }
}
