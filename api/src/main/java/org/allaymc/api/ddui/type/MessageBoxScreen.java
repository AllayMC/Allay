package org.allaymc.api.ddui.type;

import org.allaymc.api.ddui.DDUIScreenCloseReason;
import org.allaymc.api.ddui.MessageBoxResult;
import org.allaymc.api.ddui.Observable;
import org.allaymc.api.ddui.internal.BindableValue;
import org.allaymc.api.ddui.session.DDUIScreenSession;
import org.jetbrains.annotations.ApiStatus;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * A DDUI message-box screen.
 *
 * @author daoge_cmd | SerenityJS
 */
public final class MessageBoxScreen extends DDUIScreen {
    private final BindableValue<String> title = new BindableValue<>("");
    private final BindableValue<String> body = new BindableValue<>("");
    private final BindableValue<String> button1 = new BindableValue<>("");
    private final BindableValue<String> button2 = new BindableValue<>("");
    private Consumer<DDUIScreenSession> onButton1 = session -> {
    };
    private Consumer<DDUIScreenSession> onButton2 = session -> {
    };
    private BiConsumer<DDUIScreenSession, MessageBoxResult> onResponse = (session, result) -> {
    };

    /**
     * Gets the title text.
     *
     * @return the title text
     */
    public String getTitle() {
        return title.value();
    }

    /**
     * Gets the title observable.
     *
     * @return the title observable, or {@code null} if the title is not observable-backed
     */
    public Observable<String> getTitleObservable() {
        return title.observable();
    }

    /**
     * Gets the body text.
     *
     * @return the body text
     */
    public String getBody() {
        return body.value();
    }

    /**
     * Gets the body observable.
     *
     * @return the body observable, or {@code null} if the body is not observable-backed
     */
    public Observable<String> getBodyObservable() {
        return body.observable();
    }

    /**
     * Gets the first button label.
     *
     * @return the first button label
     */
    public String getButton1() {
        return button1.value();
    }

    /**
     * Gets the first button observable.
     *
     * @return the first button observable, or {@code null} if the label is not observable-backed
     */
    public Observable<String> getButton1Observable() {
        return button1.observable();
    }

    /**
     * Gets the second button label.
     *
     * @return the second button label
     */
    public String getButton2() {
        return button2.value();
    }

    /**
     * Gets the second button observable.
     *
     * @return the second button observable, or {@code null} if the label is not observable-backed
     */
    public Observable<String> getButton2Observable() {
        return button2.observable();
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
