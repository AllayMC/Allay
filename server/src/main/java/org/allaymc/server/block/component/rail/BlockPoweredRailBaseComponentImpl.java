package org.allaymc.server.block.component.rail;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.property.type.BlockPropertyTypes;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.world.Dimension;
import org.joml.Vector3i;
import org.joml.Vector3ic;

/**
 * {@code BlockPoweredRailBaseComponentImpl} manages the active bit for powered and
 * activator rails while sharing straight-rail placement and support checks. Direct
 * redstone power activates a rail immediately. Otherwise the component searches in both
 * connection directions for a directly powered rail of the same type.
 *
 * <p>
 * Each chain search stays on the selected horizontal axis, adjusts height for the
 * current ascent, and tries one lower position when the expected position lacks the
 * same rail type. The search accepts at most eight neighboring rails and does not treat
 * another rail's stored active bit as a power source. A changed result updates
 * {@code RAIL_DATA_BIT} and notifies the local circuit, including the support-side
 * position of an ascending end.
 *
 * @author daoge_cmd
 */
public class BlockPoweredRailBaseComponentImpl extends BlockRailBaseComponentImpl {

    /**
     * {@code MAX_CHAIN_LENGTH} limits each directional search to eight neighboring
     * rails, preventing a directly powered rail farther along the chain from activating
     * this rail.
     */
    protected static final int MAX_CHAIN_LENGTH = 8;

    /**
     * Creates a powered rail component restricted to six non-curved orientation values.
     */
    public BlockPoweredRailBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType, false, BlockPropertyTypes.RAIL_DIRECTION_6);
    }

    /**
     * Applies shared support checks before refreshing the rail's stored powered bit. A
     * live-type check skips rails removed by inherited handling; changed power notifies
     * the rail's surroundings and the adjacent support position of an ascending end.
     */
    @Override
    public void onNeighborUpdate(Block block, Block neighbor, BlockFace face, BlockState oldNeighborState) {
        super.onNeighborUpdate(block, neighbor, face, oldNeighborState);

        // Support handling may remove the rail; do not apply its old powered
        // snapshot to the replacement block now occupying this position.
        if (block.getDimension().getBlockState(block.getPosition()).getBlockType() != blockType) {
            return;
        }

        var dimension = block.getDimension();
        var pos = block.getPosition();
        boolean currentlyPowered = block.getPropertyValue(BlockPropertyTypes.RAIL_DATA_BIT);

        boolean shouldBePowered = checkPowered(dimension, pos, block.getBlockState());

        if (currentlyPowered != shouldBePowered) {
            dimension.updateBlockProperty(BlockPropertyTypes.RAIL_DATA_BIT, shouldBePowered, pos);

            dimension.updateAround(pos);

            RailOrientation orientation = RailOrientation.byMeta(getRailDirection(block.getBlockState()));
            if (orientation.isAscending()) {
                BlockFace ascendFace = orientation.ascendingDirection();
                dimension.updateAround(ascendFace.offsetPos(pos));
            }
        }
    }

    /**
     * Returns whether this position receives direct power or reaches a directly powered
     * same-type rail. Direct power takes precedence and avoids the chain search.
     * Otherwise both horizontal endpoints from {@code blockState} are searched
     * independently with their own depth limit.
     *
     * <p>
     * The chain helper verifies rail identity and compatible horizontal axes while
     * adjusting for slopes. Stored active bits alone do not satisfy the search, so an
     * already active but disconnected chain cannot sustain itself without a directly
     * powered member.
     */
    protected boolean checkPowered(Dimension dimension, Vector3ic pos, BlockState blockState) {
        if (dimension.isPoweredAt(pos)) {
            return true;
        }

        RailOrientation orientation = RailOrientation.byMeta(getRailDirection(blockState));

        for (BlockFace face : orientation.connectingDirections()) {
            if (checkChainPower(dimension, pos, face, orientation, 0)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Searches one direction for a directly powered rail within the remaining chain
     * depth. Callers start at {@code depth} zero; reaching {@code MAX_CHAIN_LENGTH}
     * stops before inspecting another neighbor. The current orientation first raises
     * the candidate when this end ascends.
     *
     * <p>
     * A candidate of another type triggers a one-block-lower lookup, supporting a
     * descending continuation. A same-type candidate must connect along the search axis
     * before direct power is sampled. Recursion continues in the same horizontal
     * direction, using the accepted neighbor's orientation for the next height
     * adjustment.
     */
    protected boolean checkChainPower(Dimension dimension, Vector3ic pos, BlockFace direction, RailOrientation orientation, int depth) {
        // Bound propagation by distance from the querying rail, so remote
        // directly powered segments cannot activate an arbitrarily long same-axis chain.
        if (depth >= MAX_CHAIN_LENGTH) return false;

        Vector3ic nextPos = direction.offsetPos(pos);
        BlockState nextState = dimension.getBlockState(nextPos);

        if (orientation.isAscending() && orientation.ascendingDirection() == direction) {
            nextPos = new Vector3i(nextPos.x(), nextPos.y() + 1, nextPos.z());
            nextState = dimension.getBlockState(nextPos);
        }

        // The expected height may miss a downhill continuation. Try its lower
        // neighbor before rejecting the chain, retaining the same horizontal search direction.
        if (nextState.getBlockType() != blockType) {
            Vector3ic downPos = new Vector3i(nextPos.x(), nextPos.y() - 1, nextPos.z());
            BlockState downState = dimension.getBlockState(downPos);
            if (downState.getBlockType() == blockType) {
                nextPos = downPos;
                nextState = downState;
            } else {
                return false;
            }
        }

        if (nextState.getBlockType() != blockType) {
            return false;
        }

        RailOrientation nextOrientation = RailOrientation.byMeta(nextState.getPropertyValue(BlockPropertyTypes.RAIL_DIRECTION_6));
        if (!nextOrientation.hasConnection(direction) && !nextOrientation.hasConnection(direction.opposite())) {
            return false;
        }

        if (dimension.isPoweredAt(nextPos)) {
            return true;
        }

        return checkChainPower(dimension, nextPos, direction, nextOrientation, depth + 1);
    }
}
