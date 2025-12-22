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

    protected boolean openingNextDialog = false;

    @Override
    public void handleSync(Player player, NpcRequestPacket packet, long receiveTime) {
        var requestType = packet.getRequestType();
        if (requestType != NpcRequestType.EXECUTE_COMMAND_ACTION &&
            requestType != NpcRequestType.EXECUTE_OPENING_COMMANDS &&
            requestType != NpcRequestType.EXECUTE_CLOSING_COMMANDS) {
            return;
        }

        var pair = player.getDialog();
        if (pair == null) {
            // DialogViewer.closeDialog() will making the client send back EXECUTE_CLOSING_COMMANDS,
            // and in that case we can safely ignore it c:
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
                    this.openingNextDialog = true;
                    player.viewDialog(nextDialog, pair.right());
                } else {
                    player.closeDialog();
                }
            }
            case NpcRequestType.EXECUTE_CLOSING_COMMANDS -> {
                if (!this.openingNextDialog) {
                    player.removeDialog();
                    dialog.getOnClose().run();
                }
            }
            case NpcRequestType.EXECUTE_OPENING_COMMANDS -> {
                this.openingNextDialog = false;
            }
        }
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.NPC_REQUEST;
    }
}
