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
    protected FireworkExplosion explosion;

    public ItemFireworkStarBaseComponentImpl(ItemStackInitInfo initInfo) {
        super(initInfo);
    }

    @Override
    public void loadExtraTag(NbtMap extraTag) {
        super.loadExtraTag(extraTag);
        extraTag.listenForCompound(TAG_FIREWORKS_ITEM, nbt -> this.explosion = FireworkExplosion.fromNBT(nbt));
    }

    @Override
    public NbtMap saveExtraTag() {
        var builder = super.saveExtraTag().toBuilder();
        if (this.explosion != null) {
            builder.putCompound(TAG_FIREWORKS_ITEM, this.explosion.saveNBT());
            // 'customColor' controls the color shown in the firework star. So we need to set it, although we never read it
            builder.putInt(TAG_CUSTOM_COLOR, this.explosion.color().getColor().getRGB());
        }

        return builder.build();
    }
}
