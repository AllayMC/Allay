package org.allaymc.server.network.processor.ingame;

import org.allaymc.api.player.Player;
import org.cloudburstmc.protocol.bedrock.data.datastore.DataStoreUpdate;
import org.cloudburstmc.protocol.bedrock.packet.ServerboundDataStorePacket;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verify;

/**
 * Tests for serverbound DDUI data store packet routing.
 *
 * @author Miroshka
 */
@ExtendWith(MockitoExtension.class)
class ServerboundDataStorePacketProcessorTest {

    @Mock
    private Player player;

    @Test
    void shouldRouteIncomingDataStoreUpdateToPlayerScreenController() {
        var update = new DataStoreUpdate();
        update.setDataStoreName("minecraft");
        update.setProperty("custom_form_data");
        update.setPath("layout[0].text");
        update.setData("Alex");

        var packet = new ServerboundDataStorePacket();
        packet.setUpdate(update);

        var processor = new ServerboundDataStorePacketProcessor();
        processor.handleSync(player, packet, 0L);

        verify(player).handleDataDrivenScreenInput("minecraft", "custom_form_data", "layout[0].text", "Alex");
    }

    @Test
    void shouldIgnorePacketWithoutUpdate() {
        var processor = new ServerboundDataStorePacketProcessor();
        var packet = new ServerboundDataStorePacket();

        processor.handleSync(player, packet, 0L);

        verifyNoMoreInteractions(player);
    }
}
