package org.allaymc.server.block.component;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.data.Instrument;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.blockentity.interfaces.BlockEntityNoteblock;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.particle.NoteParticle;
import org.allaymc.api.world.sound.NoteSound;

/**
 * {@code BlockNoteblockBaseComponentImpl} plays a tuned note for player
 * interactions and redstone rising edges. The block below selects the instrument,
 * and the block entity supplies pitch and remembered power. A nonair block above
 * suppresses sound and note particles.
 *
 * <p>
 * Neighbor handling compares current power with remembered power before storing
 * the latest observation, so sustained power attempts one note. Player interaction
 * advances pitch, while redstone playback retains it. The packet encoder sends
 * {@code NoteParticle} positions as integer block coordinates. Centering a
 * negative block coordinate before that integer conversion can address another
 * block, so both playback paths pass the exact block position.
 *
 * @author daoge_cmd
 */
public class BlockNoteblockBaseComponentImpl extends BlockBaseComponentImpl {

    /**
     * Creates the noteblock block component for the supplied registered block
     * type.
     */
    public BlockNoteblockBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    /**
     * Runs shared handling and plays the stored pitch on a redstone rising edge.
     * The block entity remembers current power even when the note is suppressed by
     * the block above.
     */
    @Override
    public void onNeighborUpdate(Block block, Block neighbor, BlockFace face, BlockState oldNeighborState) {
        super.onNeighborUpdate(block, neighbor, face, oldNeighborState);

        var blockEntity = (BlockEntityNoteblock) block.getBlockEntity();
        if (blockEntity == null) {
            return;
        }

        var powered = block.isPowered();
        // Compare with the previous observation before recording current power so
        // a sustained signal attempts one note rather than repeating on every
        // neighbor update.
        if (powered && !blockEntity.isPowered()) {
            emitSound(block);
        }
        blockEntity.setPowered(powered);
    }

    /**
     * Advances the pitch and plays a note for a player interaction. Missing
     * interaction data, a nonair block above, or an absent block entity returns
     * {@code false} without advancing pitch.
     */
    @Override
    public boolean onInteract(ItemStack itemStack, Dimension dimension, PlayerInteractInfo interactInfo) {
        if (interactInfo == null) {
            return false;
        }

        var clickedBlock = interactInfo.getClickedBlock();
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
        // NoteParticle is encoded with integer block coordinates. Centering X=-1
        // would produce -0.5 and truncate to block zero, so bypass
        // Block.addParticle and preserve the original block address.
        clickedBlock.getDimension().addParticle(clickedBlock.getPosition(), new NoteParticle(instrument, pitch));
        return true;
    }

    /**
     * Plays the stored pitch and emits its note particle when the space above is
     * air. An absent block entity leaves the block silent, and this playback path
     * preserves its current pitch.
     */
    protected void emitSound(Block block) {
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
        // The packet encoder truncates note positions to integers. Preserve the
        // exact block address here too, so redstone notes avoid the shift at negative
        // coordinates that centering would introduce.
        block.getDimension().addParticle(block.getPosition(), new NoteParticle(instrument, pitch));
    }
}
