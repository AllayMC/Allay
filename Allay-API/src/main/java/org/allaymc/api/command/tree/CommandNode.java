package org.allaymc.api.command.tree;

import com.google.common.collect.Lists;
import org.allaymc.api.command.CommandResult;
import org.allaymc.api.command.CommandSender;
import org.allaymc.api.command.SenderType;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.effect.EffectType;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.item.enchantment.EnchantmentType;
import org.allaymc.api.item.type.ItemType;
import org.cloudburstmc.protocol.bedrock.data.GameType;
import org.cloudburstmc.protocol.bedrock.data.command.CommandParamData;
import org.cloudburstmc.protocol.bedrock.data.command.CommandParamOption;
import org.joml.Vector3fc;

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
     * @return The minimum number of arguments consumed after selecting this branch
     */
    int getMinArgCostBranch();

    void setMinArgCostBranch(int minArgCostBranch);

    /**
     * @return The maximum number of arguments consumed after selecting this branch
     */
    int getMaxArgCostBranch();

    void setMaxArgCostBranch(int maxArgCostBranch);

    /**
     * @return The maximum number of arguments this node can consume;
     * <p>
     * return a sufficiently large number to indicate infinity
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

    default CommandNode shortNum(String name, short defaultValue) {
        return addLeaf(getFactory().shortNum(name, this, defaultValue));
    }

    default CommandNode shortNum(String name) {
        return shortNum(name, (short) 0);
    }

    default CommandNode intNum(String name, int defaultValue) {
        return addLeaf(getFactory().intNum(name, this, defaultValue));
    }

    default CommandNode intNum(String name) {
        return intNum(name, 0);
    }

    default CommandNode longNum(String name, long defaultValue) {
        return addLeaf(getFactory().longNum(name, this, defaultValue));
    }

    default CommandNode longNum(String name) {
        return longNum(name, 0);
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
        var enumConstants = enumClass.getEnumConstants();
        var values = new String[enumConstants.length];
        for (int index = 0; index < enumConstants.length; index++) {
            var e = enumConstants[index];
            values[index] = e.name().toLowerCase();
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
        return remain(name, Lists.newArrayList());
    }

    default CommandNode remain(String name, Object defaultValue) {
        return addLeaf(getFactory().remain(name, this, defaultValue));
    }

    default CommandNode target(String name) {
        return addLeaf(getFactory().target(name, this, Lists.newArrayList()));
    }

    default CommandNode target(String name, List<Entity> defaultValue) {
        return addLeaf(getFactory().target(name, this, defaultValue));
    }

    default CommandNode playerTarget(String name) {
        return addLeaf(getFactory().playerTarget(name, this, Lists.newArrayList()));
    }

    default CommandNode playerTarget(String name, List<EntityPlayer> defaultValue) {
        return addLeaf(getFactory().playerTarget(name, this, defaultValue));
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

    default CommandNode pos(String name) {
        return pos(name, null);
    }

    default CommandNode pos(String name, Vector3fc defaultValue) {
        return addLeaf(getFactory().pos(name, this, defaultValue));
    }

    default CommandNode wildcardTarget(String name) {
        return wildcardTarget(name, "");
    }

    default CommandNode wildcardTarget(String name, String defaultValue) {
        return addLeaf(getFactory().wildcardTarget(name, this, defaultValue));
    }

    default CommandNode enchantmentNode(String name) {
        return enchantmentNode(name, null);
    }

    default CommandNode enchantmentNode(String name, EnchantmentType defaultValue) {
        return addLeaf(getFactory().enchantmentNode(name, this, defaultValue));
    }

    default CommandNode effectNode(String name) {
        return effectNode(name, null);
    }

    default CommandNode effectNode(String name, EffectType defaultValue) {
        return addLeaf(getFactory().effectNode(name, this, defaultValue));
    }

    default CommandNode itemTypeNode(String name) {
        return itemTypeNode(name, null);
    }

    default CommandNode itemTypeNode(String name, ItemType<?> defaultValue) {
        return addLeaf(getFactory().itemTypeNode(name, this, defaultValue));
    }
}
