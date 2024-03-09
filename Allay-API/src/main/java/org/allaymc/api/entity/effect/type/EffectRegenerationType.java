package org.allaymc.api.entity.effect.type;

import org.allaymc.api.client.data.Identifier;
import org.allaymc.api.entity.effect.AbstractEffectType;

import java.awt.*;

/**
 * Allay Project 2023/10/27
 *
 * @author daoge_cmd
 */
public class EffectRegenerationType extends AbstractEffectType {
    public static final EffectRegenerationType REGENERATION_TYPE = new EffectRegenerationType();
    private EffectRegenerationType() {
        super(10, new Identifier("minecraft:regeneration"), new Color(205, 92, 171));
    }
}
