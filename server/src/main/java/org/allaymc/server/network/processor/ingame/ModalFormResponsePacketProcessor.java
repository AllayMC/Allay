package org.allaymc.server.network.processor.ingame;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.form.FormCancelReason;
import org.allaymc.api.form.type.CustomForm;
import org.allaymc.api.player.Player;
import org.allaymc.server.network.processor.PacketProcessor;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;
import org.cloudburstmc.protocol.bedrock.packet.ModalFormResponsePacket;

/**
 * @author daoge_cmd
 */
@Slf4j
public class ModalFormResponsePacketProcessor extends PacketProcessor<ModalFormResponsePacket> {

    @Override
    public void handleSync(Player player, ModalFormResponsePacket packet, long receiveTime) {
        var id = packet.getFormId();
        var form = player.removeForm(id);
        var isServerSettingsForm = false;
        if (form == null) {
            var serverSettingForm = player.getServerSettingForm();
            if (id != serverSettingForm.left()) {
                log.warn("Received response for unknown form from player {}: {}", player.getOriginName(), packet.getFormId());
                return;
            }

            form = serverSettingForm.right();
            isServerSettingsForm = true;
        }

        var formData = packet.getFormData();
        if (formData != null) {
            form.handleResponse(formData.trim());
        } else {
            form.handleClose(switch (packet.getCancelReason().orElseThrow()) {
                case USER_CLOSED -> FormCancelReason.CLOSED;
                case USER_BUSY -> FormCancelReason.BUSY;
            });
        }
        if (isServerSettingsForm) {
            ((CustomForm) form).syncDefaultValueToResponse();
        }
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.MODAL_FORM_RESPONSE;
    }
}
