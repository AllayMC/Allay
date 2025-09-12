package org.allaymc.api.entity.data;

import org.allaymc.api.annotation.MinecraftVersionSensitive;
import org.allaymc.api.entity.Entity;

/**
 * AnimateAction contains all the available animate actions in {@link Entity}.
 *
 * @author daoge_cmd
 */
@MinecraftVersionSensitive
public enum AnimateAction {
    NO_ACTION,
    SWING_ARM,
    WAKE_UP,
    CRITICAL_HIT,
    MAGIC_CRITICAL_HIT
}
