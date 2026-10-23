package org.allaymc.api.form;

import org.allaymc.api.form.type.CustomForm;
import org.allaymc.api.form.type.ModalForm;
import org.allaymc.api.form.type.SimpleForm;

/**
 * {@code Forms} creates JSON form builders for the supported client layouts. Simple forms present
 * selectable buttons, modal forms present two choices, and custom forms collect control values;
 * callers configure the returned instance before displaying it through a form viewer.
 *
 * @author daoge_cmd
 */
public interface Forms {
    /**
     * Creates a new simple form builder with its default configuration.
     */
    static SimpleForm simple() {
        return new SimpleForm();
    }

    /**
     * Creates a new modal form builder with its default configuration.
     */
    static ModalForm modal() {
        return new ModalForm();
    }

    /**
     * Creates a new custom form builder with its default configuration.
     */
    static CustomForm custom() {
        return new CustomForm();
    }
}
