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
 * @author daoge_cmd
 */
public class ItemSignBaseComponentImpl extends ItemBaseComponentImpl implements ItemSignBaseComponent {
    protected final Supplier<BlockType<?>> wallSignType;
    protected final Supplier<BlockType<?>> standingSignType;

    public ItemSignBaseComponentImpl(ItemStackInitInfo initInfo, Supplier<BlockType<?>> wallSignTypeSupplier, Supplier<BlockType<?>> standingSignTypeSupplier) {
        super(initInfo);
        this.wallSignType = wallSignTypeSupplier;
        this.standingSignType = standingSignTypeSupplier;
    }

    public ItemSignBaseComponentImpl(ItemStackInitInfo initInfo, BlockId wallSignId, BlockId standingSignId) {
        this(initInfo, wallSignId::getBlockType, standingSignId::getBlockType);
    }

    @Override
    public boolean placeBlock(Dimension dimension, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        if (placementInfo.blockFace() == BlockFace.UP) {
            return tryPlaceBlockState(dimension, getStandingSignType().getDefaultState(), placeBlockPos, placementInfo);
        } else {
            return tryPlaceBlockState(dimension, getWallSignType().getDefaultState(), placeBlockPos, placementInfo);
        }
    }

    @Override
    public BlockType<?> getWallSignType() {
        return wallSignType.get();
    }

    @Override
    public BlockType<?> getStandingSignType() {
        return standingSignType.get();
    }
}
