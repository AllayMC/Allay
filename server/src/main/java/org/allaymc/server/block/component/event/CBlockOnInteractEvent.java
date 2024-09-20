package org.allaymc.server.block.component.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.eventbus.event.Event;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.world.Dimension;

/**
 * @author daoge_cmd
 */
@Getter
@AllArgsConstructor
public final class CBlockOnInteractEvent extends Event {
    private final ItemStack itemStack;
    private final Dimension dimension;
    private final PlayerInteractInfo interactInfo;
    @Setter
    private boolean success;
}
