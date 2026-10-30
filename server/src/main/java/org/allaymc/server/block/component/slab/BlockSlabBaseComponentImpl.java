package org.allaymc.server.block.component.slab;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.component.BlockSlabBaseComponent;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.property.enums.MinecraftVerticalHalf;
import org.allaymc.api.block.property.type.BlockPropertyTypes;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.world.Dimension;
import org.allaymc.server.block.component.BlockBaseComponentImpl;
import org.allaymc.server.block.data.BlockId;
import org.joml.Vector3ic;

import java.util.function.Supplier;

/**
 * {@code BlockSlabBaseComponentImpl} selects slab halves, combines matching slabs,
 * and controls liquid passage through filled faces. A deferred supplier resolves the
 * double-slab counterpart, while click direction and height choose whether to place a
 * half slab or replace an existing matching half.
 *
 * <p>
 * Placement distinguishes the clicked block from the target position: a vertical
 * click on an exposed matching half replaces the clicked block with a double slab,
 * while other cases write a selected half at the target. The separate combination
 * path compares existing and clicked halves using height and reports success after an
 * attempted conversion without checking that write. Liquid flow remains open only
 * through faces not completely covered by the resulting collision shape.
 *
 * @author daoge_cmd
 */
public class BlockSlabBaseComponentImpl extends BlockBaseComponentImpl implements BlockSlabBaseComponent {
    /**
     * {@code doubleSlabType} resolves the double-slab counterpart when needed,
     * allowing block type registration to complete after this component is
     * constructed.
     */
    protected final Supplier<BlockType<?>> doubleSlabType;

    /**
     * Creates slab behavior with a retained double-slab type supplier. The supplier
     * is evaluated by counterpart lookup, so construction does not require that
     * related block type to be available immediately.
     */
    public BlockSlabBaseComponentImpl(BlockType<? extends BlockBehavior> blockType, Supplier<BlockType<?>> doubleSlabTypeSupplier) {
        super(blockType);
        this.doubleSlabType = doubleSlabTypeSupplier;
    }

    /**
     * Creates slab behavior using a deferred lookup for its double-slab identifier.
     * {@code doubleSlabId} resolves the counterpart only when placement or drop
     * handling needs the related block type.
     */
    public BlockSlabBaseComponentImpl(BlockType<? extends BlockBehavior> blockType, BlockId doubleSlabId) {
        this(blockType, doubleSlabId::getBlockType);
    }

    /**
     * Places a slab half or combines a matching clicked half into its double
     * counterpart. A {@code null} {@code placementInfo} writes the supplied state
     * directly without selecting a half.
     *
     * <p>
     * Upper clicks merge matching bottom halves, lower clicks merge matching top
     * halves, and other vertical cases place the corresponding new half at the
     * target. Side clicks use the click height to select top or bottom. Each branch
     * returns its dimension write result, and merging uses the double counterpart's
     * default state.
     */
    @Override
    public boolean place(Dimension dimension, BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        if (placementInfo == null) {
            return dimension.setBlockState(placeBlockPos.x(), placeBlockPos.y(), placeBlockPos.z(), blockState);
        }

        var face = placementInfo.blockFace();
        var clickedBlockPos = placementInfo.clickedBlockPos();
        var clickedPos = placementInfo.clickedPos();
        var clickedBlock = placementInfo.getClickedBlock();
        switch (face) {
            case UP -> {
                if (clickedBlock.getBlockType() == this.blockType && clickedBlock.getPropertyValue(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF) == MinecraftVerticalHalf.BOTTOM) {
                    return dimension.setBlockState(clickedBlockPos, getDoubleSlabBlockType().getDefaultState());
                } else {
                    return dimension.setBlockState(placeBlockPos, blockState.setPropertyValue(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF, MinecraftVerticalHalf.BOTTOM));
                }
            }
            case DOWN -> {
                if (clickedBlock.getBlockType() == this.blockType && clickedBlock.getPropertyValue(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF) == MinecraftVerticalHalf.TOP) {
                    return dimension.setBlockState(clickedBlockPos, getDoubleSlabBlockType().getDefaultState());
                } else {
                    return dimension.setBlockState(placeBlockPos, blockState.setPropertyValue(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF, MinecraftVerticalHalf.TOP));
                }
            }
            default -> {
                // The half-height boundary separates upper and lower side placements.
                return dimension.setBlockState(placeBlockPos, blockState.setPropertyValue(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF, clickedPos.y() > 0.5f ? MinecraftVerticalHalf.TOP : MinecraftVerticalHalf.BOTTOM));
            }
        }
    }

    // TODO: Reconcile click-face handling with place() and report rejected double-slab writes from combine().
    /**
     * Attempts conversion when the existing block is this slab type and its half
     * differs from the clicked height. {@code placementInfo} must be present; the
     * method reports {@code true} after attempting the double-slab write without
     * checking acceptance.
     */
    @Override
    public boolean combine(Dimension dimension, BlockState blockState, Vector3ic combineBlockPos, PlayerInteractInfo placementInfo) {
        var existingBlock = dimension.getBlockState(combineBlockPos);
        if (existingBlock.getBlockType() != this.blockType) {
            return false;
        }

        var existingHalf = existingBlock.getPropertyValue(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF);
        // The half-height boundary separates upper and lower side clicks.
        var clickedHalf = placementInfo.clickedPos().y() > 0.5f ? MinecraftVerticalHalf.TOP : MinecraftVerticalHalf.BOTTOM;
        if (existingHalf != clickedHalf) {
            dimension.setBlockState(combineBlockPos, getDoubleSlabBlockType().getDefaultState());
            return true;
        }

        return false;
    }

    /**
     * Returns whether the slab collision shape leaves the requested face less than
     * full. A full face blocks liquid through that direction, while partial or empty
     * faces permit flow.
     */
    @Override
    public boolean canLiquidFlowIntoSide(BlockState blockState, BlockFace blockFace) {
        return !blockState.getBlockStateData().collisionShape().isFull(blockFace);
    }

    /**
     * Returns the double-slab block type resolved by the retained supplier.
     */
    @Override
    public BlockType<?> getDoubleSlabBlockType() {
        return doubleSlabType.get();
    }
}
