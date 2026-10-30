package org.allaymc.server.block.component.portal;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.property.enums.PortalAxis;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.item.ItemStack;
import org.allaymc.server.block.component.BlockBaseComponentImpl;

import java.util.Set;

import static org.allaymc.api.block.property.type.BlockPropertyTypes.PORTAL_AXIS;

/**
 * {@code BlockPortalBaseComponentImpl} marks entities standing in Nether portal
 * blocks and removes portal cells that lose their immediate plane support. The
 * entity component owns portal dwell time and eventual teleport; this component
 * sets {@code inNetherPortal} only when the entity's portal cooldown is clear.
 *
 * @author daoge_cmd
 */
public class BlockPortalBaseComponentImpl extends BlockBaseComponentImpl {

    /**
     * Creates this block behavior component for the supplied registered block
     * type.
     */
    public BlockPortalBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    /**
     * Marks the entity as inside a Nether portal when cooldown is clear. A
     * positive cooldown leaves the flag unchanged, and the entity tick handles
     * dwell time and teleport separately.
     */
    @Override
    public void onEntityInside(Block block, Entity entity) {
        if (entity.getPortalCooldown() > 0) {
            return;
        }
        entity.setInNetherPortal(true);
    }

    /**
     * Runs shared handling and removes this portal cell if its immediate plane
     * neighbors are invalid. The check accepts portal or obsidian neighbors and
     * does not rescan a complete rectangular frame.
     */
    @Override
    public void onNeighborUpdate(Block block, Block neighbor, BlockFace face, BlockState oldNeighborState) {
        super.onNeighborUpdate(block, neighbor, face, oldNeighborState);

        if (!isFrameIntact(block)) {
            block.getDimension().setBlockState(block.getPosition(), BlockTypes.AIR.getDefaultState());
        }
    }

    /**
     * Returns whether the four immediate neighbors in the portal plane are
     * obsidian or portal blocks. {@code PORTAL_AXIS} selects the horizontal pair;
     * an unsupported axis returns {@code false}.
     */
    private boolean isFrameIntact(Block block) {
        var axis = block.getPropertyValue(PORTAL_AXIS);

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

    /**
     * Returns whether the adjacent block is obsidian or another portal cell.
     */
    private boolean isObsidianOrPortal(Block block, BlockFace face) {
        var neighbor = block.offsetPos(face);
        var type = neighbor.getBlockType();
        return type == BlockTypes.OBSIDIAN || type == BlockTypes.PORTAL;
    }

    /**
     * Returns an empty drop set for the Nether portal interior.
     */
    @Override
    public Set<ItemStack> getDrops(Block block, ItemStack usedItem, Entity entity) {
        return Set.of();
    }
}
