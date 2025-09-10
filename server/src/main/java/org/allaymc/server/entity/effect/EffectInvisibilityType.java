package org.allaymc.server.entity.effect;

import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.effect.AbstractEffectType;
import org.allaymc.api.entity.effect.EffectInstance;
import org.allaymc.api.utils.identifier.Identifier;
import org.cloudburstmc.protocol.bedrock.data.entity.EntityFlag;

import java.awt.*;

/**
 * @author daoge_cmd
 */
public class EffectInvisibilityType extends AbstractEffectType {
    public EffectInvisibilityType() {
        super(14, new Identifier("minecraft:invisibility"), new Color(0xf6f6f6));
    }

    // TODO: Figure out the usages for EntityFlag.CAN_SHOW_NAME, EntityFlag.ALWAYS_SHOW_NAME and EntityDataTypes.NAMETAG_ALWAYS_SHOW and wrap apis for them

    @Override
    public void onAdd(Entity entity, EffectInstance effectInstance) {
        entity.setInvisible(true);
        entity.setFlag(EntityFlag.CAN_SHOW_NAME, false);
    }

    @Override
    public void onRemove(Entity entity, EffectInstance effectInstance) {
        entity.setInvisible(false);
        entity.setFlag(EntityFlag.CAN_SHOW_NAME, true);
    }
}
