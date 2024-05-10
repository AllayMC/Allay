package org.allaymc.api.entity.effect.type;

import org.allaymc.api.entity.effect.AbstractEffectType;
import org.allaymc.api.utils.Identifier;

import java.awt.*;

/**
 * Allay Project 2023/10/27
 *
 * @author daoge_cmd
 */
public class EffectSlownessType extends AbstractEffectType {
    public static final EffectSlownessType SLOWNESS_TYPE = new EffectSlownessType();

    private EffectSlownessType() {
        super(2, new Identifier("minecraft:slowness"), new Color(90, 108, 129), true);
    }
}
