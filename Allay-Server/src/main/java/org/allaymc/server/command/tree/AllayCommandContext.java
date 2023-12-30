package org.allaymc.server.command.tree;

import lombok.Getter;
import org.allaymc.api.command.CommandSender;
import org.allaymc.api.command.tree.CommandContext;
import org.allaymc.api.i18n.TrContainer;

import java.util.List;

/**
 * Allay Project 2023/12/29
 *
 * @author daoge_cmd
 */
public class AllayCommandContext implements CommandContext {

    @Getter
    protected CommandSender sender;
    @Getter
    protected int currentArgIndex = 0;
    protected String[] args;
    protected Object[] results;
    @Getter
    protected List<TrContainer> outputs;

    public AllayCommandContext(CommandSender sender, String[] args) {
        this.sender = sender;
        this.args = args;
        this.results = new Object[args.length];
    }

    @Override
    public int getArgCount() {
        return args.length;
    }

    @Override
    public String popArg() {
        return args[currentArgIndex++];
    }

    @Override
    public String queryArg(int index) {
        return args[index];
    }

    @Override
    public void putResult(Object result, int index) {
        results[index] = result;
    }

    @Override
    public <T> T getResult(int index) {
        return (T) results[index];
    }

    @Override
    public void addOutput(String output, Object... args) {
        outputs.add(new TrContainer(output, args));
    }
}
