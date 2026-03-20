package org.allaymc.server.block.component.rail;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.data.BlockTags;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.property.type.BlockPropertyType;
import org.allaymc.api.block.property.type.BlockPropertyTypes;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.world.Dimension;
import org.allaymc.server.block.component.BlockBaseComponentImpl;
import org.joml.Vector3i;
import org.joml.Vector3ic;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Base component for all rail block types. Contains shared rail connection
 * algorithm ported from PowerNukkitX-Legacy's BlockRail.
 *
 * @author daoge_cmd
 */
public class BlockRailBaseComponentImpl extends BlockBaseComponentImpl {

    protected final boolean canCurve;
    protected final BlockPropertyType<Integer> railDirectionProperty;

    public BlockRailBaseComponentImpl(BlockType<? extends BlockBehavior> blockType, boolean canCurve, BlockPropertyType<Integer> railDirectionProperty) {
        super(blockType);
        this.canCurve = canCurve;
        this.railDirectionProperty = railDirectionProperty;
    }

    protected int getRailDirection(BlockState blockState) {
        return blockState.getPropertyValue(railDirectionProperty);
    }

    protected void setRailDirection(Dimension dimension, Vector3ic pos, int direction) {
        dimension.updateBlockProperty(railDirectionProperty, direction, pos);
    }

    protected boolean isRailBlock(BlockState blockState) {
        return blockState.getBlockType().hasBlockTag(BlockTags.RAIL);
    }

    protected RailOrientation getOrientation(BlockState blockState) {
        if (!isRailBlock(blockState)) return RailOrientation.STRAIGHT_NORTH_SOUTH;
        return RailOrientation.byMeta(blockState.getPropertyValue(resolveRailDirectionProperty(blockState)));
    }

    /**
     * Resolves which rail direction property a given rail block state uses.
     * Normal rails use RAIL_DIRECTION_10 (0-9), others use RAIL_DIRECTION_6 (0-5).
     */
    @SuppressWarnings("unchecked")
    protected static BlockPropertyType<Integer> resolveRailDirectionProperty(BlockState blockState) {
        return (BlockPropertyType<Integer>) blockState.getBlockType().getProperties().get("rail_direction");
    }

