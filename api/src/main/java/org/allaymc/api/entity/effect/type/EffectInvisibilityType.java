package org.allaymc.api.entity.effect.type;

import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.effect.AbstractEffectType;
import org.allaymc.api.entity.effect.EffectInstance;
import org.allaymc.api.utils.Identifier;
import org.cloudburstmc.protocol.bedrock.data.entity.EntityFlag;

import java.awt.*;

/**
 * @author daoge_cmd
 */
public class EffectInvisibilityType extends AbstractEffectType {
    EffectInvisibilityType() {
        super(14, new Identifier("minecraft:invisibility"), new Color(0xf6f6f6));
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
