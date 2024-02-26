package org.allaymc.server.network.processor;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.network.processor.PacketProcessor;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;
import org.cloudburstmc.protocol.bedrock.packet.ModalFormResponsePacket;

/**
 * Allay Project 2024/2/26
 *
 * @author daoge_cmd
 */
@Slf4j
public class ModalFormResponsePacketProcessor extends PacketProcessor<ModalFormResponsePacket> {

    @Override
    public void handleSync(EntityPlayer player, ModalFormResponsePacket packet) {
        var id = packet.getFormId();
        var form = player.removeForm(id);
        if (form == null) {
            form = player.removeServerSettingForm(id);
            if (form == null) {
                log.warn("Received response for unknown form from player {}: {}", player.getOriginName(), packet.getFormId());
                return;
            }
        }
        form.handleResponse(packet.getFormData());
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.MODAL_FORM_RESPONSE;
    }
}
