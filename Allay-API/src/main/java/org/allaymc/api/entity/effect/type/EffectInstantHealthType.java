package org.allaymc.api.entity.effect.type;

import org.allaymc.api.client.data.Identifier;
import org.allaymc.api.entity.effect.AbstractEffectType;

import java.awt.*;

/**
 * Allay Project 2023/10/27
 *
 * @author daoge_cmd
 */
public class EffectInstantHealthType extends AbstractEffectType {
    public static final EffectInstantHealthType INSTANT_HEALTH_TYPE = new EffectInstantHealthType();
    private EffectInstantHealthType() {
        super(6, new Identifier("minecraft:instant_health"), new Color(248, 36, 35));
    }
}
