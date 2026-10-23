package org.allaymc.api.block.component;

import org.allaymc.api.block.data.BlockStateData;
import org.allaymc.api.block.type.BlockState;

/**
 * {@code BlockStateDataComponent} supplies physical and rendering metadata for a
 * block state through the owning block behavior. Implementations may share one data
 * object across states or select different data for individual property
 * combinations, so callers query the state they intend to use.
 *
 * @author daoge_cmd | CoolLoong
 */
public interface BlockStateDataComponent extends BlockComponent {
    /**
     * Returns the physical and rendering metadata associated with the supplied
     * state. Callers must supply a state supported by this component;
     * implementations may return shared data rather than a newly allocated object.
     */
    BlockStateData getBlockStateData(BlockState blockState);
}
