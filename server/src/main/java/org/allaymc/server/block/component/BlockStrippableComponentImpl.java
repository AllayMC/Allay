package org.allaymc.server.block.component;

import lombok.AllArgsConstructor;
import org.allaymc.api.block.component.BlockStrippableComponent;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.server.block.data.BlockId;

import java.util.function.Supplier;

/**
 * @author IWareQ
 */
@AllArgsConstructor
public class BlockStrippableComponentImpl implements BlockStrippableComponent {
    protected final Supplier<BlockType<?>> strippedType;

    public BlockStrippableComponentImpl(BlockId strippedType) {
        this(strippedType::getBlockType);
    }

    @Override
    public BlockState getStrippedBlockState(BlockState unstrippedBlockState) {
        return strippedType.get().copyPropertyValuesFrom(unstrippedBlockState);
    }

    @Override
    public boolean isStripped(BlockState blockState) {
        return blockState.getBlockType().equals(strippedType.get());
    }
}
