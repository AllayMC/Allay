package org.allaymc.api.entity.effect.type;

import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.effect.AbstractEffectType;
import org.allaymc.api.entity.effect.EffectInstance;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.utils.Identifier;

/**
 * @author daoge_cmd
 */
public class EffectSlownessType extends AbstractEffectType {
    EffectSlownessType() {
        super(2, new Identifier("minecraft:slowness"), true);
    }

    @Override
    public void onAdd(Entity entity, EffectInstance effectInstance) {
        if (!(entity instanceof EntityPlayer player)) return;
        var level = effectInstance.getLevel();
        var slowness = 1 - level * 0.15f;
        if (slowness <= 0) slowness = 0.00001f;
        player.setMovementSpeed(player.getMovementSpeed() * slowness);
    }

    @Override
    public void onRemove(Entity entity, EffectInstance effectInstance) {
        if (!(entity instanceof EntityPlayer player)) return;
        var level = effectInstance.getLevel();
        var slowness = 1 - level * 0.15f;
        if (slowness <= 0) slowness = 0.00001f;
        player.setMovementSpeed(player.getMovementSpeed() / slowness);
    }
}
