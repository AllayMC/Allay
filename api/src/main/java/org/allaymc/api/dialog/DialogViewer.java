package org.allaymc.api.dialog;

import org.allaymc.api.entity.Entity;
import org.allaymc.api.form.type.Form;
import org.allaymc.api.utils.tuple.Pair;
import org.jetbrains.annotations.ApiStatus;

/**
 * DialogViewer represents an object that can view and interact with a {@link Dialog}.
 *
 * @author daoge_cmd
 */
public interface DialogViewer {
    /**
     * Views a dialog, using the entity passed as the entity that the dialog is shown for. Dialogs can be viewed
     * on top of each other without the other closing, making it possible to have non-flashing transitions between
     * menus compared to {@link Form}. The viewer can either press one of the buttons or close the dialog. It is
     * impossible for a dialog to have any more than 6 buttons.
     *
     * @param dialog the dialog to be viewed
     * @param entity the entity that the dialog is shown for
     */
    void viewDialog(Dialog dialog, Entity entity);

    /**
     * Retrieves the dialog currently being viewed along with the associated entity.
     *
     * @return a {@link Pair} containing the current {@link Dialog} being viewed and the {@link Entity} it is shown for,
     * or {@code null} if no dialog is currently being viewed.
     */
    Pair<Dialog, Entity> getDialog();

    /**
     * Removes the currently active dialog being viewed by the {@link DialogViewer} directly. This method won't send any
     * packet to the client.
     */
    @ApiStatus.Internal
    void removeDialog();

    /**
     * Notifies the client to close the currently active dialog if one is being viewed.
     */
    void closeDialog();
}
