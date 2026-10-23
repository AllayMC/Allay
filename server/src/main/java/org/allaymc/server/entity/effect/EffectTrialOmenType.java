package org.allaymc.server.entity.effect;

import org.allaymc.api.utils.identifier.Identifier;

import java.awt.*;

/**
 * {@code EffectTrialOmenType} represents the built-in trial omen effect with
 * fixed registry metadata. This implementation inherits empty lifecycle
 * callbacks and supplies metadata without trial-trigger logic; its harmful
 * classification remains the base default. The constructor registers its
 * numeric ID, namespaced identifier, and particle color.
 *
 * @author IWareQ
 */
public class EffectTrialOmenType extends AbstractEffectType {
    /**
     * Creates and registers the built-in trial omen effect metadata.
     * Registration requires an initialized mutable effect registry and can
     * replace existing associations under this type's fixed numeric or
     * namespaced key.
     */
    public EffectTrialOmenType() {
        super(31, new Identifier("minecraft:trial_omen"), new Color(0x16a6a6));
    }
}
