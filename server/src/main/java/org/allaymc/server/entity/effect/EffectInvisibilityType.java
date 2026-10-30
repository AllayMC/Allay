package org.allaymc.server.entity.effect;

import org.allaymc.api.entity.effect.EffectInstance;
import org.allaymc.api.entity.interfaces.EntityLiving;
import org.allaymc.api.utils.identifier.Identifier;

import java.awt.*;

/**
 * {@code EffectInvisibilityType} represents invisibility metadata and controls
 * the entity's invisible flag through lifecycle callbacks. Addition enables
 * invisibility and accepted removal disables it; the callback pair does not
 * remember whether another source enabled invisibility before the effect was
 * added.
 *
 * @author daoge_cmd
 */
public class EffectInvisibilityType extends AbstractEffectType {
    /**
     * Creates and registers the built-in invisibility effect metadata.
     * Registration requires an initialized mutable effect registry and can
     * replace existing associations under this type's fixed numeric or
     * namespaced key.
     */
    public EffectInvisibilityType() {
        super(14, new Identifier("minecraft:invisibility"), new Color(0xf6f6f6));
    }

    /**
     * Enables the entity's invisible flag when this type is newly added. The
     * callback stores no previous flag value, so later cleanup cannot restore
     * an earlier source's invisibility state.
     */
    @Override
    public void onAdd(EntityLiving entity, EffectInstance effectInstance) {
        entity.setInvisible(true);
    }

    /**
     * Disables the entity's invisible flag after accepted effect removal. The
     * callback does not check for other invisibility sources or restore the
     * flag value that preceded the original addition.
     */
    @Override
    public void onRemove(EntityLiving entity, EffectInstance effectInstance) {
        entity.setInvisible(false);
    }
}
