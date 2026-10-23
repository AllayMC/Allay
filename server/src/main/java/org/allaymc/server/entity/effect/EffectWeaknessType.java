package org.allaymc.server.entity.effect;

import org.allaymc.api.utils.identifier.Identifier;

import java.awt.*;

/**
 * {@code EffectWeaknessType} represents the built-in harmful weakness effect
 * with fixed registry metadata. Living-entity attacker damage calculations read
 * this effect level directly, while this implementation inherits empty
 * lifecycle callbacks. The constructor registers its numeric ID, namespaced
 * identifier, and particle color.
 *
 * @author daoge_cmd
 */
public class EffectWeaknessType extends AbstractEffectType {
    /**
     * Creates and registers the built-in harmful weakness effect metadata.
     * Registration requires an initialized mutable effect registry and can
     * replace existing associations under this type's fixed numeric or
     * namespaced key.
     */
    public EffectWeaknessType() {
        super(18, new Identifier("minecraft:weakness"), new Color(0x484d48), true);
    }
}
