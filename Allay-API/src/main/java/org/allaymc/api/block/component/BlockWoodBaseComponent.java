package org.allaymc.api.block.component;

import org.allaymc.api.block.type.BlockState;

/**
 * Allay Project 2024/8/18
 *
 * @author daoge_cmd
 */
public interface BlockWoodBaseComponent extends BlockBaseComponent {
    BlockState getStrippedBlockState(BlockState unstrippedBlockState);

    boolean isStripped(BlockState blockState);
}
