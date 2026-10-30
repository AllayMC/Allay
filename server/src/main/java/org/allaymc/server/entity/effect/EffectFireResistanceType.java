package org.allaymc.server.entity.effect;

import org.allaymc.api.utils.identifier.Identifier;

import java.awt.*;

/**
 * {@code EffectFireResistanceType} represents the built-in fire resistance
 * effect with fixed registry metadata. Living-entity and hot-block checks
 * consume this effect directly when deciding fire damage eligibility; this
 * implementation inherits empty lifecycle callbacks. The constructor registers
 * its numeric ID, namespaced identifier, and particle color.
 *
 * @author daoge_cmd
 */
public class EffectFireResistanceType extends AbstractEffectType {
    /**
     * Creates and registers the built-in fire resistance effect metadata.
     * Registration requires an initialized mutable effect registry and can
     * replace existing associations under this type's fixed numeric or
     * namespaced key.
     */
    public EffectFireResistanceType() {
        super(12, new Identifier("minecraft:fire_resistance"), new Color(0xff9900));
    }
}
