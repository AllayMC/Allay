package org.allaymc.server.entity.effect;

import org.allaymc.api.utils.identifier.Identifier;

import java.awt.*;

/**
 * {@code EffectNauseaType} represents the built-in harmful nausea effect with
 * fixed registry metadata. This implementation inherits empty lifecycle
 * callbacks and defines metadata without additional server-side tick or damage
 * processing. The constructor registers its numeric ID, namespaced identifier,
 * and particle color.
 *
 * @author daoge_cmd
 */
public class EffectNauseaType extends AbstractEffectType {
    /**
     * Creates and registers the built-in harmful nausea effect metadata.
     * Registration requires an initialized mutable effect registry and can
     * replace existing associations under this type's fixed numeric or
     * namespaced key.
     */
    public EffectNauseaType() {
        super(9, new Identifier("minecraft:nausea"), new Color(0x551d4a), true);
    }
}