    @Override
    public boolean place(Dimension dimension, BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        BlockState below = dimension.getBlockState(BlockFace.DOWN.offsetPos(placeBlockPos));
        if (!below.getBlockStateData().collisionShape().isFull(BlockFace.UP)) {
            return false;
        }

        // Find affected neighbors BEFORE placing (so connect logic doesn't see the new rail)
        List<RailNeighbor> neighbors = findRailsAroundAffected(dimension, placeBlockPos);
        List<BlockFace> faces = neighbors.stream().map(RailNeighbor::face).toList();

        // Connect to neighbors and determine orientation (following PNX's place() logic)
        RailOrientation orientation;
        if (neighbors.isEmpty()) {
            orientation = RailOrientation.STRAIGHT_NORTH_SOUTH;
        } else if (neighbors.size() == 1) {
            var n = neighbors.getFirst();
            orientation = connectOne(dimension, placeBlockPos, n.pos(), n.face());
        } else if (neighbors.size() == 4) {
            if (canCurve) {
                orientation = connectTwo(dimension, placeBlockPos,
                        getByFace(neighbors, BlockFace.SOUTH), BlockFace.SOUTH,
                        getByFace(neighbors, BlockFace.EAST), BlockFace.EAST);
            } else {
                orientation = connectTwo(dimension, placeBlockPos,
                        getByFace(neighbors, BlockFace.EAST), BlockFace.EAST,
                        getByFace(neighbors, BlockFace.WEST), BlockFace.WEST);
            }
        } else if (canCurve) {
            // isAbstract == true (normal rail)
            if (neighbors.size() == 2) {
                var n1 = neighbors.get(0);
                var n2 = neighbors.get(1);
                orientation = connectTwo(dimension, placeBlockPos,
                        n1.pos(), n1.face(), n2.pos(), n2.face());
            } else {
                // 3 neighbors: find first matching curve pair (SE, NE, SW order like PNX)
                RailOrientation[] tryCurves = {
                        RailOrientation.CURVED_SOUTH_EAST,
                        RailOrientation.CURVED_NORTH_EAST,
                        RailOrientation.CURVED_SOUTH_WEST
                };
                BlockFace f1 = null, f2 = null;
                for (RailOrientation curve : tryCurves) {
                    var cd = curve.connectingDirections();
                    if (faces.contains(cd[0]) && faces.contains(cd[1])) {
                        f1 = cd[0];
                        f2 = cd[1];
                        break;
                    }
                }
                if (f1 != null) {
                    orientation = connectTwo(dimension, placeBlockPos,
                            getByFace(neighbors, f1), f1, getByFace(neighbors, f2), f2);
                } else {
                    orientation = RailOrientation.STRAIGHT_NORTH_SOUTH;
                }
            }
        } else {
            // Powered rail with 2-3 neighbors: find opposite pair or single
            // Pick the face with the highest ordinal (EAST > WEST > SOUTH > NORTH, matching PNX)
            BlockFace f = faces.stream()
                    .max(Comparator.comparingInt(Enum::ordinal))
                    .orElse(BlockFace.SOUTH);
            BlockFace fo = f.opposite();
            if (faces.contains(fo)) {
                orientation = connectTwo(dimension, placeBlockPos,
                        getByFace(neighbors, f), f, getByFace(neighbors, fo), fo);
            } else {
                orientation = connectOne(dimension, placeBlockPos,
                        getByFace(neighbors, f), f);
            }
        }

        blockState = blockState.setPropertyValue(railDirectionProperty, orientation.getMeta());
        return dimension.setBlockState(placeBlockPos.x(), placeBlockPos.y(), placeBlockPos.z(), blockState, placementInfo);
    }

    /**
     * Connects to a single neighbor. Updates the neighbor's orientation and returns
     * the orientation for the newly placed rail.
     * <p>
     * Ported from PNX's {@code BlockRail.connect(BlockRail other, BlockFace face)}.
     *
     * @param face direction from the new rail to the neighbor (horizontal)
     */
    protected RailOrientation connectOne(Dimension dimension, Vector3ic thisPos, Vector3ic neighborPos, BlockFace face) {
        int delta = thisPos.y() - neighborPos.y();

        BlockState neighborState = dimension.getBlockState(neighborPos);
        if (!isRailBlock(neighborState)) {
            return RailOrientation.straight(face);
        }

        boolean neighborCanCurve = resolveRailDirectionProperty(neighborState) == BlockPropertyTypes.RAIL_DIRECTION_10;
        BlockPropertyType<Integer> neighborProp = resolveRailDirectionProperty(neighborState);

        // Get neighbor's VERIFIED mutual connections (rails that connect back)
        List<BlockFace> verifiedConnections = getVerifiedConnections(dimension, neighborPos);

        if (verifiedConnections.isEmpty()) {
            // Neighbor has no real connections: point it toward us
            dimension.updateBlockProperty(neighborProp,
                    (delta == 1 ? RailOrientation.ascending(face.opposite()) : RailOrientation.straight(face)).getMeta(), neighborPos);
            return delta == -1 ? RailOrientation.ascending(face) : RailOrientation.straight(face);
        }

        if (verifiedConnections.size() == 1) {
            BlockFace faceConnected = verifiedConnections.getFirst();

            if (neighborCanCurve && faceConnected != face) {
                // Curve! Neighbor connects to existing + new rail
                RailOrientation curved = RailOrientation.curved(face.opposite(), faceConnected);
                if (curved != null) {
                    dimension.updateBlockProperty(neighborProp, curved.getMeta(), neighborPos);
                }
                return delta == -1 ? RailOrientation.ascending(face) : RailOrientation.straight(face);
            }

            if (faceConnected == face) {
                // Turn! Neighbor's existing connection is in the same direction as us
                RailOrientation neighborOri = getOrientation(dimension.getBlockState(neighborPos));
                if (!neighborOri.isAscending()) {
                    dimension.updateBlockProperty(neighborProp,
                            (delta == 1 ? RailOrientation.ascending(face.opposite()) : RailOrientation.straight(face)).getMeta(), neighborPos);
                }
                return delta == -1 ? RailOrientation.ascending(face) : RailOrientation.straight(face);
            }

            RailOrientation neighborOri = getOrientation(dimension.getBlockState(neighborPos));
            if (neighborOri.hasConnection(BlockFace.NORTH) && neighborOri.hasConnection(BlockFace.SOUTH)) {
                // Neighbor is N-S: override
                dimension.updateBlockProperty(neighborProp,
                        (delta == 1 ? RailOrientation.ascending(face.opposite()) : RailOrientation.straight(face)).getMeta(), neighborPos);
                return delta == -1 ? RailOrientation.ascending(face) : RailOrientation.straight(face);
            }
        }

        return RailOrientation.STRAIGHT_NORTH_SOUTH;
    }

