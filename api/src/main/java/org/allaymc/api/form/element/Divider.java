package org.allaymc.api.form.element;

/**
 * @author daoge_cmd
 */
public final class Divider implements SimpleFormElement, CustomFormElement {

    @SuppressWarnings("unused")
    private final String type = "divider";

    // NOTICE: Useless but if we do not add it, the form will be rejected by client
    private final String text = "";

    /**
     * Create a new divider.
     */
    public Divider() {
    }

    @Override
    public void syncDefaultValueToResponse(String response) {
        // Do nothing
    }
}
