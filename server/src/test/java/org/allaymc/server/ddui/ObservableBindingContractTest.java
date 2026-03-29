package org.allaymc.server.ddui;

import org.allaymc.api.ddui.BindingScope;
import org.allaymc.api.ddui.DDUI;
import org.allaymc.api.ddui.Observable;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests for DDUI observable, binding suppression and disposal behavior.
 *
 * @author Miroshka
 */
class ObservableBindingContractTest {

    @Test
    void shouldCancelObservableBindingSubscription() {
        var observable = DDUI.observable("initial");
        var notifications = new AtomicInteger();

        var subscription = observable.bind(value -> notifications.incrementAndGet());
        observable.setValue("first");
        subscription.cancel();
        observable.setValue("second");

        assertEquals(1, notifications.get());
    }

    @Test
    void shouldSuppressNestedObservableNotifications() {
        var observable = DDUI.observable("initial");
        var notifications = new AtomicInteger();

        observable.bind(value -> notifications.incrementAndGet());

        BindingScope.suppressed(() -> {
            observable.setValue("first");
            BindingScope.suppressed(() -> observable.setValue("second"));
        });

        assertEquals("second", observable.getValue());
        assertEquals(0, notifications.get());

        observable.setValue("third");

        assertEquals(1, notifications.get());
    }

    @Test
    void shouldReplaceScreenTitleBindingWithStaticValue() {
        var title = DDUI.observable("bound");
        var screen = DDUI.customForm().title(title);

        screen.title("static");
        title.setValue("updated");

        assertEquals("static", wire(screen).get("title"));
    }

    @Test
    void shouldReplaceBoundButtonVisibilityWithStaticValue() {
        var visible = DDUI.observable(true);
        var screen = DDUI.customForm().button("Save", player -> {
        }).screen();
        var button = screen.getLayout().getProperty("0");
        assertTrue(button instanceof org.allaymc.api.ddui.element.ButtonElement);
        ((org.allaymc.api.ddui.element.ButtonElement) button).setVisibility(visible);

        visible.setValue(false);
        assertFalse(layoutEntry(screen, 0).get("button_visible").equals(Boolean.TRUE));

        ((org.allaymc.api.ddui.element.ButtonElement) button).setVisibility(true);
        visible.setValue(false);

        assertEquals(Boolean.TRUE, layoutEntry(screen, 0).get("button_visible"));
    }

    @Test
    void shouldReplaceTooltipBindingWithAnotherObservable() {
        var first = DDUI.observable("first");
        var second = DDUI.observable("second");
        var button = DDUI.customForm().button("Save", player -> {
        });

        button.setToolTip(first);
        first.setValue("from-first");
        button.setToolTip(second);
        first.setValue("stale");
        second.setValue("from-second");

        assertEquals("from-second", button.getToolTip());
    }

    @Test
    void shouldDisposeScreenBindingsRecursively() {
        var title = DDUI.observable("title");
        var tooltip = DDUI.observable("tooltip");
        var screen = DDUI.customForm().title(title);
        screen.button("Save", player -> {
        }).setToolTip(tooltip);

        screen.dispose();
        title.setValue("updated-title");
        tooltip.setValue("updated-tooltip");

        assertEquals("title", wire(screen).get("title"));
        assertEquals("tooltip", layoutEntry(screen, 0).get("tooltip"));
    }

    @SuppressWarnings("unchecked")
    private static Map<String, Object> wire(org.allaymc.api.ddui.DataDrivenScreen screen) {
        return (Map<String, Object>) screen.toWireValue();
    }

    @SuppressWarnings("unchecked")
    private static Map<String, Object> layoutEntry(org.allaymc.api.ddui.DataDrivenScreen screen, int index) {
        var layout = (Map<String, Object>) wire(screen).get("layout");
        return (Map<String, Object>) layout.get(String.valueOf(index));
    }
}
