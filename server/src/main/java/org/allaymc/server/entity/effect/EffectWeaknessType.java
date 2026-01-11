package org.allaymc.server.entity.effect;

import org.allaymc.api.utils.identifier.Identifier;

import java.awt.*;

/**
 * @author daoge_cmd
 */
public class EffectWeaknessType extends AbstractEffectType {
    public EffectWeaknessType() {
        super(18, new Identifier("minecraft:weakness"), new Color(0x484d48), true);
    }
}
