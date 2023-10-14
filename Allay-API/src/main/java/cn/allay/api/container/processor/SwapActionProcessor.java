package cn.allay.api.container.processor;

import cn.allay.api.container.Container;
import cn.allay.api.entity.interfaces.player.EntityPlayer;
import lombok.extern.slf4j.Slf4j;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.ItemStackRequestActionType;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.SwapAction;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.response.ItemStackResponse;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.response.ItemStackResponseContainer;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.response.ItemStackResponseSlot;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.response.ItemStackResponseStatus;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Allay Project 2023/10/7
 *
 * @author daoge_cmd
 */
@Slf4j
public class SwapActionProcessor implements ContainerActionProcessor<SwapAction> {
    @Override
    public ItemStackRequestActionType getType() {
        return ItemStackRequestActionType.SWAP;
    }

    @Override
    public ItemStackResponse handle(SwapAction action, EntityPlayer player, int requestId, LinkedHashMap<ItemStackRequestActionType, ItemStackResponse> chainInfo) {
        Container sourceContainer = player.getReachableContainerBySlotType(action.getSource().getContainer());
        Container destinationContainer = player.getReachableContainerBySlotType(action.getDestination().getContainer());
        var sourceSlot = action.getSource().getSlot();
        var destinationSlot = action.getDestination().getSlot();
        var sourceItem = sourceContainer.getItemStack(sourceSlot);
        var destinationItem = destinationContainer.getItemStack(destinationSlot);
        if (sourceItem.getStackNetworkId() != action.getSource().getStackNetworkId()) {
            log.warn("mismatch stack network id!");
            return error(requestId);
        }
        if (destinationItem.getStackNetworkId() != action.getDestination().getStackNetworkId()) {
            log.warn("mismatch stack network id!");
            return error(requestId);
        }
        sourceContainer.setItemStack(sourceSlot, destinationItem);
        destinationContainer.setItemStack(destinationSlot, sourceItem);
        return new ItemStackResponse(
                ItemStackResponseStatus.OK,
                requestId,
                List.of(
                        new ItemStackResponseContainer(
                                sourceContainer.getSlotType(sourceSlot),
                                Collections.singletonList(
                                        new ItemStackResponseSlot(
                                                sourceSlot,
                                                sourceSlot,
                                                destinationItem.getCount(),
                                                destinationItem.getStackNetworkId(),
                                                destinationItem.getCustomName(),
                                                destinationItem.getDurability()
                                        )
                                )
                        ),
                        new ItemStackResponseContainer(
                                destinationContainer.getSlotType(destinationSlot),
                                Collections.singletonList(
                                        new ItemStackResponseSlot(
                                                destinationSlot,
                                                destinationSlot,
                                                sourceItem.getCount(),
                                                sourceItem.getStackNetworkId(),
                                                sourceItem.getCustomName(),
                                                sourceItem.getDurability()
                                        )
                                )
                        )
                )
        );
    }
}
