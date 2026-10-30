package org.allaymc.api.form.type;

import com.google.gson.Gson;
import lombok.Getter;
import org.allaymc.api.form.FormCancelReason;
import org.allaymc.api.form.FormViewer;

import java.util.function.Consumer;

/**
 * {@code Form} represents a mutable JSON form and supplies common display, close-callback,
 * response, and serialization facilities. Concrete layouts interpret responses differently; runtime
 * form registrations retain the same instance, so callers coordinate any shared configuration and
 * callback access.
 *
 * @author daoge_cmd
 */
public abstract class Form {

    /**
     * {@code GSON} serializes concrete form instances and parses layout responses using the default
     * Gson configuration shared by form implementations.
     */
    protected static final Gson GSON = new Gson();

    /**
     * {@code onClose} receives cancellation reasons. The callback starts as a no-op, remains
     * outside serialized form data, and can propagate failures during cancellation handling.
     */
    protected transient Consumer<FormCancelReason> onClose = reason -> {
    };
    /**
     * {@code response} retains the most recently stored layout-specific response, or {@code null}
     * before storage; cancellation does not clear this value.
     */
    @Getter
    protected transient Object response;

    /**
     * Displays this form through the supplied viewer using a new ordinary form registration. The
     * viewer retains this same mutable instance for response dispatch, and this convenience
     * operation discards its assigned identifier.
     */
    public void sendTo(FormViewer viewer) {
        viewer.viewForm(this);
    }

    /**
     * Interprets the supplied response data and dispatches callbacks according to the concrete
     * layout. Cancellation is delivered separately through {@code handleClose}; parsing and
     * callback failures follow the concrete implementation's storage ordering.
     */
    public abstract void handleResponse(String data);

    /**
     * Dispatches a cancellation reason to this form's close callback. Concrete layouts preserve any
     * previously stored response, allowing server-settings synchronization to reuse earlier values
     * after cancellation.
     */
    public abstract void handleClose(FormCancelReason reason);

    /**
     * Stores a cancellation callback that ignores the supplied close reason and returns this form.
     * The action runs when cancellation is dispatched, and its exceptions propagate to the
     * dispatching caller.
     */
    public Form onClose(Runnable onClose) {
        return onClose(reason -> onClose.run());
    }

    /**
     * Stores the callback receiving form cancellation reasons and returns this form. This replaces
     * the previous callback; direct viewer bookkeeping removal and bulk closure do not invoke this
     * handler themselves.
     */
    public Form onClose(Consumer<FormCancelReason> onClose) {
        this.onClose = onClose;
        return this;
    }


    /**
     * Serializes this concrete form's current nontransient fields as JSON for client delivery.
     * {@code GSON} omits {@code null} object fields under its default configuration, including
     * optional image and tooltip references.
     */
    public String toJson() {
        return GSON.toJson(this);
    }
}
