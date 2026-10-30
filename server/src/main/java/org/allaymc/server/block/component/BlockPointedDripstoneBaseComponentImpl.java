package org.allaymc.server.block.component;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.property.enums.CauldronLiquid;
import org.allaymc.api.block.property.enums.DripstoneThickness;
import org.allaymc.api.block.property.type.BlockPropertyTypes;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.eventbus.event.block.PointedDripstoneDripEvent;
import org.allaymc.api.math.position.Position3i;
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.sound.PointedDripstoneDripSound;
import org.joml.Vector3ic;

import java.util.concurrent.ThreadLocalRandom;

/**
 * {@code BlockPointedDripstoneBaseComponentImpl} manages pointed dripstone placement,
 * column thickness, growth, and liquid dripping. Hanging and standing columns use
 * opposite base and tip directions, so the same neighboring block positions have
 * different structural roles depending on {@code HANGING}. Placement can extend a
 * column or merge opposing tips, then updates nearby thickness properties to reflect
 * the resulting shape.
 *
 * <p>
 * Thickness calculation gives opposing-tip merges priority over distance-based tip,
 * frustum, base, and middle states. Column scans use a fixed height bound, while
 * unsupported standing columns break upward and hanging-column falling is delegated
 * to a separate component. Hanging tips can grow into air and locate liquid above
 * their support before searching for a receiving cauldron. Cauldron filling uses
 * liquid-specific probability and a cancellable event; ordered state writes
 * throughout this component are not rolled back when a dimension write fails.
 *
 * @author daoge_cmd
 */
public class BlockPointedDripstoneBaseComponentImpl extends BlockBaseComponentImpl {
    /**
     * {@code GROWTH_PROBABILITY} sets the per-random-update chance for an eligible
     * hanging tip to attempt downward growth.
     */
    private static final double GROWTH_PROBABILITY = 0.011377778;
    /**
     * {@code WATER_DRIP_PROBABILITY} controls water filling attempts after a hanging
     * tip locates liquid and a receiving cauldron.
     */
    private static final double WATER_DRIP_PROBABILITY = 45.0 / 256.0;
    /**
     * {@code LAVA_DRIP_PROBABILITY} controls lava filling attempts separately from
     * water, using the same cauldron eligibility and cancellation path.
     */
    private static final double LAVA_DRIP_PROBABILITY = 15.0 / 256.0;
    /**
     * {@code MAX_COLUMN_HEIGHT} bounds column-length scans and growth, limiting how
     * far thickness and dripping logic inspect a connected formation.
     */
    private static final int MAX_COLUMN_HEIGHT = 11;

    /**
     * Creates pointed dripstone base behavior for the supplied block type.
     */
    public BlockPointedDripstoneBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    /**
     * Places pointed dripstone using the clicked face and neighboring column states.
     * A {@code null} {@code placementInfo} delegates to inherited placement; player
     * placement rejects positions with air both above and below.
     *
     * <p>
     * Existing dripstone above or below selects extension or opposing-tip merge
     * behavior. The method adjusts adjacent thickness values as it creates the new
     * tip or merge point, and those writes can trigger further block updates. Most
     * branches return {@code true} without checking dimension write results, so
     * callers cannot infer that all related states were stored.
     */
    @Override
    public boolean place(Dimension dimension, BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        if (placementInfo == null) {
            return super.place(dimension, blockState, placeBlockPos, placementInfo);
        }

        var face = placementInfo.blockFace();
        int x = placeBlockPos.x(), y = placeBlockPos.y(), z = placeBlockPos.z();

        var aboveState = dimension.getBlockState(x, y + 1, z);
        var belowState = dimension.getBlockState(x, y - 1, z);

        boolean hasDripstoneAbove = aboveState.getBlockType() == BlockTypes.POINTED_DRIPSTONE;
        boolean hasDripstoneBelow = belowState.getBlockType() == BlockTypes.POINTED_DRIPSTONE;
        boolean isAboveAir = aboveState.getBlockType() == BlockTypes.AIR;
        boolean isBelowAir = belowState.getBlockType() == BlockTypes.AIR;
        if (isAboveAir && isBelowAir) {
            return false;
        }

        // Classify both vertical neighbors together because bridging two columns
        // requires merge handling, while a single existing column determines the
        // extension direction.
        int state = hasDripstoneAbove ? (hasDripstoneBelow ? 1 : 2) : (!hasDripstoneBelow ? 3 : 4);
        boolean hanging = false;

        switch (state) {
            case 1 -> {
                setMergeBlock(dimension, blockState, x, y, z, false);
                setBlockThicknessAt(dimension, x, y + 1, z, true, DripstoneThickness.MERGE);
            }
            case 2 -> {
                if (!isBelowAir) {
                    if (face == BlockFace.UP) {
                        setBlockThicknessAt(dimension, x, y + 1, z, true, DripstoneThickness.MERGE);
                        setMergeBlock(dimension, blockState, x, y, z, false);
                    } else {
                        setTipBlock(dimension, blockState, x, y, z, true);
                        updateColumnAfterAdd(dimension, x, y, z, true);
                    }
                    return true;
                }
                hanging = true;
            }
            case 3 -> {
                setTipBlock(dimension, blockState, x, y, z, face != BlockFace.UP);
                return true;
            }
            case 4 -> {
                if (!isAboveAir) {
                    if (face == BlockFace.DOWN) {
                        setMergeBlock(dimension, blockState, x, y, z, true);
                        setBlockThicknessAt(dimension, x, y - 1, z, false, DripstoneThickness.MERGE);
                    } else {
                        setTipBlock(dimension, blockState, x, y, z, false);
                        updateColumnAfterAdd(dimension, x, y, z, false);
                    }
                    return true;
                }
            }
        }
        // Column shape updates target support-side blocks around the incoming tip;
        // the merge case has already written its central block before this shared
        // path.
        updateColumnAfterAdd(dimension, x, y, z, hanging);
        if (state == 1) {
            return true;
        }
        setTipBlock(dimension, blockState, x, y, z, hanging);
        return true;
    }

