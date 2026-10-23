package org.allaymc.server.block.component.container;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.component.BlockBlockEntityHolderComponent;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.property.type.BlockPropertyTypes;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.blockentity.interfaces.BlockEntityHopper;
import org.allaymc.api.world.Dimension;
import org.allaymc.server.block.component.BlockBaseComponentImpl;
import org.allaymc.server.component.annotation.Dependency;
import org.joml.Vector3ic;

/**
 * {@code BlockHopperBaseComponentImpl} orients hopper outputs and mirrors redstone
 * power into the disabled-state property. Player placement faces away from the
 * clicked surface and converts an upward result to downward. The associated block
 * entity owns item transfer, while this component exposes inventory fullness to
 * comparators.
 *
 * @author ClexaGod | daoge_cmd
 */
public class BlockHopperBaseComponentImpl extends BlockBaseComponentImpl {

    /**
     * {@code blockEntityHolderComponent} resolves hopper inventory state.
     * Component initialization injects this dependency before the composed block
     * behavior handles its world callbacks.
     */
    @Dependency
    private BlockBlockEntityHolderComponent<BlockEntityHopper> blockEntityHolderComponent;

    /**
     * Creates this container block component for the supplied registered block
     * type.
     */
    public BlockHopperBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    /**
     * Places a hopper pointing away from the clicked surface. An upward output is
     * replaced with downward, and a {@code null} placement context writes the
     * supplied state directly without player property processing.
     */
    @Override
    public boolean place(Dimension dimension, BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        if (placementInfo == null) {
            return dimension.setBlockState(placeBlockPos.x(), placeBlockPos.y(), placeBlockPos.z(), blockState);
        }

        var processedState = processBlockProperties(blockState, placeBlockPos, placementInfo);
        var facing = placementInfo.blockFace().opposite();
        if (facing == BlockFace.UP) {
            facing = BlockFace.DOWN;
        }

        processedState = processedState.setPropertyValue(BlockPropertyTypes.FACING_DIRECTION, facing.ordinal());
        return dimension.setBlockState(placeBlockPos.x(), placeBlockPos.y(), placeBlockPos.z(), processedState, placementInfo);
    }

    /**
     * Runs shared neighbor handling and mirrors current power into
     * {@code TOGGLE_BIT}. The bit denotes the disabled state used by hopper
     * processing, and it is written only when the observed power differs.
     */
    @Override
    public void onNeighborUpdate(Block block, Block neighbor, BlockFace face, BlockState oldNeighborState) {
        super.onNeighborUpdate(block, neighbor, face, oldNeighborState);

        var powered = block.isPowered();
        var currentlyDisabled = block.getPropertyValue(BlockPropertyTypes.TOGGLE_BIT);

        if (powered != currentlyDisabled) {
            block.updateBlockProperty(BlockPropertyTypes.TOGGLE_BIT, powered);
        }
    }

    /**
     * Returns whether this container supplies its own comparator signal.
     */
    @Override
    public boolean hasComparatorInputOverride() {
        return true;
    }

    /**
     * Returns the hopper inventory's comparator fullness signal. A holder that
     * supplies {@code null} produces zero; otherwise the inventory performs the
     * fullness calculation for its current contents.
     */
    @Override
    public int getComparatorInputOverride(Block block) {
        var hopper = blockEntityHolderComponent.getBlockEntity(block.getPosition());
        if (hopper == null) {
            return 0;
        }
        return hopper.getContainer().calculateComparatorSignal();
    }
}
