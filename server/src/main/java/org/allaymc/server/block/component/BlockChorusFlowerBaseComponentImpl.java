package org.allaymc.server.block.component;

import it.unimi.dsi.fastutil.Pair;
import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.BlockStateWithPos;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.Sound;
import org.joml.Vector3ic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static org.allaymc.api.block.property.type.BlockPropertyTypes.AGE_6;

/**
 * @author IWareQ
 */
public class BlockChorusFlowerBaseComponentImpl extends BlockBaseComponentImpl {
    private static final int MAX_STEM_HEIGHT = 5;

    public BlockChorusFlowerBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    @Override
    public void onNeighborUpdate(BlockStateWithPos current, BlockStateWithPos neighbor, BlockFace face) {
        super.onNeighborUpdate(current, neighbor, face);

        if (face == BlockFace.DOWN && !canBeSupportedAt(current.offsetPos(BlockFace.DOWN))) {
            current.breakBlock();
        }
    }

    @Override
    public void onRandomUpdate(BlockStateWithPos current) {
        super.onRandomUpdate(current);

        var currentAge = current.getPropertyValue(AGE_6);
        if (currentAge >= AGE_6.getMax()) {
            return;
        }

        var stemInfo = findStem(current);
        var stemHeight = stemInfo.left();
        var hasBranch = stemInfo.right();

        // Try upward growth
        if (currentAge < 5 && shouldGrowUpward(stemHeight, hasBranch) && isSurroundingAir(current.offsetPos(BlockFace.UP), null)) {
            growInDirection(current, BlockFace.UP, 0);
            return;
        }

        // Try horizontal growth
        if (currentAge < 4) {
            // Determine number of growth attempts (0-3 if branched, 1-4 if not)
            var maxAttempts = ThreadLocalRandom.current().nextInt(hasBranch ? 0 : 1, (hasBranch ? 3 : 4) + 1);

            List<BlockFace> directions = new ArrayList<>(List.of(BlockFace.getHorizontalBlockFaces()));
            Collections.shuffle(directions);

            boolean grew = false;
            for (var face : directions) {
                maxAttempts--;
                if (maxAttempts <= 0) {
                    break;
                }

                var side = current.offsetPos(face);
                if (canBranchTo(side, face)) {
                    growInDirection(current, face, 1);
                    grew = true;
                }
            }

            if (grew) {
                current.getDimension().setBlockState(current.getPos(), BlockTypes.CHORUS_PLANT.getDefaultState());
                return;
            }
        }

        // Growth failed — kill flower
        current.getDimension().setBlockState(current.getPos(), current.setPropertyValue(AGE_6, AGE_6.getMax()));
    }

    @Override
    public boolean place(Dimension dimension, BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        if (placementInfo == null) {
            return super.place(dimension, blockState, placeBlockPos, null);
        }

        if (canBeSupportedAt(placementInfo.getClickedBlockState()) && placementInfo.blockFace() == BlockFace.UP) {
            return dimension.setBlockState(placeBlockPos, blockState);
        }

        return false;
    }

    @Override
    public boolean canRandomUpdate() {
        return true;
    }

    protected boolean canBeSupportedAt(BlockState blockState) {
        return blockState.getBlockType() == BlockTypes.END_STONE || blockState.getBlockType() == BlockTypes.CHORUS_PLANT;
    }

    protected boolean shouldGrowUpward(int stemHeight, boolean hasBranch) {
        if (stemHeight >= MAX_STEM_HEIGHT) {
            return false;
        }

        var chance = switch (stemHeight) {
            case 0, 1 -> 1f; // 100% chance for short stems
            case 2 -> hasBranch ? 0.5f : 0.6f;
            case 3 -> hasBranch ? 0.25f : 0.4f;
            case 4 -> hasBranch ? 0f : 0.2f;
            default -> 0f; // No growth beyond max
        };

        return ThreadLocalRandom.current().nextFloat() < chance;
    }

    protected boolean canBranchTo(BlockStateWithPos side, BlockFace fromFace) {
        return side.getBlockType() == BlockTypes.AIR &&
               side.offsetPos(BlockFace.DOWN).getBlockType() == BlockTypes.AIR &&
               isSurroundingAir(side, fromFace.opposite());
    }

    protected boolean isSurroundingAir(BlockStateWithPos current, BlockFace ignore) {
        for (var face : BlockFace.getHorizontalBlockFaces()) {
            if (face == ignore) {
                continue;
            }

            if (current.offsetPos(face).getBlockType() != BlockTypes.AIR) {
                return false;
            }
        }

        return true;
    }

    protected Pair<Integer, Boolean> findStem(BlockStateWithPos current) {
        int stemHeight = 0;
        boolean hasBranch = false;
        var down = current;

        for (int i = 0; i < MAX_STEM_HEIGHT; i++) {
            down = down.offsetPos(BlockFace.DOWN);
            if (down.getBlockType() != BlockTypes.CHORUS_PLANT) {
                break;
            }

            stemHeight++;

            for (var face : BlockFace.getHorizontalBlockFaces()) {
                if (down.offsetPos(face).getBlockType() == BlockTypes.CHORUS_PLANT) {
                    hasBranch = true;
                    break;
                }
            }
        }

        return Pair.of(stemHeight, hasBranch);
    }

    protected void growInDirection(BlockStateWithPos current, BlockFace face, int addingAge) {
        current.getDimension().setBlockState(current.getPos(), BlockTypes.CHORUS_PLANT.getDefaultState());

        var newPos = current.offsetPos(face).getPos();
        var newBlockState = (BlockState) current;
        var currentAge = newBlockState.getPropertyValue(AGE_6);
        newBlockState = newBlockState.setPropertyValue(AGE_6, Math.min(AGE_6.getMax(), currentAge + addingAge));
        current.getDimension().setBlockState(newPos, newBlockState);
        current.getDimension().addSound(newPos, Sound.BLOCK_CHORUSFLOWER_GROW);
    }
}
