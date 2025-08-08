package org.allaymc.server.block.component.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.eventbus.event.Event;
import org.allaymc.api.item.ItemStack;

/**
 * @author daoge_cmd
 */
@Getter
@AllArgsConstructor
public final class CBlockOnPunchEvent extends Event {
    private final Block currentBlock;
    private final BlockFace blockFace;
    private final ItemStack usedItem;
    private final Entity entity;
}
