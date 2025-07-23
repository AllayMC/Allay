package org.allaymc.server.network.processor.impl.ingame;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.blockentity.interfaces.BlockEntityItemFrame;
import org.allaymc.api.container.Container;
import org.allaymc.api.container.FullContainerType;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.item.interfaces.ItemFilledMapStack;
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
    public void handleSync(EntityPlayer player, MapInfoRequestPacket packet, long receiveTime) {
        // TODO: PlayerMapInfoRequestEvent
        var mapId = packet.getUniqueMapId();

        // Try to find the map item in the player's offhand and inventory
        ItemFilledMapStack mapItem = findMapItemIn(mapId, player.getContainer(FullContainerType.OFFHAND));
        if (mapItem == null) {
            mapItem = findMapItemIn(mapId, player.getContainer(FullContainerType.PLAYER_INVENTORY));
        }

        // Try to find the map item in item frames
        if (mapItem == null) {
            for (var blockEntity : player.getDimension().getBlockEntities().values()) {
                if (blockEntity instanceof BlockEntityItemFrame frame &&
                    frame.getItemStack() instanceof ItemFilledMapStack item &&
                    item.getMapId() == mapId) {
                    mapItem = item;
                }
            }
        }

        if (mapItem == null) {
            log.warn("Cannot find map item with ID {} for player {}", mapId, player.getOriginName());
            return;
        }

        var finalMapItem = mapItem;
        if (mapItem.getImage() == null) {
            Thread.ofVirtual().name("Map Render Thread #" + mapId).start(() -> {
                var floorPos = player.getLocation().floor(new Vector3d());
                finalMapItem.renderMap(
                        player.getDimension(),
                        ((int) floorPos.x / 128) * 128,
                        ((int) floorPos.z / 128) * 128
                );
                finalMapItem.sendToPlayer(player);
//                Server.getInstance().getScheduler().scheduleDelayed(
//                        Server.getInstance(),
//                        () -> {
//                            finalMapItem.sendToPlayer(player);
//                            return true;
//                        },
//                        20
//                );
            });
        } else {
            finalMapItem.sendToPlayer(player);
//            Server.getInstance().getScheduler().scheduleDelayed(
//                    Server.getInstance(),
//                    () -> {
//                        finalMapItem.sendToPlayer(player);
//                        return true;
//                    },
//                    20
//            );
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
