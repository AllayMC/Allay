package org.allaymc.server.entity.effect;

import org.allaymc.api.utils.identifier.Identifier;

import java.awt.*;

/**
 * {@code EffectJumpBoostType} represents the built-in jump boost effect with
 * fixed registry metadata. Living-entity fall damage calculations read this
 * effect level directly, while this implementation inherits empty lifecycle
 * callbacks. The constructor registers its numeric ID, namespaced identifier,
 * and particle color.
 *
 * @author daoge_cmd
 */
public class EffectJumpBoostType extends AbstractEffectType {
    /**
     * Creates and registers the built-in jump boost effect metadata.
     * Registration requires an initialized mutable effect registry and can
     * replace existing associations under this type's fixed numeric or
     * namespaced key.
     */
    public EffectJumpBoostType() {
        super(8, new Identifier("minecraft:jump_boost"), new Color(0xfdff84));
    }
}
