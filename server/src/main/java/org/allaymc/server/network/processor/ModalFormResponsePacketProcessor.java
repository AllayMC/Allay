package org.allaymc.server.network.processor;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.form.type.CustomForm;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;
import org.cloudburstmc.protocol.bedrock.packet.ModalFormResponsePacket;

/**
 * @author daoge_cmd
 */
@Slf4j
public class ModalFormResponsePacketProcessor extends PacketProcessor<ModalFormResponsePacket> {

    @Override
    public void handleSync(EntityPlayer player, ModalFormResponsePacket packet, long receiveTime) {
        var id = packet.getFormId();
        var form = player.removeForm(id);
        var isServerSettingsForm = false;
        if (form == null) {
            form = player.getServerSettingForm(id);
            if (form == null) {
                log.warn("Received response for unknown form from player {}: {}", player.getOriginName(), packet.getFormId());
                return;
            }

            isServerSettingsForm = true;
        }

        form.handleResponse(packet.getFormData() != null ? packet.getFormData().trim() : null);
        if (isServerSettingsForm) {
            ((CustomForm) form).syncDefaultValueToResponse();
        }
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.MODAL_FORM_RESPONSE;
    }
}