    /**
     * Attempts to write a tip state with the requested hanging orientation. The
     * supplied state supplies other properties, and the dimension write result is
     * ignored by this helper.
     */
    private void setTipBlock(Dimension dimension, BlockState blockState, int x, int y, int z, boolean hanging) {
        var newState = blockState
                .setPropertyValue(BlockPropertyTypes.HANGING, hanging)
                .setPropertyValue(BlockPropertyTypes.DRIPSTONE_THICKNESS, DripstoneThickness.TIP);
        dimension.setBlockState(x, y, z, newState);
    }

    /**
     * Attempts to write a merge state with the requested hanging orientation. The
     * supplied state supplies other properties, and the dimension write result is
     * ignored by this helper.
     */
    private void setMergeBlock(Dimension dimension, BlockState blockState, int x, int y, int z, boolean hanging) {
        var newState = blockState
                .setPropertyValue(BlockPropertyTypes.HANGING, hanging)
                .setPropertyValue(BlockPropertyTypes.DRIPSTONE_THICKNESS, DripstoneThickness.MERGE);
        dimension.setBlockState(x, y, z, newState);
    }

    /**
     * Updates orientation and thickness only when the position currently contains
     * pointed dripstone. Other block types are left unchanged, and the dimension
     * write result is ignored after a matching state is found.
     */
    private void setBlockThicknessAt(Dimension dimension, int x, int y, int z, boolean hanging, DripstoneThickness thickness) {
        var state = dimension.getBlockState(x, y, z);
        if (state.getBlockType() != BlockTypes.POINTED_DRIPSTONE) {
            return;
        }
        var newState = state
                .setPropertyValue(BlockPropertyTypes.HANGING, hanging)
                .setPropertyValue(BlockPropertyTypes.DRIPSTONE_THICKNESS, thickness);
        dimension.setBlockState(x, y, z, newState);
    }

    /**
     * Updates the nearest support-side thickness values after adding a tip. The
     * hanging flag reverses the support direction, and {@code getColumnLength}
     * returns a capped count that includes the supplied position.
     *
     * <p>
     * The closest matching block becomes a frustum. When a second support-side block
     * exists, it becomes a base for the shorter case or middle for longer columns.
     * Other existing thickness values stay unchanged in this helper; each targeted
     * write verifies that pointed dripstone is still present.
     */
    private void updateColumnAfterAdd(Dimension dimension, int x, int y, int z, boolean hanging) {
        int length = getColumnLength(dimension, x, y, z, hanging);
        int dy = hanging ? 1 : -1;
        if (length == 1) {
            setBlockThicknessAt(dimension, x, y + dy, z, hanging, DripstoneThickness.FRUSTUM);
        } else if (length == 2) {
            setBlockThicknessAt(dimension, x, y + dy * 2, z, hanging, DripstoneThickness.BASE);
            setBlockThicknessAt(dimension, x, y + dy, z, hanging, DripstoneThickness.FRUSTUM);
        } else if (length >= 3) {
            setBlockThicknessAt(dimension, x, y + dy * 2, z, hanging, DripstoneThickness.MIDDLE);
            setBlockThicknessAt(dimension, x, y + dy, z, hanging, DripstoneThickness.FRUSTUM);
        }
    }

