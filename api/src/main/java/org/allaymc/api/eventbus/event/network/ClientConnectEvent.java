package org.allaymc.api.eventbus.event.network;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.eventbus.event.CancellableEvent;
import org.allaymc.api.i18n.MayContainTrKey;
import org.cloudburstmc.protocol.bedrock.BedrockServerSession;

/**
 * @author daoge_cmd
 */
@AllArgsConstructor
@Getter
public class ClientConnectEvent extends NetworkEvent implements CancellableEvent {
    protected BedrockServerSession session;
    /**
     * The reason that will be shown to the player if the event is cancelled.
     * If the event is not cancelled, this field will be ignored.
     */
    @MayContainTrKey
    @Setter
    protected String disconnectReason;
}
