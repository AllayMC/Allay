package org.allaymc.server.network.processor.ingame;

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
 * @author daoge_cmd
 */
@Slf4j
public class MapInfoRequestPacketProcessor extends PacketProcessor<MapInfoRequestPacket> {

    @Override
    public void handleSync(Player player, MapInfoRequestPacket packet, long receiveTime) {
        var mapId = packet.getUniqueMapId();
        var entity = player.getControlledEntity();

        // Try to find the map item in the player's offhand and inventory
        ItemFilledMapStack mapItem = findMapItemIn(mapId, entity.getContainer(ContainerTypes.OFFHAND));
        if (mapItem == null) {
            mapItem = findMapItemIn(mapId, entity.getContainer(ContainerTypes.INVENTORY));
        }

        // Try to find the map item in item frames
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
        if (!event.call()) {
            return;
        }

        var finalMapItem = mapItem;
        if (mapItem.getImage() == null) {
            var floorPos = entity.getLocation().floor(new Vector3d());
            finalMapItem.renderMap(
                    entity.getDimension(),
                    ((int) floorPos.x / 128) * 128,
                    ((int) floorPos.z / 128) * 128
            ).thenRun(() -> finalMapItem.sendToPlayer(player));
        } else {
            finalMapItem.sendToPlayer(player);
        }
    }

    protected ItemFilledMapStack findMapItemIn(long mapId, Container playerInventory) {
        for (var itemStack : playerInventory.getItemStacks()) {
            if (itemStack instanceof ItemFilledMapStack mapItem && mapItem.getMapId() == mapId) {
                return mapItem;
            }
        }

        return null;
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.MAP_INFO_REQUEST;
    }
}
