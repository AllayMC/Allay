package org.allaymc.server.item.component.firework;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.component.ItemFireworkStarBaseComponent;
import org.allaymc.api.utils.DyeColor;
import org.allaymc.api.world.data.FireworkType;
import org.allaymc.api.world.explosion.FireworkExplosion;
import org.allaymc.server.item.component.ItemBaseComponentImpl;
import org.cloudburstmc.nbt.NbtMap;

import java.awt.*;
import java.util.Collections;
import java.util.List;

/**
 * {@code ItemFireworkStarBaseComponentImpl} stores a firework star's explosion
 * definition and derives the serialized item color from its primary dyes. The
 * explosion contains shape, fade colors, and animation flags, but the item
 * color averages only the primary dye list. Every list occurrence contributes
 * equally, so a repeated dye changes the displayed average while fade colors
 * do not.
 *
 * <p>
 * The constructor creates a white small-sphere definition. Generated accessors
 * retain and expose the explosion record directly, including any mutable color
 * lists inside it. Saving writes the explosion and its derived color together
 * when the definition is present; a definition with no primary dyes fails
 * during the average calculation.
 *
 * @author daoge_cmd
 */
public class ItemFireworkStarBaseComponentImpl extends ItemBaseComponentImpl implements ItemFireworkStarBaseComponent {

    // TAG_FIREWORKS_ITEM stores the complete explosion, while TAG_CUSTOM_COLOR
    // supplies the derived primary-dye color written for the star's client appearance.
    /**
     * Stored explosion compound tag.
     */
    protected static final String TAG_FIREWORKS_ITEM = "FireworksItem";
    /**
     * Derived star icon-color tag.
     */
    protected static final String TAG_CUSTOM_COLOR = "customColor";

    /**
     * {@code explosion} holds the shared definition or {@code null} when
     * absent. Generated accessors retain the record directly, and its primary
     * color list must be nonempty for color serialization.
     */
    @Getter
    @Setter
    protected FireworkExplosion explosion;

    /**
     * Creates star behavior with a white small-sphere explosion definition.
     * The initial definition has no fade colors, flicker, or trail; component
     * initialization can replace it from stored extra data.
     */
    public ItemFireworkStarBaseComponentImpl(ItemStackInitInfo initInfo) {
        super(initInfo);
        this.explosion = new FireworkExplosion(
                FireworkType.SMALL_SPHERE, List.of(DyeColor.WHITE),
                Collections.emptyList(), false, false
        );
    }

    /**
     * Loads inherited stack data and replaces a present stored explosion
     * definition. A missing explosion compound retains the current definition,
     * and decoding applies {@code FireworkExplosion.fromNBT} including its
     * default primary color behavior.
     */
    @Override
    public void loadExtraTag(NbtMap extraTag) {
        super.loadExtraTag(extraTag);
        extraTag.listenForCompound(TAG_FIREWORKS_ITEM, nbt -> this.explosion = FireworkExplosion.fromNBT(nbt));
    }

    /**
     * Serializes inherited data with a present explosion and its derived item
     * color. An absent definition omits both star-specific tags; a nonempty
     * primary color list is required for averaging during this save.
     */
    @Override
    public NbtMap saveExtraTag() {
        var builder = super.saveExtraTag().toBuilder();
        if (this.explosion != null) {
            builder.putCompound(TAG_FIREWORKS_ITEM, this.explosion.saveNBT());
            // The client item color is derived from primary explosion dyes, so write
            // it alongside the definition even though loading reads only the definition.
            builder.putInt(TAG_CUSTOM_COLOR, mixDyeColors(this.explosion.colors()).getRGB());
        }

        return builder.build();
    }

    /**
     * Returns the integer RGB average of the supplied primary dye colors. Each
     * list entry contributes one color to every channel sum, so duplicate dyes
     * carry repeated weight in the result. Fade colors and explosion shape do
     * not participate in this calculation.
     *
     * <p>
     * The caller supplies a nonempty list with non-{@code null} entries.
     * Integer division truncates each channel average before the opaque color
     * is created, and an empty list causes division by zero rather than
     * selecting a default color.
     */
    protected static Color mixDyeColors(List<DyeColor> dyeColors) {
        int r = 0, g = 0, b = 0;
        for (var dyeColor : dyeColors) {
            var color = dyeColor.getColor();
            r += color.getRed();
            g += color.getGreen();
            b += color.getBlue();
        }

        // Integer channel averages discard fractional parts while retaining equal weight
        // for every primary dye occurrence in the supplied explosion definition.
        int size = dyeColors.size();
        return new Color(r / size, g / size, b / size);
    }
}
