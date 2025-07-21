package org.allaymc.server.block.component;

import lombok.Getter;
import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.component.BlockBaseComponent;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.BlockStateWithPos;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.component.interfaces.ComponentManager;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.enchantment.type.EnchantmentTypes;
import org.allaymc.api.math.MathUtils;
import org.allaymc.api.utils.Identifier;
import org.allaymc.api.utils.Utils;
import org.allaymc.api.world.Dimension;
import org.allaymc.server.block.component.event.*;
import org.allaymc.server.component.annotation.Manager;
import org.cloudburstmc.protocol.bedrock.data.GameType;
import org.joml.Vector3ic;

import java.util.Set;

/**
 * @author daoge_cmd
 */
public class BlockBaseComponentImpl implements BlockBaseComponent {
    @Identifier.Component
    public static final Identifier IDENTIFIER = new Identifier("minecraft:block_base_component");

    @Manager
    protected ComponentManager manager;

    @Getter
    protected BlockType<? extends BlockBehavior> blockType;

    public BlockBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        this.blockType = blockType;
    }

    @Override
    public void onNeighborUpdate(BlockStateWithPos current, BlockStateWithPos neighbor, BlockFace face) {
        manager.callEvent(new CBlockOnNeighborUpdateEvent(current, neighbor, face));
    }

    @Override
    public void onRandomUpdate(BlockStateWithPos current) {
        manager.callEvent(new CBlockRandomUpdateEvent(current));
    }

    @Override
    public Set<ItemStack> getDrops(BlockStateWithPos current, ItemStack usedItem, Entity entity) {
        if (getBlockType().getItemType() != null) {
            return Set.of(getSilkTouchDrop(current));
        }

        return Utils.EMPTY_ITEM_STACK_SET;
    }

    @Override
    public boolean place(Dimension dimension, BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        return dimension.setBlockState(
                placeBlockPos.x(), placeBlockPos.y(), placeBlockPos.z(),
                processBlockProperties(blockState, placeBlockPos, placementInfo),
                placementInfo
        );
    }

    protected BlockState processBlockProperties(BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        for (var propertyType : blockState.getBlockType().getProperties().values()) {
            var processor = propertyType.getProcessor();
            if (processor != null) {
                blockState = processor.process(blockState, placeBlockPos, placementInfo);
            }
        }
        return blockState;
    }

    @Override
    public void onPlace(BlockStateWithPos currentBlockState, BlockState newBlockState, PlayerInteractInfo placementInfo) {
        manager.callEvent(new CBlockOnPlaceEvent(currentBlockState, newBlockState, placementInfo));
    }

    @Override
    public void afterPlaced(BlockStateWithPos oldBlockState, BlockState newBlockState, PlayerInteractInfo placementInfo) {
        manager.callEvent(new CBlockAfterPlacedEvent(oldBlockState, newBlockState, placementInfo));
    }

    @Override
    public void onReplace(BlockStateWithPos currentBlockState, BlockState newBlockState, PlayerInteractInfo placementInfo) {
        manager.callEvent(new CBlockOnReplaceEvent(currentBlockState, newBlockState, placementInfo));
    }

    @Override
    public void afterReplaced(BlockStateWithPos oldBlockState, BlockState newBlockState, PlayerInteractInfo placementInfo) {
        manager.callEvent(new CBlockAfterReplacedEvent(oldBlockState, newBlockState, placementInfo));
    }

    @Override
    public void afterNeighborLayerReplace(BlockStateWithPos currentBlockState, BlockState newBlockState, PlayerInteractInfo placementInfo) {
        manager.callEvent(new CBlockAfterNeighborLayerReplaceEvent(currentBlockState, newBlockState, placementInfo));
    }

    @Override
    public void onPunch(BlockStateWithPos currentBlockState, BlockFace blockFace, ItemStack usedItem, Entity entity) {
        manager.callEvent(new CBlockOnPunchEvent(currentBlockState, blockFace, usedItem, entity));
    }

    @Override
    public void onBreak(BlockStateWithPos blockState, ItemStack usedItem, Entity entity) {
        if (!isDroppable(blockState, usedItem, entity)) {
            return;
        }

        var dropPos = MathUtils.center(blockState.getPos());
        var dimension = blockState.getDimension();
        if (usedItem != null && usedItem.hasEnchantment(EnchantmentTypes.SILK_TOUCH)) {
            // Silk Touch, directly drop the block itself
            dimension.dropItem(getSilkTouchDrop(blockState), dropPos);
            return;
        }

        var drops = getDrops(blockState, usedItem, entity);
        for (var drop : drops) {
            dimension.dropItem(drop, dropPos);
        }

        var dropXpAmount = getDropXpAmount(blockState, usedItem, entity);
        if (dropXpAmount > 0) {
            dimension.dropXpOrb(dropPos, dropXpAmount);
        }
    }

    @Override
    public boolean isDroppable(BlockStateWithPos blockState, ItemStack usedItem, Entity entity) {
        if (entity instanceof EntityPlayer player && player.getGameType() == GameType.CREATIVE) {
            return false;
        }

        return !blockState.getBlockStateData().requiresCorrectToolForDrops() || (usedItem != null && usedItem.isCorrectToolFor(blockState));
    }

    @Override
    public boolean onInteract(ItemStack itemStack, Dimension dimension, PlayerInteractInfo interactInfo) {
        var event = new CBlockOnInteractEvent(itemStack, dimension, interactInfo, false);
        manager.callEvent(event);
        return event.isSuccess();
    }
}
