package org.allaymc.api.entity.effect.type;

import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.effect.AbstractEffectType;
import org.allaymc.api.entity.effect.EffectInstance;
import org.allaymc.api.utils.Identifier;
import org.cloudburstmc.protocol.bedrock.data.entity.EntityDataTypes;
import org.cloudburstmc.protocol.bedrock.data.entity.EntityFlag;

import java.awt.*;

/**
 * Allay Project 2023/10/27
 *
 * @author daoge_cmd
 */
public class EffectInvisibilityType extends AbstractEffectType {
    public static final EffectInvisibilityType INVISIBILITY_TYPE = new EffectInvisibilityType();

    private EffectInvisibilityType() {
        super(14, new Identifier("minecraft:invisibility"), new Color(246, 246, 246));
    }

    @Override
    public void onAdd(Entity entity, EffectInstance effectInstance) {
        entity.setAndSendEntityFlag(EntityFlag.INVISIBLE, true);
        entity.setAndSendEntityFlag(EntityFlag.CAN_SHOW_NAME, false);
    }

    @Override
    public void onRemove(Entity entity, EffectInstance effectInstance) {
        entity.setAndSendEntityFlag(EntityFlag.INVISIBLE, false);
        entity.setAndSendEntityFlag(EntityFlag.CAN_SHOW_NAME, true);
    }
}
