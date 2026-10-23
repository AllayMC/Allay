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
 * {@code BlockRailBaseComponentImpl} implements shared rail support checks, placement
 * geometry, and neighbor connection updates. Rail variants choose whether curves are
 * permitted and which integer direction property stores their shape. The connection
 * algorithm follows the existing PowerNukkitX-Legacy rail placement structure,
 * including fixed junction preferences and ascending shapes.
 *
 * <p>
 * Placement collects eligible neighbors before the new rail becomes visible, excluding
 * neighbors already holding two reciprocal connections. Connection helpers can change
 * those neighbors before the final placement request, and a refused final update does
 * not undo those earlier changes. Height searches inspect the same level, then one
 * above, then one below; endpoint checks determine reciprocal horizontal connectivity
 * without reconstructing a complete track path. Support checks and surrounding-rail
 * notifications then maintain geometry as blocks are added or removed.
 *
 * @author daoge_cmd
 */
public class BlockRailBaseComponentImpl extends BlockBaseComponentImpl {

    /**
     * Whether this rail variant accepts curved orientation values.
     */
    protected final boolean canCurve;
    /**
     * The integer shape property selected for this rail variant.
     */
    protected final BlockPropertyType<Integer> railDirectionProperty;

    /**
     * Creates a rail component with the supplied curve capability and direction
     * property. The property must belong to states handled by this component and accept
     * the metadata values selected by its geometry helpers.
     */
    public BlockRailBaseComponentImpl(BlockType<? extends BlockBehavior> blockType, boolean canCurve, BlockPropertyType<Integer> railDirectionProperty) {
        super(blockType);
        this.canCurve = canCurve;
        this.railDirectionProperty = railDirectionProperty;
    }

    /**
     * Returns the configured direction property from the supplied rail state.
     */
    protected int getRailDirection(BlockState blockState) {
        return blockState.getPropertyValue(railDirectionProperty);
    }

    /**
     * Requests a direction-property update at the supplied position. The caller must
     * provide a value accepted by {@code railDirectionProperty}; this helper does not
     * inspect whether the dimension accepted the change.
     */
    protected void setRailDirection(Dimension dimension, Vector3ic pos, int direction) {
        dimension.updateBlockProperty(railDirectionProperty, direction, pos);
    }

    /**
     * Returns whether the supplied state's type carries the rail block tag.
     */
    protected boolean isRailBlock(BlockState blockState) {
        return blockState.getBlockType().hasBlockTag(BlockTags.RAIL);
    }

    /**
     * Returns the orientation stored by a tagged rail's own direction property.
     * Non-rail states select {@code STRAIGHT_NORTH_SOUTH}; tagged rail types must
     * expose an integer {@code "rail_direction"} property for lookup.
     */
    protected RailOrientation getOrientation(BlockState blockState) {
        if (!isRailBlock(blockState)) return RailOrientation.STRAIGHT_NORTH_SOUTH;
        return RailOrientation.byMeta(blockState.getPropertyValue(resolveRailDirectionProperty(blockState)));
    }

    /**
     * Returns the integer property named {@code "rail_direction"} on the supplied type.
     * Callers must supply a rail type with that property; absence returns {@code null},
     * and this helper does not validate its data type.
     */
    @SuppressWarnings("unchecked")
    protected static BlockPropertyType<Integer> resolveRailDirectionProperty(BlockState blockState) {
        return (BlockPropertyType<Integer>) blockState.getBlockType().getProperties().get("rail_direction");
    }

