package org.allaymc.server.blockentity.component;

import lombok.Getter;
import org.allaymc.api.blockentity.BlockEntityInitInfo;
import org.allaymc.api.blockentity.component.BlockEntityCauldronBaseComponent;
import org.allaymc.api.item.data.PotionType;
import org.cloudburstmc.nbt.NbtMap;

import java.awt.*;

/**
 * Implementation of the cauldron block entity component.
 *
 * @author daoge_cmd
 */
@Getter
public class BlockEntityCauldronBaseComponentImpl extends BlockEntityBaseComponentImpl implements BlockEntityCauldronBaseComponent {

    protected static final String TAG_POTION_ID = "PotionId";
    protected static final String TAG_POTION_TYPE = "PotionType";
    protected static final String TAG_CUSTOM_COLOR = "CustomColor";

    protected PotionType potionType;
    protected CauldronPotionType cauldronPotionType = CauldronPotionType.NONE;
    protected Color customColor;

    public BlockEntityCauldronBaseComponentImpl(BlockEntityInitInfo initInfo) {
        super(initInfo);
    }

    @Override
    public void setPotionType(PotionType potionType) {
        this.potionType = potionType;
        sendBlockEntityToViewers();
    }

    @Override
    public void setCauldronPotionType(CauldronPotionType type) {
        this.cauldronPotionType = type;
        sendBlockEntityToViewers();
    }

    @Override
    public void setCustomColor(Color color) {
        this.customColor = color;
        sendBlockEntityToViewers();
    }

    @Override
    public void mixColor(Color dyeColor) {
        if (!hasCustomColor()) {
            setCustomColor(dyeColor);
            return;
        }

        // Mix colors using sqrt blending
        Color existingColor = this.customColor;
        int r1 = existingColor.getRed();
        int g1 = existingColor.getGreen();
        int b1 = existingColor.getBlue();

        int r2 = dyeColor.getRed();
        int g2 = dyeColor.getGreen();
        int b2 = dyeColor.getBlue();

        // Square root blending with slight darkening (0.965 factor)
        int r = (int) Math.round(Math.sqrt(r1 * r2) * 0.965);
        int g = (int) Math.round(Math.sqrt(g1 * g2) * 0.965);
        int b = (int) Math.round(Math.sqrt(b1 * b2) * 0.965);

        setCustomColor(new Color(r, g, b));
    }

    @Override
    public void loadNBT(NbtMap nbt) {
        super.loadNBT(nbt);
        nbt.listenForShort(TAG_POTION_ID, value -> {
            if (value >= 0) {
                this.potionType = PotionType.fromId(value);
            }
        });
        nbt.listenForShort(TAG_POTION_TYPE, value -> this.cauldronPotionType = fromNbtTypeData(value));
        nbt.listenForInt(TAG_CUSTOM_COLOR, value -> this.customColor = new Color(value));
    }

    @Override
    public NbtMap saveNBT() {
        var builder = super.saveNBT().toBuilder();
        builder.putShort(TAG_POTION_ID, (short) (potionType != null ? potionType.ordinal() : -1));
        builder.putShort(TAG_POTION_TYPE, (short) toNbtTypeData(cauldronPotionType));
        if (hasCustomColor()) {
            builder.putInt(TAG_CUSTOM_COLOR, customColor.getRGB());
        }
        return builder.build();
    }

    /**
     * Converts NBT type data to CauldronPotionType.
     * NBT values: -1=NONE, 0=NORMAL, 1=SPLASH, 2=LINGERING
     */
    private static CauldronPotionType fromNbtTypeData(int typeData) {
        return switch (typeData) {
            case 0 -> CauldronPotionType.NORMAL;
            case 1 -> CauldronPotionType.SPLASH;
            case 2 -> CauldronPotionType.LINGERING;
            default -> CauldronPotionType.NONE;
        };
    }

    /**
     * Converts CauldronPotionType to NBT type data.
     * NBT values: -1=NONE, 0=NORMAL, 1=SPLASH, 2=LINGERING
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
