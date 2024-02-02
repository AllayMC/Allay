package org.allaymc.server.network.processor;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.server.container.SimpleContainerActionProcessorHolder;
import org.allaymc.server.container.processor.ActionResponse;
import org.allaymc.server.container.processor.ContainerActionProcessor;
import org.allaymc.server.container.processor.ContainerActionProcessorHolder;
import org.allaymc.server.network.PacketProcessor;
import org.cloudburstmc.protocol.bedrock.data.inventory.ContainerSlotType;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.ItemStackRequestAction;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.ItemStackRequestActionType;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.response.ItemStackResponse;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.response.ItemStackResponseContainer;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.response.ItemStackResponseSlot;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.response.ItemStackResponseStatus;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;
import org.cloudburstmc.protocol.bedrock.packet.ItemStackRequestPacket;
import org.cloudburstmc.protocol.bedrock.packet.ItemStackResponsePacket;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Allay Project 11/22/2023
 *
 * @author Cool_Loong
 */
@Slf4j
public class ItemStackRequestPacketProcessor extends PacketProcessor<ItemStackRequestPacket> {
    protected final ContainerActionProcessorHolder containerActionProcessorHolder;

    public ItemStackRequestPacketProcessor() {
        containerActionProcessorHolder = new SimpleContainerActionProcessorHolder();
        ContainerActionProcessorHolder.registerDefaultContainerActionProcessors(containerActionProcessorHolder);
    }

    @Override
    public void handleSync(EntityPlayer player, ItemStackRequestPacket pk) {
        List<ItemStackResponse> encodedResponses = new LinkedList<>();
        label:
        for (var request : pk.getRequests()) {
            // It is possible to have two same type actions in one request!
            var responses = new LinkedList<ActionResponse>();
            // Indicate that subsequent destroy action do not return a response
            // For more details, see inventory_stack_packet.md
            boolean noResponseForDestroyAction = false;
            var actions = request.getActions();
            var dataPool = new HashMap<>();
            for (int index = 0; index < actions.length; index++) {
                var action = actions[index];
                if (action.getType() == ItemStackRequestActionType.CRAFT_RESULTS_DEPRECATED) {
                    noResponseForDestroyAction = true;
                }
                ContainerActionProcessor<ItemStackRequestAction> processor = containerActionProcessorHolder.getProcessor(action.getType());
                if (processor == null) {
                    log.warn("Unhandled inventory action type " + action.getType());
                    continue;
                }
                var response = processor.handle(action, player, index, actions, dataPool);
                if (response != null) {
                    if (!response.ok()) {
                        encodedResponses.add(new ItemStackResponse(ItemStackResponseStatus.ERROR, request.getRequestId(), null));
                        continue label;
                    }
                    if (noResponseForDestroyAction && action.getType() == ItemStackRequestActionType.DESTROY) {
                        noResponseForDestroyAction = false;
                    } else {
                        responses.add(response);
                    }
                }
            }
            encodedResponses.add(encodeActionResponses(responses, request.getRequestId()));
        }
        var itemStackResponsePacket = new ItemStackResponsePacket();
        itemStackResponsePacket.getEntries().addAll(encodedResponses);
        player.sendPacket(itemStackResponsePacket);
    }

    private ItemStackResponse encodeActionResponses(List<ActionResponse> responses, int requestId) {
        var changedContainers = new HashMap<ContainerSlotType, List<ItemStackResponseSlot>>();
        for (var response : responses) {
            response.containers().forEach(container -> {
                if (!changedContainers.containsKey(container.getContainer())) {
                    changedContainers.put(container.getContainer(), new ArrayList<>(container.getItems()));
                } else {
                    changedContainers.get(container.getContainer()).addAll(container.getItems());
                }
            });
        }
        List<ItemStackResponseContainer> containers = new ArrayList<>();
        changedContainers.forEach((type, slots) -> containers.add(new ItemStackResponseContainer(type, slots)));
        return new ItemStackResponse(ItemStackResponseStatus.OK, requestId, containers);
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.ITEM_STACK_REQUEST;
    }
}
