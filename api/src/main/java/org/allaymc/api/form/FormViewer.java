package org.allaymc.api.form;

import org.allaymc.api.form.type.CustomForm;
import org.allaymc.api.form.type.Form;
import org.allaymc.api.utils.tuple.Pair;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.Map;

/**
 * FormViewer represents an object that can view and respond to a form.
 *
 * @author daoge_cmd
 */
public interface FormViewer {

    /**
     * Gets the server setting form that the viewer is having currently.
     *
     * @return a pair of the form's id and the form, or {@code null} if the viewer doesn't
     * have any active server setting form.
     */
    Pair<Integer, CustomForm> getServerSettingForm();

    /**
     * Sets a server setting form to the viewer.
     *
     * @param form the form to set
     * @return the assigned form id
     */
    int setServerSettingForm(CustomForm form);

    /**
     * Removes the server setting form that the viewer is having currently.
     *
     * @return a pair of the form's id and the form, or {@code null} if the viewer doesn't
     * have any active server setting form.
     */
    Pair<Integer, CustomForm> removeServerSettingForm();

    /**
     * Views a form.
     *
     * @param form the form to view
     * @return the assigned form id
     */
    int viewForm(Form form);

    /**
     * Get the forms that the viewer is viewing currently.
     *
     * @return the forms that the viewer is viewing currently
     */
    @UnmodifiableView
    Map<Integer, Form> getForms();

    /**
     * Removes a form with the specified form ID that the viewer is currently viewing. This method won't
     * send any packet to the client.
     *
     * @param formId the ID of the form to be removed
     * @return the removed form if it exists, or {@code null} if no form with the specified ID is found
     */
    @ApiStatus.Internal
    Form removeForm(int formId);

    /**
     * Closes all the forms that the viewer is viewing currently.
     */
    void closeAllForms();
}
