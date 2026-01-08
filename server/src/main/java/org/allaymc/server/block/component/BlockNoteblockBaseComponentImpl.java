package org.allaymc.server.block.component;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.data.BlockTags;
import org.allaymc.api.block.data.Instrument;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.blockentity.interfaces.BlockEntityNoteblock;
import org.allaymc.api.math.position.Position3i;
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.particle.NoteParticle;
import org.allaymc.api.world.sound.NoteSound;
import org.joml.Vector3ic;

/**
 * Implementation of the noteblock.
 * Plays sound when powered by redstone (on rising edge).
 *
 * @author daoge_cmd
 */
public class BlockNoteblockBaseComponentImpl extends BlockBaseComponentImpl {

    public BlockNoteblockBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    @Override
    public void onNeighborUpdate(Block block, Block neighbor, BlockFace face) {
        super.onNeighborUpdate(block, neighbor, face);

        var blockEntity = (BlockEntityNoteblock) block.getBlockEntity();
        if (blockEntity == null) {
            return;
        }

        boolean powered = isReceivingPower(block);
        if (powered && !blockEntity.isPowered()) {
            // Rising edge - play sound
            emitSound(block);
        }
        blockEntity.setPowered(powered);
    }

    /**
     * Checks if the noteblock is receiving redstone power from any direction.
     */
    protected boolean isReceivingPower(Block block) {
        var dimension = block.getDimension();
        var pos = block.getPosition();

        for (BlockFace face : BlockFace.values()) {
            Vector3ic neighborPos = face.offsetPos(pos);
            BlockState neighborState = dimension.getBlockState(neighborPos);
            Block neighborBlock = new Block(neighborState, new Position3i(neighborPos, dimension));

            // Check weak power
            int weakPower = neighborState.getBehavior().getWeakPower(neighborBlock, face.opposite());
            if (weakPower > 0) {
                return true;
            }

            // Check power through solid blocks (only strong power can conduct through)
            if (neighborState.getBlockStateData().isOpaqueSolid()) {
                int powerThroughBlock = getStrongPowerIntoBlock(dimension, neighborPos, face.opposite());
                if (powerThroughBlock > 0) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Gets strong power flowing into a solid block.
     */
    protected int getStrongPowerIntoBlock(Dimension dimension, Vector3ic blockPos, BlockFace excludeFace) {
        int maxPower = 0;
        for (BlockFace face : BlockFace.values()) {
            if (face == excludeFace) continue;

            Vector3ic checkPos = face.offsetPos(blockPos);
            BlockState state = dimension.getBlockState(checkPos);
            Block checkBlock = new Block(state, new Position3i(checkPos, dimension));

            int strongPower = state.getBehavior().getStrongPower(checkBlock, face.opposite());
            maxPower = Math.max(maxPower, strongPower);
        }
        return maxPower;
    }

    /**
     * Emits sound and particle from the noteblock.
     */
    protected void emitSound(Block block) {
        // Can't play if block above is not air
        if (block.offsetPos(BlockFace.UP).getBlockType() != BlockTypes.AIR) {
            return;
        }

        var blockEntity = (BlockEntityNoteblock) block.getBlockEntity();
        if (blockEntity == null) {
            return;
        }

        var dimension = block.getDimension();
        var pos = block.getPosition();
        var instrument = getInstrument(block.offsetPos(BlockFace.DOWN).getBlockType());
        var pitch = blockEntity.getPitch();

        dimension.addSound(pos, new NoteSound(instrument, pitch));
        dimension.addParticle(pos, new NoteParticle(instrument, pitch));
    }

    /**
     * Gets the instrument based on the block below the noteblock.
     */
    protected Instrument getInstrument(BlockType<?> block) {
        if (block.hasBlockTag(BlockTags.WOOD) ||
            block.hasBlockTag(BlockTags.LOG)) {
            return Instrument.BASS;
        }

        if (block.hasBlockTag(BlockTags.SAND) ||
            block.hasBlockTag(BlockTags.GRAVEL) ||
            block.hasBlockTag(BlockTags.CONCRETE_POWDER) ||
            block == BlockTypes.HEAVY_CORE) {
            return Instrument.SNARE;
        }

        if (block.hasBlockTag(BlockTags.STONE)) {
            return Instrument.BASS_DRUM;
        }

        if (block == BlockTypes.GOLD_BLOCK) {
            return Instrument.BELL;
        }

        if (block.hasBlockTag(BlockTags.INFESTED) ||
            block == BlockTypes.CLAY ||
            block == BlockTypes.HONEYCOMB_BLOCK ||
            block == BlockTypes.RESIN_BLOCK) {
            return Instrument.FLUTE;
        }

        if (block == BlockTypes.PACKED_ICE) {
            return Instrument.CHIMES;
        }

        if (block.hasBlockTag(BlockTags.WOOL)) {
            return Instrument.GUITAR;
        }

        if (block == BlockTypes.BONE_BLOCK) {
            return Instrument.XYLOPHONE;
        }

        if (block == BlockTypes.IRON_BLOCK) {
            return Instrument.IRON_XYLOPHONE;
        }

        if (block == BlockTypes.SOUL_SAND) {
            return Instrument.COW_BELL;
        }

        if (block == BlockTypes.PUMPKIN) {
            return Instrument.DIDGERIDOO;
        }

        if (block == BlockTypes.EMERALD_BLOCK) {
            return Instrument.BIT;
        }

        if (block == BlockTypes.HAY_BLOCK) {
            return Instrument.BANJO;
        }

        if (block == BlockTypes.GLOWSTONE) {
            return Instrument.PLING;
        }

        return Instrument.PIANO;
    }
}
