package org.allaymc.server.blockentity.component;

import com.google.common.base.Preconditions;
import lombok.Getter;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.blockentity.BlockEntityInitInfo;
import org.allaymc.api.blockentity.component.BlockEntityBedBaseComponent;
import org.allaymc.api.eventbus.EventHandler;
import org.allaymc.api.utils.DyeColor;
import org.allaymc.server.block.component.BlockBedBaseComponentImpl;
import org.allaymc.server.block.component.event.CBlockOnPlaceEvent;
import org.cloudburstmc.nbt.NbtMap;

import java.util.Optional;

/**
 * {@code BlockEntityBedBaseComponentImpl} stores a bed half's dye color and
 * keeps an available paired half consistent when that color changes. NBT uses
 * the dye ordinal, and player placement reads held-item metadata. Its
 * constructor selects red before the inherited completion hook loads initial
 * NBT; unsupported ordinals fail during dye lookup before the optional red
 * fallback can apply.
 *
 * <p>
 * {@code setColor} changes this half first and invokes the paired half's setter
 * only when its color differs. That comparison stops the reciprocal setters
 * from recursing indefinitely once both halves hold the same enum value. Each
 * setter broadcasts after propagation; failures do not roll back earlier color
 * assignments, and loading one half's NBT does not perform pair propagation.
 *
 * @author harry-xi
 */
public class BlockEntityBedBaseComponentImpl extends BlockEntityBaseComponentImpl implements BlockEntityBedBaseComponent {

    /**
     * {@code TAG_COLOR} names the byte dye ordinal used to save and load bed
     * appearance.
     */
    protected static final String TAG_COLOR = "color";

    /**
     * Stored bed dye color.
     */
    @Getter
    protected DyeColor color;

    /**
     * Creates common block-entity state and initializes this bed half as red.
     * The inherited completion hook loads supplied NBT later, after component
     * wiring, so a present color tag can replace this default.
     */
    public BlockEntityBedBaseComponentImpl(BlockEntityInitInfo initInfo) {
        super(initInfo);
        this.color = DyeColor.RED;
    }

    /**
     * Stores a dye color, propagates it to the paired half, and updates
     * viewers. The paired setter is invoked only when its color differs, which
     * stops the two halves from calling each other indefinitely. The assignment
     * is retained if propagation or delivery fails. This method accepts
     * {@code null} without validation, but later saving dereferences the stored
     * color and therefore requires a usable dye value.
     */
    public void setColor(DyeColor color) {
        this.color = color;
        var pair = BlockBedBaseComponentImpl.getPairBlock(new Block(this.getDimension(), this.getPosition())).getBlockEntity();
        // The paired setter can call back into this half, so propagate only
        // differing enum values after this half already holds the requested
        // color.
        if (pair instanceof BlockEntityBedBaseComponent bed) {
            if (bed.getColor() != color) {
                bed.setColor(color);
            }
        }
        sendBlockEntityToViewers();
    }

    /**
     * Loads common state and the optional bed dye byte. An absent color tag
     * preserves the current color. Unsupported ordinals fail inside
     * {@code DyeColor.from} before the optional red fallback can be selected.
     */
    @Override
    public void loadNBT(NbtMap nbt) {
        super.loadNBT(nbt);
        // DyeColor.from rejects unsupported ordinals before Optional can supply
        // its red fallback, so malformed color bytes still fail this load after
        // common fields change.
        nbt.listenForByte(TAG_COLOR, b -> this.color = Optional.ofNullable(DyeColor.from(b)).orElse(DyeColor.RED));
    }

    /**
     * Builds common NBT and appends this bed half's dye ordinal byte.
     */
    @Override
    public NbtMap saveNBT() {
        return super.saveNBT()
                .toBuilder()
                .putByte(TAG_COLOR, (byte) this.color.ordinal())
                .build();
    }

    /**
     * Reads this bed half's color from the placing player's held-item metadata.
     * Events without placement information preserve the color, while an
     * unsupported metadata ordinal fails inside {@code DyeColor.from} before
     * assignment.
     */
    @EventHandler
    protected void onBlockPlace(CBlockOnPlaceEvent event) {
        var placementInfo = event.getPlacementInfo();
        if (placementInfo != null) {
            this.color = Preconditions.checkNotNull(DyeColor.from(placementInfo.player().getItemInHand().getMeta()));
        }
    }
}
