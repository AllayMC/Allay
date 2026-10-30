package org.allaymc.server.block.component;

import lombok.AllArgsConstructor;
import org.allaymc.api.block.component.BlockStrippableComponent;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.server.block.data.BlockId;

import java.util.function.Supplier;

/**
 * {@code BlockStrippableComponentImpl} resolves a configured stripped counterpart and
 * copies input property values into its state lookup. The supplier is retained for
 * deferred resolution, so compatible block types can finish registration before
 * stripping occurs; incompatible property values can leave the resulting state
 * absent.
 *
 * @author IWareQ
 */
@AllArgsConstructor
public class BlockStrippableComponentImpl implements BlockStrippableComponent {
    /**
     * {@code strippedType} resolves the counterpart for each stripping operation. The
     * Lombok constructor retains the supplier instead of evaluating it during
     * construction.
     */
    protected final Supplier<BlockType<?>> strippedType;

    /**
     * Creates a stripping component with a deferred block-identifier lookup. The
     * supplied {@code strippedType} is retained through its lookup function, and
     * stripping operations resolve the actual block type later.
     */
    public BlockStrippableComponentImpl(BlockId strippedType) {
        this(strippedType::getBlockType);
    }

    /**
     * Looks up a stripped state using all property values from the input state.
     * Callers must supply properties compatible with the stripped type; an invalid
     * counterpart state lookup can return {@code null}.
     */
    @Override
    public BlockState getStrippedBlockState(BlockState unstrippedBlockState) {
        return strippedType.get().copyPropertyValuesFrom(unstrippedBlockState);
    }

    /**
     * Returns whether the supplied state belongs to the resolved stripped type.
     */
    @Override
    public boolean isStripped(BlockState blockState) {
        return blockState.getBlockType().equals(strippedType.get());
    }
}
