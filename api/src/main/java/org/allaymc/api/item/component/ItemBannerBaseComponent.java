package org.allaymc.api.item.component;

import org.allaymc.api.block.data.BannerPattern;
import org.allaymc.api.block.data.BannerType;
import org.allaymc.api.utils.DyeColor;

import java.util.List;

/**
 * {@code ItemBannerBaseComponent} exposes a banner item's base color, banner kind,
 * and ordered pattern layers. The color belongs to item metadata, while the banner
 * kind and pattern layers participate in extra-tag persistence and transfer to
 * the placed banner.
 *
 * @author daoge_cmd
 */
public interface ItemBannerBaseComponent extends ItemBaseComponent {
    /**
     * Returns the banner base color decoded from inverted dye metadata.
     * Metadata must identify a supported dye color; the server's direct array
     * lookup throws for an out-of-range value instead of choosing a default.
     */
    DyeColor getBaseColor();

    /**
     * Sets the base dye color represented by this banner item.
     */
    void setBaseColor(DyeColor color);

    /**
     * Returns the banner kind stored by this item stack.
     */
    BannerType getBannerType();

    /**
     * Sets the banner kind stored by this item stack.
     */
    void setBannerType(BannerType type);

    /**
     * Returns whether this banner has the {@code BannerType.OMINOUS} kind.
     */
    default boolean isOminous() {
        return getBannerType() == BannerType.OMINOUS;
    }

    /**
     * Returns the banner's pattern layers in their stored order.
     * The returned list is a copy of the list container, so adding or removing entries
     * does not change this banner's patterns.
     */
    List<BannerPattern> getPatterns();

    /**
     * Replaces the banner's ordered pattern layers from the supplied list.
     * The component copies the list container, so later structural changes to
     * {@code patterns} do not change the stored layer order.
     */
    void setPatterns(List<BannerPattern> patterns);

    /**
     * Appends one pattern layer after the banner's existing layers.
     */
    void addPattern(BannerPattern pattern);

    /**
     * Removes the pattern layer at the supplied zero-based index.
     * An index outside the current list returns {@code false} without changing the
     * list; a removed layer produces {@code true}.
     */
    boolean removePattern(int index);

    /**
     * Removes every pattern layer currently stored on this banner.
     */
    void clearPatterns();

    /**
     * Returns the number of pattern layers stored on this banner.
     */
    int getPatternCount();
}
