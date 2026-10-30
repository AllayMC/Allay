package org.allaymc.server.network.processor.ingame;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.player.Player;
import org.allaymc.server.network.processor.PacketProcessor;
import org.cloudburstmc.protocol.bedrock.data.NpcRequestType;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;
import org.cloudburstmc.protocol.bedrock.packet.NpcRequestPacket;

/**
 * {@code NPCRequestPacketProcessor} dispatches dialog button actions and opening
 * or closing notifications against
 * the player's active dialog. Only the three command-related NPC request types
 * are considered. Button callbacks run before selecting that button's next
 * dialog, and closing without an active dialog is silently accepted as an
 * already-completed close.
 *
 * <p>
 * The connection-local {@code openingNextDialog} flag distinguishes a transition to
 * another dialog from a final close. It is set before presenting the next
 * dialog and cleared by an opening notification, suppressing closing callbacks
 * while the transition remains marked. Final close removes the stored dialog
 * before its callback. Callback failures are not recovered, and the processor
 * validates only the upper button-index bound before list access, leaving
 * negative indexes to the list's failure behavior.
 *
 * @author daoge_cmd
 */
@Slf4j
public class NPCRequestPacketProcessor extends PacketProcessor<NpcRequestPacket> {

    /**
     * {@code openingNextDialog} marks a next-dialog presentation until an opening
     * notification arrives. Closing notifications are suppressed while this flag
     * remains set.
     */
    protected boolean openingNextDialog = false;

    /**
     * Routes accepted NPC request types to the player's active dialog. Missing
     * dialogs are ignored, with warnings for non-close requests, and button
     * indexes at or above the button count are rejected before callback
     * dispatch.
     *
     * <p>
     * A button callback executes before reading its next-dialog link. A linked
     * dialog sets the transition flag before presentation, suppressing
     * intermediate close processing until an opening notification clears it. A
     * final close removes the active dialog before running its close callback.
     * Exceptions propagate without restoring removed dialogs or resetting a
     * transition flag already set.
     */
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
            // A close notification can arrive after the stored dialog was
            // removed; consume that acknowledgement silently while still
            // diagnosing other requests without an active dialog.
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
                    // Mark the transition before presenting the next dialog so
                    // intervening close notifications do not remove that
                    // replacement or run its close callback.
                    this.openingNextDialog = true;
                    player.viewDialog(nextDialog, pair.right());
                } else {
                    player.closeDialog();
                }
            }
            case NpcRequestType.EXECUTE_CLOSING_COMMANDS -> {
                if (!this.openingNextDialog) {
                    // Remove the old dialog before its callback so callback
                    // code can install a new dialog without a later removal
                    // erasing it.
                    player.removeDialog();
                    dialog.getOnClose().run();
                }
            }
            case NpcRequestType.EXECUTE_OPENING_COMMANDS -> {
                this.openingNextDialog = false;
            }
        }
    }

    /**
     * Returns the npc request packet type handled by this processor.
     */
    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.NPC_REQUEST;
    }
}
