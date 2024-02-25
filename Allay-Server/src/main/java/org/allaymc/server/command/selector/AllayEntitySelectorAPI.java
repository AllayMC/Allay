package org.allaymc.server.command.selector;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.allaymc.api.command.CommandSender;
import org.allaymc.api.command.NPCCommandSender;
import org.allaymc.api.command.selector.EntitySelectorAPI;
import org.allaymc.api.command.selector.SelectorSyntaxException;
import org.allaymc.api.command.selector.args.ISelectorArgument;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.math.location.Location3f;
import org.allaymc.api.utils.AllayStringUtils;
import org.allaymc.server.command.selector.args.*;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.stream.Collectors;

import static org.allaymc.api.command.selector.SelectorType.*;

/**
 * Allay Project 2024/2/25
 *
 * @author daoge_cmd
 */
public class AllayEntitySelectorAPI implements EntitySelectorAPI {
    /**
     * 对目标选择器文本的预解析缓存
     */
    private static final Cache<String, Map<String, List<String>>> ARGS_CACHE = Caffeine.newBuilder().maximumSize(65535).expireAfterAccess(1, TimeUnit.MINUTES).build();
    private static final Cache<String, Boolean> MATCHES_CACHE = Caffeine.newBuilder().maximumSize(65535).expireAfterAccess(1, TimeUnit.MINUTES).build();

    Map<String, ISelectorArgument> registry = new HashMap<>();
    List<ISelectorArgument> orderedArgs = new ArrayList<>();

    public AllayEntitySelectorAPI() {
        registerDefaultArguments();
    }

    private void registerDefaultArguments() {
        registerArgument(new X());
        registerArgument(new Y());
        registerArgument(new Z());
        registerArgument(new DX());
        registerArgument(new DY());
        registerArgument(new DZ());
        registerArgument(new C());
        registerArgument(new R());
        registerArgument(new RM());
        registerArgument(new Name());
//        registerArgument(new Tag()); TODO
        registerArgument(new L());
        registerArgument(new LM());
        registerArgument(new M());
        registerArgument(new Type());
        registerArgument(new RX());
        registerArgument(new RXM());
        registerArgument(new RY());
        registerArgument(new RYM());
//        registerArgument(new Scores()); TODO
    }

