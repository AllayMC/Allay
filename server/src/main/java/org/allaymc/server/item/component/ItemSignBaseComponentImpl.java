package org.allaymc.server.item.component;

import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.component.ItemSignBaseComponent;
import org.allaymc.api.world.Dimension;
import org.allaymc.server.block.data.BlockId;
import org.joml.Vector3ic;

import java.util.function.Supplier;

/**
 * {@code ItemSignBaseComponentImpl} resolves a sign item's standing and wall block
 * variants and chooses one for placement. Suppliers defer block-type lookup until
 * an accessor is called; the clicked upper face selects the standing variant,
 * and other faces select the wall variant.
 *
 * @author daoge_cmd
 */
public class ItemSignBaseComponentImpl extends ItemBaseComponentImpl implements ItemSignBaseComponent {
    /**
     * Lazy wall-sign block-type supplier.
     */
    protected final Supplier<BlockType<?>> wallSignType;
    /**
     * Lazy standing-sign block-type supplier.
     */
    protected final Supplier<BlockType<?>> standingSignType;

    /**
     * Creates a sign component retaining both block-type suppliers.
     * The constructor does not resolve them; callers must supply usable suppliers
     * whose returned types support the standing and wall placement paths.
     */
    public ItemSignBaseComponentImpl(ItemStackInitInfo initInfo, Supplier<BlockType<?>> wallSignTypeSupplier, Supplier<BlockType<?>> standingSignTypeSupplier) {
        super(initInfo);
        this.wallSignType = wallSignTypeSupplier;
        this.standingSignType = standingSignTypeSupplier;
    }

    /**
     * Creates lazy sign-variant suppliers from the supplied block identifier mappings.
     */
    public ItemSignBaseComponentImpl(ItemStackInitInfo initInfo, BlockId wallSignId, BlockId standingSignId) {
        this(initInfo, wallSignId::getBlockType, standingSignId::getBlockType);
    }

    /**
     * Places the standing variant for an upper clicked face and the wall variant
     * for every other face. The caller must provide non-{@code null}
     * {@code placementInfo}; the shared helper applies placement effects after the
     * selected block behavior succeeds.
     */
    @Override
    public boolean placeBlock(Dimension dimension, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        if (placementInfo.blockFace() == BlockFace.UP) {
            return tryPlaceBlockState(dimension, getStandingSignType().getDefaultState(), placeBlockPos, placementInfo);
        } else {
            return tryPlaceBlockState(dimension, getWallSignType().getDefaultState(), placeBlockPos, placementInfo);
        }
    }

    /**
     * Returns the wall-sign block type from the retained supplier.
     */
    @Override
    public BlockType<?> getWallSignType() {
        return wallSignType.get();
    }

    /**
     * Returns the standing-sign block type from the retained supplier.
     */
    @Override
    public BlockType<?> getStandingSignType() {
        return standingSignType.get();
    }
}
