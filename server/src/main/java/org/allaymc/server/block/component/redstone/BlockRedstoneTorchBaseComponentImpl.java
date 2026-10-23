package org.allaymc.server.block.component.redstone;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.property.enums.TorchFacingDirection;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.math.position.Position3i;
import org.allaymc.api.world.Dimension;
import org.allaymc.server.block.component.BlockBaseComponentImpl;
import org.joml.Vector3ic;

import java.time.Duration;

import static org.allaymc.api.block.property.type.BlockPropertyTypes.TORCH_FACING_DIRECTION;

/**
 * {@code BlockRedstoneTorchBaseComponentImpl} inverts the power detected at a torch's
 * supporting block and exposes directional output for the lit variant. Neighbor
 * notifications request a delayed input check, while loss of a valid attachment surface
 * asks the torch to break. Variant replacement preserves the attachment direction.
 *
 * <p>
 * Input sampling combines direct weak power with strong power entering surrounding
 * solid blocks. Conducted sampling excludes wire and a designated return face so stored
 * dust signals do not feed the torch through that path. Position exclusions use object
 * equality on offset coordinate vectors; a dimension-bearing position wrapper need not
 * compare equal at identical coordinates. Output changes notify the torch's own
 * neighbors, the supporting block's neighbors, and the neighborhood above, where lit
 * torches emit strong power.
 *
 * @author daoge_cmd
 */
public class BlockRedstoneTorchBaseComponentImpl extends BlockBaseComponentImpl {

    /**
     * {@code UPDATE_DELAY} postpones input reevaluation by two game ticks so neighbor
     * notifications sample the support after the requested delay.
     */
    protected static final Duration UPDATE_DELAY = Duration.ofMillis(100);

    /**
     * {@code lit} identifies this component's registered torch variant. Power queries
     * and delayed transitions use that fixed identity rather than a mutable light
     * property.
     */
    protected final boolean lit;

    /**
     * Creates a redstone torch component with the supplied fixed lit-variant identity.
     */
    public BlockRedstoneTorchBaseComponentImpl(BlockType<? extends BlockBehavior> blockType, boolean lit) {
        super(blockType);
        this.lit = lit;
    }

    /**
     * Places a torch after selecting its attachment encoding and checking support for
     * player placement. A {@code null} {@code placementInfo} preserves the supplied
     * state and skips support validation; accepted placement then notifies direct and
     * second-order neighbors.
     */
    @Override
    public boolean place(Dimension dimension, BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        if (placementInfo != null) {
            TorchFacingDirection facing = calculateTorchFacing(placementInfo);
            blockState = blockState.setPropertyValue(TORCH_FACING_DIRECTION, facing);

            BlockFace attachedFace = getAttachedFace(facing);
            Vector3ic attachedPos = attachedFace.offsetPos(placeBlockPos);
            BlockState attachedState = dimension.getBlockState(attachedPos);
            if (!canSupportTorch(attachedState, attachedFace)) {
                return false;
            }
        }

        if (!dimension.setBlockState(placeBlockPos.x(), placeBlockPos.y(), placeBlockPos.z(), blockState, placementInfo)) {
            return false;
        }

        TorchFacingDirection facing = blockState.getPropertyValue(TORCH_FACING_DIRECTION);
        updateSecondOrderNeighbors(dimension, placeBlockPos, facing);
        return true;
    }

    /**
     * Checks attachment support after inherited neighbor handling and otherwise
     * schedules delayed input evaluation. Every surviving neighbor notification
     * requests a new deadline, so an existing position-based update can be replaced by
     * this request.
     */
    @Override
    public void onNeighborUpdate(Block block, Block neighbor, BlockFace face, BlockState oldNeighborState) {
        super.onNeighborUpdate(block, neighbor, face, oldNeighborState);

        TorchFacingDirection facing = block.getPropertyValue(TORCH_FACING_DIRECTION);
        BlockFace attachedFace = getAttachedFace(facing);

        if (face == attachedFace && !canSupportTorch(neighbor.getBlockState(), attachedFace)) {
            block.breakBlock();
            return;
        }

        block.getDimension().getBlockUpdateManager().scheduleBlockUpdateInDelay(
                block.getPosition(), UPDATE_DELAY
        );
    }

    /**
     * Reevaluates the support's input and switches variant when the torch's fixed light
     * identity disagrees with the required inverted output. Matching lit/input
     * combinations leave the current torch type unchanged.
     */
    @Override
    public void onScheduledUpdate(Block block) {
        boolean attachedPowered = isAttachedBlockPowered(block);

        if (lit && attachedPowered) {
            switchState(block, false);
        } else if (!lit && !attachedPowered) {
            switchState(block, true);
        }
    }

