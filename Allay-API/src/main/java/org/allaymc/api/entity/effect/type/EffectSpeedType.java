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
public class EffectSpeedType extends AbstractEffectType {
    public static final EffectSpeedType SPEED_TYPE = new EffectSpeedType();

    private EffectSpeedType() {
        super(1, new Identifier("minecraft:speed"), new Color(51, 235, 255));
    }

    @Override
    public void onAdd(Entity entity, EffectInstance effectInstance) {
        if (!(entity instanceof EntityPlayer player)) return;
        var amplifier = effectInstance.getAmplifier() + 1;
        var speed = 1 + amplifier * 0.2f;
        player.setMovementSpeed(player.getMovementSpeed() * speed);
    }

    @Override
    public void onRemove(Entity entity, EffectInstance effectInstance) {
        if (!(entity instanceof EntityPlayer player)) return;
        var amplifier = effectInstance.getAmplifier() + 1;
        var speed = 1 + amplifier * 0.2f;
        player.setMovementSpeed(player.getMovementSpeed() / speed);
    }
}
