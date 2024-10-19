package org.allaymc.api.block.component.data;

import org.allaymc.api.block.component.BlockComponent;
import org.allaymc.api.block.type.BlockState;

/**
 * @author daoge_cmd | CoolLoong
 */
public interface BlockStateDataComponent extends BlockComponent {
    BlockStateData getBlockStateData(BlockState blockState);
}
