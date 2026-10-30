package org.allaymc.server.container.processor;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.player.Player;
import org.cloudburstmc.protocol.bedrock.data.inventory.FullContainerName;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.DestroyAction;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.ItemStackRequestAction;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.ItemStackRequestActionType;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.response.ItemStackResponseContainer;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.response.ItemStackResponseSlot;

import java.util.Collections;
import java.util.Map;

import static org.allaymc.api.item.interfaces.ItemAirStack.AIR_STACK;
import static org.allaymc.api.item.type.ItemTypes.AIR;

/**
 * {@code DestroyActionProcessor} removes a requested quantity from a reachable container
 * stack and reports the remaining slot state. Partial removal keeps the existing stack
 * identity, while removing the full count installs air and uses the ordinary listener
 * notification path with viewer sending suppressed.
 *
 * @author daoge_cmd
 */
@Slf4j
public class DestroyActionProcessor implements ContainerActionProcessor<DestroyAction> {
    /**
     * Destroys the requested quantity after checking stack identity, non-air type, and
     * available count. The handler does not reject zero or negative counts explicitly;
     * accepted changes notify listeners with {@code send=false} and return the
     * translated remaining slot state.
     */
    @Override
    public ActionResponse handle(DestroyAction action, Player player, int currentActionIndex, ItemStackRequestAction[] actions, Map<String, Object> dataPool) {
        var container = ContainerActionProcessor.getContainerFrom(player, action.source().containerName());
        var count = action.count();
        var slot = ContainerActionProcessor.fromNetworkSlotIndex(container, action.source().slot());

        var item = container.getItemStack(slot);
        if (failToValidateStackUniqueId(item.getUniqueId(), action.source().stackNetworkId())) {
            log.warn("mismatch stack unique id!");
            return error();
        }

        if (item.getItemType() == AIR) {
            log.warn("cannot destroy an air!");
            return error();
        }

        if (item.getCount() < count) {
            log.warn("cannot destroy more items than the current amount!");
            return error();
        }

        if (item.getCount() > count) {
            item.setCount(item.getCount() - count);
            container.notifySlotChange(slot, false);
        } else {
            item = AIR_STACK;
            container.clearSlot(slot, false);
        }

        return new ActionResponse(
                true,
                Collections.singletonList(
                        new ItemStackResponseContainer(
                                ContainerActionProcessor.getSlotType(container, slot),
                                Collections.singletonList(
                                        new ItemStackResponseSlot(
                                                ContainerActionProcessor.toNetworkSlotIndex(container, slot),
                                                ContainerActionProcessor.toNetworkSlotIndex(container, slot),
                                                item.getCount(),
                                                item.getUniqueId(),
                                                item.getCustomName(),
                                                item.getDamage(),
                                                ""
                                        )
                                ),
                                new FullContainerName(ContainerActionProcessor.getSlotType(container, slot), null)
                        )
                )
        );
    }

    /**
     * Returns the stack-request action type dispatched to {@code
     * DestroyActionProcessor}.
     */
    @Override
    public ItemStackRequestActionType getType() {
        return ItemStackRequestActionType.DESTROY;
    }
}
