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
 * @author daoge_cmd
 */
@Getter
public class AllayCommandContext implements CommandContext {
    protected Command command;
    @Setter
    protected CommandSender sender;
    protected int currentArgIndex = 0;
    protected int currentResultIndex = 0;
    protected String[] args;
    protected Map<Integer, Object> results;
    protected List<TrContainer> outputs;
    protected List<String> permissions;

    public AllayCommandContext(Command command, CommandSender sender, String[] args) {
        this.command = command;
        this.sender = sender;
        this.args = args;
        this.results = new Int2ObjectOpenHashMap<>();
        this.outputs = new ArrayList<>();
        this.permissions = new ArrayList<>();
        this.permissions.addAll(command.getPermissions());
    }

    @Override
    public int getArgCount() {
        return args.length;
    }

    @Override
    public String popArg() {
        if (!haveUnhandledArg()) throw new CommandParseException("No more args!");
        return args[currentArgIndex++];
    }

    @Override
    public String queryArg(int index) {
        return args[index];
    }

    @Override
    public void putResult(Object result) {
        results.put(currentResultIndex++, result);
    }

    @Override
    public void clearResults() {
        results.clear();
        currentResultIndex = 0;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T getResult(int index) {
        var result = results.get(index);
        try {
            return (T) result;
        } catch (ClassCastException e) {
            throw new CommandParseException("Result type mismatch! Expected: " + result.getClass().getSimpleName());
        }
    }

    @Override
    public void addOutput(String output, Object... args) {
        outputs.add(new TrContainer(output, args));
    }

    @Override
    public void addOutputs(Collection<TrContainer> outputs) {
        this.outputs.addAll(outputs);
    }

    @Override
    public void addPermission(String permission) {
        this.permissions.add(permission);
    }

    @Override
    public void addPermissions(Collection<String> permissions) {
        this.permissions.addAll(permissions);
    }
}
