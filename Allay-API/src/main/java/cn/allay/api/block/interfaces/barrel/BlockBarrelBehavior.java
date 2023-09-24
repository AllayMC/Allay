package cn.allay.api.block.interfaces.barrel;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.component.base.BlockBaseComponentImpl;
import cn.allay.api.block.component.blockentity.BlockEntityHolderComponent;
import cn.allay.api.block.data.BlockFace;
import cn.allay.api.block.data.BlockStateWithPos;
import cn.allay.api.block.function.OnPlace;
import cn.allay.api.block.type.BlockState;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.blockentity.interfaces.barrel.BlockEntityBarrel;
import cn.allay.api.data.VanillaBlockId;

import static cn.allay.api.data.VanillaBlockPropertyTypes.FACING_DIRECTION;
import static cn.allay.api.data.VanillaBlockPropertyTypes.OPEN_BIT;
import static java.lang.Math.abs;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockBarrelBehavior extends
        BlockBehavior, BlockEntityHolderComponent<BlockEntityBarrel> {
    BlockType<BlockBarrelBehavior> BARREL_TYPE = BlockTypeBuilder
            .builder(BlockBarrelBehavior.class)
            .vanillaBlock(VanillaBlockId.BARREL)
            .setProperties(FACING_DIRECTION, OPEN_BIT)
            .setBlockBaseComponentSupplier(
                    type -> BlockBaseComponentImpl
                            .builder()
                            .place((player, world, blockState, targetBlockPos, placeBlockPos, clickPos, blockFace) -> {
                                if (player != null) {
                                    if (abs(player.getLocation().x() - placeBlockPos.x()) < 2 && abs(player.getLocation().z() - placeBlockPos.z()) < 2) {
                                        var y = player.getLocation().y() + player.getEyeHeight();
                                        if (y - placeBlockPos.y() > 2) {
                                            blockState = blockState.setProperty(FACING_DIRECTION, BlockFace.UP.ordinal());
                                        } else if (placeBlockPos.y() - y > 0) {
                                            blockState = blockState.setProperty(FACING_DIRECTION, BlockFace.DOWN.ordinal());
                                        } else {
                                            blockState = blockState.setProperty(FACING_DIRECTION, player.getHorizontalFace().opposite().ordinal());
                                        }
                                    } else {
                                        blockState = blockState.setProperty(FACING_DIRECTION, player.getHorizontalFace().opposite().ordinal());
                                    }
                                }
                                world.setBlockState(placeBlockPos.x(), placeBlockPos.y(), placeBlockPos.z(), blockState);
                                return true;
                            })
                            .onPlace(new OnPlace() {
                                @Override
                                public void onPlace(BlockStateWithPos currentBlockState, BlockState newBlockState) {
                                    var pos = currentBlockState.pos();
                                    var world = pos.world();
                                    var behavior = BARREL_TYPE.getBlockBehavior();
                                    var blockEntity = behavior.getBlockEntity(pos);
                                    blockEntity.getContainer().addOnOpenListener(viewer -> {
                                        behavior.updateBlockProperty(OPEN_BIT, true, pos.x(), pos.y(), pos.z(), world);
                                    });
                                    blockEntity.getContainer().addOnCloseListener(viewer -> {
                                        if (blockEntity.getContainer().getViewers().isEmpty()) {
                                            behavior.updateBlockProperty(OPEN_BIT, false, pos.x(), pos.y(), pos.z(), world);
                                        }
                                    });
                                }
                            })
                            .build()
            )
            .bindBlockEntity(BlockEntityBarrel.BARREL_TYPE)
            .build();
}
