package org.allaymc.api.ddui.element;

import org.allaymc.api.ddui.Observable;

/**
 * Shared base implementation for static-text DDUI elements.
 *
 * @param <E> the concrete element type
 *
 * @author OpenAI
 */
abstract class TextElementBase<E extends TextElementBase<E>> extends ElementBase<E> {
    private String text = "";
    private Observable<String> textObservable;

    /**
     * Gets the text shown by this element.
     *
     * @return the element text
     */
    public String getText() {
        return text;
    }

    /**
     * Gets the observable bound to this element's text.
     *
     * @return the text observable, or {@code null} if the text is not observable-backed
     */
    public Observable<String> getTextObservable() {
        return textObservable;
    }

    /**
     * Sets the text shown by this element.
     *
     * @param text the element text
     * @return this element
     */
    public E text(String text) {
        this.text = text;
        this.textObservable = null;
        return self();
    }

    /**
     * Binds this element's text to an observable.
     *
     * @param text the text observable
     * @return this element
     */
    public E text(Observable<String> text) {
        this.text = text.get();
        this.textObservable = text;
        return self();
    }
}
