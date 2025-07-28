package org.allaymc.api.entity.effect.type;

import org.allaymc.api.entity.effect.AbstractEffectType;
import org.allaymc.api.utils.Identifier;

import java.awt.*;

/**
 * @author daoge_cmd
 */
public class EffectJumpBoostType extends AbstractEffectType {
    EffectJumpBoostType() {
        super(8, new Identifier("minecraft:jump_boost"), new Color(0xfdff84));
    }
}
