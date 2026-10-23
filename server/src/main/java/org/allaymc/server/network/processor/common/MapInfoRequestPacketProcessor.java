package org.allaymc.server.network.processor.common;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.blockentity.interfaces.BlockEntityItemFrame;
import org.allaymc.api.container.Container;
import org.allaymc.api.container.ContainerTypes;
import org.allaymc.api.eventbus.event.player.PlayerMapInfoRequestEvent;
import org.allaymc.api.item.interfaces.ItemFilledMapStack;
import org.allaymc.api.player.Player;
import org.allaymc.server.network.processor.PacketProcessor;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;
import org.cloudburstmc.protocol.bedrock.packet.MapInfoRequestPacket;
import org.joml.Vector3d;

/**
 * {@code MapInfoRequestPacketProcessor} finds a filled map requested by a player
 * and sends its image after event approval. Lookup prefers the offhand, then the
 * inventory, then item frames in
 * the current dimension. If several frames match, the last encountered frame
 * supplies the selected map and the event's frame reference.
 *
 * <p>
 * An existing image can be sent immediately, but an unrendered map first starts
 * asynchronous rendering around a 128-block-aligned origin derived from the
 * player location. Sending is attached to successful completion of that
 * rendering future. The standard map renderer recovers errors with a blank
 * image, which can therefore be sent through the same continuation. This
 * processor neither retries failed rendering nor rechecks the player's
 * connection or location afterward. Cancellation prevents
 * both immediate sending and starting a new render, keeping plugin approval
 * ahead of either path.
 *
 * @author daoge_cmd
 */
@Slf4j
public class MapInfoRequestPacketProcessor extends PacketProcessor<MapInfoRequestPacket> {

    /**
     * Finds and sends the requested map for the player's controlled entity.
     * Missing maps are logged and ignored, and a cancelled map request event
     * suppresses the response.
     *
     * <p>
     * The lookup order gives held maps priority over item-frame maps, with the
     * final matching frame used only when neither container contains the ID.
     * Missing image data starts a render and schedules sending on successful
     * future completion, including the standard renderer's blank-image recovery.
     * The chosen map reference is retained across that
     * boundary; no second lookup, retry, or connection-state check occurs here
     * after rendering completes.
     */
    @Override
    public void handleSync(Player player, MapInfoRequestPacket packet, long receiveTime) {
        var mapId = packet.getUniqueMapId();
        var entity = player.getControlledEntity();

        // Prefer held copies before scanning frames so the response uses the
        // player-owned map when several items carry the same requested ID.
        ItemFilledMapStack mapItem = findMapItemIn(mapId, entity.getContainer(ContainerTypes.OFFHAND));
        if (mapItem == null) {
            mapItem = findMapItemIn(mapId, entity.getContainer(ContainerTypes.INVENTORY));
        }

        // Only fall back to frames when neither held container matched;
        // continuing the scan deliberately leaves the last matching frame
        // associated with the selected map.
        BlockEntityItemFrame itemFrame = null;
        if (mapItem == null) {
            for (var blockEntity : entity.getDimension().getBlockEntities().values()) {
                if (blockEntity instanceof BlockEntityItemFrame frame &&
                    frame.getItemStack() instanceof ItemFilledMapStack item &&
                    item.getMapId() == mapId) {
                    mapItem = item;
                    itemFrame = frame;
                }
            }
        }

        if (mapItem == null) {
            log.warn("Cannot find map item with ID {} for player {}", mapId, player.getOriginName());
            return;
        }

        var event = new PlayerMapInfoRequestEvent(entity, mapItem, itemFrame);
        // Approval precedes rendering as well as sending, so cancellation does
        // not start background image work for this map request.
        if (!event.call()) {
            return;
        }

        var finalMapItem = mapItem;
        if (mapItem.getImage() == null) {
            // Render only when no image exists, then send the same selected map
            // after successful completion; failed futures do not run this
            // continuation.
            var floorPos = entity.getLocation().floor(new Vector3d());
            finalMapItem.renderMap(
                    entity.getDimension(),
                    // Integer division aligns toward zero, including negative
                    // block coordinates.
                    ((int) floorPos.x / 128) * 128,
                    ((int) floorPos.z / 128) * 128
            ).thenRun(() -> finalMapItem.sendToPlayer(player));
        } else {
            finalMapItem.sendToPlayer(player);
        }
    }

    /**
     * Returns the first filled map with the requested ID in a nonnull
     * container. Matching uses item iteration order, and absence of a match
     * returns {@code null} so the caller can try another source.
     */
    protected ItemFilledMapStack findMapItemIn(long mapId, Container playerInventory) {
        for (var itemStack : playerInventory.getItemStacks()) {
            if (itemStack instanceof ItemFilledMapStack mapItem && mapItem.getMapId() == mapId) {
                return mapItem;
            }
        }

        return null;
    }

    /**
     * Returns the map information request packet type handled by this
     * processor.
     */
    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.MAP_INFO_REQUEST;
    }
}
