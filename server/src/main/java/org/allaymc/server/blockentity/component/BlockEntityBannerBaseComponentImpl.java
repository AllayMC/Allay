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
 * {@code BlockEntityBannerBaseComponentImpl} stores banner appearance and
 * serializes its ordered pattern layers alongside common block-entity data.
 * Placement copies banner-item appearance when available and otherwise decodes
 * the held item's metadata as an inverted dye ordinal. Public mutators send
 * updated state to viewers, while NBT loading replaces only supplied fields.
 *
 * @author daoge_cmd
 */
@Getter
public class BlockEntityBannerBaseComponentImpl extends BlockEntityBaseComponentImpl implements BlockEntityBannerBaseComponent {

    /**
     * {@code TAG_BASE} names the persisted inverted base dye ordinal. The
     * banner tag constants select appearance entries for {@code loadNBT} and
     * {@code saveNBT}.
     */
    protected static final String TAG_BASE = "Base";
    /**
     * Persisted banner variant ordinal.
     */
    protected static final String TAG_TYPE = "Type";
    /**
     * Persisted ordered pattern compounds.
     */
    protected static final String TAG_PATTERNS = "Patterns";

    /**
     * Base dye beneath patterns.
     */
    protected DyeColor baseColor = DyeColor.WHITE;
    /**
     * Ordinary or ominous variant.
     */
    protected BannerType bannerType = BannerType.NORMAL;
    /**
     * {@code patterns} holds the ordered mutable layers. The generated getter
     * exposes this list directly, while {@code setPatterns} copies the supplied
     * list container.
     */
    protected List<BannerPattern> patterns = new ArrayList<>();

    /**
     * Creates a banner component with common identity and initial appearance
     * defaults.
     */
    public BlockEntityBannerBaseComponentImpl(BlockEntityInitInfo initInfo) {
        super(initInfo);
    }

    /**
     * Replaces the base dye color and sends updated state to viewers.
     * {@code color} must be non-{@code null}; the assignment precedes
     * broadcasting, so a delivery failure leaves the new color stored.
     */
    @Override
    public void setBaseColor(DyeColor color) {
        this.baseColor = Objects.requireNonNull(color);
        sendBlockEntityToViewers();
    }

    /**
     * Replaces the banner variant and sends updated state to viewers.
     * {@code type} must be non-{@code null}; the assignment precedes
     * broadcasting, so a delivery failure leaves the new variant stored.
     */
    @Override
    public void setBannerType(BannerType type) {
        this.bannerType = Objects.requireNonNull(type);
        sendBlockEntityToViewers();
    }

    /**
     * Copies the supplied pattern list and sends updated state to viewers. The
     * new list retains the same layer objects, and a {@code null} input fails
     * before the existing list is replaced.
     */
    @Override
    public void setPatterns(List<BannerPattern> patterns) {
        this.patterns = new ArrayList<>(patterns);
        sendBlockEntityToViewers();
    }

    /**
     * Appends the supplied layer reference and sends updated state to viewers.
     * This method does not validate {@code pattern}; a {@code null} layer can
     * be stored but fails when saving invokes its serializer.
     */
    @Override
    public void addPattern(BannerPattern pattern) {
        this.patterns.add(pattern);
        sendBlockEntityToViewers();
    }

    /**
     * Removes a zero-based layer and sends updated state to viewers. Invalid
     * indices return {@code false} without modifying the list or broadcasting;
     * successful removal returns {@code true} after delivery is requested.
     */
    @Override
    public boolean removePattern(int index) {
        if (index < 0 || index >= patterns.size()) {
            return false;
        }
        patterns.remove(index);
        sendBlockEntityToViewers();
        return true;
    }

    /**
     * Clears the existing pattern list and sends updated state to viewers. The
     * same list object remains exposed by the generated getter, and the method
     * broadcasts even when no layers were present.
     */
    @Override
    public void clearPatterns() {
        patterns.clear();
        sendBlockEntityToViewers();
    }

    /**
     * Returns the current size of the stored banner layer list.
     */
    @Override
    public int getPatternCount() {
        return patterns.size();
    }

    /**
     * Loads common data and supplied banner appearance tags into this
     * component. A supplied pattern list clears existing layers and skips
     * unknown pattern identifiers; invalid dye ordinals can fail after earlier
     * changes have occurred.
     */
    @Override
    public void loadNBT(NbtMap nbt) {
        super.loadNBT(nbt);
        // Banner NBT encodes dye colors in inverted order, while the variant
        // tag retains its ordinary enum ordinal for the same appearance.
        nbt.listenForInt(TAG_BASE, value -> this.baseColor = DyeColor.fromInverted(value));
        nbt.listenForInt(TAG_TYPE, value -> this.bannerType = BannerType.from(value));
        nbt.listenForList(TAG_PATTERNS, NbtType.COMPOUND, patternsNbt -> {
            this.patterns.clear();
            for (var patternNbt : patternsNbt) {
                var pattern = BannerPattern.fromNBT(patternNbt);
                // Unknown identifiers cannot represent a pattern layer, so keep
                // the recognized layers without retaining an unusable
                // placeholder in their order.
                if (pattern != null) {
                    this.patterns.add(pattern);
                }
            }
        });
    }

    /**
     * Builds common and banner-specific NBT using inverted base dye ordinals.
     * An empty pattern list omits {@code "Patterns"}; nonempty lists serialize
     * in order and require every retained pattern to support serialization.
     */
    @Override
    public NbtMap saveNBT() {
        var builder = super.saveNBT().toBuilder();
        // Encode runtime dye values with the inverted ordinal expected by
        // banner NBT, independently of the ordinary banner-variant ordinal.
        builder.putInt(TAG_BASE, baseColor.ordinalInverted());
        builder.putInt(TAG_TYPE, bannerType.ordinal());
        if (!patterns.isEmpty()) {
            builder.putList(TAG_PATTERNS, NbtType.COMPOUND, patterns.stream().map(BannerPattern::toNBT).toList());
        }
        return builder.build();
    }

    /**
     * Copies banner appearance from the player's held item during placement.
     * Events without placement information leave appearance unchanged;
     * non-banner items contribute only their metadata-derived base color and
     * preserve other fields.
     */
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
            // Items without the banner capability expose no layer data, so use
            // their metadata to recover only the base dye color.
            this.baseColor = DyeColor.fromInverted(itemStack.getMeta());
        }
    }
}
