package org.allaymc.server.blockentity.component;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.blockentity.BlockEntityInitInfo;
import org.allaymc.api.blockentity.component.BlockEntityJukeboxBaseComponent;
import org.allaymc.api.item.ItemHelper;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.component.ItemMusicDiscBaseComponent;
import org.allaymc.api.world.sound.MusicDiscPlaySound;
import org.allaymc.api.world.sound.SimpleSound;
import org.allaymc.server.block.component.event.CBlockOnReplaceEvent;
import org.cloudburstmc.nbt.NbtMap;
import org.joml.Vector3d;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @author IWareQ
 */
public class BlockEntityJukeboxBaseComponentImpl extends BlockEntityBaseComponentImpl implements BlockEntityJukeboxBaseComponent {
    protected static final String TAG_RECORD_ITEM = "RecordItem";

    @Getter
    @Setter
    private ItemStack musicDiscItem;

    public BlockEntityJukeboxBaseComponentImpl(BlockEntityInitInfo initInfo) {
        super(initInfo);
    }

    @Override
    public void play() {
        if (this.musicDiscItem instanceof ItemMusicDiscBaseComponent component) {
            this.getDimension().addSound(this.getPosition(), new MusicDiscPlaySound(component.getDiscType()));
        }
    }

    @Override
    public void stop() {
        this.getDimension().addSound(this.getPosition(), SimpleSound.MUSIC_DISC_END);
    }

    @Override
    public void onBlockReplace(CBlockOnReplaceEvent event) {
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
        nbt.listenForCompound(TAG_RECORD_ITEM, value -> this.musicDiscItem = ItemHelper.fromNBT(value));
    }
}
