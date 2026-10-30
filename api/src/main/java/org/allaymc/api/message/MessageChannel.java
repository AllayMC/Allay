package org.allaymc.api.message;

import com.google.common.collect.Sets;
import org.allaymc.api.annotation.ThreadSafe;
import org.allaymc.api.command.CommandSender;

import java.util.List;
import java.util.Set;

/**
 * {@code MessageChannel} broadcasts text, translatable messages, and command output to registered
 * receivers. A concurrent membership set permits registration changes during traversal, but a
 * broadcast is not a fixed membership snapshot and receiver callbacks execute on the broadcasting
 * caller's thread.
 */
@ThreadSafe
public class MessageChannel {

    /**
     * {@code receivers} contains concurrent channel membership. Broadcast traversal can observe
     * membership changes, and receiver exceptions propagate rather than being isolated per
     * recipient.
     */
    protected Set<MessageReceiver> receivers;

    /**
     * Creates an empty message channel backed by a concurrent receiver set.
     */
    public MessageChannel() {
        this.receivers = Sets.newConcurrentHashSet();
    }

    /**
     * Adds a receiver to this channel's concurrent membership set. Duplicate additions share one
     * registration, and {@code null} receivers are rejected by the backing set rather than retained
     * for later delivery.
     */
    public void addReceiver(MessageReceiver receiver) {
        this.receivers.add(receiver);
    }

    /**
     * Removes a receiver from this channel's concurrent membership set when present. A broadcast
     * already traversing the set may still deliver to that receiver, so removal is not a delivery
     * barrier.
     */
    public void removeReceiver(MessageReceiver receiver) {
        this.receivers.remove(receiver);
    }

    /**
     * Returns whether the receiver is currently registered in this channel. Concurrent registration
     * changes can alter membership immediately after this check; the result does not reserve
     * delivery in a later broadcast.
     */
    public boolean hasReceiver(MessageReceiver receiver) {
        return this.receivers.contains(receiver);
    }

    /**
     * Broadcasts plain text through the currently traversed receiver membership. Receiver callbacks
     * run synchronously; an exception stops later delivery, and concurrent membership changes can
     * affect which receivers participate.
     */
    public void broadcastMessage(String message) {
        this.receivers.forEach(receiver -> receiver.sendMessage(message));
    }

    /**
     * Broadcasts translatable text through the currently traversed receiver membership. Receiver
     * callbacks run synchronously; an exception stops later delivery, and concurrent membership
     * changes can affect which receivers participate.
     */
    public void broadcastTranslatable(@MayContainTrKey String translatable) {
        this.receivers.forEach(receiver -> receiver.sendTranslatable(translatable));
    }

    /**
     * Broadcasts translatable text through the currently traversed receiver membership. Receiver
     * callbacks run synchronously; an exception stops later delivery, and concurrent membership
     * changes can affect which receivers participate.
     */
    public void broadcastTranslatable(@MayContainTrKey String translatable, Object... args) {
        this.receivers.forEach(receiver -> receiver.sendTranslatable(translatable, args));
    }

    /**
     * Broadcasts command output with its sender, status, and permission requirements through the
     * currently traversed receiver membership. Receiver callbacks run synchronously; an exception
     * stops later delivery, and concurrent membership changes can affect which receivers
     * participate.
     */
    public void broadcastCommandOutputs(CommandSender sender, int status, List<String> permissions, TrContainer... outputs) {
        this.receivers.forEach(receiver -> receiver.sendCommandOutputs(sender, status, permissions, outputs));
    }
}
