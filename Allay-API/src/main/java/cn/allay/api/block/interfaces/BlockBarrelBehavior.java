package cn.allay.api.block.interfaces;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.component.base.BlockBaseComponentImpl;
import cn.allay.api.block.component.blockentity.BlockEntityHolderComponent;
import cn.allay.api.block.data.BlockFace;
import cn.allay.api.block.function.OnInteract;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.blockentity.interfaces.BlockEntityBarrel;
import cn.allay.api.container.FullContainerType;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.data.VanillaBlockPropertyTypes;
import cn.allay.api.entity.interfaces.EntityPlayer;
import cn.allay.api.item.ItemStack;
import cn.allay.api.world.World;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3fc;
import org.joml.Vector3ic;

import java.util.Objects;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockBarrelBehavior extends
        BlockBehavior, BlockEntityHolderComponent<BlockEntityBarrel> {
    BlockType<BlockBarrelBehavior> BARREL_TYPE = BlockTypeBuilder
            .builder(BlockBarrelBehavior.class)
            .vanillaBlock(VanillaBlockId.BARREL)
            .setProperties(VanillaBlockPropertyTypes.FACING_DIRECTION, VanillaBlockPropertyTypes.OPEN_BIT)
            .bindBlockEntity(BlockEntityBarrel.BARREL_TYPE)
            .setBlockBaseComponentSupplier(type ->
                BlockBaseComponentImpl
                        .builder()
                        .onInteract(new BarrelOnInteract())
                        .build()
            )
            .build();

    class BarrelOnInteract implements OnInteract {
        @Override
        public boolean onInteract(@Nullable EntityPlayer player, ItemStack itemStack, World world, Vector3ic blockPos, Vector3ic placeBlockPos, Vector3fc clickPos, BlockFace blockFace) {
            if (player == null || player.isSneaking()) return false;
            var blockEntity = BARREL_TYPE.getBlockBehavior().getBlockEntity(blockPos.x(), blockPos.y(), blockPos.z(), world);
            var container = blockEntity.getContainer(FullContainerType.BARREL);
            Objects.requireNonNull(container).addViewer(player);
            return true;
        }
    }
}
