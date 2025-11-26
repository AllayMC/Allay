package org.allaymc.api.message;

import com.google.common.collect.Sets;
import org.allaymc.api.annotation.ThreadSafe;
import org.allaymc.api.command.CommandSender;

import java.util.List;
import java.util.Set;

/**
 * MessageChannel represents a channel that holds multiple {@link MessageReceiver}s and several methods
 * to broadcast different kinds of message to the receivers. All the methods inside should be thread-safe.
 */
@ThreadSafe
public class MessageChannel {

    protected Set<MessageReceiver> receivers;

    public MessageChannel() {
        this.receivers = Sets.newConcurrentHashSet();
    }

    /**
     * Adds a {@link MessageReceiver} to this channel.
     *
     * @param receiver the receiver to add
     */
    public void addReceiver(MessageReceiver receiver) {
        this.receivers.add(receiver);
    }

    /**
     * Removes a {@link MessageReceiver} from this channel.
     *
     * @param receiver the receiver to remove
     */
    public void removeReceiver(MessageReceiver receiver) {
        this.receivers.remove(receiver);
    }

    /**
     * Checks if the specified {@link MessageReceiver} is registered in this {@code MessageChannel}.
     *
     * @param receiver the {@code MessageReceiver} to check for existence
     * @return {@code true} if the receiver is registered, otherwise {@code false}
     */
    public boolean hasReceiver(MessageReceiver receiver) {
        return this.receivers.contains(receiver);
    }

    /**
     * Broadcasts a plain message to all registered receivers.
     *
     * @param message the plain message text to send
     */
    public void broadcastMessage(String message) {
        this.receivers.forEach(receiver -> receiver.sendMessage(message));
    }

    /**
     * Broadcasts a translatable message to all receivers.
     *
     * @param translatable the translatable message
     */
    public void broadcastTranslatable(@MayContainTrKey String translatable) {
        this.receivers.forEach(receiver -> receiver.sendTranslatable(translatable));
    }

    /**
     * Broadcasts a translatable message with formatting arguments to all receivers.
     *
     * @param translatable the translatable message
     * @param args         the formatting arguments applied by the receiver's localization system
     */
    public void broadcastTranslatable(@MayContainTrKey String translatable, Object... args) {
        this.receivers.forEach(receiver -> receiver.sendTranslatable(translatable, args));
    }

    /**
     * Broadcasts command outputs originating from a specific {@link CommandSender}.
     *
     * @param sender      the source that executed the command
     * @param status      status code of the command execution result
     * @param permissions the permissions required to view the command outputs
     * @param outputs     one or more translatable output containers to forward
     */
    public void broadcastCommandOutputs(CommandSender sender, int status, List<String> permissions, TrContainer... outputs) {
        this.receivers.forEach(receiver -> receiver.sendCommandOutputs(sender, status, permissions, outputs));
    }
}