    /**
     * Connects to two neighbors. Updates both neighbors' orientations and returns
     * the orientation for the newly placed rail.
     * <p>
     * Ported from PNX's {@code BlockRail.connect(BlockRail rail1, BlockFace face1, BlockRail rail2, BlockFace face2)}.
     */
    protected RailOrientation connectTwo(Dimension dimension, Vector3ic thisPos,
                                         Vector3ic pos1, BlockFace face1,
                                         Vector3ic pos2, BlockFace face2) {
        connectOne(dimension, thisPos, pos1, face1);
        connectOne(dimension, thisPos, pos2, face2);

        if (face1.opposite() == face2) {
            int delta1 = thisPos.y() - pos1.y();
            int delta2 = thisPos.y() - pos2.y();

            if (delta1 == -1) {
                return RailOrientation.ascending(face1);
            } else if (delta2 == -1) {
                return RailOrientation.ascending(face2);
            }
        }

        if (canCurve) {
            RailOrientation result = RailOrientation.straightOrCurved(face1, face2);
            return result != null ? result : RailOrientation.straight(face1);
        }
        return RailOrientation.straight(face1);
    }

    /**
     * Gets the verified mutual connections for a rail at the given position.
     * A "verified connection" means the connecting direction has a rail that
     * connects BACK to this rail.
     * <p>
     * Ported from PNX's {@code BlockRail.checkRailsConnected()}.
     */
    protected List<BlockFace> getVerifiedConnections(Dimension dimension, Vector3ic pos) {
        BlockState state = dimension.getBlockState(pos);
        RailOrientation orientation = getOrientation(state);
        List<BlockFace> verified = new ArrayList<>();

        for (BlockFace face : orientation.connectingDirections()) {
            Vector3ic sidePos = face.offsetPos(pos);
            // Check same Y, Y+1, Y-1
            for (Vector3ic checkPos : new Vector3ic[]{
                    sidePos,
                    new Vector3i(sidePos.x(), sidePos.y() + 1, sidePos.z()),
                    new Vector3i(sidePos.x(), sidePos.y() - 1, sidePos.z())}) {
                BlockState checkState = dimension.getBlockState(checkPos);
                if (!isRailBlock(checkState)) continue;
                // Does the found rail connect back?
                if (getOrientation(checkState).hasConnection(face.opposite())) {
                    verified.add(face);
                    break;
                }
            }
        }
        return verified;
    }

