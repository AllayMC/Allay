package org.allaymc.server.item.component;

import org.allaymc.api.block.data.BannerPattern;
import org.allaymc.api.block.data.BannerType;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.component.ItemBannerBaseComponent;
import org.allaymc.api.utils.DyeColor;
import org.allaymc.api.world.Dimension;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtType;
import org.joml.Vector3ic;

import java.util.ArrayList;
import java.util.List;

/**
 * {@code ItemBannerBaseComponentImpl} stores banner colors, banner kinds, and
 * ordered pattern layers and chooses standing or wall banner placement.
 * The base color uses the inverted dye ordinal in item metadata; the banner
 * kind and pattern list use separate entries in extra NBT.
 *
 * <p>
 * Pattern getters and setters copy the list container so callers do not change
 * layer order through a retained list. Loading a present pattern list replaces
 * existing layers and discards entries with unknown pattern identifiers. Saving
 * keeps layer order and omits normal banner kind and empty pattern data; absent
 * extra entries leave the corresponding loaded state unchanged.
 *
 * @author daoge_cmd
 */
public class ItemBannerBaseComponentImpl extends ItemBaseComponentImpl implements ItemBannerBaseComponent {

    /**
     * {@code TAG_TYPE} identifies the banner-kind ordinal. Together with
     * {@code TAG_PATTERNS}, it names extra-tag data kept separate from the base color
     * stored in item metadata.
     */
    protected static final String TAG_TYPE = "Type";
    /**
     * Ordered banner-layer NBT key.
     */
    protected static final String TAG_PATTERNS = "Patterns";

    /**
     * Current banner kind value.
     */
    protected BannerType bannerType = BannerType.NORMAL;
    /**
     * {@code patterns} retains banner layers in display order. Accessors copy the
     * list container, while individual immutable pattern records remain shared values.
     */
    protected List<BannerPattern> patterns = new ArrayList<>();

    /**
     * Creates a banner component with normal kind and no pattern layers.
     */
    public ItemBannerBaseComponentImpl(ItemStackInitInfo initInfo) {
        super(initInfo);
    }

    /**
     * Places a standing banner on an upper face or a wall banner on a side face.
     * Missing interaction information and a lower clicked face return {@code false};
     * successful variants use the shared placement helper's effects.
     */
    @Override
    public boolean placeBlock(Dimension dimension, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        if (placementInfo == null) {
            return false;
        }
        if (placementInfo.blockFace() == BlockFace.UP) {
            return tryPlaceBlockState(dimension, BlockTypes.STANDING_BANNER.getDefaultState(), placeBlockPos, placementInfo);
        } else if (placementInfo.blockFace() != BlockFace.DOWN) {
            return tryPlaceBlockState(dimension, BlockTypes.WALL_BANNER.getDefaultState(), placeBlockPos, placementInfo);
        }
        return false;
    }

    /**
     * Returns the banner base color decoded from inverted dye metadata.
     * Metadata must identify a supported dye color; the server's direct array
     * lookup throws for an out-of-range value instead of choosing a default.
     */
    @Override
    public DyeColor getBaseColor() {
        // Banner metadata uses the inverse of DyeColor declaration order, so
        // decode it before exposing the color used by placement and rendering.
        return DyeColor.fromInverted(getMeta());
    }

    /**
     * Stores the banner base color using the inverted dye ordinal.
     */
    @Override
    public void setBaseColor(DyeColor color) {
        // Store the inverted ordinal expected by banner item metadata so a later
        // read or serialized item resolves the same base color.
        setMeta(color.ordinalInverted());
    }

    /**
     * Returns the banner kind currently stored by this component.
     */
    @Override
    public BannerType getBannerType() {
        return bannerType;
    }

    /**
     * Replaces the banner kind currently stored by this component.
     */
    @Override
    public void setBannerType(BannerType type) {
        this.bannerType = type;
    }

    /**
     * Returns a new list containing the banner's current ordered layers.
     * The copied container can be changed without changing {@code patterns}; its
     * immutable pattern records are retained as the same values.
     */
    @Override
    public List<BannerPattern> getPatterns() {
        return new ArrayList<>(patterns);
    }

    /**
     * Replaces the ordered layers with a copy of the supplied list.
     * Later structural changes to the caller's list do not affect this component;
     * the pattern records themselves are retained.
     */
    @Override
    public void setPatterns(List<BannerPattern> patterns) {
        this.patterns = new ArrayList<>(patterns);
    }

    /**
     * Appends the supplied layer after all currently stored banner patterns.
     */
    @Override
    public void addPattern(BannerPattern pattern) {
        this.patterns.add(pattern);
    }

    /**
     * Removes the banner layer at a valid zero-based index.
     * An index below zero or beyond the current pattern list returns {@code false}
     * without changing the list; a removal returns {@code true}.
     */
    @Override
    public boolean removePattern(int index) {
        if (index < 0 || index >= patterns.size()) {
            return false;
        }
        patterns.remove(index);
        return true;
    }

    /**
     * Removes all banner layers while retaining color and banner kind.
     */
    @Override
    public void clearPatterns() {
        patterns.clear();
    }

    /**
     * Returns the number of layers in the current pattern list.
     */
    @Override
    public int getPatternCount() {
        return patterns.size();
    }

    /**
     * Loads banner kind and ordered pattern data after inherited extra-tag loading.
     * A missing kind or pattern entry retains the corresponding current value;
     * a present pattern list clears existing layers before decoding its entries.
     *
     * <p>
     * Unknown pattern identifiers are skipped because they cannot identify a banner
     * layer. A recognized pattern with invalid color data can still fail decoding
     * after earlier layers were added. This method does not roll back either
     * inherited state or the partially replaced pattern list.
     */
    @Override
    public void loadExtraTag(NbtMap extraTag) {
        super.loadExtraTag(extraTag);
        extraTag.listenForInt(TAG_TYPE, value -> this.bannerType = BannerType.from(value));
        extraTag.listenForList(TAG_PATTERNS, NbtType.COMPOUND, patternsNbt -> {
            this.patterns.clear();
            for (var patternNbt : patternsNbt) {
                var pattern = BannerPattern.fromNBT(patternNbt);
                // Unknown pattern identifiers have no layer representation;
                // retain the remaining recognized layers in their original order.
                if (pattern != null) {
                    this.patterns.add(pattern);
                }
            }
        });
    }

    /**
     * Adds banner kind and ordered pattern layers to inherited extra NBT.
     * Normal banner kind and an empty pattern list add no entries; the base color
     * remains in item metadata rather than this extra tag.
     */
    @Override
    public NbtMap saveExtraTag() {
        var builder = super.saveExtraTag().toBuilder();
        // Like BDS, the banner type is always written, also for a normal banner.
        builder.putInt(TAG_TYPE, bannerType.ordinal());
        if (!patterns.isEmpty()) {
            builder.putList(TAG_PATTERNS, NbtType.COMPOUND, patterns.stream().map(BannerPattern::toNBT).toList());
        }
        return builder.build();
    }
}
