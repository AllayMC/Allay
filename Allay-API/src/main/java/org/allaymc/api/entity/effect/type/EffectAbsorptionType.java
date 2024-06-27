package org.allaymc.api.entity.effect.type;

import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.effect.AbstractEffectType;
import org.allaymc.api.entity.effect.EffectInstance;
import org.allaymc.api.utils.Identifier;

import java.awt.*;

/**
 * Allay Project 2023/10/27
 *
 * @author daoge_cmd
 */
public class EffectAbsorptionType extends AbstractEffectType {
    public static final EffectAbsorptionType ABSORPTION_TYPE = new EffectAbsorptionType();

    private EffectAbsorptionType() {
        super(22, new Identifier("minecraft:absorption"), new Color(37, 82, 165));
    }

    @Override
    public void onAdd(Entity entity, EffectInstance effectInstance) {
        var amplifier = effectInstance.getAmplifier();
        int newValue = (amplifier + 1) << 2;
        if (newValue > entity.getAbsorption()) {
            entity.setAbsorption(newValue);
        }
    }

    @Override
    public void onRemove(Entity entity, EffectInstance effectInstance) {
        entity.setAbsorption(0);
    }
}
