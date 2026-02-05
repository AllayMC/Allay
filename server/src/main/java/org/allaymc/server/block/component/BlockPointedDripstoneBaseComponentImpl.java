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
 * Implementation of pointed dripstone block behavior.
 * <p>
 * Pointed dripstone can form stalactites (hanging) and stalagmites (ground).
 * They can grow, drip liquids, and deal fall damage.
 *
 * @author daoge_cmd
 */
public class BlockPointedDripstoneBaseComponentImpl extends BlockBaseComponentImpl {

    // Growth probability per random tick (approximately 1.14%)
    private static final double GROWTH_PROBABILITY = 0.011377778;

    // Drip probabilities
    private static final double WATER_DRIP_PROBABILITY = 45.0 / 256.0;
    private static final double LAVA_DRIP_PROBABILITY = 15.0 / 256.0;

    // Maximum height of a dripstone column
    private static final int MAX_COLUMN_HEIGHT = 11;

    public BlockPointedDripstoneBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

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

        // Check if placement is valid (must have solid block or dripstone to attach to)
        if (isAboveAir && isBelowAir) {
            return false;
        }

        /*
         * State determination based on PNX1.0:
         * State 1: Both above and below have dripstone - merge
         * State 2: Only above has dripstone - extend stalactite (hanging)
         * State 3: Neither has dripstone - new single dripstone
         * State 4: Only below has dripstone - extend stalagmite (not hanging)
         */
        int state = hasDripstoneAbove ? (hasDripstoneBelow ? 1 : 2) : (!hasDripstoneBelow ? 3 : 4);
        boolean hanging = false;

        switch (state) {
            case 1 -> {
                // Both above and below have dripstone - create merge point
                setMergeBlock(dimension, blockState, x, y, z, false);
                setBlockThicknessAt(dimension, x, y + 1, z, true, DripstoneThickness.MERGE);
            }
            case 2 -> {
                // Only above has dripstone - extend stalactite downward
                if (!isBelowAir) {
                    // Solid block below
                    if (face == BlockFace.UP) {
                        // Clicked on solid from below - merge with stalactite above
                        setBlockThicknessAt(dimension, x, y + 1, z, true, DripstoneThickness.MERGE);
                        setMergeBlock(dimension, blockState, x, y, z, false);
                    } else {
                        // Extend stalactite downward
                        setTipBlock(dimension, blockState, x, y, z, true);
                        updateColumnAfterAdd(dimension, x, y, z, true);
                    }
                    return true;
                }
                hanging = true;
            }
            case 3 -> {
                // Neither has dripstone - new single dripstone
                setTipBlock(dimension, blockState, x, y, z, face != BlockFace.UP);
                return true;
            }
            case 4 -> {
                // Only below has dripstone - extend stalagmite upward
                if (!isAboveAir) {
                    // Solid block above
                    if (face == BlockFace.DOWN) {
                        // Clicked on solid from above - merge with stalagmite below
                        setMergeBlock(dimension, blockState, x, y, z, true);
                        setBlockThicknessAt(dimension, x, y - 1, z, false, DripstoneThickness.MERGE);
                    } else {
                        // Extend stalagmite upward
                        setTipBlock(dimension, blockState, x, y, z, false);
                        updateColumnAfterAdd(dimension, x, y, z, false);
                    }
                    return true;
                }
                // hanging stays false for stalagmite
            }
        }

