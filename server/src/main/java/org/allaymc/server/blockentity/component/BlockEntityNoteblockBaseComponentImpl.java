package org.allaymc.server.blockentity.component;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.data.BlockTags;
import org.allaymc.api.block.data.Instrument;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.blockentity.BlockEntityInitInfo;
import org.allaymc.api.blockentity.component.BlockEntityNoteblockBaseComponent;
import org.allaymc.api.eventbus.EventHandler;
import org.allaymc.api.world.particle.NoteParticle;
import org.allaymc.api.world.sound.NoteSound;
import org.allaymc.server.block.component.event.CBlockOnInteractEvent;
import org.cloudburstmc.nbt.NbtMap;

/**
 * @author daoge_cmd
 */
public class BlockEntityNoteblockBaseComponentImpl extends BlockEntityBaseComponentImpl implements BlockEntityNoteblockBaseComponent {

    protected static final String TAG_NOTE = "note";

    @Getter
    @Setter
    protected int pitch;

    @Getter
    @Setter
    protected boolean powered;

    public BlockEntityNoteblockBaseComponentImpl(BlockEntityInitInfo initInfo) {
        super(initInfo);
    }

    @Override
    public void loadNBT(NbtMap nbt) {
        super.loadNBT(nbt);
        nbt.listenForInt(TAG_NOTE, value -> pitch = value);
    }

    @Override
    public NbtMap saveNBT() {
        return super.saveNBT()
                .toBuilder()
                .putInt(TAG_NOTE, pitch)
                .build();
    }

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

    protected int getNextPitch() {
        this.pitch = (this.pitch + 1) % 25;
        return this.pitch;
    }

    @EventHandler
    protected void onBlockInteract(CBlockOnInteractEvent event) {
        var interactInfo = event.getInteractInfo();

        // Sneaking player should not trigger noteblock (allows placing blocks on top)
        if (interactInfo.player().isSneaking()) {
            return;
        }

        if (interactInfo.getClickedBlock().offsetPos(BlockFace.UP).getBlockType() != BlockTypes.AIR) {
            return;
        }

        var dimension = event.getDimension();
        var instrument = getInstrument(interactInfo.getClickedBlock().offsetPos(BlockFace.DOWN).getBlockType());
        var nextPitch = getNextPitch();
        var pos = interactInfo.clickedBlockPos();
        dimension.addSound(pos, new NoteSound(instrument, nextPitch));
        dimension.addParticle(pos, new NoteParticle(instrument, nextPitch));
        event.setSuccess(true);
    }
}
