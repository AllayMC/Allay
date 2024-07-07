package org.allaymc.server.network.processor;

import com.google.common.base.Preconditions;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.block.component.common.PlayerInteractInfo;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.container.FullContainerType;
import org.allaymc.api.entity.component.common.EntityDamageComponent;
import org.allaymc.api.entity.damage.DamageContainer;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.network.processor.PacketProcessor;
import org.allaymc.api.utils.MathUtils;
import org.cloudburstmc.protocol.bedrock.data.inventory.transaction.InventorySource;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;
import org.cloudburstmc.protocol.bedrock.packet.InventoryTransactionPacket;

import java.util.Objects;

import static org.allaymc.api.item.type.ItemTypes.AIR_TYPE;

/**
 * Allay Project 11/22/2023
 *
 * @author Cool_Loong
 */
@Slf4j
public class InventoryTransactionPacketProcessor extends PacketProcessor<InventoryTransactionPacket> {
    public static final int ITEM_USE_CLICK_BLOCK = 0;
    public static final int ITEM_USE_CLICK_AIR = 1;
    public static final int ITEM_USE_BREAK_BLOCK = 2;

    public static final int ITEM_USE_ON_ENTITY_INTERACT = 0;
    public static final int ITEM_USE_ON_ENTITY_ATTACK = 1;

    public static final int ITEM_RELEASE_RELEASE = 0;
    public static final int ITEM_RELEASE_CONSUME = 1;

