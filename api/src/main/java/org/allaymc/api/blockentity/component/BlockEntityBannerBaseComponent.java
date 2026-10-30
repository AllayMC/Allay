package org.allaymc.api.blockentity.component;

import org.allaymc.api.block.data.BannerPattern;
import org.allaymc.api.block.data.BannerType;
import org.allaymc.api.utils.DyeColor;

import java.util.List;

/**
 * {@code BlockEntityBannerBaseComponent} exposes a banner's base color,
 * variant, and ordered pattern layers. The server retains those layers in NBT
 * and broadcasts changes made through its mutators; callers use the inherited
 * base contract for identity and position.
 *
 * @author daoge_cmd
 */
public interface BlockEntityBannerBaseComponent extends BlockEntityBaseComponent {
    /**
     * Returns the base dye color displayed beneath banner patterns.
     */
    DyeColor getBaseColor();

    /**
     * Replaces the banner's base dye color and updates viewers. The server
     * implementation rejects {@code null} and changes its stored color before
     * sending the updated block-entity state.
     */
    void setBaseColor(DyeColor color);

    /**
     * Returns the stored ordinary or ominous variant of this banner.
     */
    BannerType getBannerType();

    /**
     * Replaces the ordinary or ominous variant and updates viewers. The server
     * implementation rejects {@code null} and stores the selected variant
     * before sending the updated block-entity state.
     */
    void setBannerType(BannerType type);

    /**
     * Returns whether this banner uses the {@code BannerType.OMINOUS} variant.
     */
    default boolean isOminous() {
        return getBannerType() == BannerType.OMINOUS;
    }

    /**
     * Returns the ordered pattern layers currently stored on this banner. The
     * server exposes its mutable list directly, so callers changing that list
     * bypass the update broadcasts performed by the pattern mutators.
     */
    List<BannerPattern> getPatterns();

    /**
     * Replaces the ordered pattern layers and updates the banner's viewers. The
     * server copies the supplied list container, retains its pattern objects,
     * and rejects a {@code null} list.
     */
    void setPatterns(List<BannerPattern> patterns);

    /**
     * Appends a layer after the banner's existing patterns and updates viewers.
     * The server retains the supplied reference without validation; callers
     * must supply a usable pattern for later NBT serialization.
     */
    void addPattern(BannerPattern pattern);

    /**
     * Removes the layer at the supplied zero-based position and updates
     * viewers. An index outside the current list returns {@code false} without
     * changing patterns or sending an update.
     */
    boolean removePattern(int index);

    /**
     * Removes every pattern layer and updates the banner's viewers. The base
     * color and ordinary or ominous variant remain unchanged, including when
     * the pattern list was already empty.
     */
    void clearPatterns();

    /**
     * Returns the number of ordered pattern layers on this banner.
     */
    int getPatternCount();
}
