package org.allaymc.server.terminal;

import org.allaymc.api.command.Command;
import org.allaymc.api.command.tree.CommandNode;
import org.allaymc.api.player.Player;
import org.allaymc.api.registry.Registries;
import org.allaymc.api.server.Server;
import org.allaymc.server.command.tree.AllayCommandContext;
import org.allaymc.server.command.tree.node.BaseNode;
import org.cloudburstmc.protocol.bedrock.data.command.CommandParam;
import org.cloudburstmc.protocol.bedrock.data.command.CommandParamData;
import org.jline.reader.Candidate;
import org.jline.reader.Completer;
import org.jline.reader.LineReader;
import org.jline.reader.ParsedLine;

import java.util.*;

/**
 * Simple command completer backed by Allay's command tree metadata.
 */
public final class AllayCommandCompleter implements Completer {
    private final Server server;

    public AllayCommandCompleter(Server server) {
        this.server = server;
    }

    @Override
    public void complete(LineReader reader, ParsedLine line, List<Candidate> candidates) {
        var words = line.words();
        var wordIndex = line.wordIndex();
        var currentWord = line.word();
        if (currentWord == null) {
            currentWord = "";
        }

        if (wordIndex <= 0) {
            collectCommandCandidates(normalizeRootToken(currentWord), currentWord.startsWith("/"), candidates);
            return;
        }

        if (words.isEmpty()) {
            collectCommandCandidates("", false, candidates);
            return;
        }

        var command = Registries.COMMANDS.findCommand(normalizeRootToken(words.get(0)));
        if (command == null) {
            return;
        }

        var node = resolveNode(command, words, wordIndex);
        if (node == null || node.isLeaf()) {
            return;
        }

        addNodeCandidates(node, currentWord, candidates);
    }

    private void collectCommandCandidates(String prefix, boolean includeSlash, List<Candidate> candidates) {
        commandNames().stream()
                .filter(name -> startsWithIgnoreCase(name, prefix))
                .map(name -> includeSlash ? "/" + name : name)
                .map(Candidate::new)
                .forEach(candidates::add);
    }

    private List<String> commandNames() {
        Set<String> names = new LinkedHashSet<>();
        Registries.COMMANDS.getContent().values().stream()
                .filter(command -> server.hasPermissions(command.getPermissions()))
                .sorted(Comparator.comparing(Command::getName))
                .forEach(command -> {
                    names.add(command.getName());
                    names.addAll(command.getAliases());
                });
        return List.copyOf(names);
    }

    private CommandNode resolveNode(Command command, List<String> words, int wordIndex) {
        var tree = command.getCommandTree();
        var args = new ArrayList<String>(Math.max(0, wordIndex - 1));
        for (int index = 1; index < wordIndex; index++) {
            args.add(words.get(index));
        }

        var context = new AllayCommandContext(command, server, args.toArray(String[]::new));
        CommandNode node = tree.getRoot();

        while (true) {
            node = resolveRedirect(node, context, tree.getRoot());
            if (node == null || !context.haveUnhandledArg()) {
                return node;
            }

            if (node.isLeaf()) {
                return node;
            }

            var nextNode = node.nextNode(context);
            if (nextNode == null || !nextNode.checkPermissions(server)) {
                return null;
            }

            context.addPermissions(nextNode.getPermissions());
            node = nextNode;
        }
    }

    private CommandNode resolveRedirect(CommandNode node, AllayCommandContext context, CommandNode root) {
        while (node != null && node.isLeaf() && node.getOnRedirect() != null) {
            node.getOnRedirect().accept(context);
            context.clearResults();
            node = root;
        }
        return node;
    }

    private void addNodeCandidates(CommandNode node, String currentWord, List<Candidate> candidates) {
        var prefix = currentWord == null ? "" : currentWord;
        Set<String> values = new LinkedHashSet<>();

        for (var leaf : node.getLeaves()) {
            if (!leaf.checkPermissions(server) || !(leaf instanceof BaseNode baseNode)) {
                continue;
            }

            var param = baseNode.toNetworkData();
            collectParameterSuggestions(param, values);
        }

        values.stream()
                .filter(value -> startsWithIgnoreCase(value, prefix))
                .sorted(String::compareTo)
                .map(Candidate::new)
                .forEach(candidates::add);
    }

    private void collectParameterSuggestions(CommandParamData param, Set<String> values) {
        var enumData = param.getEnumData();
        if (enumData != null && !enumData.values().isEmpty()) {
            values.addAll(enumData.values().keySet());
            return;
        }

        if (CommandParam.TARGET.equals(param.getType())) {
            server.getPlayerManager().getPlayers().values().stream()
                    .map(Player::getOriginName)
                    .forEach(values::add);
        }
    }

    private String normalizeRootToken(String token) {
        if (token == null || token.isEmpty()) {
            return "";
        }
        return token.charAt(0) == '/' ? token.substring(1) : token;
    }

    private boolean startsWithIgnoreCase(String value, String prefix) {
        if (prefix == null || prefix.isEmpty()) {
            return true;
        }

        return value.regionMatches(true, 0, prefix, 0, prefix.length());
    }
}
