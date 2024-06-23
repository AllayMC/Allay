package org.allaymc.server.network.processor;

import com.google.common.base.Preconditions;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.block.component.common.PlayerInteractInfo;
import org.allaymc.api.block.data.BlockFace;
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
                        var placeBlockPos = Objects.requireNonNull(blockFace).offsetPos(clickBlockPos);
                        var dimension = player.getDimension();
                        var interactedBlock = world.getBlockState(clickBlockPos);
                        var interactInfo = new PlayerInteractInfo(
                                player, clickBlockPos,
                                clickPos, blockFace
                        );
                        if (!player.isInteractingBlock()) {
                            itemStack.useItemOn(dimension, placeBlockPos, interactInfo);
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
                    case ITEM_USE_CLICK_AIR -> {
                        if (!itemStack.useItemInAir(player)) break;
                        if (!player.hasAction()) player.setAction(true);
                        player.setAction(false);
                    }
                }

                player.sendItemInHandUpdate();
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
                if (packet.getActions().size() != 2) {
                    log.warn("Expected two actions for dropping an item, got {}", packet.getActions().size());
                    return;
                }

                for (var action : packet.getActions()) {
                    if (!action.getSource().getType().equals(InventorySource.Type.WORLD_INTERACTION)) continue;
                    if (!action.getSource().getFlag().equals(InventorySource.Flag.DROP_ITEM)) continue;
                    // Do not ask me why Mojang still use the old item transaction pk even the server-auth inv was enabled
                    var count = action.getToItem().getCount();
                    player.tryDropItemInHand(count);
                }
            }
        }
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.INVENTORY_TRANSACTION;
    }
}