    /**
     * Notifies direct and second-order circuits after inherited replacement handling
     * finishes. The old snapshot's attachment direction identifies the former
     * supporting block whose neighbors may need to observe the changed torch output.
     */
    @Override
    public void afterReplaced(Block oldBlock, BlockState newBlockState, PlayerInteractInfo placementInfo) {
        super.afterReplaced(oldBlock, newBlockState, placementInfo);

        var dimension = oldBlock.getDimension();
        var pos = oldBlock.getPosition();
        TorchFacingDirection facing = oldBlock.getPropertyValue(TORCH_FACING_DIRECTION);
        updateSecondOrderNeighbors(dimension, pos, facing);
    }

    /**
     * Returns maximum weak power from a lit torch except toward its supporting block.
     * Unlit variants and the attachment face return {@code 0}, preventing direct output
     * into that support direction.
     */
    @Override
    public int getWeakPower(Block block, BlockFace face) {
        if (!lit) {
            return 0;
        }

        TorchFacingDirection facing = block.getPropertyValue(TORCH_FACING_DIRECTION);
        BlockFace attachedFace = getAttachedFace(facing);
        if (face == attachedFace) {
            return 0;
        }

        return MAX_REDSTONE_POWER;
    }

    /**
     * Returns maximum strong power only upward from a lit torch. Unlit variants and
     * other queried faces return {@code 0}, independently of the torch's wall or floor
     * attachment encoding.
     */
    @Override
    public int getStrongPower(Block block, BlockFace face) {
        if (!lit) {
            return 0;
        }

        if (face == BlockFace.UP) {
            return MAX_REDSTONE_POWER;
        }
        return 0;
    }

    /**
     * Checks input power around the torch's supporting position while requesting
     * exclusion of the torch position. The delegated exclusion uses vector object
     * equality, so coordinate-equivalent values with different runtime classes may not
     * be excluded.
     */
    protected boolean isAttachedBlockPowered(Block block) {
        TorchFacingDirection facing = block.getPropertyValue(TORCH_FACING_DIRECTION);
        BlockFace attachedFace = getAttachedFace(facing);
        Vector3ic attachedPos = attachedFace.offsetPos(block.getPosition());

        var dimension = block.getDimension();
        BlockState attachedState = dimension.getBlockState(attachedPos);

        return isPoweredExcluding(dimension, attachedPos, block.getPosition());
    }

