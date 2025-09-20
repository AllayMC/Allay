package org.allaymc.server.block.component;

import lombok.AllArgsConstructor;
import org.allaymc.api.block.component.BlockStrippableComponent;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.server.block.data.BlockId;

/**
 * @author IWareQ
 */
@AllArgsConstructor
public class BlockStrippableComponentImpl implements BlockStrippableComponent {
    protected final BlockId strippedType;

    @Override
    public BlockState getStrippedBlockState(BlockState unstrippedBlockState) {
        return strippedType.getBlockType().copyPropertyValuesFrom(unstrippedBlockState);
    }

    @Override
    public boolean isStripped(BlockState blockState) {
        return blockState.getBlockType().equals(strippedType.getBlockType());
    }
}
