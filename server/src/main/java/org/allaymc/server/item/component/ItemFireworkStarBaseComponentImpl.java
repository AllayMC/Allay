package org.allaymc.server.item.component;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.component.ItemFireworkStarBaseComponent;
import org.allaymc.api.world.FireworkExplosion;
import org.cloudburstmc.nbt.NbtMap;

/**
 * @author daoge_cmd
 */
public class ItemFireworkStarBaseComponentImpl extends ItemBaseComponentImpl implements ItemFireworkStarBaseComponent {

    protected static final String TAG_FIREWORKS_ITEM = "FireworksItem";
    protected static final String TAG_CUSTOM_COLOR = "customColor";

    @Getter
    @Setter
    protected FireworkExplosion fireworkExplosion;

    public ItemFireworkStarBaseComponentImpl(ItemStackInitInfo initInfo) {
        super(initInfo);
    }

    @Override
    public void loadExtraTag(NbtMap extraTag) {
        super.loadExtraTag(extraTag);
        extraTag.listenForCompound(TAG_FIREWORKS_ITEM, nbt -> this.fireworkExplosion = FireworkExplosion.fromNBT(nbt));
    }

    @Override
    public NbtMap saveExtraTag() {
        var builder = super.saveExtraTag().toBuilder();
        if (this.fireworkExplosion != null) {
            builder.putCompound(TAG_FIREWORKS_ITEM, this.fireworkExplosion.saveNBT());
            // 'customColor' controls the color shown in the firework star. So we need to set it, although we never read it
            builder.putInt(TAG_CUSTOM_COLOR, this.fireworkExplosion.color().getColor().getRGB());
        }

        return builder.build();
    }
}
