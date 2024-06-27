package org.allaymc.api.entity.effect.type;

import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.effect.AbstractEffectType;
import org.allaymc.api.entity.effect.EffectInstance;
import org.allaymc.api.entity.interfaces.EntityPlayer;
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
        super(2, new Identifier("minecraft:slowness"), new Color(139, 175, 224), true);
    }

    @Override
    public void onAdd(Entity entity, EffectInstance effectInstance) {
        if (!(entity instanceof EntityPlayer player)) return;
        var amplifier = effectInstance.getAmplifier();
        player.setMovementSpeed(EntityPlayer.DEFAULT_MOVEMENT_SPEED * (1 - 0.15f * (amplifier + 1)));
    }

    @Override
    public void onRemove(Entity entity, EffectInstance effectInstance) {
        if (!(entity instanceof EntityPlayer player)) return;
        player.setMovementSpeed(EntityPlayer.DEFAULT_MOVEMENT_SPEED);
    }
}