    /**
     * Forwards the neighbor event, breaks unsupported standing columns, and
     * recalculates thickness after adjacent dripstone or air changes. Hanging support
     * loss is handled by the falling component rather than this standing-column break
     * path.
     */
    @Override
    public void onNeighborUpdate(Block block, Block neighbor, BlockFace face, BlockState oldNeighborState) {
        super.onNeighborUpdate(block, neighbor, face, oldNeighborState);

        var hanging = block.getPropertyValue(BlockPropertyTypes.HANGING);
        var supportFace = hanging ? BlockFace.UP : BlockFace.DOWN;
        if (!hanging && face == supportFace) {
            var supportBlock = block.offsetPos(supportFace);
            if (!supportBlock.getBlockStateData().isSolid() &&
                supportBlock.getBlockType() != BlockTypes.POINTED_DRIPSTONE) {
                breakColumn(block.getDimension(), block.getPosition().x(), block.getPosition().y(),
                        block.getPosition().z());
            }
        }
        if (neighbor.getBlockType() == BlockTypes.POINTED_DRIPSTONE ||
            neighbor.getBlockType() == BlockTypes.AIR) {
            recalculateThickness(block);
        }
    }

    /**
     * Attempts to break pointed dripstone at the supplied position and recurses
     * through standing dripstone above it. Individual break results are ignored, so
     * cancellation of one break does not stop attempts farther up the column.
     */
    private void breakColumn(Dimension dimension, int x, int y, int z) {
        var currentState = dimension.getBlockState(x, y, z);
        if (currentState.getBlockType() != BlockTypes.POINTED_DRIPSTONE) {
            return;
        }
        dimension.breakBlock(x, y, z, null, null);
        int nextY = y + 1;
        var nextState = dimension.getBlockState(x, nextY, z);
        if (nextState.getBlockType() == BlockTypes.POINTED_DRIPSTONE &&
            !nextState.getPropertyValue(BlockPropertyTypes.HANGING)) {
            breakColumn(dimension, x, nextY, z);
        }
    }

    /**
     * Refreshes thickness from the dimension's current state when pointed dripstone
     * still occupies the position. A block already removed by preceding support
     * handling is skipped, and unchanged thickness avoids another property write.
     */
    private void recalculateThickness(Block block) {
        var dimension = block.getDimension();
        var pos = block.getPosition();
        int x = pos.x(), y = pos.y(), z = pos.z();
        var currentState = dimension.getBlockState(x, y, z);
        if (currentState.getBlockType() != BlockTypes.POINTED_DRIPSTONE) {
            return;
        }

        var hanging = currentState.getPropertyValue(BlockPropertyTypes.HANGING);
        var currentThickness = currentState.getPropertyValue(BlockPropertyTypes.DRIPSTONE_THICKNESS);

        DripstoneThickness thickness = calculateThickness(dimension, x, y, z, hanging);

        if (thickness != currentThickness) {
            dimension.updateBlockProperty(BlockPropertyTypes.DRIPSTONE_THICKNESS, thickness, pos);
        }
    }

    /**
     * Returns a capped column count including the supplied position and matching
     * support-side dripstone. The hanging flag selects scan direction, and a
     * different block type or orientation ends the scan.
     */
    private int getColumnLength(Dimension dimension, int x, int y, int z, boolean hanging) {
        int length = 1;
        int dy = hanging ? 1 : -1;
        int currentY = y + dy;

        // The height cap limits work on unusually long dripstone columns.
        while (length < MAX_COLUMN_HEIGHT) {
            var state = dimension.getBlockState(x, currentY, z);
            if (state.getBlockType() != BlockTypes.POINTED_DRIPSTONE) {
                break;
            }
            if (state.getPropertyValue(BlockPropertyTypes.HANGING) != hanging) {
                break;
            }
            length++;
            currentY += dy;
        }

        return length;
    }

