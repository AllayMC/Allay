package org.allaymc.server.entity.effect;

import org.allaymc.api.entity.data.EntityFlag;
import org.allaymc.api.entity.effect.AbstractEffectType;
import org.allaymc.api.entity.effect.EffectInstance;
import org.allaymc.api.entity.interfaces.EntityLiving;
import org.allaymc.api.utils.identifier.Identifier;

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
    public void onAdd(EntityLiving entity, EffectInstance effectInstance) {
        entity.setInvisible(true);
        entity.setFlag(EntityFlag.CAN_SHOW_NAME, false);
    }

    @Override
    public void onRemove(EntityLiving entity, EffectInstance effectInstance) {
        entity.setInvisible(false);
        entity.setFlag(EntityFlag.CAN_SHOW_NAME, true);
    }
}
