package org.allaymc.api.item.component;

import org.allaymc.api.data.VanillaBlockId;

/**
 * Allay Project 2024/8/15
 *
 * @author daoge_cmd
 */
public interface ItemSignBaseComponent extends ItemBaseComponent {
    VanillaBlockId getWallSignId();

    VanillaBlockId getStandingSignId();
}
