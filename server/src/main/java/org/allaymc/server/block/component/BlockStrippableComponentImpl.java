package org.allaymc.server.block.component;

import lombok.AllArgsConstructor;
import org.allaymc.api.block.component.BlockStrippableComponent;
import org.allaymc.api.block.data.BlockId;
import org.allaymc.api.block.type.BlockState;

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
