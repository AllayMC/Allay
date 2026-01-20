package org.allaymc.api.item.component;

import org.allaymc.api.block.data.BannerPattern;
import org.allaymc.api.block.data.BannerType;
import org.allaymc.api.utils.DyeColor;

import java.util.List;

/**
 * Component interface for banner items.
 *
 * @author daoge_cmd
 */
public interface ItemBannerBaseComponent extends ItemBaseComponent {
    /**
     * Gets the base color of the banner.
     *
     * @return the base dye color
     */
    DyeColor getBaseColor();

    /**
     * Sets the base color of the banner.
     *
     * @param color the new base dye color
     */
    void setBaseColor(DyeColor color);

    /**
     * Gets the type of the banner.
     *
     * @return the banner type
     */
    BannerType getBannerType();

    /**
     * Sets the type of the banner.
     *
     * @param type the new banner type
     */
    void setBannerType(BannerType type);

    /**
     * Checks if the banner is an ominous banner.
     *
     * @return {@code true} if the banner is ominous, {@code false} otherwise
     */
    default boolean isOminous() {
        return getBannerType() == BannerType.OMINOUS;
    }

    /**
     * Gets the list of patterns on the banner.
     *
     * @return the list of patterns
     */
    List<BannerPattern> getPatterns();

    /**
     * Sets the patterns on the banner.
     *
     * @param patterns the new list of patterns
     */
    void setPatterns(List<BannerPattern> patterns);

    /**
     * Adds a pattern to the banner.
     *
     * @param pattern the pattern to add
     */
    void addPattern(BannerPattern pattern);

    /**
     * Removes a pattern at the specified index.
     *
     * @param index the index of the pattern to remove
     * @return {@code true} if the pattern was removed, {@code false} if the index was invalid
     */
    boolean removePattern(int index);

    /**
     * Clears all patterns from the banner.
     */
    void clearPatterns();

    /**
     * Gets the number of patterns on the banner.
     *
     * @return the pattern count
     */
    int getPatternCount();
}
