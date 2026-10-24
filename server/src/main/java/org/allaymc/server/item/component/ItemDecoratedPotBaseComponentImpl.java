package org.allaymc.server.item.component;

import lombok.Getter;
import org.allaymc.api.block.data.PotSherds;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.component.ItemDecoratedPotBaseComponent;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtType;

/**
 * {@code ItemDecoratedPotBaseComponentImpl} stores a decorated-pot item's four
 * face decorations and includes them in extra NBT. Its sherd setter replaces
 * {@code null} with {@code PotSherds.DEFAULT}, while the shared sherd decoder
 * supplies the same fallback for incomplete stored layouts.
 *
 * @author IWareQ
 */
public class ItemDecoratedPotBaseComponentImpl extends ItemBaseComponentImpl implements ItemDecoratedPotBaseComponent {
    /**
     * {@code sherds} holds the four-face layout exposed by the generated getter.
     * The initial value and a {@code null} setter input select {@code PotSherds.DEFAULT}.
     */
    @Getter
    protected PotSherds sherds = PotSherds.DEFAULT;

    /**
     * Creates a decorated-pot component initially using the default brick faces.
     */
    public ItemDecoratedPotBaseComponentImpl(ItemStackInitInfo initInfo) {
        super(initInfo);
    }

    /**
     * Replaces the decorated-pot layout with the supplied immutable sherd value.
     * A {@code null} value selects {@code PotSherds.DEFAULT}, ensuring the save path
     * always has a four-face layout to serialize.
     */
    @Override
    public void setSherds(PotSherds sherds) {
        this.sherds = sherds != null ? sherds : PotSherds.DEFAULT;
    }

    /**
     * Loads inherited item state and then decodes the four-face sherd layout.
     * The shared decoder supplies default brick faces when the stored list is
     * missing or too short to describe every face.
     */
    @Override
    public void loadExtraTag(NbtMap extraTag) {
        super.loadExtraTag(extraTag);
        this.sherds = PotSherds.fromNBT(extraTag);
    }

    /**
     * Adds the four sherd identifiers to inherited item extra NBT.
     */
    @Override
    public NbtMap saveExtraTag() {
        var builder = super.saveExtraTag().toBuilder();
        // Like BDS, a pot without sherds has no sherds tag.
        if (!this.sherds.equals(PotSherds.DEFAULT)) {
            builder.putList(PotSherds.TAG_SHERDS, NbtType.STRING, this.sherds.toNBTList());
        }
        return builder.build();
    }
}