    @Override
    public void handleSync(EntityPlayer player, InventoryTransactionPacket packet) {
        var transactionType = packet.getTransactionType();
        switch (transactionType) {
            case ITEM_USE -> {
                var clickBlockPos = MathUtils.CBVecToJOMLVec(packet.getBlockPosition());
                var clickPos = MathUtils.CBVecToJOMLVec(packet.getClickPosition());
                var blockFace = BlockFace.fromId(packet.getBlockFace());
                var itemStack = player.getItemInHand();
                var world = player.getLocation().dimension();
                switch (packet.getActionType()) {
                    case ITEM_USE_CLICK_BLOCK -> {
                        var dimension = player.getDimension();
                        var clickedBlockStateReplaceable = dimension.getBlockState(clickBlockPos).getBlockType().getMaterial().isReplaceable();
                        var placeBlockPos = clickedBlockStateReplaceable ? clickBlockPos : Objects.requireNonNull(blockFace).offsetPos(clickBlockPos);
                        var interactedBlock = world.getBlockState(clickBlockPos);
                        var interactInfo = new PlayerInteractInfo(
                                player, clickBlockPos,
                                clickPos, blockFace
                        );
                        itemStack.rightClickItemOn(dimension, placeBlockPos, interactInfo);
                        if (player.isUsingItemOnBlock()) {
                            if (itemStack.useItemOnBlock(dimension, placeBlockPos, interactInfo)) {
                                // Using item on the block successfully, no need to call BlockBehavior::onInteract()
                                break;
                            }
                            if (!interactedBlock.getBehavior().onInteract(itemStack, dimension, interactInfo)) {
                                // Player interaction with the block was unsuccessful, possibly the plugin rolled back the event, need to override the client block change
                                // Override the block change that was clicked
                                var blockStateClicked = dimension.getBlockState(clickBlockPos);
                                dimension.sendBlockUpdateTo(blockStateClicked, clickBlockPos, 0, player);

                                // Player places a block
                                if (itemStack.getItemType() == AIR_TYPE) break;
                                if (!itemStack.placeBlock(dimension, placeBlockPos, interactInfo)) {
                                    var blockStateReplaced = dimension.getBlockState(placeBlockPos);
                                    dimension.sendBlockUpdateTo(blockStateReplaced, placeBlockPos, 0, player);
                                }
                            }
                        }
                    }
                    case ITEM_USE_CLICK_AIR -> {
                        if (!player.isUsingItemInAir()) {
                            if (itemStack.canUseItemInAir(player)) {
                                // Start using item
                                player.setUsingItemInAir(true);
                            }
                        } else if (player.isUsingItemInAir()) {
                            // Item used
                            itemStack.useItemInAir(player, player.getItemUsingInAirTime());
                            player.setUsingItemInAir(false);
                        }
                    }
                }

                player.sendItemInHandUpdate();
            }
            case ITEM_RELEASE -> {
                switch (packet.getActionType()) {
                    case ITEM_RELEASE_RELEASE -> {
                        player.getItemInHand().releaseUsingItem(player, player.getItemUsingInAirTime());
                        // 玩家吃东西中断时，ITEM_USE_CLICK_AIR并不会发送
                        // 然而ITEM_RELEASE_RELEASE总是会在玩家停止使用物品时发送，无论是否使用成功
                        // 所以我们在收到ITEM_RELEASE_RELEASE时也刷新玩家物品使用状态，作为ITEM_USE_CLICK_AIR的补充
                        player.setUsingItemInAir(false);
                    }
                    case ITEM_RELEASE_CONSUME -> {
                        // TODO: It seems that this value is deprecated
                    }
                }
            }
            case ITEM_USE_ON_ENTITY -> {
                var target = player.getDimension().getEntityByRuntimeId(packet.getRuntimeEntityId());
                Preconditions.checkNotNull(target, "Player " + player.getOriginName() + " try to attack a entity which doesn't exist! Entity id: " + packet.getRuntimeEntityId());
                switch (packet.getActionType()) {
                    case ITEM_USE_ON_ENTITY_INTERACT -> {
                        target.onInteract(player, player.getItemInHand());
                        player.sendItemInHandUpdate();
                    }
                    case ITEM_USE_ON_ENTITY_ATTACK -> {
                        // Doesn't have damage component, can't attack
                        if (!(target instanceof EntityDamageComponent damageable)) return;

                        // TODO: Check whether the player can touch the target entity or not (to prevent cheater)
                        var itemInHand = player.getItemInHand();
                        var damage = itemInHand.calculateAttackDamage();
                        if (damage == 0) damage = 1;

                        var damageContainer = DamageContainer.entityAttack(player, damage);
                        if (!damageable.attack(damageContainer)) return;

                        itemInHand.onAttackEntity(player, target);
                        if (itemInHand.isBroken()) player.setItemInHand(AIR_TYPE.createItemStack());
                        else player.sendItemInHandUpdate();
                    }
                }
            }
            case NORMAL -> {
                // When the ItemStackRequest system is used, this transaction type is used for dropping items by pressing Q.
                // I don't know why they don't just use ItemStackRequest for that too, which already supports dropping items by
                // clicking them outside an open inventory menu, but for now it is what it is.
                // Fortunately, this means we can be much stricter about the validation criteria.
                // For more details, see item_throwing.md
                if (packet.getActions().size() != 2) {
                    log.warn("Expected two actions for dropping an item, got {}", packet.getActions().size());
                    return;
                }

                var worldInteractionAction = packet.getActions().getFirst();
                if (!worldInteractionAction.getSource().getType().equals(InventorySource.Type.WORLD_INTERACTION)) {
                    log.warn("Expected WORLD_INTERACTION action type, got {}", worldInteractionAction.getSource().getType());
                    return;
                }

                var containerAction = packet.getActions().getLast();
                if (!containerAction.getSource().getType().equals(InventorySource.Type.CONTAINER)) {
                    log.warn("Expected CONTAINER action type, got {}", containerAction.getSource().getType());
                    return;
                }

                var dropSlot = containerAction.getSlot();
                var dropCount = containerAction.getFromItem().getCount() - containerAction.getToItem().getCount();
                if (!player.tryDropItem(FullContainerType.PLAYER_INVENTORY, dropSlot, dropCount)) {
                    log.warn("Failed to drop item from slot {} with count {}", dropSlot, dropCount);
                }
            }
        }
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.INVENTORY_TRANSACTION;
    }
}
