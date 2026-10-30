package org.allaymc.server.container.processor;

import org.allaymc.api.container.Container;
import org.allaymc.api.container.ContainerTypes;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.component.ItemWearableComponent;
import org.allaymc.api.item.data.ArmorType;
import org.allaymc.api.item.data.ItemTags;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.api.player.Player;
import org.allaymc.server.container.ContainerNetworkInfo;
import org.allaymc.server.container.impl.FakeContainerImpl;
import org.allaymc.server.entity.component.player.EntityPlayerContainerHolderComponentImpl;
import org.allaymc.server.entity.impl.EntityPlayerImpl;
import org.allaymc.server.player.AllayPlayer;
import org.cloudburstmc.protocol.bedrock.data.inventory.ContainerSlotType;
import org.cloudburstmc.protocol.bedrock.data.inventory.FullContainerName;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.ItemStackRequestAction;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.ItemStackRequestActionType;

import java.util.List;
import java.util.Map;

/**
 * {@code ContainerActionProcessor} processes one action within an ordered item-stack
 * request and supplies shared container lookup, slot translation, identity validation,
 * and equipment placement rules. A request-local data map connects related actions such
 * as recipe selection and output creation. Implementations apply their effects directly;
 * the caller does not roll back earlier actions when a later action fails.
 *
 * <p>
 * Container lookup prefers opened windows before held containers and resolves only the
 * slot category from a full container name. Translation metadata preserves network and
 * internal slot identities in both directions. Positive client stack IDs must match,
 * while nonpositive IDs skip comparison. Fake slots dispatch click callbacks instead of
 * allowing a transfer, and equipment placement checks distinguish offhand eligibility
 * and armor positions.
 *
 * @author daoge_cmd
 */
public interface ContainerActionProcessor<T extends ItemStackRequestAction> {

    /**
     * {@code ERROR_RESPONSE} is the shared failed-action result with no slot changes.
     * Request processing uses its status to stop later actions without undoing earlier
     * effects.
     */
    ActionResponse ERROR_RESPONSE = new ActionResponse(false, List.of());

    /**
     * Processes one action in its request context. A successful response contributes
     * changed-slot data, a failed response stops remaining actions, and {@code null}
     * contributes no response entry after any effects already applied. {@code dataPool}
     * remains shared only among actions in this request.
     */
    ActionResponse handle(T action, Player player, int currentActionIndex, ItemStackRequestAction[] actions, Map<String, Object> dataPool);

    /**
     * Returns the request action type accepted by this handler.
     */
    ItemStackRequestActionType getType();

    /**
     * Returns the shared failed-action response with no container changes.
     */
    default ActionResponse error() {
        return ERROR_RESPONSE;
    }

    /**
     * Returns whether a positive client stack ID differs from the server ID. Zero and
     * negative client values skip comparison, so this helper alone does not validate the
     * identity of those requests.
     */
    default boolean failToValidateStackUniqueId(int expectedId, int clientId) {
        // Nonpositive client IDs defer to current server state; only positive IDs
        // participate in this comparison, including later actions referencing earlier
        // request results.
        return clientId > 0 && expectedId != clientId;
    }

    /**
     * Resolves a reachable container by preferring an opened window over held storage
     * with the same slot category. The dynamic name ID is ignored, and no matching
     * container returns {@code null}.
     */
    @SuppressWarnings("unchecked")
    static <T extends Container> T getContainerFrom(Player player, FullContainerName containerName) {
        var playerImpl = (EntityPlayerImpl) player.getControlledEntity();
        var container = ((AllayPlayer) player).getOpenedContainer(containerName.container());
        // Held storage remains reachable when no matching window is open; the slot
        // category, rather than the dynamic container ID, selects this fallback.
        if (container == null) {
            container = ((EntityPlayerContainerHolderComponentImpl) playerImpl.getContainerHolderComponent()).getContainer(containerName.container());
        }
        return (T) container;
    }

    /**
     * Returns the network category for an internal slot using its container metadata.
     * The container must have registered metadata and the index must address its slot
     * table before this lookup.
     */
    static ContainerSlotType getSlotType(Container container, int slot) {
        return ContainerNetworkInfo.getInfo(container.getContainerType()).getSlotType(slot);
    }

    /**
     * Translates an internal slot to its network index using the inverse metadata map.
     * Missing metadata or an absent mapping fails rather than returning a sentinel slot
     * index.
     */
    static int toNetworkSlotIndex(Container container, int index) {
        return ContainerNetworkInfo.getInfo(container.getContainerType()).networkSlotIndexMapper().inverse().get(index);
    }

    /**
     * Translates a network slot to its internal index using container metadata. Missing
     * metadata or an absent mapping fails rather than returning a sentinel, so callers
     * must supply reachable mapped slots.
     */
    static int fromNetworkSlotIndex(Container container, int index) {
        return ContainerNetworkInfo.getInfo(container.getContainerType()).networkSlotIndexMapper().get(index);
    }

    /**
     * Dispatches a click for a fake transfer endpoint and returns whether a fake
     * container was found. Source takes precedence when both endpoints are fake; callers
     * reject movement after a {@code true} result.
     */
    static boolean tryHandleFakeContainer(Container source, int sourceSlot, Container destination, int destinationSlot) {
        if (source instanceof FakeContainerImpl fakeContainer) {
            fakeContainer.onClick(sourceSlot);
            return true;
        }

        if (destination instanceof FakeContainerImpl fakeContainer) {
            fakeContainer.onClick(destinationSlot);
            return true;
        }

        return false;
    }

    /**
     * Returns whether equipment placement permits the item in the specified container
     * slot. Air always succeeds, allowing clearing or swapping an empty value regardless
     * of equipment restrictions.
     *
     * <p>
     * Offhand storage requires the allow-offhand item tag, while armor storage requires
     * a wearable whose armor type matches the selected helmet, chestplate, leggings, or
     * boots index. Other containers are accepted without recipe, capacity, or index
     * validation here.
     */
    static boolean canPlaceItemToSlot(Container container, int slot, ItemStack item) {
        if (item.getItemType() == ItemTypes.AIR) {
            return true;
        }

        if (container.getContainerType() == ContainerTypes.OFFHAND && !item.getItemType().hasItemTag(ItemTags.ALLOW_OFFHAND)) {
            return false;
        }

        if (container.getContainerType() == ContainerTypes.ARMOR) {
            if (item instanceof ItemWearableComponent wearableComponent) {
                return switch (slot) {
                    case 0 -> wearableComponent.getArmorType() == ArmorType.HELMET;
                    case 1 -> wearableComponent.getArmorType() == ArmorType.CHESTPLATE;
                    case 2 -> wearableComponent.getArmorType() == ArmorType.LEGGINGS;
                    case 3 -> wearableComponent.getArmorType() == ArmorType.BOOTS;
                    default -> false;
                };
            }

            return false;
        }

        return true;
    }
}
