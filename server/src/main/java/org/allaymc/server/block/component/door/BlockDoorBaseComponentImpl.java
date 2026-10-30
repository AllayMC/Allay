package org.allaymc.server.block.component.door;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.data.BlockTags;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.math.MathUtils;
import org.allaymc.api.math.position.Position3i;
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.sound.DoorSound;
import org.allaymc.server.block.component.BlockBaseComponentImpl;
import org.joml.Vector3i;
import org.joml.Vector3ic;

import java.util.Set;

import static org.allaymc.api.block.data.BlockTags.POWER_SOURCE;
import static org.allaymc.api.block.property.type.BlockPropertyTypes.*;

/**
 * {@code BlockDoorBaseComponentImpl} manages two-block door placement, support,
 * paired opening, and lower-half drops. The lower and upper states share facing,
 * hinge, and opening properties while their upper-half flags identify their
 * positions. Player placement requires an upward click, solid lower support, and
 * replaceable space above; placement without player context uses a default facing.
 *
 * <p>
 * The two halves are written and updated in sequence without rollback. Support checks
 * remove invalid halves, and redstone combines power at both positions so either half
 * can open the pair. Only changes involving tagged power sources reevaluate redstone
 * opening, preserving manual state after unrelated neighbor updates. Breaking an
 * upper half delegates a break to the lower position, while ordinary drop selection
 * returns items only for the lower half.
 *
 * @author Dhaiven
 */
public class BlockDoorBaseComponentImpl extends BlockBaseComponentImpl {
    /**
     * Creates door base behavior for the supplied block type.
     */
    public BlockDoorBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    /**
     * Places the lower and upper door states after support and clearance checks.
     * Player context requires an upward click; absent context selects south before
     * the door's orientation transform.
     *
     * <p>
     * Neighboring doors and transparency can select a left hinge by setting
     * {@code DOOR_HINGE_BIT} to {@code true}. Otherwise placement retains the
     * incoming hinge bit rather than clearing it. The lower state is written first,
     * and only acceptance permits the upper write; a rejected upper write leaves the
     * accepted lower state in place. The returned conjunction reports both writes
     * without compensating an incomplete pair.
     */
    @Override
    public boolean place(Dimension dimension, BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        if (placementInfo != null && placementInfo.blockFace() != BlockFace.UP) {
            return false;
        }

        var downBlockState = dimension.getBlockState(BlockFace.DOWN.offsetPos(placeBlockPos));
        if (!downBlockState.getBlockStateData().isSolid()) {
            return false;
        }

        var upBlockState = dimension.getBlockState(BlockFace.UP.offsetPos(placeBlockPos));
        if (!upBlockState.getBlockType().hasBlockTag(BlockTags.REPLACEABLE)) {
            return false;
        }

        BlockFace face = BlockFace.SOUTH;
        if (placementInfo != null) {
            face = placementInfo.player().getHorizontalFace();
        }
        blockState = blockState.setPropertyValue(MINECRAFT_CARDINAL_DIRECTION, face.rotateY().toMinecraftCardinalDirection());

        var leftBlockState = dimension.getBlockState(face.rotateYCCW().offsetPos(placeBlockPos));
        var rightBlockState = dimension.getBlockState(face.rotateY().offsetPos(placeBlockPos));

        var hingeOnLeft = leftBlockState.getBlockType() == getBlockType() ||
                          (!rightBlockState.getBlockStateData().isTransparent() && leftBlockState.getBlockStateData().isTransparent());
        if (hingeOnLeft) {
            blockState = blockState.setPropertyValue(DOOR_HINGE_BIT, true);
        }
        // Write the lower half first and attempt the upper only after acceptance. A
        // failed upper write leaves the lower half without compensation.
        return dimension.setBlockState(placeBlockPos.x(), placeBlockPos.y(), placeBlockPos.z(), blockState.setPropertyValue(UPPER_BLOCK_BIT, false), placementInfo) &&
               dimension.setBlockState(placeBlockPos.x(), placeBlockPos.y() + 1, placeBlockPos.z(), blockState.setPropertyValue(UPPER_BLOCK_BIT, true), placementInfo);
    }

    /**
     * Forwards the update, breaks unsupported halves, and reevaluates qualifying
     * power-source changes. {@code oldNeighborState} may be {@code null}; surviving
     * halves use the new neighbor's power-source tag when old context is unavailable.
     */
    @Override
    public void onNeighborUpdate(Block block, Block neighbor, BlockFace face, BlockState oldNeighborState) {
        super.onNeighborUpdate(block, neighbor, face, oldNeighborState);

        var keep = true;
        if (face == BlockFace.UP) {
            keep = block.getPropertyValue(UPPER_BLOCK_BIT) || isSameDoor(neighbor);
        } else if (face == BlockFace.DOWN) {
            keep = block.getPropertyValue(UPPER_BLOCK_BIT) ? isSameDoor(neighbor) : neighbor.getBlockStateData().isSolid();
        }

        if (!keep) {
            block.breakBlock();
            return;
        }
        // Only new or removed tagged power sources reevaluate opening, so ordinary
        // neighbor changes do not close doors that players opened manually.
        boolean isNewPowerSource = neighbor.getBlockType().hasBlockTag(POWER_SOURCE);
        boolean wasOldPowerSource = oldNeighborState != null && oldNeighborState.getBlockType().hasBlockTag(POWER_SOURCE);
        
        if (isNewPowerSource || wasOldPowerSource) {
            checkRedstonePower(block);
        }
    }