    @Override
    public List<Entity> matchEntities(CommandSender sender, String token) throws SelectorSyntaxException {
        var cachedMatches = MATCHES_CACHE.getIfPresent(token);
        //先从缓存确认不是非法选择器
        if (cachedMatches != null && !cachedMatches)
            throw new SelectorSyntaxException("Malformed entity selector token");
        Matcher matcher = ENTITY_SELECTOR.matcher(token);
        //非法目标选择器文本
        if (!matcher.matches()) {
            //记录非法选择器到缓存
            MATCHES_CACHE.put(token, false);
            throw new SelectorSyntaxException("Malformed entity selector token");
        }
        //查询是否存在预解析结果。若不存在则解析
        Map<String, List<String>> arguments = ARGS_CACHE.getIfPresent(token);
        if (arguments == null) {
            arguments = parseArgumentMap(matcher.group(2));
            ARGS_CACHE.put(token, arguments);
        }
        //获取克隆过的执行者位置信息
        var senderLocation = new Location3f(sender.getCmdExecuteLocation());
        //获取选择器类型
        var selectorType = parseSelectorType(matcher.group(1));
        //根据选择器类型先确定实体检测范围
        List<Entity> entities;
        if (selectorType != SELF) {
            entities = Lists.newArrayList(senderLocation.dimension().getEntities().values());
        } else {
            if (sender.isEntity())
                entities = Lists.newArrayList(sender.asEntity());
            //没有符合条件的实体
            else return Lists.newArrayList();
        }
        //若是NPC触发选择器，则只处理触发NPC对话的玩家
        if (selectorType == NPC_INITIATOR) {
            if (sender instanceof NPCCommandSender npc)
                entities = Lists.newArrayList(npc.getInitiator());
            else
                return Lists.newArrayList();
        }
        //对于确定的玩家类型选择器，排除掉不是玩家的实体
        switch (selectorType) {
            case ALL_PLAYERS, NEAREST_PLAYER ->
                entities.removeIf(e -> !(e instanceof EntityPlayer));
            default -> {}
        }
        //没符合条件的实体了，return
        if (entities.isEmpty()) return entities;
        //参照坐标
        for (var arg : orderedArgs) {
            try {
                if (!arg.isFilter()) {
                    Predicate<Entity> predicate;
                    if (arguments.containsKey(arg.getKeyName()))
                        predicate = arg.getPredicate(selectorType, sender, senderLocation, arguments.get(arg.getKeyName()).toArray(new String[0]));
                    else if (arg.getDefaultValue(arguments, selectorType, sender) != null)
                        predicate = arg.getPredicate(selectorType, sender, senderLocation, arg.getDefaultValue(arguments, selectorType, sender));
                    else continue;
                    if (predicate == null)
                        continue;
                    entities.removeIf(entity -> !predicate.test(entity));
                } else {
                    if (arguments.containsKey(arg.getKeyName()))
                        entities = arg.getFilter(selectorType, sender, senderLocation, arguments.get(arg.getKeyName()).toArray(new String[0])).apply(entities);
                    else continue;
                }
            } catch (Throwable t) {
                throw new SelectorSyntaxException("Error while parsing selector argument: " + arg.getKeyName(), t);
            }
            //没符合条件的实体了，return
            if (entities.isEmpty()) return entities;
        }
        //随机选择一个
        if (selectorType == RANDOM_PLAYER && !entities.isEmpty()) {
            var index = ThreadLocalRandom.current().nextInt(entities.size()) + 1;
            Entity currentEntity = null;
            int i = 1;
            for (var localCurrent : entities){
                if (i == index) {
                    currentEntity = localCurrent;
                    break;
                }
                i++;
            }
            return Lists.newArrayList(currentEntity);
        }
        //选择最近玩家
        if (selectorType == NEAREST_PLAYER && entities.size() != 1) {
            Entity nearest = null;
            float min = Float.MAX_VALUE;
            for (var entity : entities) {
                var distanceSquared = 0f;
                if ((distanceSquared = senderLocation.distanceSquared(entity.getLocation())) < min) {
                    min = distanceSquared;
                    nearest = entity;
                }
            }
            entities = Lists.newArrayList(nearest);
        }
        return entities;
    }

    @Override
    public boolean checkValid(String token) {
        return MATCHES_CACHE.get(token, k -> ENTITY_SELECTOR.matcher(token).matches());
    }

    @Override
    public boolean registerArgument(ISelectorArgument argument) {
        if (!registry.containsKey(argument.getKeyName())) {
            registry.put(argument.getKeyName(), argument);
            orderedArgs.add(argument);
            Collections.sort(orderedArgs);
            return true;
        }
        return false;
    }

    protected Map<String, List<String>> parseArgumentMap(String inputArguments) throws SelectorSyntaxException {
        Map<String, List<String>> args = Maps.newHashMap();

        if (inputArguments != null) {
            for (String arg : separateArguments(inputArguments)) {
                var split = AllayStringUtils.fastSplit(arg, ARGUMENT_JOINER, 2);
                String argName = split.get(0);

                if (!registry.containsKey(argName)) {
                    throw new SelectorSyntaxException("Unknown selector argument: " + argName);
                }

                if (!args.containsKey(argName)) {
                    args.put(argName, Lists.newArrayList(split.size() > 1 ? split.get(1) : ""));
                } else {
                    args.get(argName).add(split.size() > 1 ? split.get(1) : "");
                }
            }
        }

        return args;
    }

    protected List<String> separateArguments(String inputArguments) {
        boolean go_on = false;
        List<String> result = new ArrayList<>();
        int start = 0;

        for (int i = 0; i < inputArguments.length(); i++) {
            if (inputArguments.charAt(i) == ',' && !go_on) {
                result.add(inputArguments.substring(start, i));
                start = i + 1;
            }
            if (inputArguments.charAt(i) == '{') {
                go_on = true;
            }
            if (inputArguments.charAt(i) == '}') {
                go_on = false;
                i++;
                result.add(inputArguments.substring(start, i));
                start = i + 1;
            }
        }

        if (start < inputArguments.length())
            result.add(inputArguments.substring(start));

        return result.stream().filter(s -> !s.isEmpty()).collect(Collectors.toList());
    }
}
