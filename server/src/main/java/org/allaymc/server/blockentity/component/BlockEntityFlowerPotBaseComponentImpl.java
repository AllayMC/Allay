package org.allaymc.server.blockentity.component;

import lombok.Getter;
import org.allaymc.api.block.data.BlockTags;
import org.allaymc.api.block.property.type.BlockPropertyTypes;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.blockentity.BlockEntityInitInfo;
import org.allaymc.api.blockentity.component.BlockEntityFlowerPotBaseComponent;
import org.allaymc.api.eventbus.EventHandler;
import org.allaymc.api.math.MathUtils;
import org.allaymc.api.utils.NBTIO;
import org.allaymc.server.block.component.event.CBlockOnReplaceEvent;
import org.cloudburstmc.nbt.NbtMap;

/**
 * {@code BlockEntityFlowerPotBaseComponentImpl} stores a flower pot's optional
 * plant and synchronizes accepted mutations with the pot's update property and
 * viewers. Plant validation uses the pottable-plant block tag. An accepted
 * assignment retains the new plant before updating the block property and
 * sending current state, so later failures leave earlier mutations applied.
 *
 * <p>
 * NBT loading decodes a supplied plant state directly without repeating tag
 * validation or synchronizing the update bit. Block replacement drops a
 * retained plant before clearing its reference. These paths have different
 * observer and cleanup effects from the ordinary setter; callers must choose
 * the operation appropriate to an in-world mutation, saved-state load, or block
 * removal.
 *
 * @author Cdm2883
 */
public class BlockEntityFlowerPotBaseComponentImpl extends BlockEntityBaseComponentImpl implements BlockEntityFlowerPotBaseComponent {

    /**
     * {@code TAG_PLANT_BLOCK} names the compound containing the plant's
     * block-state representation for flower-pot NBT loading and saving.
     */
    protected static final String TAG_PLANT_BLOCK = "PlantBlock";

    /**
     * Optional retained plant state.
     */
    @Getter
    private BlockState plantBlock;

    /**
     * Creates common flower-pot state with no retained plant block.
     */
    public BlockEntityFlowerPotBaseComponentImpl(BlockEntityInitInfo initInfo) {
        super(initInfo);
    }

    /**
     * Stores an accepted plant, updates the pot's update bit, and informs
     * viewers. {@code null} clears the plant; other states require the
     * pottable-plant tag. Rejected states return {@code false} before mutation.
     * An accepted state is assigned before world-property updates and delivery,
     * so a later failure does not restore the previous plant. This method
     * returns {@code true} after both updates have been requested and does not
     * create an item drop for replaced plants.
     */
    @Override
    public boolean trySetPlantBlock(BlockState block) {
        if (block != null && !isValidPlant(block)) {
            return false;
        }

        plantBlock = block;

        var position = getPosition();
        var dimension = position.dimension();
        // Retain the new plant before updating the world property and
        // broadcasting, so this sequence does not announce occupancy while
        // still storing the previous plant.
        dimension.updateBlockProperty(BlockPropertyTypes.UPDATE_BIT, block != null, position);
        sendBlockEntityToViewers();
        return true;
    }

    /**
     * Returns whether the supplied block type carries the pottable-plant tag.
     */
    private boolean isValidPlant(BlockState block) {
        return block.getBlockType().hasBlockTag(BlockTags.POTTABLE_PLANT);
    }

    /**
     * Drops a retained plant as an item when the pot block is replaced. An
     * empty pot produces no drop; the retained plant is cleared only after the
     * dimension's item-drop call returns.
     */
    @EventHandler
    protected void onBlockReplace(CBlockOnReplaceEvent event) {
        if (plantBlock == null) {
            return;
        }

        var current = event.getCurrentBlock();
        current.getDimension().dropItem(plantBlock.toItemStack(), MathUtils.center(current.getPosition()));
        plantBlock = null;
    }

    /**
     * Builds common NBT and includes a plant compound only for occupied pots.
     * An empty pot returns the inherited save map directly; a present plant
     * uses its full block-state NBT representation.
     */
    @Override
    public NbtMap saveNBT() {
        var savedNbt = super.saveNBT();
        if (plantBlock == null) {
            return savedNbt;
        }

        return savedNbt
                .toBuilder()
                .putCompound(TAG_PLANT_BLOCK, this.plantBlock.getBlockStateNBT())
                .build();
    }

    /**
     * Loads common state and decodes a supplied plant compound directly. An
     * absent compound preserves the retained plant, and this path does not
     * revalidate the pottable tag or synchronize the pot's update bit.
     */
    @Override
    public void loadNBT(NbtMap nbt) {
        super.loadNBT(nbt);
        nbt.listenForCompound(TAG_PLANT_BLOCK, value -> this.plantBlock = NBTIO.getAPI().fromBlockStateNBT(value));
    }
}
