package org.allaymc.server.block.component;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.data.Instrument;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.blockentity.interfaces.BlockEntityNoteblock;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.particle.NoteParticle;
import org.allaymc.api.world.sound.NoteSound;

/**
 * Implementation of the noteblock.
 * Plays sound when powered by redstone (on rising edge) or when interacted.
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

        var powered = block.isPowered();
        if (powered && !blockEntity.isPowered()) {
            // Rising edge - play sound
            emitSound(block);
        }
        blockEntity.setPowered(powered);
    }

    @Override
    public boolean onInteract(ItemStack itemStack, Dimension dimension, PlayerInteractInfo interactInfo) {
        if (interactInfo == null) {
            return false;
        }

        // Sneaking player should not trigger noteblock (allows placing blocks on top)
        if (interactInfo.player().isSneaking()) {
            return false;
        }

        var clickedBlock = interactInfo.getClickedBlock();
        // Can't play if block above is not air
        if (clickedBlock.offsetPos(BlockFace.UP).getBlockType() != BlockTypes.AIR) {
            return false;
        }

        var blockEntity = (BlockEntityNoteblock) clickedBlock.getBlockEntity();
        if (blockEntity == null) {
            return false;
        }

        var instrument = Instrument.fromBlockBelow(clickedBlock.offsetPos(BlockFace.DOWN).getBlockType());
        var pitch = blockEntity.getNextPitch();

        clickedBlock.addSound(new NoteSound(instrument, pitch));
        // NOTICE: Do not use Block.addParticle() method which center the block pos but the client doesn't want
        clickedBlock.getDimension().addParticle(clickedBlock.getPosition(), new NoteParticle(instrument, pitch));
        return true;
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

        var instrument = Instrument.fromBlockBelow(block.offsetPos(BlockFace.DOWN).getBlockType());
        var pitch = blockEntity.getPitch();

        block.addSound(new NoteSound(instrument, pitch));
        // NOTICE: Do not use Block.addParticle() method which center the block pos but the client doesn't want
        block.getDimension().addParticle(block.getPosition(), new NoteParticle(instrument, pitch));
    }
}
