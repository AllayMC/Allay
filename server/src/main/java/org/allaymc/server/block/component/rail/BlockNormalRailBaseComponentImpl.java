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
 * {@code BlockNormalRailBaseComponentImpl} extends shared rail placement with the
 * curved shapes available to normal rails. Neighbor updates first apply support checks,
 * then revisit an already curved rail when its block type remains present. The curve
 * calculation uses neighboring rail faces and does not sample redstone power.
 *
 * <p>
 * At a junction with the current two endpoints still present, a rail on exactly one
 * opposite endpoint selects the alternate curve on that axis. Otherwise the calculation
 * searches a fixed curve order for a pair of present neighbors. Fewer than two
 * neighbors or no matching pair preserves the supplied current orientation. These
 * choices make junction behavior depend on both existing geometry and the current
 * stored curve.
 *
 * @author daoge_cmd
 */
public class BlockNormalRailBaseComponentImpl extends BlockRailBaseComponentImpl {

    /**
     * Creates a curve-capable normal rail component with ten direction values.
     */
    public BlockNormalRailBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType, true, BlockPropertyTypes.RAIL_DIRECTION_10);
    }

    /**
     * Applies shared support handling and recalculates a surviving curved normal rail.
     * The live block type is checked after inherited handling because a successful
     * support break removes the rail before curve processing begins.
     */
    @Override
    public void onNeighborUpdate(Block block, Block neighbor, BlockFace face, BlockState oldNeighborState) {
        super.onNeighborUpdate(block, neighbor, face, oldNeighborState);

        // Shared support handling can break the rail, so recheck the live type
        // before writing a curve into the position that may now contain air.
        if (block.getDimension().getBlockState(block.getPosition()).getBlockType() != blockType) {
            return;
        }

        var dimension = block.getDimension();
        var pos = block.getPosition();
        RailOrientation currentOrientation = RailOrientation.byMeta(getRailDirection(block.getBlockState()));

        if (currentOrientation.isCurved()) {
            RailOrientation newOrientation = recalculateCurveOrientation(dimension, pos, currentOrientation);
            if (newOrientation != currentOrientation) {
                setRailDirection(dimension, pos, newOrientation.getMeta());
            }
        }
    }

    /**
     * Selects a curve from the current orientation and rails found around the junction.
     * Fewer than two neighboring faces preserve {@code current}. When both current
     * endpoints still exist, exactly one populated opposite endpoint selects the
     * corresponding alternate curve.
     *
     * <p>
     * Other cases search curves in south-east, south-west, north-west, then north-east
     * order. The first pair whose endpoints both exist wins; no match preserves
     * {@code current}. The calculation does not read redstone power or require
     * reciprocal neighbor connections.
     */
    protected RailOrientation recalculateCurveOrientation(Dimension dimension, Vector3ic pos, RailOrientation current) {
        List<RailNeighbor> neighbors = findRailsAround(dimension, pos);
        if (neighbors.size() < 2) return current;

        List<BlockFace> faces = neighbors.stream().map(RailNeighbor::face).toList();

        if (faces.contains(current.connectingDirections()[0]) && faces.contains(current.connectingDirections()[1])) {
            BlockFace dir0 = current.connectingDirections()[0];
            BlockFace dir1 = current.connectingDirections()[1];

            boolean hasOppDir0 = faces.contains(dir0.opposite());
            boolean hasOppDir1 = faces.contains(dir1.opposite());

            if (hasOppDir0 && !hasOppDir1) {
                RailOrientation alt = RailOrientation.curved(dir0.opposite(), dir1);
                if (alt != null) return alt;
            } else if (hasOppDir1 && !hasOppDir0) {
                RailOrientation alt = RailOrientation.curved(dir0, dir1.opposite());
                if (alt != null) return alt;
            }
        }

        // When no single-opposite junction choice applies, this fixed order
        // resolves competing valid curves even if the current curve remains geometrically possible.
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
