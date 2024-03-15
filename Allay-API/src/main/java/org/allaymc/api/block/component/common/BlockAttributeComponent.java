package org.allaymc.api.block.component.common;

import org.allaymc.api.block.component.BlockComponent;
import org.allaymc.api.block.type.BlockState;

/**
 * Block Attributes Component, which are used to get the block {@link BlockAttributes}
 * <p>
 * Allay Project 2023/3/25
 *
 * @author daoge_cmd | CoolLoong
 */
public interface BlockAttributeComponent extends BlockComponent {
    BlockAttributes getBlockAttributes(BlockState blockState);
}
