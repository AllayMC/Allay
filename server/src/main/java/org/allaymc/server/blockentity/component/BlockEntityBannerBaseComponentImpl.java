package org.allaymc.server.blockentity.component;

import lombok.Getter;
import org.allaymc.api.block.data.BannerPattern;
import org.allaymc.api.block.data.BannerType;
import org.allaymc.api.blockentity.BlockEntityInitInfo;
import org.allaymc.api.blockentity.component.BlockEntityBannerBaseComponent;
import org.allaymc.api.eventbus.EventHandler;
import org.allaymc.api.item.component.ItemBannerBaseComponent;
import org.allaymc.api.utils.DyeColor;
import org.allaymc.server.block.component.event.CBlockOnPlaceEvent;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtType;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Implementation of the banner block entity component.
 *
 * @author daoge_cmd
 */
@Getter
public class BlockEntityBannerBaseComponentImpl extends BlockEntityBaseComponentImpl implements BlockEntityBannerBaseComponent {

    protected static final String TAG_BASE = "Base";
    protected static final String TAG_TYPE = "Type";
    protected static final String TAG_PATTERNS = "Patterns";

    protected DyeColor baseColor = DyeColor.WHITE;
    protected BannerType bannerType = BannerType.NORMAL;
    protected List<BannerPattern> patterns = new ArrayList<>();

    public BlockEntityBannerBaseComponentImpl(BlockEntityInitInfo initInfo) {
        super(initInfo);
    }

    @Override
    public void setBaseColor(DyeColor color) {
        this.baseColor = Objects.requireNonNull(color);
        sendBlockEntityToViewers();
    }

    @Override
    public void setBannerType(BannerType type) {
        this.bannerType = Objects.requireNonNull(type);
        sendBlockEntityToViewers();
    }

    @Override
    public void setPatterns(List<BannerPattern> patterns) {
        this.patterns = new ArrayList<>(patterns);
        sendBlockEntityToViewers();
    }

    @Override
    public void addPattern(BannerPattern pattern) {
        this.patterns.add(pattern);
        sendBlockEntityToViewers();
    }

    @Override
    public boolean removePattern(int index) {
        if (index < 0 || index >= patterns.size()) {
            return false;
        }
        patterns.remove(index);
        sendBlockEntityToViewers();
        return true;
    }

    @Override
    public void clearPatterns() {
        patterns.clear();
        sendBlockEntityToViewers();
    }

    @Override
    public int getPatternCount() {
        return patterns.size();
    }

    @Override
    public void loadNBT(NbtMap nbt) {
        super.loadNBT(nbt);
        nbt.listenForInt(TAG_BASE, value -> this.baseColor = DyeColor.fromInverted(value));
        nbt.listenForInt(TAG_TYPE, value -> this.bannerType = BannerType.from(value));
        nbt.listenForList(TAG_PATTERNS, NbtType.COMPOUND, patternsNbt -> {
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
    public NbtMap saveNBT() {
        var builder = super.saveNBT().toBuilder();
        builder.putInt(TAG_BASE, baseColor.ordinalInverted());
        builder.putInt(TAG_TYPE, bannerType.ordinal());
        if (!patterns.isEmpty()) {
            builder.putList(TAG_PATTERNS, NbtType.COMPOUND, patterns.stream().map(BannerPattern::toNBT).toList());
        }
        return builder.build();
    }

    @EventHandler
    protected void onBlockPlace(CBlockOnPlaceEvent event) {
        var placementInfo = event.getPlacementInfo();
        if (placementInfo == null) {
            return;
        }

        var itemStack = placementInfo.player().getItemInHand();
        if (itemStack instanceof ItemBannerBaseComponent bannerItem) {
            this.baseColor = bannerItem.getBaseColor();
            this.bannerType = bannerItem.getBannerType();
            this.patterns = new ArrayList<>(bannerItem.getPatterns());
        } else {
            // Fallback: load base color from item meta
            this.baseColor = DyeColor.fromInverted(itemStack.getMeta());
        }
    }
}