    /**
     * Places a supported rail with an orientation selected from eligible surrounding
     * rails. A full upper surface below is required before connection work starts.
     * Neighbor collection precedes placement so reciprocal checks do not mistake the
     * new rail for an existing connection.
     *
     * <p>
     * Junction size, {@code canCurve}, neighbor heights, and fixed face preferences
     * determine which connection helpers run. Those helpers may reorient existing rails
     * before the final dimension update. If that update fails or is cancelled, this
     * method returns its result without rolling back earlier neighbor changes.
     */
    @Override
    public boolean place(Dimension dimension, BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        BlockState below = dimension.getBlockState(BlockFace.DOWN.offsetPos(placeBlockPos));
        if (!below.getBlockStateData().collisionShape().isFull(BlockFace.UP)) {
            return false;
        }

        // Discover reciprocal connections before the new rail exists. Otherwise
        // this placement could consume a neighbor endpoint while deciding whether it is available.
        List<RailNeighbor> neighbors = findRailsAroundAffected(dimension, placeBlockPos);
        List<BlockFace> faces = neighbors.stream().map(RailNeighbor::face).toList();

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
            if (neighbors.size() == 2) {
                var n1 = neighbors.get(0);
                var n2 = neighbors.get(1);
                orientation = connectTwo(dimension, placeBlockPos,
                        n1.pos(), n1.face(), n2.pos(), n2.face());
            } else {
                // Several endpoint pairs may fit a three-way junction. Preserve
                // this search order so the same neighbor layout chooses the same initial curve.
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
            // Non-curving rails need one axis at a crowded junction. The face
            // ordinal preference selects a reproducible axis before checking its opposite endpoint.
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
     * Selects this rail's orientation while attempting to connect one existing
     * neighbor. {@code face} points horizontally from {@code thisPos} toward
     * {@code neighborPos}; the height difference controls which side receives an
     * ascending shape. A non-rail neighbor supplies only a straight fallback.
     *
     * <p>
     * Existing reciprocal endpoints determine whether the neighbor can turn, curve, or
     * keep its slope. Neighbor updates occur immediately and are not rolled back by the
     * caller if later placement fails. Unhandled connection combinations return
     * {@code STRAIGHT_NORTH_SOUTH} without changing that neighbor.
     */
    protected RailOrientation connectOne(Dimension dimension, Vector3ic thisPos, Vector3ic neighborPos, BlockFace face) {
        int delta = thisPos.y() - neighborPos.y();

        BlockState neighborState = dimension.getBlockState(neighborPos);
        if (!isRailBlock(neighborState)) {
            return RailOrientation.straight(face);
        }

        boolean neighborCanCurve = resolveRailDirectionProperty(neighborState) == BlockPropertyTypes.RAIL_DIRECTION_10;
        BlockPropertyType<Integer> neighborProp = resolveRailDirectionProperty(neighborState);

        List<BlockFace> verifiedConnections = getVerifiedConnections(dimension, neighborPos);

        if (verifiedConnections.isEmpty()) {
            dimension.updateBlockProperty(neighborProp,
                    (delta == 1 ? RailOrientation.ascending(face.opposite()) : RailOrientation.straight(face)).getMeta(), neighborPos);
            return delta == -1 ? RailOrientation.ascending(face) : RailOrientation.straight(face);
        }

        if (verifiedConnections.size() == 1) {
            BlockFace faceConnected = verifiedConnections.getFirst();

            if (neighborCanCurve && faceConnected != face) {
                RailOrientation curved = RailOrientation.curved(face.opposite(), faceConnected);
                if (curved != null) {
                    dimension.updateBlockProperty(neighborProp, curved.getMeta(), neighborPos);
                }
                return delta == -1 ? RailOrientation.ascending(face) : RailOrientation.straight(face);
            }

            if (faceConnected == face) {
                RailOrientation neighborOri = getOrientation(dimension.getBlockState(neighborPos));
                if (!neighborOri.isAscending()) {
                    dimension.updateBlockProperty(neighborProp,
                            (delta == 1 ? RailOrientation.ascending(face.opposite()) : RailOrientation.straight(face)).getMeta(), neighborPos);
                }
                return delta == -1 ? RailOrientation.ascending(face) : RailOrientation.straight(face);
            }

            RailOrientation neighborOri = getOrientation(dimension.getBlockState(neighborPos));
            if (neighborOri.hasConnection(BlockFace.NORTH) && neighborOri.hasConnection(BlockFace.SOUTH)) {
                dimension.updateBlockProperty(neighborProp,
                        (delta == 1 ? RailOrientation.ascending(face.opposite()) : RailOrientation.straight(face)).getMeta(), neighborPos);
                return delta == -1 ? RailOrientation.ascending(face) : RailOrientation.straight(face);
            }
        }

        // No supported reconnection branch matched the neighbor's existing
        // endpoints. Preserve a defined placement result without inventing another neighbor update.
        return RailOrientation.STRAIGHT_NORTH_SOUTH;
    }

    /**
     * Updates two selected neighbors and chooses the shape joining their faces. The
     * first {@code connectOne} call runs before the second, so its world changes can
     * affect the second helper's reciprocal checks. Callers supply horizontal faces and
     * matching neighbor positions.
     *
     * <p>
     * Opposite faces select an ascent toward the first elevated neighbor, then the
     * second. Otherwise curve-capable rails use a matching straight or curved shape,
     * falling back to the first face's axis when no pair matches. Non-curving variants
     * always retain that first axis.
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
            // An unsupported endpoint pair has no curve representation; retain
            // the first neighbor's axis so placement still receives a defined shape.
            return result != null ? result : RailOrientation.straight(face1);
        }
        return RailOrientation.straight(face1);
    }

    /**
     * Returns horizontal endpoints that have a neighboring rail pointing back toward
     * this position. The current state's orientation supplies the candidate endpoints.
     * For each endpoint, the helper inspects neighboring rails at the same height, one
     * above, and one below, accepting the first reciprocal match.
     *
     * <p>
     * Each accepted direction appears once even when multiple heights contain rails.
     * This check compares horizontal endpoints only; it does not require matching
     * ascent geometry or test whether a cart could traverse the full height transition.
     */
    protected List<BlockFace> getVerifiedConnections(Dimension dimension, Vector3ic pos) {
        BlockState state = dimension.getBlockState(pos);
        RailOrientation orientation = getOrientation(state);
        List<BlockFace> verified = new ArrayList<>();

        for (BlockFace face : orientation.connectingDirections()) {
            Vector3ic sidePos = face.offsetPos(pos);
            for (Vector3ic checkPos : new Vector3ic[]{
                    sidePos,
                    new Vector3i(sidePos.x(), sidePos.y() + 1, sidePos.z()),
                    new Vector3i(sidePos.x(), sidePos.y() - 1, sidePos.z())}) {
                BlockState checkState = dimension.getBlockState(checkPos);
                if (!isRailBlock(checkState)) continue;
                if (getOrientation(checkState).hasConnection(face.opposite())) {
                    verified.add(face);
                    break;
                }
            }
        }
        return verified;
    }

    /**
     * Applies inherited neighbor handling and checks support beneath the rail and its
     * ascending end. Loss of the lower support or the upper-facing surface beside an
     * ascent asks the rail to break.
     */
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

    /**
     * Notifies surrounding rails after inherited placement handling finishes. The
     * notification scans adjacent columns at three heights so nearby slopes can
     * reconsider geometry after this rail appears in the world.
     */
    @Override
    public void afterPlaced(Block oldBlock, BlockState newBlockState, PlayerInteractInfo placementInfo) {
        super.afterPlaced(oldBlock, newBlockState, placementInfo);
        updateSurroundingRails(oldBlock.getDimension(), oldBlock.getPosition());
    }

    /**
     * Notifies surrounding rails after inherited replacement handling finishes.
     * Adjacent columns are scanned at three heights so nearby slopes can reconsider
     * geometry after the previous rail leaves its position.
     */
    @Override
    public void afterReplaced(Block oldBlock, BlockState newBlockState, PlayerInteractInfo placementInfo) {
        super.afterReplaced(oldBlock, newBlockState, placementInfo);
        updateSurroundingRails(oldBlock.getDimension(), oldBlock.getPosition());
    }

    /**
     * Notifies tagged rails in adjacent horizontal columns at the same height and one
     * block above or below. Every matching height receives an update from this
     * position's direction, rather than selecting one preferred neighbor.
     */
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

    /**
     * Returns at most one neighboring rail per horizontal face using a height
     * preference. The scan chooses the same level first, then one block above, then one
     * below, so a nearer-height candidate hides other rails in that adjacent column.
     *
     * <p>
     * Each result stores a newly allocated mutable position and the face from
     * {@code pos} toward that column. The returned list is mutable, and this discovery
     * step does not require the selected rail to point back or have space for another
     * connection.
     */
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

    /**
     * Returns discovered neighboring rails with fewer than two reciprocal endpoints.
     * {@code findRailsAround} first applies its per-column height preference, and each
     * candidate is checked again against the live dimension before its reciprocal
     * connections are counted.
     *
     * <p>
     * A neighbor already connected at both ends is excluded from new placement
     * connections. The returned mutable list retains the discovered
     * {@code RailNeighbor} records rather than copying their positions, and candidates
     * are not reserved against later world changes.
     */
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

    /**
     * Returns the retained neighbor position for the first matching face. A missing
     * face returns {@code null}; callers selecting a face from the same neighbor list
     * can rely on a matching entry.
     */
    protected Vector3ic getByFace(List<RailNeighbor> neighbors, BlockFace face) {
        for (RailNeighbor n : neighbors) {
            if (n.face() == face) return n.pos();
        }
        return null;
    }

    /**
     * {@code RailNeighbor} pairs a neighboring rail position with its horizontal
     * approach face.
     *
     * @param pos {@code pos} retains mutable discovery coordinates without copying, so
     * consumers sharing the record observe changes to that position
     * @param face horizontal direction from the querying rail toward this neighbor
     */
    protected record RailNeighbor(Vector3i pos, BlockFace face) {}
}
