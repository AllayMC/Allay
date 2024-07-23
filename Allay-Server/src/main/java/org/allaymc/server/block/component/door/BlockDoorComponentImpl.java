package org.allaymc.server.block.component.door;

import static org.allaymc.api.data.VanillaBlockPropertyTypes.DOOR_HINGE_BIT;
import static org.allaymc.api.data.VanillaBlockPropertyTypes.OPEN_BIT;
import static org.allaymc.api.data.VanillaBlockPropertyTypes.UPPER_BLOCK_BIT;

import java.util.Set;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.component.RequireBlockProperty;
import org.allaymc.api.block.component.common.BlockLiquidComponent;
import org.allaymc.api.block.component.common.PlayerInteractInfo;
import org.allaymc.api.block.data.BlockStateWithPos;
import org.allaymc.api.block.property.type.BlockPropertyType;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.utils.Utils;
import org.allaymc.api.world.Dimension;
import org.allaymc.server.block.component.facing.BlockDirectionBaseComponentImpl;
import org.cloudburstmc.protocol.bedrock.data.SoundEvent;
import org.joml.Vector3i;
import org.joml.Vector3ic;

@RequireBlockProperty(type = BlockPropertyType.Type.INT, name = "direction")
@RequireBlockProperty(type = BlockPropertyType.Type.BOOLEAN, name = "door_hinge_bit")
@RequireBlockProperty(type = BlockPropertyType.Type.BOOLEAN, name = "open_bit")
@RequireBlockProperty(type = BlockPropertyType.Type.BOOLEAN, name = "upper_block_bit")
public class BlockDoorComponentImpl extends BlockDirectionBaseComponentImpl {
    public BlockDoorComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    @Override
    public boolean place(Dimension dimension, BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        checkPlaceMethodParam(dimension, blockState, placeBlockPos, placementInfo);
        
        var downBlockState = dimension.getBlockState(placeBlockPos.x(), placeBlockPos.y() - 1, placeBlockPos.z());
        if (downBlockState.getBlockType() != BlockTypes.AIR && !(downBlockState.getBehavior() instanceof BlockLiquidComponent)) {
            return false;
        }

        var upBlockState = dimension.getBlockState(placeBlockPos.x(), placeBlockPos.y() + 1, placeBlockPos.z());
        if (!upBlockState.getBlockType().getMaterial().isSolid()) {
            return false;
        }

        blockState = blockState.setProperty(OPEN_BIT, false);
        //TODO door inRight
        blockState = blockState.setProperty(DOOR_HINGE_BIT, true);

        var topBlockState = blockState.setProperty(UPPER_BLOCK_BIT, true);
        blockState = blockState.setProperty(UPPER_BLOCK_BIT, false);
        super.place(dimension, blockState, placeBlockPos, placementInfo);
        super.place(dimension, topBlockState, placeBlockPos.add(0, 1, 0, new Vector3i()), placementInfo);
        return true;
    }

    @Override
    public boolean onInteract(ItemStack itemStack, Dimension dimension, PlayerInteractInfo interactInfo) {
        Vector3i pos = (Vector3i) interactInfo.clickBlockPos();
        var blockState = dimension.getBlockState(pos);

        Vector3ic otherPos;
        if (blockState.getPropertyValue(UPPER_BLOCK_BIT)) {
            otherPos = pos.sub(0, 1, 0);
        } else {
            otherPos = pos.add(0, 1, 0);
        }

        var isOpen = !blockState.getPropertyValue(OPEN_BIT);

        blockState = blockState.setProperty(OPEN_BIT, isOpen);
        dimension.setBlockState(pos, blockState);

        var otherBlockState = dimension.getBlockState(otherPos);
        //TODO: check if it's the same door
        otherBlockState = otherBlockState.setProperty(OPEN_BIT, isOpen);
        dimension.setBlockState(pos, blockState);

        dimension.addLevelSoundEvent(pos.x(), pos.y(), pos.z(), isOpen ? SoundEvent.DOOR_OPEN : SoundEvent.DOOR_CLOSE);
        return super.onInteract(itemStack, dimension, interactInfo);
    }

    @Override
    public Set<ItemStack> getDrops(BlockStateWithPos blockState, ItemStack usedItem, Entity entity) {
        return blockState.blockState().getPropertyValue(UPPER_BLOCK_BIT) ? Utils.EMPTY_ITEM_STACK_SET : super.getDrops(blockState, usedItem, entity);
    }
}
