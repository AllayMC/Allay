package org.allaymc.api.ddui.element;

import org.allaymc.api.ddui.internal.ObjectProperty;

/**
 * DDUI spacer element used to separate sections in custom forms.
 *
 * @author Miroshka
 */
public final class SpacerElement extends CustomFormElement {

    public SpacerElement(ObjectProperty<?> parent) {
        super("spacer", parent);
        setVisibility(true);
    }
}
