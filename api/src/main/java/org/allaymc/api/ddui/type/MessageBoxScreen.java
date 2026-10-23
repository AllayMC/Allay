package org.allaymc.api.ddui.type;

import org.allaymc.api.ddui.*;
import org.jetbrains.annotations.ApiStatus;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * {@code MessageBoxScreen} represents a DDUI message-box template with a title, body, and two
 * labeled response buttons. Properties accept literal values or observable bindings that runtime
 * sessions subscribe to when they compile this template.
 *
 * <p>
 * A runtime session records the selected button before invoking {@code onButton1} or
 * {@code onButton2}, then invokes {@code onResponse}, then closes silently. A callback exception
 * prevents later steps, while the recorded selection suppresses another response and the ordinary
 * close callback. Callback configuration remains on this shared template, so existing sessions
 * dispatch through the current handlers rather than copying them at compilation.
 *
 * @author daoge_cmd | SerenityJS
 */
public final class MessageBoxScreen extends DDUIScreen {
    /**
     * Bindable screen title property.
     */
    private final Property<String> title = new Property<>("");
    /**
     * Bindable screen body property.
     */
    private final Property<String> body = new Property<>("");
    /**
     * Bindable first button label property.
     */
    private final Property<String> button1 = new Property<>("");
    /**
     * Bindable second button label property.
     */
    private final Property<String> button2 = new Property<>("");
    /**
     * {@code onButton1} receives selection of the first button. A callback exception propagates and
     * prevents subsequent response processing.
     */
    private Consumer<DDUIScreenSession> onButton1 = session -> {
    };
    /**
     * {@code onButton2} receives selection of the second button. A callback exception propagates
     * and prevents subsequent response processing.
     */
    private Consumer<DDUIScreenSession> onButton2 = session -> {
    };
    /**
     * {@code onResponse} receives either selected button after its specific callback. A callback
     * exception propagates and prevents subsequent response processing.
     */
    private BiConsumer<DDUIScreenSession, MessageBoxResult> onResponse = (session, result) -> {
    };

    /**
     * Returns the mutable {@code title} property used when this template is compiled. Observable
     * bindings supply live session updates; literal changes to the template do not update already
     * compiled sessions.
     */
    public Property<String> getTitle() {
        return title;
    }

    /**
     * Returns the mutable {@code body} property used when this template is compiled. Observable
     * bindings supply live session updates; literal changes to the template do not update already
     * compiled sessions.
     */
    public Property<String> getBody() {
        return body;
    }

    /**
     * Returns the mutable {@code button1} property used when this template is compiled. Observable
     * bindings supply live session updates; literal changes to the template do not update already
     * compiled sessions.
     */
    public Property<String> getButton1() {
        return button1;
    }

    /**
     * Returns the mutable {@code button2} property used when this template is compiled. Observable
     * bindings supply live session updates; literal changes to the template do not update already
     * compiled sessions.
     */
    public Property<String> getButton2() {
        return button2;
    }

    /**
     * Stores the literal {@code title} template property and returns this screen for further
     * configuration. This clears its observable binding and does not send a property update to
     * existing sessions.
     */
    public MessageBoxScreen title(String title) {
        this.title.set(title);
        return this;
    }

    /**
     * Binds the {@code title} template property to the supplied observable and returns this screen.
     * Newly compiled sessions subscribe to that source; existing sessions retain the source
     * compiled when they opened.
     */
    public MessageBoxScreen title(Observable<String> title) {
        this.title.bind(title);
        return this;
    }

    /**
     * Stores the literal {@code body} template property and returns this screen for further
     * configuration. This clears its observable binding and does not send a property update to
     * existing sessions.
     */
    public MessageBoxScreen body(String body) {
        this.body.set(body);
        return this;
    }

    /**
     * Binds the {@code body} template property to the supplied observable and returns this screen.
     * Newly compiled sessions subscribe to that source; existing sessions retain the source
     * compiled when they opened.
     */
    public MessageBoxScreen body(Observable<String> body) {
        this.body.bind(body);
        return this;
    }

    /**
     * Stores the literal {@code button1} template property and returns this screen for further
     * configuration. This clears its observable binding and does not send a property update to
     * existing sessions.
     */
    public MessageBoxScreen button1(String label) {
        this.button1.set(label);
        return this;
    }

