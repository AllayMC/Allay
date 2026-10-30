package org.allaymc.api.dialog;

import org.allaymc.api.entity.Entity;
import org.allaymc.api.form.type.Form;
import org.allaymc.api.utils.tuple.Pair;
import org.jetbrains.annotations.ApiStatus;

/**
 * {@code DialogViewer} displays entity dialogs and tracks the current dialog with its rendered
 * entity. New presentations can support transitions between menus, while direct removal only
 * changes server bookkeeping and explicit closure also sends a client close request.
 *
 * @author daoge_cmd
 */
public interface DialogViewer {
    /**
     * Displays the supplied dialog with the supplied entity and replaces the viewer's tracked
     * dialog pair. The same mutable dialog is retained for button processing; the previous dialog's
     * close callback is not invoked here.
     */
    void viewDialog(Dialog dialog, Entity entity);

    /**
     * Returns the currently tracked dialog and the entity used for its model. The result is
     * {@code null} when none is tracked, and the pair retains the original references.
     */
    Pair<Dialog, Entity> getDialog();

    /**
     * Removes the tracked dialog without sending a close packet or invoking its callback. Runtime
     * request handling uses this bookkeeping operation before dispatching a final client-close
     * callback.
     */
    @ApiStatus.Internal
    void removeDialog();

    /**
     * Sends a close request for the tracked dialog and removes the server reference. An absent
     * dialog does nothing; this operation does not directly invoke the dialog's close callback.
     */
    void closeDialog();
}
