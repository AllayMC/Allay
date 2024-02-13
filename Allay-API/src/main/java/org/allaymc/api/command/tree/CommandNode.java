package org.allaymc.api.command.tree;

import org.allaymc.api.command.CommandResult;
import org.allaymc.api.command.CommandSender;
import org.allaymc.api.command.SenderType;
import org.allaymc.api.entity.Entity;
import org.cloudburstmc.protocol.bedrock.data.GameType;
import org.cloudburstmc.protocol.bedrock.data.command.CommandParamData;
import org.cloudburstmc.protocol.bedrock.data.command.CommandParamOption;

import java.util.Collections;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

import static org.allaymc.api.command.tree.CommandNodeFactory.getFactory;

/**
 * Allay Project 2023/12/29
 *
 * @author daoge_cmd
 */
public interface CommandNode {

    boolean isOptional();

    Object getDefaultValue();

    /**
     * @return 选中这个分支后消耗的最小参数数量
     */
    int getMinArgCostBranch();

    void setMinArgCostBranch(int minArgCostBranch);

    /**
     * @return 选中这个分支后后消耗的最大参数数量
     */
    int getMaxArgCostBranch();

    void setMaxArgCostBranch(int maxArgCostBranch);

    /**
     * @return 这个node将会消耗几个参数，可以返回一个足够大的数来表示无穷
     */
    default int getMaxArgCost() {
        return 1;
    }

    CommandNode defaultValue(Object defaultValue);

    CommandNode optional();

    CommandNode getOptionalLeaf();

    void setOptionalLeaf(CommandNode optionalLeaf);

    String name();

    int depth();

    default boolean isRoot() {
        return depth() == 0;
    }

    void setDepth(int depth);

    CommandNode parent();

    CommandNode up(int count);

    default CommandNode up() {
        return up(1);
    }

    default CommandNode root() {
        return up(depth());
    }

    boolean match(CommandContext context);


    CommandNode nextNode(CommandContext context);

    boolean isLeaf();

    List<CommandNode> getLeaves();

    CommandNode addLeaf(CommandNode leaf);

    <SENDER_TYPE extends CommandSender> CommandNode exec(BiFunction<CommandContext, SENDER_TYPE, CommandResult> executor, SenderType<SENDER_TYPE> senderType);

    default CommandNode exec(Function<CommandContext, CommandResult> executor) {
        return exec((context, sender) -> executor.apply(context), SenderType.ANY);
    }

    CommandResult applyExecutor(CommandContext context);

    CommandNode addParamOption(CommandParamOption option);

    default CommandNode suppressEnumAutoCompletion() {
        return addParamOption(CommandParamOption.SUPPRESS_ENUM_AUTOCOMPLETION);
    }

    default CommandNode hasSemanticConstraint() {
        return addParamOption(CommandParamOption.HAS_SEMANTIC_CONSTRAINT);
    }

    default CommandNode enumAsChainedCommand() {
        return addParamOption(CommandParamOption.ENUM_AS_CHAINED_COMMAND);
    }

    CommandParamData toNetworkData();

    default CommandNode key(String key, String defaultValue) {
        return addLeaf(getFactory().key(key, this, defaultValue));
    }

    default CommandNode key(String key) {
        return key(key, "");
    }

    default CommandNode str(String name, String defaultValue) {
        return addLeaf(getFactory().str(name, this, defaultValue));
    }

    default CommandNode str(String name) {
        return str(name, "");
    }

    default CommandNode intNum(String name, int defaultValue) {
        return addLeaf(getFactory().intNum(name, this, defaultValue));
    }

    default CommandNode intNum(String name) {
        return intNum(name, 0);
    }

    default CommandNode floatNum(String name, float defaultValue) {
        return addLeaf(getFactory().floatNum(name, this, defaultValue));
    }

    default CommandNode floatNum(String name) {
        return floatNum(name, 0f);
    }

    default CommandNode doubleNum(String name, double defaultValue) {
        return addLeaf(getFactory().doubleNum(name, this, defaultValue));
    }

    default CommandNode doubleNum(String name) {
        return doubleNum(name, 0d);
    }

    default CommandNode bool(String name, boolean defaultValue) {
        return addLeaf(getFactory().bool(name, this, defaultValue));
    }

    default CommandNode bool(String name) {
        return bool(name, false);
    }

    default CommandNode enums(String name, String defaultValue, String[] enums) {
        return addLeaf(getFactory().enums(name, this, defaultValue, enums));
    }

    default CommandNode enums(String name, String defaultValue, String enumName, String[] enums) {
        return addLeaf(getFactory().enums(name, this, enumName, defaultValue, enums));
    }

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

    default CommandNode enumsIgnoreCase(String name, String defaultValue, String[] enums) {
        return addLeaf(getFactory().enumsIgnoreCase(name, this, defaultValue, enums));
    }

    default CommandNode enumsIgnoreCase(String name, String defaultValue, String enumName, String[] enums) {
        return addLeaf(getFactory().enumsIgnoreCase(name, this, enumName, defaultValue, enums));
    }

    default CommandNode msg(String name) {
        return msg(name, "");
    }

    default CommandNode msg(String name, String defaultValue) {
        return addLeaf(getFactory().msg(name, this, defaultValue));
    }

    default CommandNode remain(String name) {
        return remain(name, Collections.EMPTY_LIST);
    }

    default CommandNode remain(String name, Object defaultValue) {
        return addLeaf(getFactory().remain(name, this, defaultValue));
    }

    default CommandNode target(String name) {
        return addLeaf(getFactory().target(name, this, Collections.EMPTY_LIST));
    }

    default CommandNode target(String name, List<Entity> defaultValue) {
        return addLeaf(getFactory().target(name, this, defaultValue));
    }

    default CommandNode gameMode() {
        return gameMode("gameMode");
    }

    default CommandNode gameMode(String name) {
        return addLeaf(getFactory().gameMode(name, this, null));
    }

    default CommandNode gameMode(String name, GameType defaultValue) {
        return addLeaf(getFactory().gameMode(name, this, defaultValue));
    }
}
