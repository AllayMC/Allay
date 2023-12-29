package org.allaymc.server.cmdv2.tree;

import lombok.Getter;
import org.allaymc.api.cmdv2.CommandSender;
import org.allaymc.api.cmdv2.tree.CommandContext;

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

    public AllayCommandContext(CommandSender sender, String[] args) {
        this.sender = sender;
        this.args = args;
        this.results = new Object[args.length];
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
}
