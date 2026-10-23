package org.allaymc.server.command.tree;

import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.command.Command;
import org.allaymc.api.command.CommandSender;
import org.allaymc.api.command.tree.CommandContext;
import org.allaymc.api.message.TrContainer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * {@code AllayCommandContext} stores one command invocation's input cursor,
 * parsed-result sequence, current sender, and accumulated feedback. The
 * constructor retains the command and argument array, creates separate mutable
 * result and output collections, and copies the command's current permission
 * entries. Node matching advances the argument and result cursors independently
 * because one node can consume several tokens while producing one result.
 *
 * <p> Redirects clear the result map and reset only {@code currentResultIndex};
 * {@code currentArgIndex} continues through the same argument array. This keeps
 * the remaining command text available while giving the restarted tree a result
 * sequence beginning at zero. Generated getters expose the retained array and
 * collections directly, so consumers share subsequent changes rather than
 * receiving snapshots.
 *
 * @author daoge_cmd
 */
@Getter
public class AllayCommandContext implements CommandContext {
    /**
     * Command whose tree owns this invocation.
     */
    protected Command command;
    /**
     * {@code sender} supplies the current execution identity and origin.
     * Redirect callbacks may replace it through the generated setter before
     * parsing the remaining arguments.
     */
    @Setter
    protected CommandSender sender;
    /**
     * {@code currentArgIndex} identifies the next unconsumed token in
     * {@code args}. {@code popArg} advances it; clearing results leaves it
     * unchanged during a redirect.
     */
    protected int currentArgIndex = 0;
    /**
     * {@code currentResultIndex} identifies the next insertion slot in
     * {@code results}. {@code putResult} advances it, and {@code clearResults}
     * resets it to zero.
     */
    protected int currentResultIndex = 0;
    /**
     * {@code args} retains the caller's original token array without copying.
     * The generated getter exposes the same array to parsers, executors, and
     * syntax-feedback construction.
     */
    protected String[] args;
    /**
     * {@code results} maps insertion indices to parsed values, including
     * {@code null}. Its generated getter exposes shared mutable storage, while
     * {@code clearResults} removes all entries for redirect parsing.
     */
    protected Map<Integer, Object> results;
    /**
     * {@code outputs} holds feedback in insertion order until registry
     * delivery. Its generated getter exposes the mutable list, and redirect
     * result clearing leaves these messages intact.
     */
    protected List<TrContainer> outputs;
    /**
     * {@code permissions} accumulates command and selected-node requirements
     * for feedback filtering. Its generated getter exposes the mutable list,
     * and redirects retain previously collected requirements.
     */
    protected List<String> permissions;

    /**
     * Creates a fresh invocation context retaining the command, sender, and
     * argument array. Result and output storage starts empty; permissions are
     * copied from the command so later list changes on the command do not
     * replace this invocation's entries.
     */
    public AllayCommandContext(Command command, CommandSender sender, String[] args) {
        this.command = command;
        this.sender = sender;
        this.args = args;
        this.results = new Int2ObjectOpenHashMap<>();
        this.outputs = new ArrayList<>();
        this.permissions = new ArrayList<>();
        this.permissions.addAll(command.getPermissions());
    }

    /**
     * Returns the current length of the retained command argument array.
     */
    @Override
    public int getArgCount() {
        return args.length;
    }

    /**
     * Returns the next token and advances {@code currentArgIndex} by one.
     * Exhausted input raises {@code CommandParseException} without advancing
     * the cursor, so nodes must establish that enough arguments remain before
     * consuming them.
     */
    @Override
    public String popArg() {
        if (!haveUnhandledArg()) throw new CommandParseException("No more args!");
        return args[currentArgIndex++];
    }

    /**
     * Returns the token at the supplied absolute index without moving either
     * cursor. An index outside {@code args} raises the array's bounds
     * exception; no default token is supplied.
     */
    @Override
    public String queryArg(int index) {
        return args[index];
    }

    /**
     * Stores the value at the next result index and advances
     * {@code currentResultIndex}. The value is retained directly and may be
     * {@code null}; result indices track producing nodes rather than consumed
     * token counts.
     */
    @Override
    public void putResult(Object result) {
        results.put(currentResultIndex++, result);
    }

    /**
     * Removes all parsed results and resets the result insertion cursor to
     * zero. The method retains argument progress, the current sender, feedback,
     * and permissions so a redirect can resume on unconsumed input.
     */
    @Override
    public void clearResults() {
        results.clear();
        currentResultIndex = 0;
    }

    /**
     * Returns the stored result or {@code null} when the index is absent. The
     * caller must request the type produced by the matching node; generic
     * erasure means incompatible assignments can fail at the caller rather than
     * inside this method.
     */
    @Override
    @SuppressWarnings("unchecked")
    public <T> T getResult(int index) {
        var result = results.get(index);
        try {
            return (T) result;
        } catch (ClassCastException e) {
            // This guard can translate only a cast failure raised here; erased generic
            // return types leave incompatible caller-side assignments outside this catch.
            throw new CommandParseException("Result type mismatch! Expected: " + result.getClass().getSimpleName());
        }
    }

    /**
     * Appends a translation container retaining the supplied message and
     * formatting arguments.
     */
    @Override
    public void addOutput(String output, Object... args) {
        outputs.add(new TrContainer(output, args));
    }

    /**
     * Appends the supplied containers to this invocation's feedback list in
     * iteration order. Container objects remain shared with the source
     * collection, and this method does not send them immediately.
     */
    @Override
    public void addOutputs(Collection<TrContainer> outputs) {
        this.outputs.addAll(outputs);
    }

    /**
     * Appends one permission requirement for the registry's command-feedback
     * recipient filtering.
     */
    @Override
    public void addPermission(String permission) {
        this.permissions.add(permission);
    }

    /**
     * Appends all supplied permission requirements for command-feedback
     * recipient filtering in order.
     */
    @Override
    public void addPermissions(Collection<String> permissions) {
        this.permissions.addAll(permissions);
    }
}
