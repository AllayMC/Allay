package org.allaymc.server.blockentity.component;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.blockentity.BlockEntityInitInfo;
import org.allaymc.api.blockentity.component.BlockEntityJukeboxBaseComponent;
import org.allaymc.api.eventbus.EventHandler;
import org.allaymc.api.item.interfaces.ItemMusicDiscStack;
import org.allaymc.api.utils.NBTIO;
import org.allaymc.api.world.sound.MusicDiscPlaySound;
import org.allaymc.api.world.sound.SimpleSound;
import org.allaymc.server.block.component.event.CBlockOnReplaceEvent;
import org.cloudburstmc.nbt.NbtMap;
import org.joml.Vector3d;

import java.util.concurrent.ThreadLocalRandom;

/**
 * {@code BlockEntityJukeboxBaseComponentImpl} stores one optional music disc
 * and emits jukebox playback sounds. Disc assignment refreshes comparator
 * output but leaves playback control to explicit calls; block replacement drops
 * the disc, clears its reference, and emits the stop sound.
 *
 * @author IWareQ | daoge_cmd
 */
@Slf4j
public class BlockEntityJukeboxBaseComponentImpl extends BlockEntityBaseComponentImpl implements BlockEntityJukeboxBaseComponent {
    /**
     * {@code TAG_RECORD_ITEM} names the retained music-disc compound used by
     * jukebox NBT loading and saving alongside the common block-entity state.
     */
    protected static final String TAG_RECORD_ITEM = "RecordItem";

    /**
     * {@code musicDiscItem} holds the shared mutable disc, or {@code null} when
     * empty. The generated getter exposes the same reference used by playback
     * and saving.
     */
    @Getter
    private ItemMusicDiscStack musicDiscItem;

    /**
     * Retains the supplied disc reference and refreshes comparator output.
     * {@code null} clears the reference; the method does not copy the stack,
     * send a block-entity update, or start or stop playback.
     */
    @Override
    public void setMusicDiscItem(ItemMusicDiscStack musicDiscItem) {
        this.musicDiscItem = musicDiscItem;
        this.getDimension().updateComparatorOutputLevel(this.getPosition());
    }

    /**
     * Creates common jukebox state with no retained music-disc reference.
     */
    public BlockEntityJukeboxBaseComponentImpl(BlockEntityInitInfo initInfo) {
        super(initInfo);
    }

    /**
     * Emits a music-disc play sound using the retained disc's type. The disc
     * reference must be non-{@code null}; playback does not clear it or record
     * a separate playing flag in this component.
     */
    @Override
    public void play() {
        this.getDimension().addSound(this.getPosition(), new MusicDiscPlaySound(this.musicDiscItem.getDiscType()));
    }

    /**
     * Emits the music-disc end sound at this jukebox's stored position.
     */
    @Override
    public void stop() {
        this.getDimension().addSound(this.getPosition(), SimpleSound.MUSIC_DISC_END);
    }

    /**
     * Drops a retained disc, clears the reference, and stops playback during
     * replacement. Empty jukeboxes do nothing; the reference is cleared only
     * after the randomized drop operation returns, before the stop sound is
     * emitted.
     */
    @EventHandler
    protected void onBlockReplace(CBlockOnReplaceEvent event) {
        if (this.musicDiscItem != null) {
            var current = event.getCurrentBlock();
            var pos = current.getPosition();
            var rand = ThreadLocalRandom.current();

            // Restrict randomized drop coordinates to the block's central half.
            current.getDimension().dropItem(this.musicDiscItem, new Vector3d(
                    pos.x() + rand.nextDouble(0.5) + 0.25,
                    pos.y() + rand.nextDouble(0.5) + 0.25,
                    pos.z() + rand.nextDouble(0.5) + 0.25
            ));
            this.musicDiscItem = null;
            this.stop();
        }
    }

    /**
     * Builds common NBT and includes a disc compound only when occupied. An
     * empty jukebox returns the inherited save map directly, while a present
     * disc serializes through its retained mutable stack.
     */
    @Override
    public NbtMap saveNBT() {
        var savedNbt = super.saveNBT();
        if (musicDiscItem != null) {
            savedNbt = savedNbt.toBuilder()
                    .putCompound(TAG_RECORD_ITEM, this.musicDiscItem.saveNBT())
                    .build();
        }
        return savedNbt;
    }

    /**
     * Loads common state and accepts a decoded music disc from its optional
     * compound. A missing compound preserves the current reference; a decoded
     * non-disc is logged and leaves the previous disc unchanged without
     * playback changes.
     */
    @Override
    public void loadNBT(NbtMap nbt) {
        super.loadNBT(nbt);
        nbt.listenForCompound(TAG_RECORD_ITEM, value -> {
            var item = NBTIO.getAPI().fromItemStackNBT(value);
            if (item instanceof ItemMusicDiscStack musicDiscStack) {
                this.musicDiscItem = musicDiscStack;
            } else {
                // A decoded non-disc cannot supply a playback type, so retain
                // the previous disc and report the rejected stored item instead
                // of replacing it.
                log.warn("Invalid music disc item {} in jukebox at {}", item.getItemType().getIdentifier(), this.getPosition());
            }
        });
    }
}
