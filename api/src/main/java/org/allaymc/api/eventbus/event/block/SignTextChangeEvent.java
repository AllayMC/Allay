package org.allaymc.api.eventbus.event.block;

import lombok.Getter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.event.CancellableEvent;

/**
 * {@code SignTextChangeEvent} represents a player's proposed replacement text for
 * one side of a sign. Handlers may cancel the edit or replace its text array
 * before the sign component applies it. The event retains array references and
 * accepts at most four lines.
 *
 * @author daoge_cmd
 */
@Getter
@CallerThread(ThreadType.WORLD)
public class SignTextChangeEvent extends BlockEvent implements CancellableEvent {
    /**
     * {@code text} holds the proposed lines for the edited sign side. The getter
     * exposes the retained array, and {@code setText} can replace that array.
     */
    protected String[] text;
    /**
     * Player proposing the sign text.
     */
    protected EntityPlayer editor;

    /**
     * Creates a sign edit proposal retaining its block, text array, and editor.
     * {@code setText} validates the line count during construction; later listener
     * changes remain visible when the sign component reads the event.
     */
    public SignTextChangeEvent(Block block, String[] text, EntityPlayer editor) {
        super(block);
        setText(text);
        this.editor = editor;
    }

    /**
     * Replaces the proposed text with the supplied array reference. Arrays may
     * contain zero through four lines; a longer array causes
     * {@code IllegalArgumentException}, and {@code null} causes
     * {@code NullPointerException} while reading its length.
     */
    public void setText(String[] text) {
        if (text.length > 4) {
            throw new IllegalArgumentException("Sign text must be 4 lines or less");
        }
        this.text = text;
    }
}
