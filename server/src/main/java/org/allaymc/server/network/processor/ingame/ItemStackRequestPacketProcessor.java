package org.allaymc.server.network.processor.ingame;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.player.Player;
import org.allaymc.server.container.processor.ActionResponse;
import org.allaymc.server.container.processor.ContainerActionProcessor;
import org.allaymc.server.container.processor.ContainerActionProcessorHolder;
import org.allaymc.server.network.processor.PacketProcessor;
import org.allaymc.server.player.AllayPlayer;
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

import java.util.*;

import static org.allaymc.server.container.processor.CraftRecipeOptionalActionProcessor.FILTER_STRINGS_DATA_KEY;

/**
 * {@code ItemStackRequestPacketProcessor} processes each item-stack request as
 * an ordered sequence of container actions
 * with request-local shared data. Multiple actions of the same type remain
 * distinct, and processors can return no response after applying their own
 * effects. Unknown action types are logged and skipped rather than failing the
 * whole request.
 *
 * <p>
 * An action failure emits one error response and skips the request's remaining
 * actions without undoing effects already applied. Successful responses are
 * collected, except for the next responding destroy action after a deprecated
 * crafting-results marker. Before transmission, changes are coalesced by
 * container slot type and slot number with the last response winning. Dynamic
 * container IDs are not retained by that grouping, and map iteration does not
 * promise response ordering. The player's negotiated encoder supplies any
 * version-specific response normalization.
 *
 * @author Cool_Loong
 */
@Slf4j
public class ItemStackRequestPacketProcessor extends PacketProcessor<ItemStackRequestPacket> {
    /**
     * {@code processorHolder} retains the action processors used across requests on this packet
     * processor; request-local shared data is allocated separately for each
     * request.
     */
    protected final ContainerActionProcessorHolder processorHolder = new ContainerActionProcessorHolder();

    /**
     * Processes all requests and sends their aggregated responses through the
     * {@code AllayPlayer}'s protocol encoder. Each request gets a fresh shared data
     * pool containing its filter strings, while action processors belong to
     * this processor instance.
     *
     * <p>
     * Unknown processors and {@code null} action responses add no response entry. A
     * failed response emits an error and skips remaining actions for that
     * request without rollback. A deprecated crafting-results action arms
     * suppression of the next successful responding destroy action; other
     * successful results are coalesced afterward. Earlier request effects
     * remain if a later processor or final encoding throws.
     */
    @Override
    public void handleSync(Player player, ItemStackRequestPacket packet, long receiveTime) {
        List<ItemStackResponse> encodedResponses = new LinkedList<>();
        label:
        for (var request : packet.getRequests()) {
            // Preserve action order and repeated action types; each result can
            // change the same slot again before the final response is
            // coalesced.
            List<ActionResponse> responses = new LinkedList<>();
            // A deprecated craft-results marker suppresses the next responding
            // successful destroy action, matching the replacement sequence
            // recorded in docs/old/protocol_research/inventory_stack_packet.md.
            var noResponseForDestroyAction = false;
            var actions = request.actions();

            Map<String, Object> dataPool = new HashMap<>();
            dataPool.put(FILTER_STRINGS_DATA_KEY, request.filterStrings());

            for (int index = 0; index < actions.length; index++) {
                var action = actions[index];
                if (action.getType() == ItemStackRequestActionType.CRAFT_RESULTS_DEPRECATED) {
                    noResponseForDestroyAction = true;
                }

                ContainerActionProcessor<ItemStackRequestAction> processor = processorHolder.getProcessor(action.getType());
                // Unknown action handlers are skipped while the remaining
                // request continues; absence of a handler does not itself
                // generate an error response.
                if (processor == null) {
                    log.warn("Not found handler for action type {}", action.getType());
                    continue;
                }

                var response = processor.handle(action, player, index, actions, dataPool);
                if (response == null) {
                    continue;
                }

                // Stop only the current request after an action error,
                // preserving already applied effects and emitting one error
                // instead of partial successful response entries.
                if (!response.ok()) {
                    encodedResponses.add(new ItemStackResponse(ItemStackResponseStatus.ERROR, request.requestId(), null));
                    continue label;
                }

                // Suppress the selected destroy result only after it succeeded;
                // failed or response-free actions do not consume this one-shot
                // response suppression here.
                if (noResponseForDestroyAction && action.getType() == ItemStackRequestActionType.DESTROY) {
                    noResponseForDestroyAction = false;
                } else {
                    responses.add(response);
                }
            }

            encodedResponses.add(encodeActionResponses(responses, request.requestId()));
        }

        var allayPlayer = (AllayPlayer) player;
        allayPlayer.sendPacket(allayPlayer.getProtocol().getEncoder().encodeItemStackResponse(encodedResponses));
    }

    /**
     * Coalesces successful action changes into one response for the request ID.
     * Entries are grouped by container slot type and slot number, with later
     * changes replacing earlier values for the same pair.
     *
     * <p>
     * This retains the final reported slot contents when several actions modify
     * a slot, rather than exposing intermediate states. Dynamic container-name
     * IDs are discarded and reconstructed as {@code null}, so the grouping does not
     * distinguish containers sharing a slot type. Hash-map iteration supplies
     * no stable output order, and slot response objects are retained by
     * reference inside new lists.
     */
    private ItemStackResponse encodeActionResponses(List<ActionResponse> responses, int requestId) {
        Map<ContainerSlotType, Int2ObjectMap<ItemStackResponseSlot>> changedContainers = new HashMap<>();
        responses.forEach(response -> response.containers().forEach(container -> {
            for (var changedSlot : container.items()) {
                var changedSlots = changedContainers.computeIfAbsent(container.containerName().container(), $ -> new Int2ObjectOpenHashMap<>());
                // Later actions overwrite earlier reports for the same
                // container type and slot, leaving the response with the last
                // reported contents.
                changedSlots.put(changedSlot.getSlot(), changedSlot);
            }
        }));

        var containers = changedContainers.entrySet().stream()
                .map(entry -> new ItemStackResponseContainer(entry.getKey(), new ArrayList<>(entry.getValue().values()), new FullContainerName(entry.getKey(), null)))
                .toList();
        return new ItemStackResponse(ItemStackResponseStatus.OK, requestId, containers);
    }

    /**
     * Returns the item stack request packet type handled by this processor.
     */
    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.ITEM_STACK_REQUEST;
    }
}
