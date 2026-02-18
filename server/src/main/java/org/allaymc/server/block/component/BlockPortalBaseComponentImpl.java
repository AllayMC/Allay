package org.allaymc.server.block.component;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.property.enums.PortalAxis;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.item.ItemStack;

import java.util.Set;

import static org.allaymc.api.block.property.type.BlockPropertyTypes.PORTAL_AXIS;

/**
 * Block component for nether portal blocks.
 *
 * <p>Sets the entity's inNetherPortal flag when an entity stands inside the block.
 * The actual teleportation is handled by {@link org.allaymc.server.entity.component.EntityBaseComponentImpl#tickPortal()}.
 *
 * <p>Breaks (turns to air) when the surrounding obsidian frame is no longer intact.
 *
 * @author daoge_cmd
 */
public class BlockPortalBaseComponentImpl extends BlockBaseComponentImpl {

    public BlockPortalBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    @Override
    public void onEntityInside(Block block, Entity entity) {
        if (entity.getPortalCooldown() > 0) {
            return;
        }
        entity.setInNetherPortal(true);
    }

    @Override
    public void onNeighborUpdate(Block block, Block neighbor, BlockFace face, BlockState oldNeighborState) {
        super.onNeighborUpdate(block, neighbor, face, oldNeighborState);

        if (!isFrameIntact(block)) {
            block.getDimension().setBlockState(block.getPosition(), BlockTypes.AIR.getDefaultState());
        }
    }

    private boolean isFrameIntact(Block block) {
        var axis = block.getPropertyValue(PORTAL_AXIS);

        // Check adjacent blocks along the portal plane — they must be obsidian or portal
        if (axis == PortalAxis.X) {
            return isObsidianOrPortal(block, BlockFace.EAST) &&
                   isObsidianOrPortal(block, BlockFace.WEST) &&
                   isObsidianOrPortal(block, BlockFace.UP) &&
                   isObsidianOrPortal(block, BlockFace.DOWN);
        } else if (axis == PortalAxis.Z) {
            return isObsidianOrPortal(block, BlockFace.NORTH) &&
                   isObsidianOrPortal(block, BlockFace.SOUTH) &&
                   isObsidianOrPortal(block, BlockFace.UP) &&
                   isObsidianOrPortal(block, BlockFace.DOWN);
        }
        return false;
    }

    private boolean isObsidianOrPortal(Block block, BlockFace face) {
        var neighbor = block.offsetPos(face);
        var type = neighbor.getBlockType();
        return type == BlockTypes.OBSIDIAN || type == BlockTypes.PORTAL;
    }

    @Override
    public Set<ItemStack> getDrops(Block block, ItemStack usedItem, Entity entity) {
        return Set.of();
    }
}