    /**
     * Updates both halves when power at either position changes the requested
     * opening. The other position is derived from {@code UPPER_BLOCK_BIT}, and the
     * stronger of the two measured signals decides the result.
     *
     * <p>
     * An unchanged opening avoids all writes and sound. A changed opening updates
     * this half and then the other half before playing one door sound. Those property
     * writes are separate and their results are not used for rollback, so this method
     * does not guarantee an atomic pair update.
     */
    protected void checkRedstonePower(Block block) {
        var dimension = block.getDimension();
        var pos = block.getPosition();
        var isUpperBlock = block.getPropertyValue(UPPER_BLOCK_BIT);
        var otherPos = isUpperBlock
                ? BlockFace.DOWN.offsetPos(pos)
                : BlockFace.UP.offsetPos(pos);
        var powerAtThis = block.getPower();
        var otherPosition = new Position3i(otherPos, dimension);
        var powerAtOther = block.getDimension().getPowerAt(otherPosition);
        // Either door half can receive power independently. Use the stronger
        // observation so an unpowered half cannot close a pair powered at its
        // counterpart.
        var maxPower = Math.max(powerAtThis, powerAtOther);

        var shouldBeOpen = maxPower > 0;
        var isCurrentlyOpen = block.getPropertyValue(OPEN_BIT);

        if (shouldBeOpen != isCurrentlyOpen) {
            block.updateBlockProperty(OPEN_BIT, shouldBeOpen);
            // Mirror the requested opening after updating this half. The separate
            // writes do not provide rollback if the counterpart update fails.
            dimension.updateBlockProperty(OPEN_BIT, shouldBeOpen, otherPos);
            block.addSound(new DoorSound(block.getBlockState(), shouldBeOpen));
        }
    }

    /**
     * Returns whether the neighboring block has this exact door type.
     */
    protected boolean isSameDoor(Block neighbor) {
        return neighbor.getBlockType() == getBlockType();
    }

    /**
     * Offers the interaction to companion components before toggling both door
     * halves. Missing context returns {@code false}; the manual path currently
     * requires {@code clickedBlockPos} to be a {@code Vector3i}.
     *
     * <p>
     * The clicked state determines the counterpart position and the opposite opening
     * value. The method writes the clicked half and then the other half, plays a
     * sound at the clicked position, and reports success without checking the
     * property-write results. It does not validate or repair the counterpart before
     * applying that update.
     */
    @Override
    public boolean onInteract(ItemStack itemStack, Dimension dimension, PlayerInteractInfo interactInfo) {
        if (super.onInteract(itemStack, dimension, interactInfo)) return true;
        if (interactInfo == null) return false;

        var pos = (Vector3i) interactInfo.clickedBlockPos();
        var blockState = dimension.getBlockState(pos);

        var otherPos = blockState.getPropertyValue(UPPER_BLOCK_BIT)
                ? BlockFace.DOWN.offsetPos(pos)
                : BlockFace.UP.offsetPos(pos);

        var isOpen = !blockState.getPropertyValue(OPEN_BIT);

        dimension.updateBlockProperty(OPEN_BIT, isOpen, pos);
        dimension.updateBlockProperty(OPEN_BIT, isOpen, otherPos);
        dimension.addSound(MathUtils.center(pos), new DoorSound(blockState, isOpen));
        return true;
    }

    /**
     * Breaks the lower half when this callback handles an upper door half, then
     * invokes inherited drop handling. The delegated lower break uses a {@code null}
     * tool and retains the responsible entity.
     */
    @Override
    public void onBreak(Block block, ItemStack usedItem, Entity entity) {
        if (block.getPropertyValue(UPPER_BLOCK_BIT)) {
            block.getDimension().breakBlock(BlockFace.DOWN.offsetPos(block.getPosition()), null, entity);
        }
        super.onBreak(block, usedItem, entity);
    }

    /**
     * Returns no ordinary drops for an upper door half and inherited drops for the
     * lower half. This keeps paired breaks from producing two ordinary door items
     * through this selection.
     */
    @Override
    public Set<ItemStack> getDrops(Block block, ItemStack usedItem, Entity entity) {
        return block.getPropertyValue(UPPER_BLOCK_BIT) ? Set.of() : super.getDrops(block, usedItem, entity);
    }
}
