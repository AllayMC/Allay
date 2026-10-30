package org.allaymc.server.entity.effect;

import org.allaymc.api.utils.identifier.Identifier;

import java.awt.*;

/**
 * {@code EffectStrengthType} represents the built-in strength effect with fixed
 * registry metadata. Living-entity attacker damage calculations read this
 * effect level directly, while this implementation inherits empty lifecycle
 * callbacks. The constructor registers its numeric ID, namespaced identifier,
 * and particle color.
 *
 * @author daoge_cmd
 */
public class EffectStrengthType extends AbstractEffectType {
    /**
     * Creates and registers the built-in strength effect metadata. Registration
     * requires an initialized mutable effect registry and can replace existing
     * associations under this type's fixed numeric or namespaced key.
     */
    public EffectStrengthType() {
        super(5, new Identifier("minecraft:strength"), new Color(0xffc700));
    }
}
