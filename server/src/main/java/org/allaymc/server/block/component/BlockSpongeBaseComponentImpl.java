package org.allaymc.server.block.component;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.BlockStateWithPos;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.tag.BlockCustomTags;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.math.MathUtils;
import org.cloudburstmc.protocol.bedrock.data.LevelEvent;
import org.cloudburstmc.protocol.bedrock.data.SoundEvent;

import java.util.ArrayDeque;

/**
 * @author daoge_cmd
 */
public class BlockSpongeBaseComponentImpl extends BlockBaseComponentImpl {

    protected static final int MAX_REMOVED_WATER_COUNT = 64;

    public BlockSpongeBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    @Override
    public void afterPlaced(BlockStateWithPos oldBlockState, BlockState newBlockState, PlayerInteractInfo placementInfo) {
        super.afterPlaced(oldBlockState, newBlockState, placementInfo);
        tryAbsorbWater(oldBlockState);
    }

    @Override
    public void onNeighborUpdate(BlockStateWithPos current, BlockStateWithPos neighbor, BlockFace face) {
        super.onNeighborUpdate(current, neighbor, face);
        tryAbsorbWater(current);
    }

    protected void tryAbsorbWater(BlockStateWithPos center) {
        if (performAbsorbWater(center)) {
            center.dimension().setBlockState(center.pos(), BlockTypes.WET_SPONGE.getDefaultState());
            center.dimension().addLevelEvent(
                    MathUtils.center(center.pos()),
                    LevelEvent.PARTICLE_DESTROY_BLOCK,
                    BlockTypes.WATER.getDefaultState().blockStateHash()
            );
            center.dimension().addLevelSoundEvent(
                    MathUtils.center(center.pos()),
                    SoundEvent.SPONGE_ABSORB
            );
        }
    }

    protected boolean performAbsorbWater(BlockStateWithPos center) {
        var waterFound = false;
        for (var face : BlockFace.values()) {
            if (center.offsetPos(face).blockState().getBlockType().hasBlockTag(BlockCustomTags.WATER)) {
                waterFound = true;
                break;
            }
        }
        if (!waterFound) {
            return false;
        }

        var entries = new ArrayDeque<Entry>();
        entries.add(new Entry(center, 0));

        Entry entry;
        var waterRemoved = 0;
        while (waterRemoved < MAX_REMOVED_WATER_COUNT && (entry = entries.poll()) != null) {
            for (var face : BlockFace.values()) {
                var side = entry.block.offsetPos(face);
                if (side.blockState().getBlockType().hasBlockTag(BlockCustomTags.WATER)) {
                    center.dimension().setBlockState(side.pos(), BlockTypes.AIR.getDefaultState());
                    ++waterRemoved;
                    if (entry.distance < 6) {
                        entries.add(new Entry(side, entry.distance + 1));
                    }
                } else if (side.blockState().getBlockType() == BlockTypes.AIR) {
                    if (entry.distance < 6) {
                        entries.add(new Entry(side, entry.distance + 1));
                    }
                }
            }
        }

        return waterRemoved > 0;
    }

    protected record Entry(BlockStateWithPos block, int distance) {}
}