        // For state 1, just return true
        // For state 2 (hanging=true) and state 4 (hanging=false) when continuing here:
        // update the column and place tip block
        updateColumnAfterAdd(dimension, x, y, z, hanging);
        if (state == 1) {
            return true;
        }
        setTipBlock(dimension, blockState, x, y, z, hanging);
        return true;
    }

    /**
     * Place a dripstone block as TIP.
     */
    private void setTipBlock(Dimension dimension, BlockState blockState, int x, int y, int z, boolean hanging) {
        var newState = blockState
                .setPropertyValue(BlockPropertyTypes.HANGING, hanging)
                .setPropertyValue(BlockPropertyTypes.DRIPSTONE_THICKNESS, DripstoneThickness.TIP);
        dimension.setBlockState(x, y, z, newState);
    }

    /**
     * Place a dripstone block as MERGE.
     */
    private void setMergeBlock(Dimension dimension, BlockState blockState, int x, int y, int z, boolean hanging) {
        var newState = blockState
                .setPropertyValue(BlockPropertyTypes.HANGING, hanging)
                .setPropertyValue(BlockPropertyTypes.DRIPSTONE_THICKNESS, DripstoneThickness.MERGE);
        dimension.setBlockState(x, y, z, newState);
    }

    /**
     * Update an existing dripstone block's thickness.
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
     * Update the column thickness after adding a new dripstone.
     * Updates blocks above (for hanging) or below (for not hanging) based on new column length.
     */
    private void updateColumnAfterAdd(Dimension dimension, int x, int y, int z, boolean hanging) {
        int length = getColumnLength(dimension, x, y, z, hanging);
        int dy = hanging ? 1 : -1;

        // Update thickness based on distance from the new tip
        if (length == 1) {
            // Only one block above/below the tip - make it FRUSTUM
            setBlockThicknessAt(dimension, x, y + dy, z, hanging, DripstoneThickness.FRUSTUM);
        } else if (length == 2) {
            // Two blocks - BASE and FRUSTUM
            setBlockThicknessAt(dimension, x, y + dy * 2, z, hanging, DripstoneThickness.BASE);
            setBlockThicknessAt(dimension, x, y + dy, z, hanging, DripstoneThickness.FRUSTUM);
        } else if (length >= 3) {
            // Three or more - MIDDLE, FRUSTUM (and the rest stay as they are)
            setBlockThicknessAt(dimension, x, y + dy * 2, z, hanging, DripstoneThickness.MIDDLE);
            setBlockThicknessAt(dimension, x, y + dy, z, hanging, DripstoneThickness.FRUSTUM);
        }
    }

    @Override
    public void onNeighborUpdate(Block block, Block neighbor, BlockFace face, BlockState oldNeighborState) {
        super.onNeighborUpdate(block, neighbor, face, oldNeighborState);

        var hanging = block.getPropertyValue(BlockPropertyTypes.HANGING);
        var supportFace = hanging ? BlockFace.UP : BlockFace.DOWN;

        // Check if support was removed for stalagmites (they break, don't fall)
        // Stalactites are handled by the fallable component
        if (!hanging && face == supportFace) {
            var supportBlock = block.offsetPos(supportFace);
            if (!supportBlock.getBlockStateData().isSolid() &&
                supportBlock.getBlockType() != BlockTypes.POINTED_DRIPSTONE) {
                // Stalagmites just break
                breakColumn(block.getDimension(), block.getPosition().x(), block.getPosition().y(),
                        block.getPosition().z());
            }
        }

        // Handle thickness when adjacent dripstone changes
        if (neighbor.getBlockType() == BlockTypes.POINTED_DRIPSTONE ||
            neighbor.getBlockType() == BlockTypes.AIR) {
            recalculateThickness(block);
        }
    }

    /**
     * Breaks a stalagmite column (non-hanging dripstones).
     */
    private void breakColumn(Dimension dimension, int x, int y, int z) {
        var currentState = dimension.getBlockState(x, y, z);
        if (currentState.getBlockType() != BlockTypes.POINTED_DRIPSTONE) {
            return;
        }

        // Break this block
        dimension.breakBlock(x, y, z, null, null);

        // Break the rest of the column (upward for stalagmites)
        int nextY = y + 1;
        var nextState = dimension.getBlockState(x, nextY, z);
        if (nextState.getBlockType() == BlockTypes.POINTED_DRIPSTONE &&
            !nextState.getPropertyValue(BlockPropertyTypes.HANGING)) {
            breakColumn(dimension, x, nextY, z);
        }
    }

    private void recalculateThickness(Block block) {
        var dimension = block.getDimension();
        var pos = block.getPosition();
        int x = pos.x(), y = pos.y(), z = pos.z();

        // Check if the block is still a pointed dripstone (it may have been broken)
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

    private int getColumnLength(Dimension dimension, int x, int y, int z, boolean hanging) {
        int length = 1;
        int dy = hanging ? 1 : -1;
        int currentY = y + dy;

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

    private DripstoneThickness calculateThickness(Dimension dimension, int x, int y, int z, boolean hanging) {
        // For hanging=true (stalactite): base is UP, tip is DOWN
        // For hanging=false (stalagmite): base is DOWN, tip is UP
        int baseDir = hanging ? 1 : -1;
        int tipDir = -baseDir;

        // Check for merge first (dripstone from opposite direction)
        var oppositeState = dimension.getBlockState(x, y + tipDir, z);
        if (oppositeState.getBlockType() == BlockTypes.POINTED_DRIPSTONE) {
            var oppositeHanging = oppositeState.getPropertyValue(BlockPropertyTypes.HANGING);
            if (oppositeHanging != hanging) {
                return DripstoneThickness.MERGE;
            }
        }

        // Count distance to tip (blocks in tip direction with same hanging value)
        int distanceToTip = 0;
        int currentY = y + tipDir;
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

        // Count distance to base (blocks in base direction with same hanging value)
        int distanceToBase = 0;
        currentY = y + baseDir;
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

        // Determine thickness based on position
        if (distanceToTip == 0) {
            // This is the tip
            return DripstoneThickness.TIP;
        } else if (distanceToTip == 1) {
            // One block from tip
            return DripstoneThickness.FRUSTUM;
        } else if (distanceToBase == 0) {
            // This is at the base (attached to solid block)
            return DripstoneThickness.BASE;
        } else {
            // In the middle
            return DripstoneThickness.MIDDLE;
        }
    }

    @Override
    public boolean canRandomUpdate() {
        return true;
    }

    @Override
    public void onRandomUpdate(Block block) {
        var thickness = block.getPropertyValue(BlockPropertyTypes.DRIPSTONE_THICKNESS);
        var hanging = block.getPropertyValue(BlockPropertyTypes.HANGING);

        // Only tips can grow and drip
        if (thickness != DripstoneThickness.TIP || !hanging) {
            return;
        }

        // Try to grow
        if (ThreadLocalRandom.current().nextDouble() < GROWTH_PROBABILITY) {
            tryGrow(block);
        }

        // Try to drip liquid
        tryDrip(block);
    }

    private void tryGrow(Block block) {
        var dimension = block.getDimension();
        var pos = block.getPosition();
        int x = pos.x(), y = pos.y(), z = pos.z();

        // Check if there's space below
        var below = dimension.getBlockState(x, y - 1, z);
        if (below.getBlockType() != BlockTypes.AIR) {
            return;
        }

        // Check column height
        int columnHeight = getColumnLength(dimension, x, y, z, true);
        if (columnHeight >= MAX_COLUMN_HEIGHT) {
            return;
        }

        // Grow downward
        var newTip = block.getBlockState()
                .setPropertyValue(BlockPropertyTypes.HANGING, true)
                .setPropertyValue(BlockPropertyTypes.DRIPSTONE_THICKNESS, DripstoneThickness.TIP);

        dimension.setBlockState(x, y - 1, z, newTip);

        // Update current block to FRUSTUM
        dimension.updateBlockProperty(BlockPropertyTypes.DRIPSTONE_THICKNESS, DripstoneThickness.FRUSTUM, pos);
    }

    private void tryDrip(Block block) {
        var dimension = block.getDimension();
        var pos = block.getPosition();
        int x = pos.x(), y = pos.y(), z = pos.z();

        // Find the top of the stalactite
        int topY = y;
        while (true) {
            var above = dimension.getBlockState(x, topY + 1, z);
            if (above.getBlockType() != BlockTypes.POINTED_DRIPSTONE) {
                break;
            }
            topY++;
            if (topY - y > MAX_COLUMN_HEIGHT) {
                return;
            }
        }

        // Check if there's liquid above the stalactite
        var sourceBlock = dimension.getBlockState(x, topY + 2, z);
        var sourceType = sourceBlock.getBlockType();

        boolean isWater = sourceType == BlockTypes.WATER || sourceType == BlockTypes.FLOWING_WATER;
        boolean isLava = sourceType == BlockTypes.LAVA || sourceType == BlockTypes.FLOWING_LAVA;

        if (!isWater && !isLava) {
            return;
        }

        // Find cauldron below
        int searchY = y - 1;
        while (searchY > dimension.getDimensionInfo().minHeight()) {
            var belowState = dimension.getBlockState(x, searchY, z);
            var belowType = belowState.getBlockType();

            if (belowType != BlockTypes.AIR) {
                if (belowType == BlockTypes.CAULDRON) {
                    // Found cauldron - try to fill
                    tryFillCauldron(dimension, x, searchY, z, isWater);
                }
                break;
            }
            searchY--;
        }

        // Add drip sound
        dimension.addSound(x + 0.5, y, z + 0.5, new PointedDripstoneDripSound(isWater));
    }

    private void tryFillCauldron(Dimension dimension, int x, int y, int z, boolean isWater) {
        var rand = ThreadLocalRandom.current();
        double probability = isWater ? WATER_DRIP_PROBABILITY : LAVA_DRIP_PROBABILITY;

        if (rand.nextDouble() > probability) {
            return;
        }

        var cauldronState = dimension.getBlockState(x, y, z);

        // Call event
        var event = new PointedDripstoneDripEvent(
                new Block(cauldronState, new Position3i(x, y, z, dimension), 0), isWater);
        event.call();
        if (event.isCancelled()) {
            return;
        }

        // Check current cauldron state
        var currentLiquid = cauldronState.getPropertyValue(BlockPropertyTypes.CAULDRON_LIQUID);
        var fillLevel = cauldronState.getPropertyValue(BlockPropertyTypes.FILL_LEVEL);
        var expectedLiquid = isWater ? CauldronLiquid.WATER : CauldronLiquid.LAVA;

        // Fill empty cauldron or add to matching type
        if (fillLevel == 0) {
            // Empty cauldron - fill with liquid
            var newState = cauldronState
                    .setPropertyValue(BlockPropertyTypes.CAULDRON_LIQUID, expectedLiquid)
                    .setPropertyValue(BlockPropertyTypes.FILL_LEVEL, 1);
            dimension.setBlockState(x, y, z, newState);
        } else if (currentLiquid == expectedLiquid && fillLevel < 6) {
            // Add to matching liquid cauldron
            dimension.updateBlockProperty(BlockPropertyTypes.FILL_LEVEL, fillLevel + 1, x, y, z);
        }
    }
}
