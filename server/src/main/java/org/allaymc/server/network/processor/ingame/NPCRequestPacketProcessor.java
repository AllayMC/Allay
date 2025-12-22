package org.allaymc.server.network.processor.ingame;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.player.Player;
import org.allaymc.server.network.processor.PacketProcessor;
import org.cloudburstmc.protocol.bedrock.data.NpcRequestType;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;
import org.cloudburstmc.protocol.bedrock.packet.NpcRequestPacket;

/**
 * @author daoge_cmd
 */
@Slf4j
public class NPCRequestPacketProcessor extends PacketProcessor<NpcRequestPacket> {

    @Override
    public void handleSync(Player player, NpcRequestPacket packet, long receiveTime) {
        var requestType = packet.getRequestType();
        if (requestType != NpcRequestType.EXECUTE_COMMAND_ACTION && requestType != NpcRequestType.EXECUTE_CLOSING_COMMANDS) {
            return;
        }

        var pair = player.getDialog();
        if (pair == null) {
            // Call DialogViewer.closeDialog() will making the client send back EXECUTE_CLOSING_COMMANDS
            if (requestType != NpcRequestType.EXECUTE_CLOSING_COMMANDS) {
                log.warn("Player {} does not have any opened dialog!", player.getOriginName());
            }

            return;
        }

        var dialog = pair.left();
        switch (requestType) {
            case NpcRequestType.EXECUTE_COMMAND_ACTION -> {
                var index = packet.getActionType();
                if (index >= dialog.getButtons().size()) {
                    log.warn("Player {} clicked invalid button index {}!", player.getOriginName(), index);
                    return;
                }

                var button = dialog.getButtons().get(index);
                button.getOnClick().accept(button);

                var nextDialog = button.getNextDialog();
                if (nextDialog != null) {
                    player.viewDialog(dialog, pair.right());
                } else {
                    player.closeDialog();
                }
            }
            case NpcRequestType.EXECUTE_CLOSING_COMMANDS -> {
                player.removeDialog();
                dialog.getOnClose().run();
            }
        }
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.NPC_REQUEST;
    }
}
