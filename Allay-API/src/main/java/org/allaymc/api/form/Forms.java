package org.allaymc.api.form;

import org.allaymc.api.form.type.Custom;
import org.allaymc.api.form.type.Modal;
import org.allaymc.api.form.type.Simple;

/**
 * Allay Project 2024/2/26
 *
 * @author daoge_cmd
 */
public interface Forms {
    static Simple simple() {
        return new Simple();
    }

    static Modal modal() {
        return new Modal();
    }

    static Custom custom() {
        return new Custom();
    }
}
