package org.allaymc.api.form.element;

/**
 * {@code SimpleFormElement} identifies entries accepted by the simple-form layout. Buttons provide
 * selectable actions, while labels, headers, and dividers provide decorative content; response
 * handling counts only button entries when mapping a returned selection to the retained element
 * list.
 *
 * @author daoge_cmd
 */
public sealed interface SimpleFormElement permits Button, Divider, Header, Label {
}
