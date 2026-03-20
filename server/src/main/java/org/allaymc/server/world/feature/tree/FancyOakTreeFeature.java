package org.allaymc.server.world.feature.tree;

import org.allaymc.api.block.property.enums.PillarAxis;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.utils.identifier.Identifier;
import org.allaymc.api.world.feature.WorldFeatureContext;
import org.joml.Vector3i;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Fancy oak tree feature implementation.
 *
 * @author daoge_cmd
 */
public class FancyOakTreeFeature extends TreeWorldFeature {

    public static final Identifier IDENTIFIER = new Identifier("minecraft:fancy_oak_tree");

    public FancyOakTreeFeature() {
        super(
                IDENTIFIER,
                BlockTypes.OAK_LOG,
                BlockTypes.OAK_LEAVES
        );
    }

    @Override
    public boolean place(WorldFeatureContext context, int x, int y, int z) {
        int height = calculateHeight(3, 11, 0);
        if (!canGrowOn(context.getBlockState(x, y - 1, z))) {
            return false;
        }

        int maxFreeTreeHeight = getMaxFreeTreeHeight(context, height, x, y, z, (treeHeight, currentHeight) -> 0, true);
        if (maxFreeTreeHeight < height && maxFreeTreeHeight < 4) {
            return false;
        }

        int trunkAndFoliageHeight = maxFreeTreeHeight + 2;
        int trunkTopOffset = (int) Math.floor(trunkAndFoliageHeight * 0.618);
        int branchBaseLimit = y + trunkTopOffset;
        int foliageStart = trunkAndFoliageHeight - 5;
        var foliageCoords = new ArrayList<FoliageCoords>();
        foliageCoords.add(new FoliageCoords(new Vector3i(x, y + foliageStart, z), branchBaseLimit));

        for (int current = foliageStart; current >= 0; current--) {
            float treeShape = treeShape(trunkAndFoliageHeight, current);
            if (treeShape < 0.0f) {
                continue;
            }

            double branchLength = treeShape * (ThreadLocalRandom.current().nextFloat() + 0.328);
            double angle = ThreadLocalRandom.current().nextFloat() * Math.PI * 2.0;
            int branchX = x + (int) Math.floor(branchLength * Math.sin(angle) + 0.5);
            int branchZ = z + (int) Math.floor(branchLength * Math.cos(angle) + 0.5);
            var foliagePos = new Vector3i(branchX, y + current - 1, branchZ);
            var foliageCheckTop = new Vector3i(branchX, y + current + 4, branchZ);
            if (!makeLimb(context, foliagePos, foliageCheckTop, false)) {
                continue;
            }

            int xDiff = x - branchX;
            int zDiff = z - branchZ;
            double branchBaseY = foliagePos.y() - Math.sqrt(xDiff * xDiff + zDiff * zDiff) * 0.381;
            int attachmentBaseY = branchBaseY > branchBaseLimit ? branchBaseLimit : (int) branchBaseY;
            var branchBase = new Vector3i(x, attachmentBaseY, z);
            if (makeLimb(context, branchBase, foliagePos, false)) {
                foliageCoords.add(new FoliageCoords(foliagePos, attachmentBaseY));
            }
        }

        placeDirtUnder(context, x, y - 1, z);
        makeLimb(context, new Vector3i(x, y, z), new Vector3i(x, y + trunkTopOffset, z), true);

        for (var foliageCoord : foliageCoords) {
            int branchBase = foliageCoord.branchBase() - y;
            if (trimBranches(trunkAndFoliageHeight, branchBase)) {
                var branchStart = new Vector3i(x, foliageCoord.branchBase(), z);
                if (!branchStart.equals(foliageCoord.attachment())) {
                    makeLimb(context, branchStart, foliageCoord.attachment(), true);
                }
            }
        }

        var placedLeaves = new ArrayList<Vector3i>();
        for (var foliageCoord : foliageCoords) {
            if (trimBranches(trunkAndFoliageHeight, foliageCoord.branchBase() - y)) {
                placeFancyFoliage(
                        context,
                        new FoliageAttachment(foliageCoord.attachment().x(), foliageCoord.attachment().y(), foliageCoord.attachment().z(), 0, false),
                        4,
                        2,
                        4,
                        placedLeaves
                );
            }
        }
        return true;
    }

    private void placeFancyFoliage(
            WorldFeatureContext context,
            FoliageAttachment attachment,
            int foliageHeight,
            int foliageRadius,
            int offset,
            List<Vector3i> placedLeaves
    ) {
        for (int localY = offset; localY >= offset - foliageHeight; localY--) {
            int range = foliageRadius + (localY != offset && localY != offset - foliageHeight ? 1 : 0);
            placeLeavesRow(
                    context,
                    attachment.x(),
                    attachment.y(),
                    attachment.z(),
                    range,
                    localY,
                    attachment.doubleTrunk(),
                    (random, coord, rowY, rowRange, large) -> {
                        float dx = coord.localX() + 0.5f;
                        float dz = coord.localZ() + 0.5f;
                        return dx * dx + dz * dz > rowRange * rowRange;
                    },
                    placedLeaves
            );
        }
    }

    private boolean makeLimb(WorldFeatureContext context, Vector3i from, Vector3i to, boolean place) {
        if (!place && from.equals(to)) {
            return true;
        }

        int deltaX = to.x() - from.x();
        int deltaY = to.y() - from.y();
        int deltaZ = to.z() - from.z();
        int steps = Math.max(Math.abs(deltaX), Math.max(Math.abs(deltaY), Math.abs(deltaZ)));
        float stepX = (float) deltaX / steps;
        float stepY = (float) deltaY / steps;
        float stepZ = (float) deltaZ / steps;

        for (int i = 0; i <= steps; i++) {
            int currentX = from.x() + (int) Math.floor(0.5f + i * stepX);
            int currentY = from.y() + (int) Math.floor(0.5f + i * stepY);
            int currentZ = from.z() + (int) Math.floor(0.5f + i * stepZ);
            if (place) {
                PillarAxis axis = determineLogAxis(from, new Vector3i(currentX, currentY, currentZ));
                placeLogWithAxisIfValid(context, currentX, currentY, currentZ, axis);
            } else if (!isFree(context, currentX, currentY, currentZ)) {
                return false;
            }
        }

        return true;
    }

    private PillarAxis determineLogAxis(Vector3i from, Vector3i to) {
        int xDiff = Math.abs(to.x() - from.x());
        int zDiff = Math.abs(to.z() - from.z());
        int max = Math.max(xDiff, zDiff);
        if (max == 0) {
            return PillarAxis.Y;
        }
        return xDiff == max ? PillarAxis.X : PillarAxis.Z;
    }

    private boolean trimBranches(int maxHeight, int currentHeight) {
        return currentHeight >= maxHeight * 0.2;
    }

    private float treeShape(int height, int currentY) {
        if (currentY < height * 0.3f) {
            return -1.0f;
        }

        float midpoint = height / 2.0f;
        float heightFromMid = midpoint - currentY;
        float radius = (float) Math.sqrt(midpoint * midpoint - heightFromMid * heightFromMid);
        if (heightFromMid == 0.0f) {
            radius = midpoint;
        } else if (Math.abs(heightFromMid) >= midpoint) {
            return 0.0f;
        }
        return radius * 0.5f;
    }

    private record FoliageCoords(Vector3i attachment, int branchBase) {
    }
}
