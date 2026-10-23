package org.allaymc.server.network.processor.ingame;

import org.allaymc.api.container.ContainerTypes;
import org.allaymc.api.player.Player;
import org.allaymc.server.network.processor.PacketProcessor;
import org.allaymc.server.player.AllayPlayer;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;
import org.cloudburstmc.protocol.bedrock.packet.ContainerClosePacket;

/**
 * {@code ContainerClosePacketProcessor} resolves client container-close
 * notifications against the player's opened containers and controls the
 * acknowledgement emitted during viewer removal. An
 * exact opened ID closes that container. For an unmatched minus-one ID, the
 * inventory is preferred, otherwise every currently opened container is removed
 * with its next close response suppressed.
 *
 * <p>
 * If no container can be resolved, a direct acknowledgement uses the incoming
 * ID and type. Normal matching closes mark the operation as client initiated
 * before removing the viewer, while the minus-one fallback sets the one-shot
 * suppression flag instead. These flags are consumed by {@code AllayPlayer}'s
 * container-close sending path, so their ordering relative to viewer removal
 * determines whether a response is omitted or identified as client initiated.
 * The processor does not restore viewer membership after a removal failure.
 *
 * @author Cool_Loong | daoge_cmd
 */
public class ContainerClosePacketProcessor extends PacketProcessor<ContainerClosePacket> {

    /**
     * Closes a resolved container for an {@code AllayPlayer} and prepares the
     * corresponding response policy. An unmatched minus-one ID first selects
     * the open inventory, then falls back to removing all opened containers
     * with responses suppressed.
     *
     * <p>
     * No resolved container produces a direct non-server-initiated
     * acknowledgement. For a selected container, the client-initiated or
     * suppression flag is set before {@code removeViewer}, because removal triggers the
     * player's outgoing close handling. Suppression is armed separately for
     * every container in the all-opened fallback; these operations are not
     * rolled back if a later removal fails.
     */
    @Override
    public void handleSync(Player player, ContainerClosePacket packet, long receiveTime) {
        var allayPlayer = (AllayPlayer) player;
        var opened = player.getOpenedContainer(packet.getId());
        var suppressCloseResponse = false;

        // An unmatched minus-one ID requests fallback closure: prefer the
        // inventory, otherwise close all opened containers while suppressing
        // responses tied to their ordinary assigned IDs.
        if (opened == null && packet.getId() == -1) {
            opened = player.getOpenedContainer(ContainerTypes.INVENTORY);
            if (opened != null) {
                suppressCloseResponse = true;
            } else if (!player.getOpenedContainers().isEmpty()) {
                for (var container : player.getOpenedContainers()) {
                    allayPlayer.setSuppressNextContainerClosePacket(true);
                    container.removeViewer(player);
                }
                return;
            }
        }

        if (opened == null) {
            // The server may already have removed this container; acknowledge
            // the received identity directly because no remaining viewer
            // removal can trigger its normal close response.
            allayPlayer.sendPacket(allayPlayer.getProtocol().getEncoder().encodeContainerClose(
                    packet.getId(),
                    packet.getType(),
                    false
            ));
            return;
        }

        // Set the outgoing close policy before removing the viewer, since that
        // removal reaches AllayPlayer and consumes the one-shot flag.
        if (suppressCloseResponse) {
            allayPlayer.setSuppressNextContainerClosePacket(true);
        } else {
            allayPlayer.setContainerClosedByClient(true);
        }
        opened.removeViewer(player);
    }

    /**
     * Returns the container close packet type handled by this processor.
     */
    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.CONTAINER_CLOSE;
    }
}
