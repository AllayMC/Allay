package org.allaymc.api.entity.effect.type;

import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.effect.AbstractEffectType;
import org.allaymc.api.entity.effect.EffectInstance;
import org.allaymc.api.utils.Identifier;

/**
 * Allay Project 2023/10/27
 *
 * @author daoge_cmd
 */
public class EffectAbsorptionType extends AbstractEffectType {
    public static final EffectAbsorptionType ABSORPTION_TYPE = new EffectAbsorptionType();

    private EffectAbsorptionType() {
        super(22, new Identifier("minecraft:absorption"));
    }

    @Override
    public void onAdd(Entity entity, EffectInstance effectInstance) {
        entity.setAbsorption(effectInstance.getLevel() * 4);
    }

    @Override
    public void onRemove(Entity entity, EffectInstance effectInstance) {
        entity.setAbsorption(0);
    }
}
