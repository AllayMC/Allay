package org.allaymc.server.command.tree;

import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import lombok.Getter;
import org.allaymc.api.command.Command;
import org.allaymc.api.command.CommandSender;
import org.allaymc.api.command.SenderType;
import org.allaymc.api.command.tree.CommandContext;
import org.allaymc.api.i18n.TrContainer;
import org.allaymc.api.command.exception.CommandParseException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Allay Project 2023/12/29
 *
 * @author daoge_cmd
 */
public class AllayCommandContext implements CommandContext {
    @Getter
    protected Command command;
    @Getter
    protected CommandSender sender;
    @Getter
    protected int currentArgIndex = 0;
    @Getter
    protected String[] args;
    protected Map<Integer, Object> results;
    @Getter
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
        if (!haveUnhandledArg()) {
            throw new CommandParseException("No more args!");
        }
        return args[currentArgIndex++];
    }

    @Override
    public String queryArg(int index) {
        return args[index];
    }

    @Override
    public void putResult(int index, Object result) {
        results.put(index, result);
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
