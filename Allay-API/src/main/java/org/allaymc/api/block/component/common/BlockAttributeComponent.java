package org.allaymc.api.block.component.common;

import org.allaymc.api.block.component.BlockComponent;
import org.allaymc.api.block.type.BlockState;

/**
 * Allay Project 2023/3/25
 *
 * @author daoge_cmd
 */
public interface BlockAttributeComponent extends BlockComponent {
    BlockAttributes getBlockAttributes(BlockState blockState);
}
