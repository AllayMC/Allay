package org.allaymc.server.network.processor;

import com.google.common.base.Preconditions;
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
import org.joml.Vector3fc;
import org.joml.Vector3ic;

import static org.allaymc.api.item.type.ItemTypes.AIR_TYPE;

/**
 * Allay Project 11/22/2023
 *
 * @author Cool_Loong
 */
public class InventoryTransactionPacketProcessor extends PacketProcessor<InventoryTransactionPacket> {
    public static final int ITEM_USE_CLICK_BLOCK = 0;
    public static final int ITEM_USE_CLICK_AIR = 1;
    public static final int ITEM_USE_BREAK_BLOCK = 2;


    public static final int ITEM_USE_ON_ENTITY_INTERACT = 0;
    public static final int ITEM_USE_ON_ENTITY_ATTACK = 1;

    @Override
    public void handleSync(EntityPlayer player, InventoryTransactionPacket packet) {
        var transactionType = packet.getTransactionType();
        switch (transactionType) {
            case ITEM_USE -> {
                Vector3ic blockPos = MathUtils.CBVecToJOMLVec(packet.getBlockPosition());
                Vector3fc clickPos = MathUtils.CBVecToJOMLVec(packet.getClickPosition());
                BlockFace blockFace = BlockFace.fromId(packet.getBlockFace());
                var itemStack = player.getItemInHand();
                var world = player.getLocation().dimension();
                switch (packet.getActionType()) {
                    case ITEM_USE_CLICK_BLOCK -> {
                        var placePos = blockFace.offsetPos(blockPos);
                        var dimension = player.getDimension();
                        var interactedBlock = world.getBlockState(blockPos);
                        if (player.isInteractingBlock()) {
                            if (!interactedBlock.getBehavior().onInteract(player, itemStack, dimension, blockPos, placePos, clickPos, blockFace)) {
                                // 玩家与方块的互动不成功，可能是插件撤回了事件，需要覆盖客户端的方块变化
                                // 覆盖被点击方块变化
                                var blockStateClicked = dimension.getBlockState(blockPos);
                                dimension.sendBlockUpdateTo(blockStateClicked, blockPos, 0, player);

                                // 玩家放置方块
                                if (itemStack.getItemType() != AIR_TYPE) {
                                    if (!itemStack.placeBlock(player, dimension, blockPos, placePos, clickPos, blockFace)) {
                                        var blockStateReplaced = dimension.getBlockState(placePos);
                                        dimension.sendBlockUpdateTo(blockStateReplaced, placePos, 0, player);
                                    }
                                }
                            }
                        } else itemStack.useItemOn(player, dimension, blockPos, placePos, clickPos, blockFace);
                    }
                    case ITEM_USE_CLICK_AIR -> {
                        if (itemStack.useItemInAir(player)) {
                            if (!player.hasAction()) {
                                player.setAction(true);
                            }
                            player.setAction(false);
                        }
                    }
                }
                player.sendItemInHandUpdate();
            }
            case ITEM_USE_ON_ENTITY -> {
                var target = player.getDimension().getEntityByRuntimeId(packet.getRuntimeEntityId());
                Preconditions.checkNotNull(target, "Player " + player.getOriginName() + " try to attack a entity which doesn't exist! Entity id: " + packet.getRuntimeEntityId());
                switch (packet.getActionType()) {
                    case ITEM_USE_ON_ENTITY_INTERACT -> target.onInteract(player, player.getItemInHand());
                    case ITEM_USE_ON_ENTITY_ATTACK -> {
                        EntityDamageComponent damageable;
                        if (target instanceof EntityDamageComponent cast) {
                            damageable = cast;
                            // Doesn't have damage component, can't attack
                        } else {
                            return;
                        }
                        // TODO: Check whether the player can touch the target entity or not (to prevent cheater)
                        var itemInHand = player.getItemInHand();
                        var damage = itemInHand.calculateAttackDamage();
                        if (damage == 0) damage = 1;
                        var damageContainer = DamageContainer.entityAttack(player, damage);
                        damageable.attack(damageContainer);
                    }
                }
                player.sendItemInHandUpdate();
            }
            case NORMAL -> {
                for (var action : packet.getActions()) {
                    if (action.getSource().getType().equals(InventorySource.Type.WORLD_INTERACTION)) {
                        if (action.getSource().getFlag().equals(InventorySource.Flag.DROP_ITEM)) {
                            // Do not ask me why mojang still use the old item transaction pk even the server-auth inv was enabled
                            var count = action.getToItem().getCount();
                            player.tryDropItemInHand(count);
                        }
                    }
                }
            }
        }
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.INVENTORY_TRANSACTION;
    }
}
