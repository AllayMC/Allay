package org.allaymc.server.blockentity.component;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.blockentity.BlockEntityInitInfo;
import org.allaymc.api.blockentity.component.BlockEntityNoteblockBaseComponent;
import org.cloudburstmc.nbt.NbtMap;

/**
 * {@code BlockEntityNoteblockBaseComponentImpl} stores note-block pitch and
 * remembered redstone input and exchanges both through NBT. The generated
 * setters retain values directly, while {@code getNextPitch} advances the pitch
 * for block interaction logic without producing sound itself.
 *
 * @author daoge_cmd
 */
public class BlockEntityNoteblockBaseComponentImpl extends BlockEntityBaseComponentImpl implements BlockEntityNoteblockBaseComponent {

    /**
     * {@code TAG_NOTE} names the persisted pitch integer. The note-block tag
     * constants identify pitch and remembered redstone state during NBT
     * exchange.
     */
    protected static final String TAG_NOTE = "note";
    /**
     * Persisted remembered redstone input.
     */
    protected static final String TAG_POWERED = "powered";

    /**
     * {@code pitch} holds the note index. The generated setter and NBT loader
     * retain integers directly, so callers maintain the ordinary
     * zero-to-twenty-four range.
     */
    @Getter
    @Setter
    protected int pitch;

    /**
     * Remembered active redstone input.
     */
    @Getter
    @Setter
    protected boolean powered;

    /**
     * Creates common note-block state with zero pitch and inactive redstone
     * input.
     */
    public BlockEntityNoteblockBaseComponentImpl(BlockEntityInitInfo initInfo) {
        super(initInfo);
    }

    /**
     * Loads common data and supplied pitch or powered-state entries. Missing
     * tags preserve current values; the pitch is retained without validation,
     * and loading does not emit a note or send updates.
     */
    @Override
    public void loadNBT(NbtMap nbt) {
        super.loadNBT(nbt);
        nbt.listenForInt(TAG_NOTE, value -> pitch = value);
        nbt.listenForBoolean(TAG_POWERED, value -> powered = value);
    }

    /**
     * Builds common NBT with retained pitch and remembered redstone state.
     */
    @Override
    public NbtMap saveNBT() {
        return super.saveNBT()
                .toBuilder()
                .putInt(TAG_NOTE, pitch)
                .putBoolean(TAG_POWERED, powered)
                .build();
    }

    /**
     * Increments the retained pitch modulo twenty-five and returns the result.
     * Ordinary pitches wrap from twenty-four to zero; invalid stored integers
     * use Java remainder behavior rather than being normalized beforehand.
     */
    @Override
    public int getNextPitch() {
        // Twenty-five distinct notes make the highest ordinary pitch wrap to
        // zero.
        this.pitch = (this.pitch + 1) % 25;
        return this.pitch;
    }
}
