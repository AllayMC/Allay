package org.allaymc.api.form;

import org.allaymc.api.form.type.CustomForm;
import org.allaymc.api.form.type.ModalForm;
import org.allaymc.api.form.type.SimpleForm;

/**
 * Form factory.
 *
 * @author daoge_cmd
 */
public interface Forms {
    /**
     * Create a simple form.
     *
     * @return the simple form.
     */
    static SimpleForm simple() {
        return new SimpleForm();
    }

    /**
     * Create a modal form.
     *
     * @return the modal form.
     */
    static ModalForm modal() {
        return new ModalForm();
    }

    /**
     * Create a custom form.
     *
     * @return the custom form.
     */
    static CustomForm custom() {
        return new CustomForm();
    }
}
