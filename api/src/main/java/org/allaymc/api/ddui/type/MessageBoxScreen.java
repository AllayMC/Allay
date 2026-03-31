package org.allaymc.api.ddui.type;

import org.allaymc.api.ddui.*;
import org.jetbrains.annotations.ApiStatus;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * A DDUI message-box screen.
 *
 * @author daoge_cmd | SerenityJS
 */
public final class MessageBoxScreen extends DDUIScreen {
    private final Property<String> title = new Property<>("");
    private final Property<String> body = new Property<>("");
    private final Property<String> button1 = new Property<>("");
    private final Property<String> button2 = new Property<>("");
    private Consumer<DDUIScreenSession> onButton1 = session -> {
    };
    private Consumer<DDUIScreenSession> onButton2 = session -> {
    };
    private BiConsumer<DDUIScreenSession, MessageBoxResult> onResponse = (session, result) -> {
    };

    /**
     * Gets the bindable title property of this screen.
     *
     * @return the title property
     */
    public Property<String> getTitle() {
        return title;
    }

    /**
     * Gets the bindable body property of this screen.
     *
     * @return the body property
     */
    public Property<String> getBody() {
        return body;
    }

    /**
     * Gets the bindable first-button label property.
     *
     * @return the first-button label property
     */
    public Property<String> getButton1() {
        return button1;
    }

    /**
     * Gets the bindable second-button label property.
     *
     * @return the second-button label property
     */
    public Property<String> getButton2() {
        return button2;
    }

    /**
     * Sets the title text.
     *
     * @param title the title text
     * @return this screen
     */
    public MessageBoxScreen title(String title) {
        this.title.set(title);
        return this;
    }

    /**
     * Binds the title text to an observable.
     *
     * @param title the title observable
     * @return this screen
     */
    public MessageBoxScreen title(Observable<String> title) {
        this.title.bind(title);
        return this;
    }

    /**
     * Sets the body text.
     *
     * @param body the body text
     * @return this screen
     */
    public MessageBoxScreen body(String body) {
        this.body.set(body);
        return this;
    }

    /**
     * Binds the body text to an observable.
     *
     * @param body the body observable
     * @return this screen
     */
    public MessageBoxScreen body(Observable<String> body) {
        this.body.bind(body);
        return this;
    }

    /**
     * Sets the first button label.
     *
     * @param label the first button label
     * @return this screen
     */
    public MessageBoxScreen button1(String label) {
        this.button1.set(label);
        return this;
    }

    /**
     * Sets the first button label and click callback.
     *
     * @param label     the first button label
     * @param onButton1 the callback to run when the first button is selected
     * @return this screen
     */
    public MessageBoxScreen button1(String label, Consumer<DDUIScreenSession> onButton1) {
        return button1(label).onButton1(onButton1);
    }

    /**
     * Binds the first button label to an observable.
     *
     * @param label the first button observable
     * @return this screen
     */
    public MessageBoxScreen button1(Observable<String> label) {
        this.button1.bind(label);
        return this;
    }

    /**
     * Sets the second button label.
     *
     * @param label the second button label
     * @return this screen
     */
    public MessageBoxScreen button2(String label) {
        this.button2.set(label);
        return this;
    }

    /**
     * Sets the second button label and click callback.
     *
     * @param label     the second button label
     * @param onButton2 the callback to run when the second button is selected
     * @return this screen
     */
    public MessageBoxScreen button2(String label, Consumer<DDUIScreenSession> onButton2) {
        return button2(label).onButton2(onButton2);
    }

    /**
     * Binds the second button label to an observable.
     *
     * @param label the second button observable
     * @return this screen
     */
    public MessageBoxScreen button2(Observable<String> label) {
        this.button2.bind(label);
        return this;
    }

    /**
     * Sets the callback for the first button.
     *
     * @param onButton1 the callback to run when the first button is selected
     * @return this screen
     */
    public MessageBoxScreen onButton1(Consumer<DDUIScreenSession> onButton1) {
        this.onButton1 = onButton1;
        return this;
    }

    /**
     * Sets the callback for the second button.
     *
     * @param onButton2 the callback to run when the second button is selected
     * @return this screen
     */
    public MessageBoxScreen onButton2(Consumer<DDUIScreenSession> onButton2) {
        this.onButton2 = onButton2;
        return this;
    }

    /**
     * Sets the callback that runs after either button is selected.
     *
     * @param onResponse the response callback
     * @return this screen
     */
    public MessageBoxScreen onResponse(BiConsumer<DDUIScreenSession, MessageBoxResult> onResponse) {
        this.onResponse = onResponse;
        return this;
    }

    /**
     * Invokes the first-button callback.
     *
     * @param session the responding session
     */
    @ApiStatus.Internal
    public void handleButton1(DDUIScreenSession session) {
        onButton1.accept(session);
    }

    /**
     * Invokes the second-button callback.
     *
     * @param session the responding session
     */
    @ApiStatus.Internal
    public void handleButton2(DDUIScreenSession session) {
        onButton2.accept(session);
    }

    /**
     * Invokes the response callback.
     *
     * @param session the responding session
     * @param result  the selected button
     */
    @ApiStatus.Internal
    public void handleResponse(DDUIScreenSession session, MessageBoxResult result) {
        onResponse.accept(session, result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MessageBoxScreen onClose(BiConsumer<DDUIScreenSession, DDUIScreenCloseReason> onClose) {
        return (MessageBoxScreen) super.onClose(onClose);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MessageBoxScreen onClose(Runnable onClose) {
        return (MessageBoxScreen) super.onClose(onClose);
    }
}
