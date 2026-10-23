package org.allaymc.server.blockentity.component;

import lombok.Getter;
import org.allaymc.api.blockentity.BlockEntityInitInfo;
import org.allaymc.api.blockentity.component.BlockEntityCauldronBaseComponent;
import org.allaymc.api.item.data.PotionType;
import org.cloudburstmc.nbt.NbtMap;

import java.awt.*;

/**
 * {@code BlockEntityCauldronBaseComponentImpl} stores cauldron potion identity,
 * bottle variant, and custom water color alongside common block-entity data.
 * Potion identity and bottle variant are independent selections. Their NBT
 * encodings use separate short tags, while a present custom color uses packed
 * RGB.
 *
 * <p>
 * {@code mixColor} adopts a dye directly when no color exists and otherwise
 * combines corresponding RGB channels through a geometric mean with a darkening
 * factor. The mutators store their values before broadcasting, so a delivery
 * failure does not undo the changed state. Loading changes supplied fields
 * without broadcasting; a negative potion-ID tag leaves an existing selection
 * unchanged rather than clearing it.
 *
 * @author daoge_cmd
 */
@Getter
public class BlockEntityCauldronBaseComponentImpl extends BlockEntityBaseComponentImpl implements BlockEntityCauldronBaseComponent {

    /**
     * {@code TAG_POTION_ID} names the stored potion-effect ID. These tag
     * constants separate potion identity, bottle variant, and packed RGB during
     * NBT exchange.
     */
    protected static final String TAG_POTION_ID = "PotionId";
    /**
     * Persisted potion-container variant code.
     */
    protected static final String TAG_POTION_TYPE = "PotionType";
    /**
     * Persisted packed RGB color.
     */
    protected static final String TAG_CUSTOM_COLOR = "CustomColor";

    /**
     * Optional potion effect selection.
     */
    protected PotionType potionType;
    /**
     * Stored potion bottle variant.
     */
    protected CauldronPotionType cauldronPotionType = CauldronPotionType.NONE;
    /**
     * Optional custom water color.
     */
    protected Color customColor;

    /**
     * Creates common cauldron state with no potion effect or custom color.
     */
    public BlockEntityCauldronBaseComponentImpl(BlockEntityInitInfo initInfo) {
        super(initInfo);
    }

    /**
     * Stores a potion effect reference and sends updated state to viewers.
     * {@code null} clears the selection, while the bottle variant and color
     * remain unchanged; delivery failures leave the assigned reference in
     * place.
     */
    @Override
    public void setPotionType(PotionType potionType) {
        this.potionType = potionType;
        sendBlockEntityToViewers();
    }

    /**
     * Stores the bottle variant and sends updated state to viewers. This setter
     * performs no validation, so {@code null} is retained even though later NBT
     * encoding cannot switch on it.
     */
    @Override
    public void setCauldronPotionType(CauldronPotionType type) {
        this.cauldronPotionType = type;
        sendBlockEntityToViewers();
    }

    /**
     * Stores the custom water color and sends updated state to viewers.
     * {@code null} removes the color; potion fields remain unchanged, and
     * delivery failures leave the assigned color reference in place.
     */
    @Override
    public void setCustomColor(Color color) {
        this.customColor = color;
        sendBlockEntityToViewers();
    }

    /**
     * Mixes a dye into the retained custom water color and broadcasts the
     * result. An absent existing color selects {@code dyeColor} directly
     * through {@code setCustomColor}. With an existing color, the method
     * multiplies each pair of RGB channels, takes the square root, scales it by
     * {@code 0.965}, and rounds to an integer channel. This geometric blend
     * ignores alpha and requires a non-{@code null} dye once an existing color
     * is present.
     */
    @Override
    public void mixColor(Color dyeColor) {
        if (!hasCustomColor()) {
            setCustomColor(dyeColor);
            return;
        }

        Color existingColor = this.customColor;
        int r1 = existingColor.getRed();
        int g1 = existingColor.getGreen();
        int b1 = existingColor.getBlue();

        int r2 = dyeColor.getRed();
        int g2 = dyeColor.getGreen();
        int b2 = dyeColor.getBlue();

        // The geometric RGB blend darkens each channel before rounding.
        int r = (int) Math.round(Math.sqrt(r1 * r2) * 0.965);
        int g = (int) Math.round(Math.sqrt(g1 * g2) * 0.965);
        int b = (int) Math.round(Math.sqrt(b1 * b2) * 0.965);

        setCustomColor(new Color(r, g, b));
    }

    /**
     * Loads common data and supplied potion or custom-color tags. Negative
     * potion IDs preserve the previous effect, while excessive positive IDs
     * fail during enum lookup. Unsupported bottle codes select
     * {@code CauldronPotionType.NONE}, and packed colors construct opaque RGB
     * values.
     */
    @Override
    public void loadNBT(NbtMap nbt) {
        super.loadNBT(nbt);
        nbt.listenForShort(TAG_POTION_ID, value -> {
            // Negative IDs encode absence when saving, but this loading path
            // leaves any existing effect selection unchanged for those values.
            if (value >= 0) {
                this.potionType = PotionType.fromId(value);
            }
        });
        // NBT bottle codes are shifted from the runtime enum ordinals, so
        // decode through the explicit mapping before storing the bottle
        // variant.
        nbt.listenForShort(TAG_POTION_TYPE, value -> this.cauldronPotionType = fromNbtTypeData(value));
        nbt.listenForInt(TAG_CUSTOM_COLOR, value -> this.customColor = new Color(value));
    }

    /**
     * Builds common NBT with potion identity, bottle code, and any custom
     * color. An absent potion effect writes {@code -1}; an absent custom color
     * omits its tag, and a non-{@code null} bottle variant is required.
     */
    @Override
    public NbtMap saveNBT() {
        var builder = super.saveNBT().toBuilder();
        builder.putShort(TAG_POTION_ID, (short) (potionType != null ? potionType.ordinal() : -1));
        // Persist the protocol bottle code rather than its enum ordinal,
        // keeping NONE distinct from the normal bottle represented by zero.
        builder.putShort(TAG_POTION_TYPE, (short) toNbtTypeData(cauldronPotionType));
        if (hasCustomColor()) {
            builder.putInt(TAG_CUSTOM_COLOR, customColor.getRGB());
        }
        return builder.build();
    }

    /**
     * Decodes bottle codes {@code 0}, {@code 1}, and {@code 2} as normal,
     * splash, and lingering variants. Every negative or unsupported positive
     * code selects {@code CauldronPotionType.NONE}, including the saved
     * absent-variant code {@code -1}.
     */
    private static CauldronPotionType fromNbtTypeData(int typeData) {
        // Unsupported bottle codes have no matching runtime variant, so use
        // NONE as the same absent selection represented by saved minus one.
        return switch (typeData) {
            case 0 -> CauldronPotionType.NORMAL;
            case 1 -> CauldronPotionType.SPLASH;
            case 2 -> CauldronPotionType.LINGERING;
            default -> CauldronPotionType.NONE;
        };
    }

    /**
     * Encodes normal, splash, and lingering bottle variants as {@code 0},
     * {@code 1}, and {@code 2}. The absent variant becomes {@code -1}; a
     * {@code null} variant raises {@code NullPointerException} during switch
     * selection.
     */
    private static int toNbtTypeData(CauldronPotionType type) {
        return switch (type) {
            case NORMAL -> 0;
            case SPLASH -> 1;
            case LINGERING -> 2;
            default -> -1;
        };
    }
}
