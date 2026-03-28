package org.allaymc.server.ddui;

import org.allaymc.api.ddui.DDUI;
import org.allaymc.api.ddui.Observable;
import org.allaymc.api.player.Player;
import org.cloudburstmc.protocol.bedrock.data.datastore.DataStoreAction;
import org.cloudburstmc.protocol.bedrock.data.datastore.DataStoreChange;
import org.cloudburstmc.protocol.bedrock.data.datastore.DataStoreUpdate;
import org.cloudburstmc.protocol.bedrock.packet.ClientboundDataDrivenUICloseScreenPacket;
import org.cloudburstmc.protocol.bedrock.packet.ClientboundDataDrivenUIShowScreenPacket;
import org.cloudburstmc.protocol.bedrock.packet.ClientboundDataStorePacket;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.clearInvocations;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

/**
 * Tests for DDUI screen controller lifecycle and batched updates.
 *
 * @author Miroshka
 */
@ExtendWith(MockitoExtension.class)
class DataDrivenScreenControllerTest {

    @Mock
    private Player player;

    private DataDrivenScreenController controller;

    @BeforeEach
    void setUp() {
        controller = new DataDrivenScreenController(player);
    }

    @Test
    void shouldSendInitialPacketsWhenViewingScreen() {
        var screen = DDUI.customForm()
                .title("Profile")
                .label("Name")
                .button("Save", it -> {
                })
                .screen();

        controller.view(screen);

        var captor = ArgumentCaptor.forClass(Object.class);
        verify(player, times(2)).sendPacket(captor.capture());

        var packets = captor.getAllValues();
        var dataPacket = assertInstanceOf(ClientboundDataStorePacket.class, packets.getFirst());
        var showPacket = assertInstanceOf(ClientboundDataDrivenUIShowScreenPacket.class, packets.get(1));

        assertEquals("minecraft:custom_form", showPacket.getScreenId());
        assertEquals(1, dataPacket.getUpdates().size());

        var action = assertInstanceOf(DataStoreChange.class, dataPacket.getUpdates().getFirst());
        assertEquals("minecraft", action.getDataStoreName());
        assertEquals("custom_form_data", action.getProperty());

        var payload = assertInstanceOf(Map.class, action.getNewValue());
        assertEquals("Profile", payload.get("title"));
        var layout = assertInstanceOf(Map.class, payload.get("layout"));
        assertEquals(2L, layout.get("length"));
        var label = assertInstanceOf(Map.class, layout.get("0"));
        assertEquals("Name", label.get("text"));
        assertEquals(Boolean.TRUE, label.get("label_visible"));
    }

    @Test
    void shouldBatchDirtyUpdatesIntoSinglePacket() {
        var title = DDUI.observable("Initial");
        var screen = DDUI.customForm().title(title);
        controller.view(screen);
        clearInvocations(player);

        title.setValue("A");
        title.setValue("B");
        title.setValue("C");

        controller.tick();

        var captor = ArgumentCaptor.forClass(Object.class);
        verify(player).sendPacket(captor.capture());

        var packet = assertInstanceOf(ClientboundDataStorePacket.class, captor.getValue());
        List<DataStoreAction> updates = packet.getUpdates();
        assertEquals(1, updates.size());

        var update = assertInstanceOf(DataStoreUpdate.class, updates.getFirst());
        assertEquals("minecraft", update.getDataStoreName());
        assertEquals("custom_form_data", update.getProperty());
        assertEquals("title", update.getPath());
        assertEquals("C", update.getData());
    }

    @Test
    void shouldRouteTextFieldInputWithoutEchoingSameValue() {
        Observable<String> name = DDUI.observable("");
        var screen = DDUI.customForm();
        screen.textField("Name", name);
        controller.view(screen);
        clearInvocations(player);

        var update = new DataStoreUpdate();
        update.setDataStoreName("minecraft");
        update.setProperty("custom_form_data");
        update.setPath("layout[0].text");
        update.setData("Alex");

        assertTrue(controller.handleInput(update));
        assertEquals("Alex", name.getValue());

        controller.tick();

        verifyNoMoreInteractions(player);
    }

    @Test
    void shouldCloseAndDetachActiveScreen() {
        var title = DDUI.observable("Initial");
        var screen = DDUI.customForm().title(title);
        controller.view(screen);
        clearInvocations(player);

        controller.closeAll();

        verify(player).sendPacket(org.mockito.ArgumentMatchers.isA(ClientboundDataDrivenUICloseScreenPacket.class));
        assertNull(controller.getActiveScreen());

        clearInvocations(player);
        title.setValue("Changed");
        controller.tick();
        verifyNoMoreInteractions(player);
    }

    @Test
    void shouldSendMessageBoxPayloadAndHandleButtonInput() {
        var clicks = new AtomicInteger();
        var screen = DDUI.messageBox()
                .title("Confirm")
                .body("Proceed")
                .button1("Yes", it -> clicks.incrementAndGet())
                .screen();

        controller.view(screen);

        var captor = ArgumentCaptor.forClass(Object.class);
        verify(player, times(2)).sendPacket(captor.capture());

        var packets = captor.getAllValues();
        var dataPacket = assertInstanceOf(ClientboundDataStorePacket.class, packets.getFirst());
        var showPacket = assertInstanceOf(ClientboundDataDrivenUIShowScreenPacket.class, packets.get(1));

        assertEquals("minecraft:message_box", showPacket.getScreenId());
        var action = assertInstanceOf(DataStoreChange.class, dataPacket.getUpdates().getFirst());
        assertEquals("message_box_data", action.getProperty());

        var payload = assertInstanceOf(Map.class, action.getNewValue());
        assertEquals("Confirm", payload.get("title"));
        assertEquals("Proceed", payload.get("body"));
        assertInstanceOf(Map.class, payload.get("button1"));

        clearInvocations(player);

        var update = new DataStoreUpdate();
        update.setDataStoreName("minecraft");
        update.setProperty("message_box_data");
        update.setPath("button1.onClick");
        update.setData(1L);

        assertTrue(controller.handleInput(update));
        assertEquals(1, clicks.get());

        controller.tick();
        verifyNoMoreInteractions(player);
    }

    @Test
    void shouldDetachPreviousScreenWhenViewingAnotherScreen() {
        var firstTitle = DDUI.observable("First");
        var firstScreen = DDUI.customForm().title(firstTitle);
        controller.view(firstScreen);
        clearInvocations(player);

        var secondScreen = DDUI.messageBox()
                .title("Second")
                .body("Body");
        controller.view(secondScreen);

        firstTitle.setValue("Changed");
        controller.tick();

        var captor = ArgumentCaptor.forClass(Object.class);
        verify(player, times(2)).sendPacket(captor.capture());

        var packets = captor.getAllValues();
        var dataPacket = assertInstanceOf(ClientboundDataStorePacket.class, packets.getFirst());
        var showPacket = assertInstanceOf(ClientboundDataDrivenUIShowScreenPacket.class, packets.get(1));

        assertEquals("minecraft:message_box", showPacket.getScreenId());
        var action = assertInstanceOf(DataStoreChange.class, dataPacket.getUpdates().getFirst());
        var payload = assertInstanceOf(Map.class, action.getNewValue());
        assertEquals("Second", payload.get("title"));
        verifyNoMoreInteractions(player);
    }
}
