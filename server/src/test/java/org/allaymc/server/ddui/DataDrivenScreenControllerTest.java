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
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
        assertEquals(2, update.getUpdateCount());
        assertEquals(1, update.getPathUpdateCount());
    }

    @Test
    void shouldAdvanceUpdateCountersAcrossFlushes() {
        var title = DDUI.observable("Initial");
        var screen = DDUI.customForm().title(title);
        controller.view(screen);
        clearInvocations(player);

        title.setValue("A");
        controller.tick();

        var firstCaptor = ArgumentCaptor.forClass(Object.class);
        verify(player).sendPacket(firstCaptor.capture());

        var firstPacket = assertInstanceOf(ClientboundDataStorePacket.class, firstCaptor.getValue());
        var firstUpdate = assertInstanceOf(DataStoreUpdate.class, firstPacket.getUpdates().getFirst());
        assertEquals(2, firstUpdate.getUpdateCount());
        assertEquals(1, firstUpdate.getPathUpdateCount());

        clearInvocations(player);

        title.setValue("B");
        controller.tick();

        var secondCaptor = ArgumentCaptor.forClass(Object.class);
        verify(player).sendPacket(secondCaptor.capture());

        var secondPacket = assertInstanceOf(ClientboundDataStorePacket.class, secondCaptor.getValue());
        var secondUpdate = assertInstanceOf(DataStoreUpdate.class, secondPacket.getUpdates().getFirst());
        assertEquals(3, secondUpdate.getUpdateCount());
        assertEquals(2, secondUpdate.getPathUpdateCount());
    }

    @Test
    void shouldUseSameUpdateCountForEveryPathInSingleFlush() {
        var title = DDUI.observable("Initial");
        var body = DDUI.observable("Body A");
        var screen = DDUI.customForm().title(title);
        screen.label(body);
        controller.view(screen);
        clearInvocations(player);

        title.setValue("Next");
        body.setValue("Body B");
        controller.tick();

        var captor = ArgumentCaptor.forClass(Object.class);
        verify(player).sendPacket(captor.capture());

        var packet = assertInstanceOf(ClientboundDataStorePacket.class, captor.getValue());
        assertEquals(2, packet.getUpdates().size());

        var firstUpdate = assertInstanceOf(DataStoreUpdate.class, packet.getUpdates().get(0));
        var secondUpdate = assertInstanceOf(DataStoreUpdate.class, packet.getUpdates().get(1));
        assertEquals(2, firstUpdate.getUpdateCount());
        assertEquals(2, secondUpdate.getUpdateCount());
        assertEquals(1, firstUpdate.getPathUpdateCount());
        assertEquals(1, secondUpdate.getPathUpdateCount());
    }

    @Test
    void shouldSendFullScreenChangeWhenLayoutTextUpdates() {
        var frame = DDUI.observable("Frame A\nLine 2");
        var screen = DDUI.customForm().title("Renderer");
        screen.label(frame);
        controller.view(screen);
        clearInvocations(player);

        frame.setValue("Frame B\nLine 2");
        controller.tick();

        var captor = ArgumentCaptor.forClass(Object.class);
        verify(player).sendPacket(captor.capture());

        var packet = assertInstanceOf(ClientboundDataStorePacket.class, captor.getValue());
        assertEquals(1, packet.getUpdates().size());

        var change = assertInstanceOf(DataStoreChange.class, packet.getUpdates().getFirst());
        assertEquals("minecraft", change.getDataStoreName());
        assertEquals("custom_form_data", change.getProperty());
        assertEquals(2, change.getUpdateCount());

        var payload = assertInstanceOf(Map.class, change.getNewValue());
        var layout = assertInstanceOf(Map.class, payload.get("layout"));
        var label = assertInstanceOf(Map.class, layout.get("0"));
        assertEquals("Frame B\nLine 2", label.get("text"));
    }

    @Test
    void shouldKeepSingleLineLayoutTextAsLeafUpdate() {
        var text = DDUI.observable("Frame A");
        var screen = DDUI.customForm().title("Renderer");
        screen.label(text);
        controller.view(screen);
        clearInvocations(player);

        text.setValue("Frame B");
        controller.tick();

        var captor = ArgumentCaptor.forClass(Object.class);
        verify(player).sendPacket(captor.capture());

        var packet = assertInstanceOf(ClientboundDataStorePacket.class, captor.getValue());
        assertEquals(1, packet.getUpdates().size());

        var update = assertInstanceOf(DataStoreUpdate.class, packet.getUpdates().getFirst());
        assertEquals("layout[0].text", update.getPath());
        assertEquals("Frame B", update.getData());
    }

    @Test
    void shouldRouteTextFieldInputToReboundObservable() {
        var first = DDUI.observable("value");
        var second = DDUI.observable("value");
        var screen = DDUI.customForm();
        var textField = screen.textField("Name", first);
        controller.view(screen);
        clearInvocations(player);

        textField.setText(second);

        var update = new DataStoreUpdate();
        update.setDataStoreName("minecraft");
        update.setProperty("custom_form_data");
        update.setPath("layout[0].text");
        update.setData("Alex");

        assertTrue(controller.handleInput(update));
        assertEquals("value", first.getValue());
        assertEquals("Alex", second.getValue());

        controller.tick();

        verifyNoMoreInteractions(player);
        clearInvocations(player);

        second.setValue("Bob");

        assertEquals("Bob", textField.getText());
    }

    @Test
    void shouldRouteToggleInputToReboundObservable() {
        var first = DDUI.observable(false);
        var second = DDUI.observable(false);
        var screen = DDUI.customForm();
        var toggle = screen.toggle("Enabled", first);
        controller.view(screen);
        clearInvocations(player);

        toggle.setToggled(second);

        var update = new DataStoreUpdate();
        update.setDataStoreName("minecraft");
        update.setProperty("custom_form_data");
        update.setPath("layout[0].toggled");
        update.setData(true);

        assertTrue(controller.handleInput(update));
        assertEquals(false, first.getValue());
        assertEquals(true, second.getValue());

        controller.tick();

        verifyNoMoreInteractions(player);
        clearInvocations(player);

        second.setValue(false);

        assertEquals(false, toggle.isToggled());
    }

    @Test
    void shouldRouteSliderInputToReboundObservable() {
        var first = DDUI.observable(0L);
        var second = DDUI.observable(0L);
        var screen = DDUI.customForm();
        var slider = screen.slider("Volume", 0L, 10L, first);
        controller.view(screen);
        clearInvocations(player);

        slider.setValue(second);

        var update = new DataStoreUpdate();
        update.setDataStoreName("minecraft");
        update.setProperty("custom_form_data");
        update.setPath("layout[0].value");
        update.setData(5.0D);

        assertTrue(controller.handleInput(update));
        assertEquals(0L, first.getValue());
        assertEquals(5L, second.getValue());

        controller.tick();

        verifyNoMoreInteractions(player);
        clearInvocations(player);

        second.setValue(7L);

        assertEquals(7L, slider.getValueLong());
    }

    @Test
    void shouldRouteDropdownInputToReboundObservable() {
        var first = DDUI.observable(0L);
        var second = DDUI.observable(0L);
        var screen = DDUI.customForm();
        var dropdown = screen.dropdown("Choice", List.of(
                new org.allaymc.api.ddui.element.DropdownElement.Item("A"),
                new org.allaymc.api.ddui.element.DropdownElement.Item("B"),
                new org.allaymc.api.ddui.element.DropdownElement.Item("C")
        ), first);
        controller.view(screen);
        clearInvocations(player);

        dropdown.setSelectedIndex(second);

        var update = new DataStoreUpdate();
        update.setDataStoreName("minecraft");
        update.setProperty("custom_form_data");
        update.setPath("layout[0].value");
        update.setData(2.0D);

        assertTrue(controller.handleInput(update));
        assertEquals(0L, first.getValue());
        assertEquals(2L, second.getValue());

        controller.tick();

        verifyNoMoreInteractions(player);
        clearInvocations(player);

        second.setValue(1L);

        assertEquals(1L, dropdown.getSelectedIndex());
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
    void shouldClearLeafUpdatesAfterSendingFullRefresh() {
        var title = DDUI.observable("Initial");
        var frame = DDUI.observable("Frame A\nLine 2");
        var screen = DDUI.customForm().title(title);
        screen.label(frame);
        controller.view(screen);
        clearInvocations(player);

        frame.setValue("Frame B\nLine 2");
        title.setValue("Updated");
        controller.tick();

        var captor = ArgumentCaptor.forClass(Object.class);
        verify(player).sendPacket(captor.capture());

        var packet = assertInstanceOf(ClientboundDataStorePacket.class, captor.getValue());
        assertEquals(1, packet.getUpdates().size());

        var change = assertInstanceOf(DataStoreChange.class, packet.getUpdates().getFirst());
        var payload = assertInstanceOf(Map.class, change.getNewValue());
        assertEquals("Updated", payload.get("title"));
        var layout = assertInstanceOf(Map.class, payload.get("layout"));
        var label = assertInstanceOf(Map.class, layout.get("0"));
        assertEquals("Frame B\nLine 2", label.get("text"));

        clearInvocations(player);

        controller.tick();

        verifyNoMoreInteractions(player);
    }

    @Test
    void shouldRouteNumericInputWithoutEchoingWireDoubleValue() {
        var clicks = new AtomicInteger();
        var screen = DDUI.messageBox()
                .title("Confirm")
                .body("Proceed")
                .button1("Yes", it -> clicks.incrementAndGet())
                .screen();
        controller.view(screen);
        clearInvocations(player);

        var update = new DataStoreUpdate();
        update.setDataStoreName("minecraft");
        update.setProperty("message_box_data");
        update.setPath("button1.onClick");
        update.setData(1.0D);

        assertTrue(controller.handleInput(update));
        assertEquals(1, clicks.get());

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

        var captor = ArgumentCaptor.forClass(Object.class);
        verify(player).sendPacket(captor.capture());
        var closePacket = assertInstanceOf(ClientboundDataDrivenUICloseScreenPacket.class, captor.getValue());
        assertNull(closePacket.getFormId());
        assertNull(controller.getActiveScreen());

        clearInvocations(player);
        title.setValue("Changed");
        controller.tick();
        verifyNoMoreInteractions(player);
    }

    @Test
    void shouldStopSendingUpdatesAfterDisposingScreenBindings() {
        var title = DDUI.observable("Initial");
        var screen = DDUI.customForm().title(title);
        controller.view(screen);
        clearInvocations(player);

        screen.dispose();
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
    void shouldAllowSettingMissingMessageBoxTextAfterAttach() {
        var screen = DDUI.messageBox();
        screen.button1("Yes", it -> {
        });
        controller.view(screen);
        clearInvocations(player);

        screen.title("Confirm");
        screen.body("Proceed");
        controller.tick();

        var captor = ArgumentCaptor.forClass(Object.class);
        verify(player).sendPacket(captor.capture());

        var packet = assertInstanceOf(ClientboundDataStorePacket.class, captor.getValue());
        assertEquals(2, packet.getUpdates().size());

        var titleUpdate = assertInstanceOf(DataStoreUpdate.class, packet.getUpdates().get(0));
        var bodyUpdate = assertInstanceOf(DataStoreUpdate.class, packet.getUpdates().get(1));
        assertEquals("title", titleUpdate.getPath());
        assertEquals("Confirm", titleUpdate.getData());
        assertEquals("body", bodyUpdate.getPath());
        assertEquals("Proceed", bodyUpdate.getData());
    }

    @Test
    void shouldAllowSettingMissingMessageBoxTooltipAfterAttach() {
        var screen = DDUI.messageBox();
        var button = screen.button1("Yes", it -> {
        });
        controller.view(screen);
        clearInvocations(player);

        button.setToolTip("More info");
        controller.tick();

        var captor = ArgumentCaptor.forClass(Object.class);
        verify(player).sendPacket(captor.capture());

        var packet = assertInstanceOf(ClientboundDataStorePacket.class, captor.getValue());
        var update = assertInstanceOf(DataStoreUpdate.class, packet.getUpdates().getFirst());
        assertEquals("button1.tooltip", update.getPath());
        assertEquals("More info", update.getData());
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

    @Test
    void shouldIgnoreLateClosePacketFromPreviousScreen() {
        var firstScreen = DDUI.customForm().title("First");
        controller.view(firstScreen);

        var firstCaptor = ArgumentCaptor.forClass(Object.class);
        verify(player, times(2)).sendPacket(firstCaptor.capture());
        var firstPackets = firstCaptor.getAllValues();
        var firstShowPacket = assertInstanceOf(ClientboundDataDrivenUIShowScreenPacket.class, firstPackets.get(1));

        clearInvocations(player);

        var secondTitle = DDUI.observable("Second");
        var secondScreen = DDUI.customForm().title(secondTitle);
        controller.view(secondScreen);

        var secondCaptor = ArgumentCaptor.forClass(Object.class);
        verify(player, times(2)).sendPacket(secondCaptor.capture());
        var secondPackets = secondCaptor.getAllValues();
        var secondShowPacket = assertInstanceOf(ClientboundDataDrivenUIShowScreenPacket.class, secondPackets.get(1));

        assertNotEquals(firstShowPacket.getFormId(), secondShowPacket.getFormId());

        clearInvocations(player);

        assertNull(controller.removeActiveScreen(firstShowPacket.getFormId()));
        assertSame(secondScreen, controller.getActiveScreen());

        secondTitle.setValue("Still active");
        controller.tick();

        var updateCaptor = ArgumentCaptor.forClass(Object.class);
        verify(player).sendPacket(updateCaptor.capture());

        var packet = assertInstanceOf(ClientboundDataStorePacket.class, updateCaptor.getValue());
        var update = assertInstanceOf(DataStoreUpdate.class, packet.getUpdates().getFirst());
        assertEquals("title", update.getPath());
        assertEquals("Still active", update.getData());
    }

    @Test
    void shouldRejectAddingElementAfterScreenIsAttached() {
        var screen = DDUI.customForm().title("Profile");
        controller.view(screen);

        assertThrows(IllegalStateException.class, () -> screen.button("Late", it -> {
        }));
    }

    @Test
    void shouldAllowUpdatingPrimitivePropertyAfterScreenIsAttached() {
        var screen = DDUI.customForm().title("Profile");
        controller.view(screen);
        clearInvocations(player);

        screen.title("Updated");
        controller.tick();

        var captor = ArgumentCaptor.forClass(Object.class);
        verify(player).sendPacket(captor.capture());

        var packet = assertInstanceOf(ClientboundDataStorePacket.class, captor.getValue());
        var update = assertInstanceOf(DataStoreUpdate.class, packet.getUpdates().getFirst());
        assertEquals("title", update.getPath());
        assertEquals("Updated", update.getData());
    }
}
