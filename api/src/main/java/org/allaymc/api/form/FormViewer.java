package org.allaymc.api.form;

import org.allaymc.api.form.type.CustomForm;
import org.allaymc.api.form.type.Form;
import org.allaymc.api.utils.tuple.Pair;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.Map;

/**
 * {@code FormViewer} displays JSON forms and tracks their protocol identifiers for response
 * dispatch. Ordinary forms and the server-settings form use separate registrations; update
 * operations reuse an existing ordinary identifier, while bookkeeping removal does not itself close
 * a client screen.
 *
 * @author daoge_cmd
 */
public interface FormViewer {

    /**
     * Returns the registered server-settings form and its protocol identifier. The result is
     * {@code null} when none is registered, and it retains the same mutable form supplied during
     * registration.
     */
    Pair<Integer, CustomForm> getServerSettingForm();

    /**
     * Registers the server-settings form under a newly assigned protocol identifier and returns
     * that identifier. This replaces any previous settings registration; registration itself does
     * not send an ordinary form display packet.
     */
    int setServerSettingForm(CustomForm form);

    /**
     * Removes and returns the server-settings registration without sending a client packet. A
     * {@code null} result means no registration existed, and ordinary form registrations are
     * unaffected by this operation.
     */
    Pair<Integer, CustomForm> removeServerSettingForm();

    /**
     * Registers and displays the supplied form under a newly assigned protocol identifier. The
     * identifier associates a later response with this same mutable form, whose callbacks handle
     * the returned data.
     */
    int viewForm(Form form);

    /**
     * Resends the first registered ordinary form whose instance is identical to {@code form},
     * reusing its identifier. The result is {@code false} when no such registration exists,
     * including settings-only forms.
     */
    boolean updateForm(Form form);

    /**
     * Returns a live unmodifiable view of ordinary form registrations keyed by protocol identifier.
     * Forms remain mutable through their own API, and the separate server-settings registration is
     * absent from this map.
     */
    @UnmodifiableView
    Map<Integer, Form> getForms();

    /**
     * Removes and returns the ordinary form registered for {@code formId} without sending any
     * client packet. The result is {@code null} when no entry exists; response dispatch removes
     * entries before invoking callbacks.
     */
    @ApiStatus.Internal
    Form removeForm(int formId);

    /**
     * Requests closure of the viewer's forms and clears ordinary form registrations. This operation
     * does not invoke individual form callbacks or remove the separately registered server-settings
     * form.
     */
    void closeAllForms();
}
