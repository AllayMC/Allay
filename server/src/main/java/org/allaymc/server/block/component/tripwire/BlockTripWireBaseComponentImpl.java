package org.allaymc.server.block.component.tripwire;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.api.world.Dimension;
import org.allaymc.server.block.component.BlockBaseComponentImpl;
import org.joml.Vector3ic;

import java.time.Duration;
import java.util.Set;

import static org.allaymc.api.block.property.type.BlockPropertyTypes.*;

/**
 * Implementation of tripwire (string) block behavior.
 * <p>
 * Tripwire blocks are placed between two tripwire hooks to form a detection line.
 * When an entity passes through attached tripwire, it triggers the connected hooks.
 * Breaking tripwire without shears triggers the hooks; breaking with shears disarms it silently.
 *
 * @author daoge_cmd
 */
public class BlockTripWireBaseComponentImpl extends BlockBaseComponentImpl {

    /**
     * Interval between entity detection checks.
     */
    private static final Duration CHECK_INTERVAL = Duration.ofMillis(500);

    public BlockTripWireBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    @Override
    public void afterPlaced(Block oldBlock, BlockState newBlockState, PlayerInteractInfo placementInfo) {
        super.afterPlaced(oldBlock, newBlockState, placementInfo);

        // Notify nearby hooks about the new tripwire
        Block newBlock = new Block(newBlockState, oldBlock.getPosition());
        updateAdjacentHooks(newBlock);
    }

    @Override
    public void onEntityInside(Block block, Entity entity) {
        // Only trigger if not already powered
        if (block.getPropertyValue(POWERED_BIT)) {
            return;
        }

        // Set powered state
        Dimension dimension = block.getDimension();
        Vector3ic pos = block.getPosition();
        dimension.updateBlockProperty(POWERED_BIT, true, pos);

        // Notify neighbors of redstone change
        dimension.updateAround(pos);

        // Notify adjacent hooks
        Block updatedBlock = new Block(dimension, pos);
        updateAdjacentHooks(updatedBlock);

        // Schedule update to check if entity has left
        dimension.getBlockUpdateManager().scheduleBlockUpdateInDelay(pos, CHECK_INTERVAL);
    }

    @Override
    public void onScheduledUpdate(Block block) {
        if (!block.getPropertyValue(POWERED_BIT)) {
            return;
        }

        Dimension dimension = block.getDimension();
        Vector3ic pos = block.getPosition();

        // Check if any entity is still on the tripwire
        boolean entityFound = false;
        var detectionAABB = block.getBlockStateData().computeOffsetShape(pos).unionAABB();

        for (Entity entity : dimension.getEntities().values()) {
            if (entity.getOffsetAABB().intersectsAABB(detectionAABB)) {
                entityFound = true;
                break;
            }
        }

        if (entityFound) {
            // Entity still present, reschedule check
            dimension.getBlockUpdateManager().scheduleBlockUpdateInDelay(pos, CHECK_INTERVAL);
        } else {
            // No entity, depower the tripwire
            dimension.updateBlockProperty(POWERED_BIT, false, pos);

            // Notify neighbors of redstone change
            dimension.updateAround(pos);

            // Notify adjacent hooks
            Block updatedBlock = new Block(dimension, pos);
            updateAdjacentHooks(updatedBlock);
        }
    }

    @Override
    public void onBreak(Block block, ItemStack usedItem, Entity entity) {
        Dimension dimension = block.getDimension();
        Vector3ic pos = block.getPosition();

        // Check if broken with shears
        if (usedItem != null && usedItem.getItemType() == ItemTypes.SHEARS) {
            // Disarm the tripwire silently
            dimension.updateBlockProperty(DISARMED_BIT, true, pos);
            Block updatedBlock = new Block(dimension, pos);
            updateAdjacentHooks(updatedBlock);
        } else {
            // Trigger the hooks (broken without shears)
            dimension.updateBlockProperty(POWERED_BIT, true, pos);
            dimension.updateAround(pos);
            Block updatedBlock = new Block(dimension, pos);
            updateAdjacentHooks(updatedBlock);
        }

        super.onBreak(block, usedItem, entity);
    }

    @Override
    public Set<ItemStack> getDrops(Block block, ItemStack usedItem, Entity entity) {
        // Tripwire always drops string
        if (getBlockType().getItemType() != null) {
            return Set.of(ItemTypes.STRING.createItemStack());
        }
        return Set.of();
    }

    /**
     * Updates adjacent tripwire hooks about state changes.
     *
     * @param block the tripwire block
     */
    private void updateAdjacentHooks(Block block) {
        Dimension dimension = block.getDimension();
        Vector3ic pos = block.getPosition();

        // Search in all horizontal directions for hooks
        for (BlockFace face : BlockFace.getHorizontalBlockFaces()) {
            for (int i = 1; i < 42; i++) {
                Vector3ic scanPos = face.offsetPos(pos, i);
                BlockState scannedState = dimension.getBlockState(scanPos);
                BlockType<?> scannedType = scannedState.getBlockType();

                if (scannedType == BlockTypes.TRIPWIRE_HOOK) {
                    // Found a hook - check if it faces this direction
                    int hookDirection = scannedState.getPropertyValue(DIRECTION_4);
                    BlockFace hookFacing = BlockFace.fromHorizontalIndex(hookDirection);

                    if (hookFacing == face.opposite()) {
                        // Hook faces toward this tripwire, schedule an update on the hook
                        dimension.getBlockUpdateManager().scheduleBlockUpdateInDelay(scanPos, Duration.ofMillis(10));
                    }
                    break;
                }

                if (scannedType != BlockTypes.TRIP_WIRE) {
                    // Non-tripwire block, stop searching in this direction
                    break;
                }
            }
        }
    }
}
