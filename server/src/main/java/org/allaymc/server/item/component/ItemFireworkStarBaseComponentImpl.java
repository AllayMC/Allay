package org.allaymc.server.item.component;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.component.ItemFireworkStarBaseComponent;
import org.allaymc.api.utils.DyeColor;
import org.allaymc.api.world.FireworkExplosion;
import org.allaymc.api.world.data.FireworkType;
import org.cloudburstmc.nbt.NbtMap;

import java.awt.*;
import java.util.Collections;
import java.util.List;

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
        this.explosion = new FireworkExplosion(
                FireworkType.SMALL_SPHERE, List.of(DyeColor.WHITE),
                Collections.emptyList(), false, false
        );
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
            // This is actually the mix of all dye colors the firework explosion has.
            builder.putInt(TAG_CUSTOM_COLOR, mixDyeColors(this.explosion.colors()).getRGB());
        }

        return builder.build();
    }

    protected static Color mixDyeColors(List<DyeColor> dyeColors) {
        int r = 0, g = 0, b = 0;
        for (var dyeColor : dyeColors) {
            var color = dyeColor.getColor();
            r += color.getRed();
            g += color.getGreen();
            b += color.getBlue();
        }

        int size = dyeColors.size();
        return new Color(r / size, g / size, b / size);
    }
}
