package org.allaymc.api.entity.effect.type;

import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.component.player.EntityPlayerHungerComponent;
import org.allaymc.api.entity.effect.AbstractEffectType;
import org.allaymc.api.entity.effect.EffectInstance;
import org.allaymc.api.utils.Identifier;

/**
 * Allay Project 2023/10/27
 *
 * @author daoge_cmd
 */
public class EffectHungerType extends AbstractEffectType {
    public static final EffectHungerType HUNGER_TYPE = new EffectHungerType();

    private EffectHungerType() {
        super(17, new Identifier("minecraft:hunger"), true);
    }

    @Override
    public void onTick(Entity entity, EffectInstance effectInstance) {
        if (entity instanceof EntityPlayerHungerComponent hungerComponent) {
            hungerComponent.exhaust(0.005f * effectInstance.getLevel());
        }
    }
}
