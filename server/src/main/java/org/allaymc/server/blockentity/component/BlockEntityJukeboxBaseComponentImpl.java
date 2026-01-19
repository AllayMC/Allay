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
 * @author IWareQ | daoge_cmd
 */
@Slf4j
public class BlockEntityJukeboxBaseComponentImpl extends BlockEntityBaseComponentImpl implements BlockEntityJukeboxBaseComponent {
    protected static final String TAG_RECORD_ITEM = "RecordItem";

    @Getter
    private ItemMusicDiscStack musicDiscItem;

    @Override
    public void setMusicDiscItem(ItemMusicDiscStack musicDiscItem) {
        this.musicDiscItem = musicDiscItem;
        // Update comparators that may be reading this jukebox
        this.getDimension().updateComparatorOutputLevel(this.getPosition());
    }

    public BlockEntityJukeboxBaseComponentImpl(BlockEntityInitInfo initInfo) {
        super(initInfo);
    }

    @Override
    public void play() {
        this.getDimension().addSound(this.getPosition(), new MusicDiscPlaySound(this.musicDiscItem.getDiscType()));
    }

    @Override
    public void stop() {
        this.getDimension().addSound(this.getPosition(), SimpleSound.MUSIC_DISC_END);
    }

    @EventHandler
    protected void onBlockReplace(CBlockOnReplaceEvent event) {
        if (this.musicDiscItem != null) {
            var current = event.getCurrentBlock();
            var pos = current.getPosition();
            var rand = ThreadLocalRandom.current();

            current.getDimension().dropItem(this.musicDiscItem, new Vector3d(
                    pos.x() + rand.nextDouble(0.5) + 0.25,
                    pos.y() + rand.nextDouble(0.5) + 0.25,
                    pos.z() + rand.nextDouble(0.5) + 0.25
            ));
            this.musicDiscItem = null;
            this.stop();
        }
    }

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

    @Override
    public void loadNBT(NbtMap nbt) {
        super.loadNBT(nbt);
        nbt.listenForCompound(TAG_RECORD_ITEM, value -> {
            var item = NBTIO.getAPI().fromItemStackNBT(value);
            if (item instanceof ItemMusicDiscStack musicDiscStack) {
                this.musicDiscItem = musicDiscStack;
            } else {
                log.warn("Invalid music disc item {} in jukebox at {}", item.getItemType().getIdentifier(), this.getPosition());
            }
        });
    }
}
