package org.allaymc.server.block.component;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.component.BlockConcretePowderBaseComponent;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.world.Dimension;
import org.allaymc.server.block.data.BlockId;
import org.joml.Vector3ic;

import java.util.function.Supplier;

/**
 * {@code BlockConcretePowderBaseComponentImpl} hardens concrete powder beside liquids
 * and resolves its solid counterpart lazily. Placement and neighbor updates share the
 * inherited adjacency helper, which excludes the lower face and currently accepts any
 * liquid, including lava, before selecting the solid default state.
 *
 * @author IWareQ
 */
public class BlockConcretePowderBaseComponentImpl extends BlockBaseComponentImpl implements BlockConcretePowderBaseComponent {
    /**
     * {@code solidBlockType} resolves the solid counterpart on every lookup, allowing
     * block type initialization to finish before the supplier is evaluated.
     */
    protected final Supplier<BlockType<?>> solidBlockType;

    /**
     * Creates powder behavior with a lazily resolved solid counterpart supplier.
     * {@code solidBlockTypeSupplier} is retained and evaluated each time by
     * {@code getSolidBlock}, rather than being resolved during this constructor.
     */
    public BlockConcretePowderBaseComponentImpl(BlockType<? extends BlockBehavior> blockType, Supplier<BlockType<?>> solidBlockTypeSupplier) {
        super(blockType);
        this.solidBlockType = solidBlockTypeSupplier;
    }

    /**
     * Creates powder behavior that resolves its solid counterpart through a block
     * identifier. {@code solidBlockId} supplies a deferred lookup, so the
     * corresponding block type is requested only when needed.
     */
    public BlockConcretePowderBaseComponentImpl(BlockType<? extends BlockBehavior> blockType, BlockId solidBlockId) {
        this(blockType, solidBlockId::getBlockType);
    }

    /**
     * Forwards the neighbor event and replaces the powder with its solid default
     * state beside liquid. The shared adjacency helper excludes the lower face and
     * accepts both water and lava.
     */
    @Override
    public void onNeighborUpdate(Block block, Block neighbor, BlockFace face, BlockState oldNeighborState) {
        super.onNeighborUpdate(block, neighbor, face, oldNeighborState);

        if (hasAdjacentWater(block.getDimension(), block.getPosition())) {
            block.getDimension().setBlockState(block.getPosition(), getSolidBlock().getDefaultState());
        }
    }

    /**
     * Selects the solid default state when liquid is adjacent before ordinary
     * placement processing. Without adjacent liquid the supplied powder state is
     * retained, and {@code placementInfo} may be {@code null}.
     */
    @Override
    public boolean place(Dimension dimension, BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        if (hasAdjacentWater(dimension, placeBlockPos)) {
            blockState = getSolidBlock().getDefaultState();
        }

        return super.place(dimension, blockState, placeBlockPos, placementInfo);
    }

    /**
     * Returns the solid concrete type resolved by the configured supplier.
     */
    @Override
    public BlockType<?> getSolidBlock() {
        return solidBlockType.get();
    }
}
