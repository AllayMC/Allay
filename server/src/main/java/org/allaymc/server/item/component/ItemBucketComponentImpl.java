package org.allaymc.server.item.component;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.block.data.BlockTags;
import org.allaymc.api.block.interfaces.BlockCauldronBehavior;
import org.allaymc.api.block.interfaces.BlockLiquidBehavior;
import org.allaymc.api.block.property.type.BlockPropertyTypes;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.container.ContainerTypes;
import org.allaymc.api.entity.EntityInitInfo;
import org.allaymc.api.entity.type.EntityType;
import org.allaymc.api.eventbus.EventHandler;
import org.allaymc.api.eventbus.event.player.PlayerBucketEmptyEvent;
import org.allaymc.api.eventbus.event.player.PlayerBucketFillEvent;
import org.allaymc.api.item.component.ItemBucketComponent;
import org.allaymc.api.item.interfaces.ItemBucketStack;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.api.math.MathUtils;
import org.allaymc.api.player.GameMode;
import org.allaymc.api.utils.identifier.Identifier;
import org.allaymc.server.block.data.BlockId;
import org.allaymc.server.component.annotation.ComponentObject;
import org.allaymc.server.entity.data.EntityId;
import org.allaymc.server.item.component.event.CItemUseOnBlockEvent;
import org.joml.Vector3ic;

/**
 * @author daoge_cmd
 */
@Slf4j
public class ItemBucketComponentImpl implements ItemBucketComponent {

    @Identifier.Component
    public static final Identifier IDENTIFIER = new Identifier("minecraft:item_bucket_component");

    protected final BlockId liquidId;
    protected final EntityId entityId;

    @ComponentObject
    protected ItemBucketStack thisBucketStack;

    public ItemBucketComponentImpl(BlockId liquidId, EntityId entityId) {
        this.liquidId = liquidId;
        this.entityId = entityId;
    }

    @Override
    public BlockType<?> getLiquidType() {
        return liquidId.getBlockType();
    }

    @Override
    public EntityType<?> getEntityType() {
        return entityId == null ? null : entityId.getEntityType();
    }

    @EventHandler
    protected void onUseItemOnBlock(CItemUseOnBlockEvent event) {
        var interactInfo = event.getInteractInfo();
        var player = interactInfo.player();
        var dimension = player.getDimension();
        var clickedBlockState = interactInfo.getClickedBlock();

        // Let cauldron handle bucket interactions itself
        if (clickedBlockState.getBehavior() instanceof BlockCauldronBehavior) {
            return;
        }

        if (isEmpty()) {
            if (!(clickedBlockState.getBehavior() instanceof BlockLiquidBehavior)) {
                return;
            }
            try {
                if (clickedBlockState.getPropertyValue(BlockPropertyTypes.LIQUID_DEPTH) != 0) {
                    return;
                }
            } catch (IllegalArgumentException ignore) {
            }

            if (!new PlayerBucketFillEvent(player, thisBucketStack, interactInfo).call()) {
                return;
            }

            var blockType = clickedBlockState.getBlockType();
            if (blockType == BlockTypes.WATER || blockType == BlockTypes.FLOWING_WATER) {
                player.tryConsumeItemInHand();
                player.getContainer(ContainerTypes.INVENTORY).tryAddItem(ItemTypes.WATER_BUCKET.createItemStack(1));
                dimension.setBlockState(interactInfo.clickedBlockPos(), BlockTypes.AIR.getDefaultState());
            } else if (blockType == BlockTypes.LAVA || blockType == BlockTypes.FLOWING_LAVA) {
                player.tryConsumeItemInHand();
                player.getContainer(ContainerTypes.INVENTORY).tryAddItem(ItemTypes.LAVA_BUCKET.createItemStack(1));
                dimension.setBlockState(interactInfo.clickedBlockPos(), BlockTypes.AIR.getDefaultState());
            }
            event.setCanBeUsed(true);
            return;
        }

        if (!new PlayerBucketEmptyEvent(player, thisBucketStack, interactInfo).call()) {
            return;
        }

        Vector3ic liquidPlacedPos = event.getPlaceBlockPos();
        if (clickedBlockState.getBlockStateData().canContainLiquidSource()) {
            dimension.setBlockState(interactInfo.clickedBlockPos(), getLiquidType().getDefaultState(), 1);
            liquidPlacedPos = interactInfo.clickedBlockPos();
        } else {
            var blockOnPlacePos = dimension.getBlockState(event.getPlaceBlockPos());
            if (blockOnPlacePos.getBlockType() == BlockTypes.AIR || blockOnPlacePos.getBlockType().hasBlockTag(BlockTags.REPLACEABLE)) {
                dimension.setBlockState(event.getPlaceBlockPos(), getLiquidType().getDefaultState(), 0);
            } else if (blockOnPlacePos.getBlockStateData().canContainLiquidSource()) {
                dimension.setBlockState(event.getPlaceBlockPos(), getLiquidType().getDefaultState(), 1);
            }
        }

        var entity = getEntityType();
        if (entity != null) {
            var entityInstance = entity.createEntity(EntityInitInfo.builder().dimension(dimension).pos(MathUtils.center(liquidPlacedPos)).build());
            dimension.getEntityManager().addEntity(entityInstance);
        }

        player.tryConsumeItemInHand();
        if (player.getGameMode() != GameMode.CREATIVE) {
            // Because the max stack size of bucket is 1
            // So we can just set the item in player's hand
            player.setItemInHand(ItemTypes.BUCKET.createItemStack(1));
        }
        event.setCanBeUsed(true);
    }
}