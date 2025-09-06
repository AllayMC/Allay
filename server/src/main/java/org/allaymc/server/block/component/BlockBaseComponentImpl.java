package org.allaymc.server.block.component;

import lombok.Getter;
import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.component.BlockBaseComponent;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
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
import org.allaymc.server.component.interfaces.ComponentManager;
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
    public void onNeighborUpdate(Block block, Block neighbor, BlockFace face) {
        manager.callEvent(new CBlockOnNeighborUpdateEvent(block, neighbor, face));
    }

    @Override
    public void onRandomUpdate(Block block) {
        manager.callEvent(new CBlockRandomUpdateEvent(block));
    }

    @Override
    public Set<ItemStack> getDrops(Block block, ItemStack usedItem, Entity entity) {
        if (getBlockType().getItemType() != null) {
            return Set.of(getSilkTouchDrop(block));
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
    public void onPlace(Block block, BlockState newBlockState, PlayerInteractInfo placementInfo) {
        manager.callEvent(new CBlockOnPlaceEvent(block, newBlockState, placementInfo));
    }

    @Override
    public void afterPlaced(Block oldBlock, BlockState newBlockState, PlayerInteractInfo placementInfo) {
        manager.callEvent(new CBlockAfterPlacedEvent(oldBlock, newBlockState, placementInfo));
    }

    @Override
    public void onReplace(Block block, BlockState newBlockState, PlayerInteractInfo placementInfo) {
        manager.callEvent(new CBlockOnReplaceEvent(block, newBlockState, placementInfo));
    }

    @Override
    public void afterReplaced(Block oldBlock, BlockState newBlockState, PlayerInteractInfo placementInfo) {
        manager.callEvent(new CBlockAfterReplacedEvent(oldBlock, newBlockState, placementInfo));
    }

    @Override
    public void afterNeighborLayerReplace(Block currentBlock, BlockState newBlockState, PlayerInteractInfo placementInfo) {
        manager.callEvent(new CBlockAfterNeighborLayerReplaceEvent(currentBlock, newBlockState, placementInfo));
    }

    @Override
    public void onPunch(Block block, BlockFace blockFace, ItemStack usedItem, Entity entity) {
        manager.callEvent(new CBlockOnPunchEvent(block, blockFace, usedItem, entity));
    }

    @Override
    public void onBreak(Block block, ItemStack usedItem, Entity entity) {
        if (!isDroppable(block, usedItem, entity)) {
            return;
        }

        var dropPos = MathUtils.center(block.getPosition());
        var dimension = block.getDimension();
        if (usedItem != null && usedItem.hasEnchantment(EnchantmentTypes.SILK_TOUCH)) {
            // Silk Touch, directly drop the block itself
            dimension.dropItem(getSilkTouchDrop(block), dropPos);
            return;
        }

        var drops = getDrops(block, usedItem, entity);
        for (var drop : drops) {
            dimension.dropItem(drop, dropPos);
        }

        var dropXpAmount = getDropXpAmount(block, usedItem, entity);
        if (dropXpAmount > 0) {
            dimension.dropXpOrb(dropPos, dropXpAmount);
        }
    }

    @Override
    public boolean isDroppable(Block block, ItemStack usedItem, Entity entity) {
        if (entity instanceof EntityPlayer player && player.getGameType() == GameType.CREATIVE) {
            return false;
        }

        return !block.getBlockStateData().requiresCorrectToolForDrops() || (usedItem != null && usedItem.isCorrectToolFor(block.getBlockState()));
    }

    @Override
    public boolean onInteract(ItemStack itemStack, Dimension dimension, PlayerInteractInfo interactInfo) {
        var event = new CBlockOnInteractEvent(itemStack, dimension, interactInfo, false);
        manager.callEvent(event);
        return event.isSuccess();
    }
}
