package org.allaymc.api.block.component.common;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.component.BlockComponent;
import org.allaymc.api.block.function.*;
import org.allaymc.api.block.type.BlockType;

/**
 * Allay Project 2023/4/8
 *
 * @author daoge_cmd
 */
public interface BlockBaseComponent extends OnNeighborUpdate, OnRandomUpdate, Place, OnPlace, OnInteract, OnReplace, OnScheduledUpdate, BlockComponent {
    BlockType<? extends BlockBehavior> getBlockType();
}
