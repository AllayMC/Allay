package org.allaymc.server.block.component;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.component.BlockBlockEntityHolderComponent;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.property.type.BlockPropertyTypes;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.blockentity.interfaces.BlockEntityChiseledBookshelf;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.data.ItemTags;
import org.allaymc.api.player.GameMode;
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.sound.ChiseledBookshelfSound;
import org.allaymc.server.component.annotation.Dependency;
import org.joml.Vector3fc;
import org.joml.Vector3ic;

/**
 * {@code BlockChiseledBookshelfBaseComponentImpl} maps front-face bookshelf clicks
 * to six stored book slots. Player placement faces the shelf toward the player,
 * while interactions remove an occupied slot or insert one tagged book into an
 * empty slot. The block entity supplies the last interacted slot for comparator
 * output.
 *
 * @author daoge_cmd
 */
public class BlockChiseledBookshelfBaseComponentImpl extends BlockBaseComponentImpl {
    /**
     * {@code blockEntityHolderComponent} resolves slot containers and comparator
     * state. Component initialization injects this holder before the bookshelf
     * behavior accepts placement or interaction calls.
     */
    @Dependency
    private BlockBlockEntityHolderComponent<BlockEntityChiseledBookshelf> blockEntityHolderComponent;

    /**
     * Creates the chiseledbookshelf block component for the supplied registered
     * block type.
     */
    public BlockChiseledBookshelfBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    /**
     * Places the shelf facing opposite the player's horizontal direction. A
     * {@code null} placement context retains the supplied state and uses the
     * direct dimension write without player placement information.
     */
    @Override
    public boolean place(Dimension dimension, BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        if (placementInfo == null) {
            return dimension.setBlockState(placeBlockPos.x(), placeBlockPos.y(), placeBlockPos.z(), blockState);
        }

        blockState = blockState.setPropertyValue(
                BlockPropertyTypes.DIRECTION_4,
                placementInfo.player().getHorizontalFace().opposite().getHorizontalIndex()
        );
        return dimension.setBlockState(placeBlockPos.x(), placeBlockPos.y(), placeBlockPos.z(), blockState, placementInfo);
    }

    /**
     * Runs shared interaction handlers, then handles the clicked front slot. An
     * occupied slot is cleared before its item is returned to the player; an empty
     * slot accepts one copied tagged book and consumes the held item outside
     * creative mode.
     */
    @Override
    public boolean onInteract(ItemStack itemStack, Dimension dimension, PlayerInteractInfo interactInfo) {
        if (super.onInteract(itemStack, dimension, interactInfo)) {
            return true;
        }

        if (interactInfo == null) {
            return false;
        }

        var pos = interactInfo.clickedBlockPos();
        var blockState = dimension.getBlockState(pos);
        var facing = BlockFace.fromHorizontalIndex(blockState.getPropertyValue(BlockPropertyTypes.DIRECTION_4));
        if (interactInfo.blockFace() != facing) {
            return false;
        }

        var slot = calculateSlot(facing, interactInfo.clickedPos());
        var container = blockEntityHolderComponent.getBlockEntity(pos.x(), pos.y(), pos.z(), dimension).getContainer();
        var slotItem = container.getItemStack(slot);
        var clickedBlock = new Block(dimension, pos);
        if (!slotItem.isEmptyOrAir()) {
            container.clearSlot(slot);
            interactInfo.player().tryAddItem(slotItem);
            clickedBlock.addSound(new ChiseledBookshelfSound(ChiseledBookshelfSound.Type.PICKUP, slotItem.hasEnchantments()));
            return true;
        }

        if (!itemStack.getItemType().hasItemTag(ItemTags.CHISELED_BOOKSHELF_BOOK)) {
            return false;
        }

        var inserted = itemStack.copy(true);
        inserted.setCount(1);
        container.setItemStack(slot, inserted);
        if (interactInfo.player().getGameMode() != GameMode.CREATIVE) {
            interactInfo.player().tryConsumeItemInHand();
        }
        clickedBlock.addSound(new ChiseledBookshelfSound(ChiseledBookshelfSound.Type.INSERT, inserted.hasEnchantments()));
        return true;
    }

    /**
     * Returns whether this bookshelf supplies its own comparator signal.
     */
    @Override
    public boolean hasComparatorInputOverride() {
        return true;
    }

    /**
     * Returns the block entity's last interacted slot for comparator output. The
     * holder's {@code null} result produces zero; this method applies no
     * additional slot offset or signal scaling.
     */
    @Override
    public int getComparatorInputOverride(Block block) {
        var blockEntity = blockEntityHolderComponent.getBlockEntity(block.getPosition());
        return blockEntity == null ? 0 : blockEntity.getLastInteractedSlot();
    }

    /**
     * Returns a six-slot index from the front face and local hit coordinates. The
     * upper half maps to the first row, and {@code calculateColumn} orders each
     * row for the supplied facing.
     */
    private int calculateSlot(BlockFace facing, Vector3fc clickedPos) {
        // The vertical midpoint separates the upper and lower book rows.
        var row = clickedPos.y() >= 0.5f ? 0 : 1;
        return row * 3 + calculateColumn(facing, clickedPos.x(), clickedPos.z());
    }

    /**
     * Returns the left-to-right column for the supplied horizontal facing. The
     * projection uses local block coordinates, wraps negative projections into the
     * unit interval, and divides the face into thirds.
     */
    private int calculateColumn(BlockFace facing, float clickX, float clickZ) {
        var offset = facing.rotateYCCW().getOffset();
        // Project onto the face's left-to-right axis before applying identical
        // slot boundaries, so rotated shelves keep the same visible column order.
        float distance = clickX * offset.x() + clickZ * offset.z();
        if (distance < 0) {
            distance += 1.0f;
        }
        // Equal thirds map the visible face to three columns.
        return distance < (1 / 3f) ? 0 : distance < (2 / 3f) ? 1 : 2;
    }
}