    /**
     * Returns whether surrounding sources power the supplied position through accepted
     * direct or conducted paths. Each neighbor is compared with {@code excludePos}
     * using object equality before its weak power is queried. A solid neighbor also
     * permits a conducted strong-power search that excludes the return face.
     *
     * <p>
     * The conducted helper omits redstone wire and applies the same position-equality
     * rule. Offset vectors are {@code Vector3i} instances, whose equality requires
     * matching runtime classes; an equal-coordinate {@code Position3i} exclusion
     * therefore does not match. The method returns on the first positive accepted
     * observation.
     */
    protected boolean isPoweredExcluding(Dimension dimension, Vector3ic blockPos, Vector3ic excludePos) {
        for (BlockFace face : BlockFace.VALUES) {
            Vector3ic neighborPos = face.offsetPos(blockPos);

            // Offset vectors use class-sensitive equality. A dimension-bearing
            // exclusion can share these coordinates without matching, leaving that source in the scan.
            if (neighborPos.equals(excludePos)) {
                continue;
            }

            BlockState neighborState = dimension.getBlockState(neighborPos);
            Block neighborBlock = new Block(neighborState, new Position3i(neighborPos, dimension));

            int weakPower = neighborState.getBehavior().getWeakPower(neighborBlock, face.opposite());
            if (weakPower > 0) {
                return true;
            }

            if (neighborState.getBlockStateData().isSolid()) {
                int powerThroughBlock = getStrongPowerIntoBlockExcluding(dimension, neighborPos, excludePos, face.opposite());
                if (powerThroughBlock > 0) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Combines strong power entering a solid block while omitting one face, a position,
     * and redstone wire sources. {@code excludeFace} points back toward the position
     * whose input is being evaluated, while {@code excludePos} is compared using vector
     * object equality.
     *
     * <p>
     * Wire is skipped even though its behavior can report strong power, preventing that
     * conducted route from turning the torch off through neighboring solid blocks.
     * Other sources contribute their maximum directed strong power.
     * Coordinate-equivalent exclusions of a different runtime vector class may remain
     * in this scan.
     */
    protected int getStrongPowerIntoBlockExcluding(Dimension dimension, Vector3ic blockPos, Vector3ic excludePos, BlockFace excludeFace) {
        int maxPower = 0;
        for (BlockFace face : BlockFace.VALUES) {
            if (face == excludeFace) continue;

            Vector3ic checkPos = face.offsetPos(blockPos);
            if (checkPos.equals(excludePos)) {
                continue;
            }

            BlockState state = dimension.getBlockState(checkPos);

            // Dust can report strong output, but this conducted input route
            // deliberately omits it so neighboring charged blocks do not feed this torch through wire.
            if (state.getBlockType() == BlockTypes.REDSTONE_WIRE) {
                continue;
            }

            Block checkBlock = new Block(state, new Position3i(checkPos, dimension));

            int strongPower = state.getBehavior().getStrongPower(checkBlock, face.opposite());
            maxPower = Math.max(maxPower, strongPower);
        }
        return maxPower;
    }

    /**
     * Requests the selected lit or unlit torch type while preserving attachment
     * direction. Direct and second-order notifications follow the request without
     * checking whether the dimension accepted the replacement state.
     */
    protected void switchState(Block block, boolean newLit) {
        var dimension = block.getDimension();
        var pos = block.getPosition();
        TorchFacingDirection facing = block.getPropertyValue(TORCH_FACING_DIRECTION);

        BlockType<?> newType = newLit ? BlockTypes.REDSTONE_TORCH : BlockTypes.UNLIT_REDSTONE_TORCH;
        BlockState newState = newType.getDefaultState().setPropertyValue(TORCH_FACING_DIRECTION, facing);

        dimension.setBlockState(pos.x(), pos.y(), pos.z(), newState);

        updateSecondOrderNeighbors(dimension, pos, facing);
    }

    /**
     * Notifies circuits around the torch, its supporting block, and the block above.
     * Support and upper neighborhoods can receive a changed conducted signal beyond the
     * torch's immediate six neighboring positions.
     */
    protected void updateSecondOrderNeighbors(Dimension dimension, Vector3ic pos, TorchFacingDirection facing) {
        dimension.updateAround(pos);
        BlockFace attachedFace = getAttachedFace(facing);
        dimension.updateAround(attachedFace.offsetPos(pos));
        dimension.updateAround(BlockFace.UP.offsetPos(pos));
    }

    /**
     * Returns the attachment encoding for the clicked placement face. A clicked ceiling
     * face selects the floor-standing {@code TOP} encoding instead, so later support
     * validation examines the block below rather than allowing ceiling attachment.
     */
    protected TorchFacingDirection calculateTorchFacing(PlayerInteractInfo placementInfo) {
        BlockFace clickedFace = placementInfo.blockFace();

        return switch (clickedFace) {
            case UP -> TorchFacingDirection.TOP;
            // Ceiling clicks have no torch attachment encoding; the standing
            // fallback still passes through placement's normal support check against the block below.
            case DOWN -> TorchFacingDirection.TOP;
            case NORTH -> TorchFacingDirection.SOUTH;
            case SOUTH -> TorchFacingDirection.NORTH;
            case WEST -> TorchFacingDirection.EAST;
            case EAST -> TorchFacingDirection.WEST;
        };
    }

    /**
     * Returns the direction from the torch toward its supporting block. {@code TOP} and
     * {@code UNKNOWN} select the block below; wall encodings select their corresponding
     * horizontal support face.
     */
    protected BlockFace getAttachedFace(TorchFacingDirection facing) {
        return switch (facing) {
            case TOP, UNKNOWN -> BlockFace.DOWN;
            case WEST -> BlockFace.WEST;   // Attached to west wall
            case EAST -> BlockFace.EAST;   // Attached to east wall
            case NORTH -> BlockFace.NORTH;
            case SOUTH -> BlockFace.SOUTH;
        };
    }

    /**
     * Returns whether the support surface is sufficient for the specified attachment
     * direction. Standing torches require only the upper face's center to be full,
     * while wall placement requires a full opposite collision face.
     */
    protected boolean canSupportTorch(BlockState blockState, BlockFace attachedFace) {
        BlockFace supportFace = attachedFace.opposite();
        var shape = blockState.getBlockStateData().collisionShape();
        return supportFace == BlockFace.UP ? shape.isCenterFull(BlockFace.UP) : shape.isFull(supportFace);
    }
}
