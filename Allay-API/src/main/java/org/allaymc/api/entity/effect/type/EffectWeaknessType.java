package org.allaymc.api.entity.effect.type;

import org.allaymc.api.entity.effect.AbstractEffectType;
import org.allaymc.api.identifier.Identifier;

import java.awt.*;

/**
 * Allay Project 2023/10/27
 *
 * @author daoge_cmd
 */
public class EffectWeaknessType extends AbstractEffectType {
    public static final EffectWeaknessType WEAKNESS_TYPE = new EffectWeaknessType();
    private EffectWeaknessType() {
        super(18, new Identifier("minecraft:weakness"), new Color(72, 77, 72), true);
    }
}
