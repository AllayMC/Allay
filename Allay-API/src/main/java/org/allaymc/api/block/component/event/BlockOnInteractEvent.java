package org.allaymc.api.block.component.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.event.Event;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.world.Dimension;
import org.joml.Vector3fc;
import org.joml.Vector3ic;

/**
 * Allay Project 2023/9/23
 *
 * @author daoge_cmd
 */
@Getter
@AllArgsConstructor
public final class BlockOnInteractEvent extends Event {
    private final EntityPlayer player;
    private final ItemStack itemStack;
    private final Dimension dimension;
    private final Vector3ic blockPos;
    private final Vector3ic placeBlockPos;
    private final Vector3fc clickPos;
    private final BlockFace blockFace;
    @Setter
    private boolean success;
}