    @Override
    public void onNeighborUpdate(Block block, Block neighbor, BlockFace face, BlockState oldNeighborState) {
        super.onNeighborUpdate(block, neighbor, face, oldNeighborState);

        var dimension = block.getDimension();
        var pos = block.getPosition();

        if (face == BlockFace.DOWN) {
            if (!neighbor.getBlockStateData().collisionShape().isFull(BlockFace.UP)) {
                block.breakBlock();
                return;
            }
        }

        RailOrientation orientation = RailOrientation.byMeta(getRailDirection(block.getBlockState()));
        if (orientation.isAscending()) {
            BlockFace ascendFace = orientation.ascendingDirection();
            Vector3ic ascendPos = ascendFace.offsetPos(pos);
            BlockState ascendBlock = dimension.getBlockState(ascendPos);
            if (!ascendBlock.getBlockStateData().collisionShape().isFull(BlockFace.UP)) {
                block.breakBlock();
            }
        }
    }

    @Override
    public void afterPlaced(Block oldBlock, BlockState newBlockState, PlayerInteractInfo placementInfo) {
        super.afterPlaced(oldBlock, newBlockState, placementInfo);
        updateSurroundingRails(oldBlock.getDimension(), oldBlock.getPosition());
    }

    @Override
    public void afterReplaced(Block oldBlock, BlockState newBlockState, PlayerInteractInfo placementInfo) {
        super.afterReplaced(oldBlock, newBlockState, placementInfo);
        updateSurroundingRails(oldBlock.getDimension(), oldBlock.getPosition());
    }

    protected void updateSurroundingRails(Dimension dimension, Vector3ic pos) {
        for (BlockFace face : BlockFace.getHorizontalBlockFaces()) {
            Vector3ic sidePos = face.offsetPos(pos);
            if (isRailBlock(dimension.getBlockState(sidePos))) {
                dimension.updateAtFace(sidePos, face.opposite());
            }
            Vector3ic upPos = new Vector3i(sidePos.x(), sidePos.y() + 1, sidePos.z());
            if (isRailBlock(dimension.getBlockState(upPos))) {
                dimension.updateAtFace(upPos, face.opposite());
            }
            Vector3ic downPos = new Vector3i(sidePos.x(), sidePos.y() - 1, sidePos.z());
            if (isRailBlock(dimension.getBlockState(downPos))) {
                dimension.updateAtFace(downPos, face.opposite());
            }
        }
    }

    protected List<RailNeighbor> findRailsAround(Dimension dimension, Vector3ic pos) {
        List<RailNeighbor> result = new ArrayList<>();
        for (BlockFace face : BlockFace.getHorizontalBlockFaces()) {
            Vector3ic sidePos = face.offsetPos(pos);

            if (isRailBlock(dimension.getBlockState(sidePos))) {
                result.add(new RailNeighbor(new Vector3i(sidePos), face));
                continue;
            }

            Vector3ic upPos = new Vector3i(sidePos.x(), sidePos.y() + 1, sidePos.z());
            if (isRailBlock(dimension.getBlockState(upPos))) {
                result.add(new RailNeighbor(new Vector3i(upPos), face));
                continue;
            }

            Vector3ic downPos = new Vector3i(sidePos.x(), sidePos.y() - 1, sidePos.z());
            if (isRailBlock(dimension.getBlockState(downPos))) {
                result.add(new RailNeighbor(new Vector3i(downPos), face));
            }
        }
        return result;
    }

    protected List<RailNeighbor> findRailsAroundAffected(Dimension dimension, Vector3ic pos) {
        List<RailNeighbor> all = findRailsAround(dimension, pos);
        List<RailNeighbor> affected = new ArrayList<>();
        for (RailNeighbor neighbor : all) {
            BlockState neighborState = dimension.getBlockState(neighbor.pos);
            if (!isRailBlock(neighborState)) continue;

            List<BlockFace> verified = getVerifiedConnections(dimension, neighbor.pos);
            if (verified.size() < 2) {
                affected.add(neighbor);
            }
        }
        return affected;
    }

    protected Vector3ic getByFace(List<RailNeighbor> neighbors, BlockFace face) {
        for (RailNeighbor n : neighbors) {
            if (n.face() == face) return n.pos();
        }
        return null;
    }

    protected record RailNeighbor(Vector3i pos, BlockFace face) {}
}
