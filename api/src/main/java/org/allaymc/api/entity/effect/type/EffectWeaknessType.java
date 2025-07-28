package org.allaymc.api.entity.effect.type;

import org.allaymc.api.entity.effect.AbstractEffectType;
import org.allaymc.api.utils.Identifier;

import java.awt.*;

/**
 * @author daoge_cmd
 */
public class EffectWeaknessType extends AbstractEffectType {
    EffectWeaknessType() {
        super(18, new Identifier("minecraft:weakness"), new Color(0x484d48), true);
    }
}