    /**
     * Determines thickness from opposing tips and matching dripstone distances toward
     * the base and tip. The hanging flag selects which vertical direction has each
     * structural role, and both distance scans are bounded.
     *
     * <p>
     * An immediately adjacent opposite-facing dripstone takes merge precedence before
     * distance checks. Otherwise no tip-side neighbors means tip, one means frustum,
     * and larger tip distance selects base only when no base-side neighbors remain.
     * All remaining positions become middle, keeping the same rules for hanging and
     * standing formations.
     */
    private DripstoneThickness calculateThickness(Dimension dimension, int x, int y, int z, boolean hanging) {
        // Hanging columns place their base above the tip; standing columns reverse
        // those roles, so both distance scans must use this shared orientation.
        int baseDir = hanging ? 1 : -1;
        int tipDir = -baseDir;
        // Opposing adjacent tips form a merge even when the column distances would
        // otherwise identify a tip, so test that identity before thickness distances.
        var oppositeState = dimension.getBlockState(x, y + tipDir, z);
        if (oppositeState.getBlockType() == BlockTypes.POINTED_DRIPSTONE) {
            var oppositeHanging = oppositeState.getPropertyValue(BlockPropertyTypes.HANGING);
            if (oppositeHanging != hanging) {
                return DripstoneThickness.MERGE;
            }
        }
        int distanceToTip = 0;
        int currentY = y + tipDir;
        // Bound tip-distance inspection by the same column height limit.
        while (distanceToTip < MAX_COLUMN_HEIGHT) {
            var state = dimension.getBlockState(x, currentY, z);
            if (state.getBlockType() != BlockTypes.POINTED_DRIPSTONE) {
                break;
            }
            if (state.getPropertyValue(BlockPropertyTypes.HANGING) != hanging) {
                break;
            }
            distanceToTip++;
            currentY += tipDir;
        }
        int distanceToBase = 0;
        currentY = y + baseDir;
        // Bound base-distance inspection by the same column height limit.
        while (distanceToBase < MAX_COLUMN_HEIGHT) {
            var state = dimension.getBlockState(x, currentY, z);
            if (state.getBlockType() != BlockTypes.POINTED_DRIPSTONE) {
                break;
            }
            if (state.getPropertyValue(BlockPropertyTypes.HANGING) != hanging) {
                break;
            }
            distanceToBase++;
            currentY += baseDir;
        }
        if (distanceToTip == 0) {
            return DripstoneThickness.TIP;
        } else if (distanceToTip == 1) {
            return DripstoneThickness.FRUSTUM;
        } else if (distanceToBase == 0) {
            return DripstoneThickness.BASE;
        } else {
            return DripstoneThickness.MIDDLE;
        }
    }

    /**
     * Returns whether pointed dripstone receives random growth and drip updates.
     */
    @Override
    public boolean canRandomUpdate() {
        return true;
    }

    /**
     * Attempts growth and liquid dripping only for hanging tip states. Growth has its
     * own random gate, while dripping is checked afterward even when the tip did not
     * grow during this update.
     */
    @Override
    public void onRandomUpdate(Block block) {
        var thickness = block.getPropertyValue(BlockPropertyTypes.DRIPSTONE_THICKNESS);
        var hanging = block.getPropertyValue(BlockPropertyTypes.HANGING);
        if (thickness != DripstoneThickness.TIP || !hanging) {
            return;
        }
        if (ThreadLocalRandom.current().nextDouble() < GROWTH_PROBABILITY) {
            tryGrow(block);
        }
        tryDrip(block);
    }

    /**
     * Attempts to add a hanging tip in the air block below a column shorter than the
     * height limit. It then changes the current tip to frustum without checking
     * either state-write result.
     */
    private void tryGrow(Block block) {
        var dimension = block.getDimension();
        var pos = block.getPosition();
        int x = pos.x(), y = pos.y(), z = pos.z();
        var below = dimension.getBlockState(x, y - 1, z);
        if (below.getBlockType() != BlockTypes.AIR) {
            return;
        }
        int columnHeight = getColumnLength(dimension, x, y, z, true);
        // The growth limit keeps new tips within the bounded column scan.
        if (columnHeight >= MAX_COLUMN_HEIGHT) {
            return;
        }
        var newTip = block.getBlockState()
                .setPropertyValue(BlockPropertyTypes.HANGING, true)
                .setPropertyValue(BlockPropertyTypes.DRIPSTONE_THICKNESS, DripstoneThickness.TIP);

        // Write the new lower tip before changing the former tip to frustum. Both
        // results are ignored, so failures can leave only part of this growth
        // applied.
        dimension.setBlockState(x, y - 1, z, newTip);
        dimension.updateBlockProperty(BlockPropertyTypes.DRIPSTONE_THICKNESS, DripstoneThickness.FRUSTUM, pos);
    }

