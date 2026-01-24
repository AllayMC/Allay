package org.allaymc.server.item.component;

import lombok.Getter;
import org.allaymc.api.block.data.PotSherds;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.component.ItemDecoratedPotBaseComponent;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtType;

/**
 * @author IWareQ
 */
public class ItemDecoratedPotBaseComponentImpl extends ItemBaseComponentImpl implements ItemDecoratedPotBaseComponent {
    @Getter
    protected PotSherds sherds = PotSherds.DEFAULT;

    public ItemDecoratedPotBaseComponentImpl(ItemStackInitInfo initInfo) {
        super(initInfo);
    }

    @Override
    public void setSherds(PotSherds sherds) {
        this.sherds = sherds != null ? sherds : PotSherds.DEFAULT;
    }

    @Override
    public void loadExtraTag(NbtMap extraTag) {
        super.loadExtraTag(extraTag);
        this.sherds = PotSherds.fromNBT(extraTag);
    }

    @Override
    public NbtMap saveExtraTag() {
        return super.saveExtraTag()
                .toBuilder()
                .putList(PotSherds.TAG_SHERDS, NbtType.STRING, this.sherds.toNBTList())
                .build();
    }
}
