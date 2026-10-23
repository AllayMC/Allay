package org.allaymc.api.block;

import org.allaymc.api.block.component.BlockBaseComponent;
import org.allaymc.api.block.component.BlockStateDataComponent;

/**
 * {@code BlockBehavior} combines the base operations and state metadata exposed by
 * a block type's components. A block type shares this behavior across its states,
 * and callers pass the relevant state or positioned block to operations that depend
 * on placement or properties.
 *
 * @author daoge_cmd
 */
public interface BlockBehavior extends
        BlockBaseComponent,
        BlockStateDataComponent {
}