    /**
     * Stores the first button's literal label and callback, then returns this template. The label
     * replaces its observable binding; callback failures propagate during response handling and
     * prevent subsequent response steps.
     */
    public MessageBoxScreen button1(String label, Consumer<DDUIScreenSession> onButton1) {
        return button1(label).onButton1(onButton1);
    }

    /**
     * Binds the {@code button1} template property to the supplied observable and returns this
     * screen. Newly compiled sessions subscribe to that source; existing sessions retain the source
     * compiled when they opened.
     */
    public MessageBoxScreen button1(Observable<String> label) {
        this.button1.bind(label);
        return this;
    }

    /**
     * Stores the literal {@code button2} template property and returns this screen for further
     * configuration. This clears its observable binding and does not send a property update to
     * existing sessions.
     */
    public MessageBoxScreen button2(String label) {
        this.button2.set(label);
        return this;
    }

    /**
     * Stores the second button's literal label and callback, then returns this template. The label
     * replaces its observable binding; callback failures propagate during response handling and
     * prevent subsequent response steps.
     */
    public MessageBoxScreen button2(String label, Consumer<DDUIScreenSession> onButton2) {
        return button2(label).onButton2(onButton2);
    }

    /**
     * Binds the {@code button2} template property to the supplied observable and returns this
     * screen. Newly compiled sessions subscribe to that source; existing sessions retain the source
     * compiled when they opened.
     */
    public MessageBoxScreen button2(Observable<String> label) {
        this.button2.bind(label);
        return this;
    }

    /**
     * Stores the first button callback and returns this screen template. A session invokes it
     * before the shared response callback, and an exception prevents that callback and subsequent
     * silent closure.
     */
    public MessageBoxScreen onButton1(Consumer<DDUIScreenSession> onButton1) {
        this.onButton1 = onButton1;
        return this;
    }

    /**
     * Stores the second button callback and returns this screen template. A session invokes it
     * before the shared response callback, and an exception prevents that callback and subsequent
     * silent closure.
     */
    public MessageBoxScreen onButton2(Consumer<DDUIScreenSession> onButton2) {
        this.onButton2 = onButton2;
        return this;
    }

    /**
     * Stores the shared response callback and returns this screen template. A session invokes it
     * after the selected button's specific callback; an exception prevents the session's subsequent
     * silent close operation.
     */
    public MessageBoxScreen onResponse(BiConsumer<DDUIScreenSession, MessageBoxResult> onResponse) {
        this.onResponse = onResponse;
        return this;
    }

    /**
     * Invokes the configured first button callback with the responding session. Exceptions
     * propagate to the runtime session; this dispatch does not invoke the shared response callback
     * or close the screen itself.
     */
    @ApiStatus.Internal
    public void handleButton1(DDUIScreenSession session) {
        onButton1.accept(session);
    }

    /**
     * Invokes the configured second button callback with the responding session. Exceptions
     * propagate to the runtime session; this dispatch does not invoke the shared response callback
     * or close the screen itself.
     */
    @ApiStatus.Internal
    public void handleButton2(DDUIScreenSession session) {
        onButton2.accept(session);
    }

    /**
     * Invokes the shared response callback with the session and selected button result. Exceptions
     * propagate to the runtime session; this dispatch performs no screen closure or callback
     * recovery itself.
     */
    @ApiStatus.Internal
    public void handleResponse(DDUIScreenSession session, MessageBoxResult result) {
        onResponse.accept(session, result);
    }

    /**
     * Stores the inherited close callback and returns this message-box template for configuration.
     * A recorded button selection suppresses normal close reporting, including when a response
     * callback fails before silent closure.
     */
    @Override
    public MessageBoxScreen onClose(BiConsumer<DDUIScreenSession, DDUIScreenCloseReason> onClose) {
        return (MessageBoxScreen) super.onClose(onClose);
    }

    /**
     * Stores the inherited close callback and returns this message-box template for configuration.
     * A recorded button selection suppresses normal close reporting, including when a response
     * callback fails before silent closure.
     */
    @Override
    public MessageBoxScreen onClose(Runnable onClose) {
        return (MessageBoxScreen) super.onClose(onClose);
    }
}
