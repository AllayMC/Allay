package org.allaymc.server.entity.effect;

import org.allaymc.api.entity.effect.EffectInstance;
import org.allaymc.api.entity.interfaces.EntityLiving;
import org.allaymc.api.utils.identifier.Identifier;

import java.awt.*;

/**
 * {@code EffectAbsorptionType} represents absorption metadata and manages the
 * entity's extra health pool through lifecycle callbacks. Addition replaces the
 * current pool with four health points per level, while removal clears the
 * entire pool rather than preserving unrelated absorption changes.
 *
 * @author daoge_cmd
 */
public class EffectAbsorptionType extends AbstractEffectType {
    /**
     * Creates and registers the built-in absorption effect metadata.
     * Registration requires an initialized mutable effect registry and can
     * replace existing associations under this type's fixed numeric or
     * namespaced key.
     */
    public EffectAbsorptionType() {
        super(22, new Identifier("minecraft:absorption"), new Color(0x2552a5));
    }

    /**
     * Replaces the entity's absorption pool with four health points per
     * supplied level. Existing absorption is overwritten, and same-type
     * instance replacement does not trigger this callback in the living
     * component.
     */
    @Override
    public void onAdd(EntityLiving entity, EffectInstance effectInstance) {
        // Each effect level contributes four health points to the replacement absorption pool.
        entity.setAbsorption(effectInstance.getLevel() * 4);
    }

    /**
     * Clears the entity's entire absorption pool after accepted effect removal.
     * This callback does not subtract only the original contribution, so
     * absorption gained from other sources is also cleared.
     */
    @Override
    public void onRemove(EntityLiving entity, EffectInstance effectInstance) {
        entity.setAbsorption(0);
    }
}
