package org.allaymc.server.network.processor.impl.ingame;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.server.container.ContainerActionProcessorHolder;
import org.allaymc.server.container.processor.ActionResponse;
import org.allaymc.server.container.processor.ContainerActionProcessor;
import org.allaymc.server.network.processor.PacketProcessor;
import org.cloudburstmc.protocol.bedrock.data.inventory.ContainerSlotType;
import org.cloudburstmc.protocol.bedrock.data.inventory.FullContainerName;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.ItemStackRequestAction;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.ItemStackRequestActionType;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.response.ItemStackResponse;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.response.ItemStackResponseContainer;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.response.ItemStackResponseSlot;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.response.ItemStackResponseStatus;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;
import org.cloudburstmc.protocol.bedrock.packet.ItemStackRequestPacket;
import org.cloudburstmc.protocol.bedrock.packet.ItemStackResponsePacket;

import java.util.*;

/**
 * @author Cool_Loong
 */
@Slf4j
public class ItemStackRequestPacketProcessor extends PacketProcessor<ItemStackRequestPacket> {
    protected final ContainerActionProcessorHolder processorHolder = new ContainerActionProcessorHolder();

    @Override
    public void handleSync(EntityPlayer player, ItemStackRequestPacket packet, long receiveTime) {
        List<ItemStackResponse> encodedResponses = new LinkedList<>();
        label:
        for (var request : packet.getRequests()) {
            // It is possible to have two same type actions in one request!
            var responses = new LinkedList<ActionResponse>();
            // Indicate that subsequent destroy action do not return a response
            // For more details, see inventory_stack_packet.md
            var noResponseForDestroyAction = false;
            var actions = request.getActions();
            var dataPool = new HashMap<>();
            for (int index = 0; index < actions.length; index++) {
                var action = actions[index];
                if (action.getType() == ItemStackRequestActionType.CRAFT_RESULTS_DEPRECATED) {
                    noResponseForDestroyAction = true;
                }

                ContainerActionProcessor<ItemStackRequestAction> processor = processorHolder.getProcessor(action.getType());
                if (processor == null) {
                    log.warn("Unhandled inventory action type {}", action.getType());
                    continue;
                }

                var response = processor.handle(action, player, index, actions, dataPool);
                if (response == null) continue;

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

            encodedResponses.add(encodeActionResponses(responses, request.getRequestId()));
        }

        var itemStackResponsePacket = new ItemStackResponsePacket();
        itemStackResponsePacket.getEntries().addAll(encodedResponses);
        player.sendPacket(itemStackResponsePacket);
    }

    private ItemStackResponse encodeActionResponses(List<ActionResponse> responses, int requestId) {
        Map<ContainerSlotType, Int2ObjectMap<ItemStackResponseSlot>> changedContainers = new HashMap<>();
        responses.forEach(response -> response.containers().forEach(container -> {
            for (var changedSlot : container.getItems()) {
                var changedSlots = changedContainers.computeIfAbsent(container.getContainerName().getContainer(), $ -> new Int2ObjectOpenHashMap<>());
                changedSlots.put(changedSlot.getSlot(), changedSlot);
            }
        }));

        var containers = changedContainers.entrySet().stream()
                .map(entry -> new ItemStackResponseContainer(entry.getKey(), new ArrayList<>(entry.getValue().values()), new FullContainerName(entry.getKey(), null)))
                .toList();
        return new ItemStackResponse(ItemStackResponseStatus.OK, requestId, containers);
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.ITEM_STACK_REQUEST;
    }
}