    /**
     * Searches above the hanging column for water or lava, then searches downward for
     * a receiving cauldron. The first nonair block stops the downward search, and a
     * liquid source also produces a drip sound without requiring successful filling.
     */
    private void tryDrip(Block block) {
        var dimension = block.getDimension();
        var pos = block.getPosition();
        int x = pos.x(), y = pos.y(), z = pos.z();
        int topY = y;
        while (true) {
            var above = dimension.getBlockState(x, topY + 1, z);
            if (above.getBlockType() != BlockTypes.POINTED_DRIPSTONE) {
                break;
            }
            topY++;
            // Bound upward source searching by the height limit used for column
            // growth.
            if (topY - y > MAX_COLUMN_HEIGHT) {
                return;
            }
        }
        // Liquid is read two positions above the top dripstone, leaving the
        // intervening block as the support between source liquid and column.
        var sourceBlock = dimension.getBlockState(x, topY + 2, z);
        var sourceType = sourceBlock.getBlockType();

        boolean isWater = sourceType == BlockTypes.WATER || sourceType == BlockTypes.FLOWING_WATER;
        boolean isLava = sourceType == BlockTypes.LAVA || sourceType == BlockTypes.FLOWING_LAVA;

        if (!isWater && !isLava) {
            return;
        }
        int searchY = y - 1;
        while (searchY > dimension.getDimensionType().getMinHeight()) {
            var belowState = dimension.getBlockState(x, searchY, z);
            var belowType = belowState.getBlockType();

            if (belowType != BlockTypes.AIR) {
                if (belowType == BlockTypes.CAULDRON) {
                    tryFillCauldron(dimension, x, searchY, z, isWater);
                }
                break;
            }
            searchY--;
        }
        dimension.addSound(x + 0.5, y, z + 0.5, new PointedDripstoneDripSound(isWater));
    }

    /**
     * Attempts a liquid-specific random filling action for the supplied cauldron
     * coordinates. A cancellable drip event runs before checking its stored liquid
     * and fill level; empty cauldrons accept the liquid, while nonempty cauldrons
     * require a matching type and spare capacity.
     */
    private void tryFillCauldron(Dimension dimension, int x, int y, int z, boolean isWater) {
        var rand = ThreadLocalRandom.current();
        double probability = isWater ? WATER_DRIP_PROBABILITY : LAVA_DRIP_PROBABILITY;

        // Liquid-specific probabilities gate filling independently of the tip growth
        // draw.
        if (rand.nextDouble() > probability) {
            return;
        }

        var cauldronState = dimension.getBlockState(x, y, z);
        var event = new PointedDripstoneDripEvent(
                new Block(cauldronState, new Position3i(x, y, z, dimension), 0), isWater);
        event.call();
        if (event.isCancelled()) {
            return;
        }
        var currentLiquid = cauldronState.getPropertyValue(BlockPropertyTypes.CAULDRON_LIQUID);
        var fillLevel = cauldronState.getPropertyValue(BlockPropertyTypes.FILL_LEVEL);
        var expectedLiquid = isWater ? CauldronLiquid.WATER : CauldronLiquid.LAVA;
        if (fillLevel == 0) {
            var newState = cauldronState
                    .setPropertyValue(BlockPropertyTypes.CAULDRON_LIQUID, expectedLiquid)
                    .setPropertyValue(BlockPropertyTypes.FILL_LEVEL, 1);
            dimension.setBlockState(x, y, z, newState);
        // Six is the maximum stored cauldron fill level.
        } else if (currentLiquid == expectedLiquid && fillLevel < 6) {
            dimension.updateBlockProperty(BlockPropertyTypes.FILL_LEVEL, fillLevel + 1, x, y, z);
        }
    }
}
