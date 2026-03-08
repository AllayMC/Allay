package org.allaymc.server.block.component.rail;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.property.type.BlockPropertyTypes;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.world.Dimension;
import org.joml.Vector3ic;

import java.util.List;

/**
 * Component for normal rail (RAIL). Supports curves (orientations 6-9) and
 * handles redstone-triggered curve switching at T-intersections.
 *
 * @author daoge_cmd
 */
public class BlockNormalRailBaseComponentImpl extends BlockRailBaseComponentImpl {

    public BlockNormalRailBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType, true, BlockPropertyTypes.RAIL_DIRECTION_10);
    }

    @Override
    public void onNeighborUpdate(Block block, Block neighbor, BlockFace face, BlockState oldNeighborState) {
        super.onNeighborUpdate(block, neighbor, face, oldNeighborState);

        // If the rail was broken by super (support check), skip curve logic
        if (block.getDimension().getBlockState(block.getPosition()).getBlockType() != blockType) {
            return;
        }

        var dimension = block.getDimension();
        var pos = block.getPosition();
        RailOrientation currentOrientation = RailOrientation.byMeta(getRailDirection(block.getBlockState()));

        // Handle T-intersection curve switching based on redstone/neighbor changes
        if (currentOrientation.isCurved()) {
            RailOrientation newOrientation = recalculateCurveOrientation(dimension, pos, currentOrientation);
            if (newOrientation != currentOrientation) {
                setRailDirection(dimension, pos, newOrientation.getMeta());
            }
        }
    }

    /**
     * Recalculates which curve direction to use at a T-intersection or crossroads,
     * based on which neighbor rails are present and whether opposite sides have rails.
     */
    protected RailOrientation recalculateCurveOrientation(Dimension dimension, Vector3ic pos, RailOrientation current) {
        List<RailNeighbor> neighbors = findRailsAround(dimension, pos);
        if (neighbors.size() < 2) return current;

        List<BlockFace> faces = neighbors.stream().map(RailNeighbor::face).toList();

        // For T-intersections (3 neighbors), pick the curve based on power/neighbor changes
        // Try to preserve current curve if it's still valid
        if (faces.contains(current.connectingDirections()[0]) && faces.contains(current.connectingDirections()[1])) {
            // Current curve is still valid, but check if we should switch
            // Switch happens when opposite side of one connection also has a rail (T-intersection)
            BlockFace dir0 = current.connectingDirections()[0];
            BlockFace dir1 = current.connectingDirections()[1];

            // Check if the opposite face of either connecting direction also has a rail
            boolean hasOppDir0 = faces.contains(dir0.opposite());
            boolean hasOppDir1 = faces.contains(dir1.opposite());

            if (hasOppDir0 && !hasOppDir1) {
                // T from dir0 axis: switch to curve using opposite of dir0 + dir1
                RailOrientation alt = RailOrientation.curved(dir0.opposite(), dir1);
                if (alt != null) return alt;
            } else if (hasOppDir1 && !hasOppDir0) {
                // T from dir1 axis: switch to curve using dir0 + opposite of dir1
                RailOrientation alt = RailOrientation.curved(dir0, dir1.opposite());
                if (alt != null) return alt;
            }
        }

        // Current curve is no longer valid, find a new one
        RailOrientation[] curves = {
                RailOrientation.CURVED_SOUTH_EAST,
                RailOrientation.CURVED_SOUTH_WEST,
                RailOrientation.CURVED_NORTH_WEST,
                RailOrientation.CURVED_NORTH_EAST
        };
        for (RailOrientation curve : curves) {
            if (faces.contains(curve.connectingDirections()[0]) && faces.contains(curve.connectingDirections()[1])) {
                return curve;
            }
        }

        return current;
    }
}
