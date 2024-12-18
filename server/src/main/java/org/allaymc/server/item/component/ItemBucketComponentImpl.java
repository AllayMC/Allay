package org.allaymc.server.item.component;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.block.interfaces.BlockLiquidBehavior;
import org.allaymc.api.block.property.type.BlockPropertyTypes;
import org.allaymc.api.block.tag.BlockCustomTags;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.container.FullContainerType;
import org.allaymc.api.entity.initinfo.EntityInitInfo;
import org.allaymc.api.entity.type.EntityType;
import org.allaymc.api.eventbus.EventHandler;
import org.allaymc.api.item.component.ItemBucketComponent;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.api.registry.Registries;
import org.allaymc.api.utils.Identifier;
import org.allaymc.server.item.component.event.CItemUseOnBlockEvent;
import org.cloudburstmc.protocol.bedrock.data.GameType;
import org.joml.Vector3ic;

/**
 * @author daoge_cmd
 */
@Slf4j
@AllArgsConstructor
public class ItemBucketComponentImpl implements ItemBucketComponent {
    public final Identifier liquidId;
    public final Identifier entityId;

    @Override
    public BlockType<?> getLiquidType() {
        return Registries.BLOCKS.get(liquidId);
    }

    @Override
    public EntityType<?> getEntityType() {
        return entityId == null ? null : Registries.ENTITIES.get(entityId);
    }

    @EventHandler
    protected void onUseItemOnBlock(CItemUseOnBlockEvent event) {
        var interactInfo = event.getInteractInfo();
        var player = interactInfo.player();
        var dimension = player.getDimension();
        var blockState = dimension.getBlockState(interactInfo.clickBlockPos());
        if (isEmpty()) {
            if (!(blockState.getBehavior() instanceof BlockLiquidBehavior)) {
                return;
            }
            try {
                if (blockState.getPropertyValue(BlockPropertyTypes.LIQUID_DEPTH) != 0) {
                    return;
                }
            } catch (IllegalArgumentException ignore) {}

            var blockType = blockState.getBlockType();
            if (blockType == BlockTypes.WATER || blockType == BlockTypes.FLOWING_WATER) {
                player.tryConsumeItemInHand();
                player.getContainer(FullContainerType.PLAYER_INVENTORY).tryAddItem(ItemTypes.WATER_BUCKET.createItemStack(1));
                dimension.setBlockState(interactInfo.clickBlockPos(), BlockTypes.AIR.getDefaultState());
            } else if (blockType == BlockTypes.LAVA || blockType == BlockTypes.FLOWING_LAVA) {
                player.tryConsumeItemInHand();
                player.getContainer(FullContainerType.PLAYER_INVENTORY).tryAddItem(ItemTypes.LAVA_BUCKET.createItemStack(1));
                dimension.setBlockState(interactInfo.clickBlockPos(), BlockTypes.AIR.getDefaultState());
            }
            event.setCanBeUsed(true);
            return;
        }

        Vector3ic liquidPlacedPos = event.getPlaceBlockPos();
        if (blockState.getBlockStateData().canContainLiquid()) {
            dimension.setBlockState(interactInfo.clickBlockPos(), getLiquidType().getDefaultState(), 1);
            liquidPlacedPos = interactInfo.clickBlockPos();
        } else {
            var blockOnPlacePos = dimension.getBlockState(event.getPlaceBlockPos());
            if (blockOnPlacePos.getBlockType() == BlockTypes.AIR || blockOnPlacePos.getBlockType().hasBlockTag(BlockCustomTags.REPLACEABLE)) {
                dimension.setBlockState(event.getPlaceBlockPos(), getLiquidType().getDefaultState(), 0);
            } else if (blockOnPlacePos.getBlockStateData().canContainLiquid()) {
                dimension.setBlockState(event.getPlaceBlockPos(), getLiquidType().getDefaultState(), 1);
            }
        }

        var entity = getEntityType();
        if (entity != null) {
            var entityInstance = entity.createEntity(
                    EntityInitInfo.builder()
                            .dimension(dimension)
                            .pos(liquidPlacedPos.x() + 0.5f, liquidPlacedPos.y() + 0.5f, liquidPlacedPos.z() + 0.5f)
                            .build()
            );
            dimension.getEntityService().addEntity(entityInstance);
        }

        player.tryConsumeItemInHand();
        if (player.getGameType() != GameType.CREATIVE) {
            // Because the max stack size of bucket is 1
            // So we can just set the item in player's hand
            player.setItemInHand(ItemTypes.BUCKET.createItemStack(1));
        }
        event.setCanBeUsed(true);
    }
}