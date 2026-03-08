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
 * Implementation of the banner item component.
 * <p>
 * Banner data is stored as follows:
 * <ul>
 *   <li>Base color: stored in item meta (Damage tag)</li>
 *   <li>Banner type and patterns: stored directly in item extra tag</li>
 * </ul>
 *
 * @author daoge_cmd
 */
public class ItemBannerBaseComponentImpl extends ItemBaseComponentImpl implements ItemBannerBaseComponent {

    protected static final String TAG_TYPE = "Type";
    protected static final String TAG_PATTERNS = "Patterns";

    protected BannerType bannerType = BannerType.NORMAL;
    protected List<BannerPattern> patterns = new ArrayList<>();

    public ItemBannerBaseComponentImpl(ItemStackInitInfo initInfo) {
        super(initInfo);
    }

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

    @Override
    public DyeColor getBaseColor() {
        return DyeColor.fromInverted(getMeta());
    }

    @Override
    public void setBaseColor(DyeColor color) {
        setMeta(color.ordinalInverted());
    }

    @Override
    public BannerType getBannerType() {
        return bannerType;
    }

    @Override
    public void setBannerType(BannerType type) {
        this.bannerType = type;
    }

    @Override
    public List<BannerPattern> getPatterns() {
        return new ArrayList<>(patterns);
    }

    @Override
    public void setPatterns(List<BannerPattern> patterns) {
        this.patterns = new ArrayList<>(patterns);
    }

    @Override
    public void addPattern(BannerPattern pattern) {
        this.patterns.add(pattern);
    }

    @Override
    public boolean removePattern(int index) {
        if (index < 0 || index >= patterns.size()) {
            return false;
        }
        patterns.remove(index);
        return true;
    }

    @Override
    public void clearPatterns() {
        patterns.clear();
    }

    @Override
    public int getPatternCount() {
        return patterns.size();
    }

    @Override
    public void loadExtraTag(NbtMap extraTag) {
        super.loadExtraTag(extraTag);
        extraTag.listenForInt(TAG_TYPE, value -> this.bannerType = BannerType.from(value));
        extraTag.listenForList(TAG_PATTERNS, NbtType.COMPOUND, patternsNbt -> {
            this.patterns.clear();
            for (var patternNbt : patternsNbt) {
                var pattern = BannerPattern.fromNBT(patternNbt);
                if (pattern != null) {
                    this.patterns.add(pattern);
                }
            }
        });
    }

    @Override
    public NbtMap saveExtraTag() {
        var builder = super.saveExtraTag().toBuilder();
        if (bannerType != BannerType.NORMAL) {
            builder.putInt(TAG_TYPE, bannerType.ordinal());
        }
        if (!patterns.isEmpty()) {
            builder.putList(TAG_PATTERNS, NbtType.COMPOUND, patterns.stream().map(BannerPattern::toNBT).toList());
        }
        return builder.build();
    }
}
