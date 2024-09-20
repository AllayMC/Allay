package org.allaymc.server.command.tree;

import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import lombok.Getter;
import org.allaymc.api.command.Command;
import org.allaymc.api.command.CommandSender;
import org.allaymc.api.command.exception.CommandParseException;
import org.allaymc.api.command.tree.CommandContext;
import org.allaymc.api.i18n.TrContainer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author daoge_cmd
 */
@Getter
public class AllayCommandContext implements CommandContext {
    protected Command command;
    protected CommandSender sender;
    protected int currentArgIndex = 0;
    protected int currentResultIndex = 0;
    protected String[] args;
    protected Map<Integer, Object> results;
    protected List<TrContainer> outputs;

    public AllayCommandContext(Command command, CommandSender sender, String[] args) {
        this.command = command;
        this.sender = sender;
        this.args = args;
        this.results = new Int2ObjectOpenHashMap<>();
        this.outputs = new ArrayList<>();
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
    public <T> T getResult(int index) {
        return (T) results.get(index);
    }

    @Override
    public void addOutput(String output, Object... args) {
        outputs.add(new TrContainer(output, args));
    }
}
