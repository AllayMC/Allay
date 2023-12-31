package org.allaymc.api.command.tree;

import org.allaymc.api.command.CommandResult;
import org.allaymc.api.command.CommandSender;
import org.allaymc.api.command.SenderType;
import org.cloudburstmc.protocol.bedrock.data.command.CommandParamData;
import org.jetbrains.annotations.Range;

import javax.annotation.Nullable;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * Allay Project 2023/12/29
 *
 * @author daoge_cmd
 */
public interface CommandNode {

    boolean isOptional();

    Object getDefaultValue();

    CommandNode defaultValue(Object defaultValue);

    CommandNode optional(boolean optional);

    default CommandNode optional() {
        return optional(true);
    }

    @Nullable
    CommandNode getOptionalLeaf();

    void setOptionalLeaf(CommandNode optionalLeaf);

    String name();

    int depth();

    void setDepth(int depth);

    CommandNode parent();

    CommandNode up(@Range(from = 1, to = Integer.MAX_VALUE) int count);

    default CommandNode up() {
        return up(1);
    }

    default CommandNode root() {
        return up(depth());
    }

    boolean match(CommandContext helper);

    @Nullable
    CommandNode nextNode(CommandContext helper);

    boolean isLeaf();

    List<CommandNode> getLeaves();

    CommandNode addLeaf(CommandNode leaf);

    CommandNode key(String key, String defaultValue);

    default CommandNode key(String key) {
        return key(key, "");
    }

    CommandNode str(String name, String defaultValue);

    default CommandNode str(String name) {
        return str(name, "");
    }

    CommandNode intNum(String name, int defaultValue);

    default CommandNode intNum(String name) {
        return intNum(name, 0);
    }

    CommandNode floatNum(String name, float defaultValue);

    default CommandNode floatNum(String name) {
        return floatNum(name, 0f);
    }

    CommandNode doubleNum(String name, double defaultValue);

    default CommandNode doubleNum(String name) {
        return doubleNum(name, 0d);
    }

    CommandNode bool(String name, boolean defaultValue);

    default CommandNode bool(String name) {
        return bool(name, false);
    }

    CommandNode enums(String name, String defaultValue, String... enums);

    default CommandNode enums(String name, String... enums) {
        return enums(name, "", enums);
    }

    default CommandNode enums(String name, Class<? extends Enum<?>> enumClass) {
        Enum<?>[] enumConstants = enumClass.getEnumConstants();
        var values = new String[enumConstants.length];
        for (int index = 0; index < enumConstants.length; index++) {
            var e = enumConstants[index];
            values[index] = e.name();
        }
        return enums(name, values);
    }

    <SENDER_TYPE extends CommandSender> CommandNode exec(BiFunction<CommandContext, SENDER_TYPE, CommandResult> executor, SenderType<SENDER_TYPE> senderType);

    default CommandNode exec(Function<CommandContext, CommandResult> executor) {
        return exec((context, sender) -> executor.apply(context), SenderType.ANY);
    }

    CommandResult applyExecutor(CommandContext context);

    CommandParamData toNetworkData();
}
