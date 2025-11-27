package org.allaymc.server.gui;

import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import org.allaymc.api.eventbus.EventHandler;
import org.allaymc.api.eventbus.event.server.PlayerJoinEvent;
import org.allaymc.api.eventbus.event.server.PlayerQuitEvent;
import org.allaymc.api.message.I18n;
import org.allaymc.api.message.TrKeys;
import org.allaymc.api.plugin.PluginDependency;
import org.allaymc.api.registry.Registries;
import org.allaymc.api.server.Server;
import org.allaymc.api.utils.AllayStringUtils;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.Document;
import java.awt.*;
import java.awt.event.*;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author daoge_cmd
 */
public final class Dashboard {
    public static final int RAM_VALUE_COUNT = 100;
    public static final int CHUNK_VALUE_COUNT = 100;
    public static final int ENTITY_VALUE_COUNT = 100;
    private static final long MEGABYTE = 1024L * 1024L;
    private static Dashboard INSTANCE;
    private JPanel rootPane;
    private JTabbedPane rootTabbedPane;
    private JPanel playerTab;
    private JPanel perfTab;
    private JTable playerTable;
    private JTable pluginTable;
    private JLabel onlinePlayerCount;
    private GraphPanel ramGraph;
    private GraphPanel chunkGraph;
    private GraphPanel entityGraph;
    private ConsolePanel consolePane;
    private JTextField cmdInput;
    private JPanel entityTab;
    private JPanel chunkTab;
    private JPanel memoryTab;
    private JPanel consoleTab;
    private JPanel pluginTab;
    private JTabbedPane perfTabbedPane;
    private List<Integer> ramValues;
    private List<Integer> chunkValues;
    private List<Integer> entityValues;

    private Dashboard() {
        $$$setupUI$$$();
        wrapSystemOutputStreams();
        JFrame frame = new JFrame(I18n.get().tr(TrKeys.ALLAY_GUI_NAME));
        frame.setContentPane(rootPane);
        frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Server.getInstance().shutdown();
            }
        });
        frame.setSize(700, 700);
        frame.setLocationRelativeTo(null);
        // Set icon
        URL image = Dashboard.class.getClassLoader().getResource("icon.png");
        if (image != null) {
            frame.setIconImage(new ImageIcon(image).getImage());
        }
        applyI18nTexts();
        // Show the frame
        frame.setVisible(true);
        pluginTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() != MouseEvent.BUTTON3) return;
                // Show the focused plugin's information
                JPopupMenu popupMenu = new JPopupMenu();
                JMenuItem infoItem = new JMenuItem(I18n.get().tr(TrKeys.ALLAY_GUI_INFO));
                infoItem.addActionListener($ -> {
                    if (pluginTable.getSelectedRow() == -1) return;
                    // Get the plugin
                    String pluginName = (String) pluginTable.getValueAt(pluginTable.getSelectedRow(), 0);
                    var pluginDescriptor = Server.getInstance().getPluginManager().getEnabledPlugin(pluginName).descriptor();
                    JOptionPane.showMessageDialog(null,
                            I18n.get().tr(TrKeys.ALLAY_GUI_PLUGIN_NAME) + ": " + pluginDescriptor.getName() + "\n" +
                            I18n.get().tr(TrKeys.ALLAY_GUI_PLUGIN_ENTRANCE) + ": " + pluginDescriptor.getEntrance() + "\n" +
                            I18n.get().tr(TrKeys.ALLAY_GUI_PLUGIN_DESCRIPTION) + ": " + pluginDescriptor.getDescription() + "\n" +
                            I18n.get().tr(TrKeys.ALLAY_GUI_PLUGIN_VERSION) + ": " + pluginDescriptor.getVersion() + "\n" +
                            I18n.get().tr(TrKeys.ALLAY_GUI_PLUGIN_API_VERSION) + ": " + pluginDescriptor.getAPIVersion() + "\n" +
                            I18n.get().tr(TrKeys.ALLAY_GUI_PLUGIN_AUTHORS) + ": " + String.join(", ", pluginDescriptor.getAuthors()) + "\n" +
                            I18n.get().tr(TrKeys.ALLAY_GUI_PLUGIN_DEPENDENCIES) + ": " + pluginDescriptor.getDependencies().stream().map(PluginDependency::name).collect(Collectors.joining(", ")) + "\n" +
                            I18n.get().tr(TrKeys.ALLAY_GUI_PLUGIN_WEBSITE) + ": " + pluginDescriptor.getWebsite(),
                            I18n.get().tr(TrKeys.ALLAY_GUI_PLUGIN_INFO),
                            JOptionPane.INFORMATION_MESSAGE);
                });
                popupMenu.add(infoItem);
                popupMenu.show(e.getComponent(), e.getX(), e.getY());
            }
        });
        playerTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() != MouseEvent.BUTTON3) return;
                JPopupMenu popupMenu = new JPopupMenu();

                JMenuItem infoItem = new JMenuItem(I18n.get().tr(TrKeys.ALLAY_GUI_INFO));
                infoItem.addActionListener($ -> {
                    if (playerTable.getSelectedRow() == -1) return;
                    // Get the player
                    String playerName = (String) playerTable.getValueAt(playerTable.getSelectedRow(), 0);
                    var player = Server.getInstance().getPlayerManager().getPlayerByName(playerName);
                    var pos = player.getControlledEntity().getLocation();
                    JOptionPane.showMessageDialog(null,
                            I18n.get().tr(TrKeys.ALLAY_GUI_PLAYER_NAME) + ": " + player.getOriginName() + "\n" +
                            I18n.get().tr(TrKeys.ALLAY_GUI_PLAYER_ADDRESS) + ": " + player.getSocketAddress().toString() + "\n" +
                            I18n.get().tr(TrKeys.ALLAY_GUI_PLAYER_UUID) + ": " + player.getLoginData().getUuid().toString() + "\n" +
                            I18n.get().tr(TrKeys.ALLAY_GUI_PLAYER_POS) + ": (" + pos.x() + ", " + pos.y() + ", " + pos.z() + ")" + "\n" +
                            I18n.get().tr(TrKeys.ALLAY_GUI_PLAYER_WORLD) + ": " + pos.dimension().getWorld().getWorldData().getDisplayName() + "\n" +
                            I18n.get().tr(TrKeys.ALLAY_GUI_PLAYER_DIMENSION) + ": " + pos.dimension().getDimensionInfo().dimensionId(),
                            I18n.get().tr(TrKeys.ALLAY_GUI_PLAYER_INFO),
                            JOptionPane.INFORMATION_MESSAGE);
                });
                popupMenu.add(infoItem);

                JMenuItem kickItem = new JMenuItem(I18n.get().tr(TrKeys.ALLAY_GUI_PLAYER_KICK));
                kickItem.addActionListener($ -> {
                    if (playerTable.getSelectedRow() == -1) return;
                    // Get the player
                    String playerName = (String) playerTable.getValueAt(playerTable.getSelectedRow(), 0);
                    var player = Server.getInstance().getPlayerManager().getPlayerByName(playerName);
                    player.disconnect(TrKeys.MC_DISCONNECT_DISCONNECTED);
                });
                popupMenu.add(kickItem);

                JMenuItem banItem = new JMenuItem(I18n.get().tr(TrKeys.ALLAY_GUI_PLAYER_BAN));
                banItem.addActionListener($ -> {
                    if (playerTable.getSelectedRow() == -1) return;
                    // Get the player
                    String playerName = (String) playerTable.getValueAt(playerTable.getSelectedRow(), 0);
                    var player = Server.getInstance().getPlayerManager().getPlayerByName(playerName);
                    Server.getInstance().getPlayerManager().ban(player.getLoginData().getUuid().toString());
                });
                popupMenu.add(banItem);

                JMenuItem banIpItem = new JMenuItem(I18n.get().tr(TrKeys.ALLAY_GUI_PLAYER_BANIP));
                banIpItem.addActionListener($ -> {
                    if (playerTable.getSelectedRow() == -1) return;
                    // Get the player
                    String playerName = (String) playerTable.getValueAt(playerTable.getSelectedRow(), 0);
                    var player = Server.getInstance().getPlayerManager().getPlayerByName(playerName);
                    Server.getInstance().getPlayerManager().banIP(AllayStringUtils.fastTwoPartSplit(player.getSocketAddress().toString().substring(1), ":", "")[0]);
                });
                popupMenu.add(banIpItem);

                popupMenu.show(e.getComponent(), e.getX(), e.getY());
            }
        });
        consolePane.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() != MouseEvent.BUTTON3) return;
                JPopupMenu popupMenu = new JPopupMenu();

                JMenuItem changeFontSizeItem = new JMenuItem(I18n.get().tr(TrKeys.ALLAY_GUI_CONSOLE_CHANGEFONTSIZE));
                changeFontSizeItem.addActionListener($ -> {
                    String input = JOptionPane.showInputDialog(I18n.get().tr(TrKeys.ALLAY_GUI_CONSOLE_CHANGEFONTSIZE_DIALOG), consolePane.getFont().getSize());
                    if (input == null) return;
                    try {
                        int fontSize = Integer.parseInt(input);
                        consolePane.setFont(consolePane.getFont().deriveFont((float) fontSize));
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, I18n.get().tr(TrKeys.ALLAY_GUI_CONSOLE_CHANGEFONTSIZE_ERROR_DIALOG), I18n.get().tr(TrKeys.ALLAY_GUI_ERROR), JOptionPane.ERROR_MESSAGE);
                    }
                });
                popupMenu.add(changeFontSizeItem);

                JMenuItem clearItem = new JMenuItem(I18n.get().tr(TrKeys.ALLAY_GUI_CONSOLE_CLEAR));
                clearItem.addActionListener($ -> consolePane.setText(""));
                popupMenu.add(clearItem);

                popupMenu.show(e.getComponent(), e.getX(), e.getY());
            }
        });
    }

    public static Dashboard getInstance() {
        if (INSTANCE == null) {
            FlatMacDarkLaf.setup();
            INSTANCE = new Dashboard();
        }
        return INSTANCE;
    }

    private void applyI18nTexts() {
        SwingUtilities.invokeLater(() -> {
            rootTabbedPane.setTitleAt(0, I18n.get().tr(TrKeys.ALLAY_GUI_CONSOLE));
            rootTabbedPane.setTitleAt(1, I18n.get().tr(TrKeys.ALLAY_GUI_PERFORMANCE));
            rootTabbedPane.setTitleAt(2, I18n.get().tr(TrKeys.ALLAY_GUI_PLAYER));
            rootTabbedPane.setTitleAt(3, I18n.get().tr(TrKeys.ALLAY_GUI_PLUGIN));

            perfTabbedPane.setTitleAt(0, (I18n.get().tr(TrKeys.ALLAY_GUI_MEMORY)));
            perfTabbedPane.setTitleAt(1, (I18n.get().tr(TrKeys.ALLAY_GUI_CHUNK)));
            perfTabbedPane.setTitleAt(2, (I18n.get().tr(TrKeys.ALLAY_GUI_ENTITY)));

            onlinePlayerCount.setText(I18n.get().tr(TrKeys.ALLAY_GUI_PLAYER_ONLINE, 0));
        });
    }

    private void wrapSystemOutputStreams() {
        var proxyOutputStream = createProxyOutputStream();
        // Override the system output streams
        System.setOut(new PrintStream(proxyOutputStream, true));
        System.setErr(new PrintStream(proxyOutputStream, true));
    }

    private @NotNull OutputStream createProxyOutputStream() {
        var originalOutputStream = System.out;
        return new OutputStream() {
            @Override
            public void write(int i) {
                originalOutputStream.write(i);
                appendTextToConsole(String.valueOf((char) i));
            }

            @Override
            public void write(byte @NotNull [] b) {
                write(b, 0, b.length);
            }

            @Override
            public void write(byte @NotNull [] b, int off, int len) {
                originalOutputStream.write(b, off, len);
                appendTextToConsole(new String(b, off, len));
            }
        };
    }

    public void afterServerStarted() {
        Runnable guiUpdateTask = () -> {
            // Update ram graph
            final long freeMemory = Runtime.getRuntime().freeMemory();
            final long totalMemory = Runtime.getRuntime().totalMemory();
            final int freePercent = (int) (freeMemory * 100.0 / totalMemory + 0.5);
            ramValues.add(100 - freePercent);
            ramGraph.setXLabel(I18n.get().tr(TrKeys.ALLAY_GUI_RAM_LABEL, String.format("%,d", (totalMemory - freeMemory) / MEGABYTE), freePercent));
            // Trim the list
            int k = ramValues.size();
            if (k > RAM_VALUE_COUNT)
                ramValues.subList(0, k - RAM_VALUE_COUNT).clear();
            // Update the graph
            ramGraph.setValues(ramValues);

            // Update chunk graph
            var loadedChunkCount = Server.getInstance()
                    .getWorldPool()
                    .getWorlds()
                    .values()
                    .stream()
                    .mapToInt(world ->
                            world.getDimensions()
                                    .values()
                                    .stream()
                                    .mapToInt(dimension -> dimension.getChunkManager().getLoadedChunks().size())
                                    .sum()
                    )
                    .sum();
            chunkValues.add(loadedChunkCount);
            chunkGraph.setXLabel(I18n.get().tr(TrKeys.ALLAY_GUI_CHUNK_LABEL, loadedChunkCount));
            // Trim the list
            k = chunkValues.size();
            if (k > CHUNK_VALUE_COUNT)
                chunkValues.subList(0, k - CHUNK_VALUE_COUNT).clear();
            // Update the graph
            chunkGraph.setValues(chunkValues);

            // Update entity graph
            var loadedEntityCount = Server.getInstance()
                    .getWorldPool()
                    .getWorlds()
                    .values()
                    .stream()
                    .mapToInt(world ->
                            world.getDimensions()
                                    .values()
                                    .stream()
                                    .mapToInt(org.allaymc.api.world.Dimension::getEntityCount)
                                    .sum()
                    )
                    .sum();
            entityValues.add(loadedEntityCount);
            entityGraph.setXLabel(I18n.get().tr(TrKeys.ALLAY_GUI_ENTITY_LABEL, loadedEntityCount));
            // Trim the list
            k = entityValues.size();
            if (k > ENTITY_VALUE_COUNT)
                entityValues.subList(0, k - ENTITY_VALUE_COUNT).clear();
            // Update the graph
            entityGraph.setValues(entityValues);
        };

        // SwingUtilities.invokeLater is called so that we don't run into threading issues with the GUI
        Server.getInstance().getScheduler().scheduleRepeating(
                Server.getInstance(),
                () -> {
                    SwingUtilities.invokeLater(guiUpdateTask);
                    return true;
                },
                20);

        var server = Server.getInstance();
        server.getEventBus().registerListener(this);
        updatePluginTable();

        cmdInput.addActionListener(new CommandListener());
        SwingUtilities.invokeLater(() -> {
            cmdInput.setEnabled(true);
            cmdInput.requestFocusInWindow();
        });
    }

    @EventHandler
    private void onPlayerJoin(PlayerJoinEvent event) {
        updateOnlinePlayerCount();
        updateOnlinePlayerTable();
    }

    @EventHandler
    private void onPlayerQuit(PlayerQuitEvent event) {
        updateOnlinePlayerCount();
        updateOnlinePlayerTable();
    }

    private void updateOnlinePlayerCount() {
        SwingUtilities.invokeLater(() -> onlinePlayerCount.setText(I18n.get().tr(TrKeys.ALLAY_GUI_PLAYER_ONLINE, Server.getInstance().getPlayerManager().getPlayerCount())));
    }

    private void updateOnlinePlayerTable() {
        var title = new String[]{I18n.get().tr(TrKeys.ALLAY_GUI_PLAYER_NAME), I18n.get().tr(TrKeys.ALLAY_GUI_PLAYER_ADDRESS), I18n.get().tr(TrKeys.ALLAY_GUI_PLAYER_UUID)};
        var players = Server.getInstance().getPlayerManager().getPlayers().values();
        String[][] data = new String[players.size()][3];
        int row = 0;
        for (var player : players) {
            data[row] = new String[]{
                    player.getOriginName(),
                    player.getSocketAddress().toString(),
                    player.getLoginData().getUuid().toString()
            };
            row++;
        }
        var model = new UneditableDefaultTableModel(data, title);
        SwingUtilities.invokeLater(() -> {
            playerTable.setModel(model);
            playerTable.getTableHeader().setReorderingAllowed(false);
        });
    }

    private void updatePluginTable() {
        var title = new String[]{I18n.get().tr(TrKeys.ALLAY_GUI_PLUGIN_NAME), I18n.get().tr(TrKeys.ALLAY_GUI_PLUGIN_DESCRIPTION), I18n.get().tr(TrKeys.ALLAY_GUI_PLUGIN_VERSION), I18n.get().tr(TrKeys.ALLAY_GUI_PLUGIN_AUTHORS)};
        var plugins = Server.getInstance().getPluginManager().getEnabledPlugins().values();
        var data = new String[plugins.size()][4];
        int row = 0;
        for (var plugin : plugins) {
            var descriptor = plugin.descriptor();
            data[row] = new String[]{
                    descriptor.getName(),
                    descriptor.getDescription().isBlank() ? "N/A" : descriptor.getDescription(),
                    descriptor.getVersion(),
                    String.join(", ", descriptor.getAuthors())
            };
            row++;
        }
        var model = new UneditableDefaultTableModel(data, title);
        SwingUtilities.invokeLater(() -> {
            pluginTable.setModel(model);
            pluginTable.getTableHeader().setReorderingAllowed(false);
        });
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     */
    private void $$$setupUI$$$() {
        createUIComponents();
        rootPane = new JPanel();
        rootPane.setLayout(new GridLayoutManager(1, 1, new Insets(8, 8, 8, 8), -1, -1));
        rootTabbedPane = new JTabbedPane();
        rootPane.add(rootTabbedPane, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(200, 200), null, 0, false));
        consoleTab = new JPanel();
        consoleTab.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        rootTabbedPane.addTab("Console", consoleTab);
        final JScrollPane scrollPane1 = new JScrollPane();
        consoleTab.add(scrollPane1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        scrollPane1.setViewportView(consolePane);
        cmdInput = new JTextField();
        cmdInput.setEnabled(false);
        consoleTab.add(cmdInput, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        perfTab = new JPanel();
        perfTab.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        rootTabbedPane.addTab("Performance", perfTab);
        perfTabbedPane = new JTabbedPane();
        perfTabbedPane.setAlignmentY(0.5f);
        perfTab.add(perfTabbedPane, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(200, 200), null, 0, false));
        memoryTab = new JPanel();
        memoryTab.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        perfTabbedPane.addTab("Memory", memoryTab);
        memoryTab.add(ramGraph, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        chunkTab = new JPanel();
        chunkTab.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        perfTabbedPane.addTab("Chunk", chunkTab);
        chunkTab.add(chunkGraph, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        entityTab = new JPanel();
        entityTab.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        perfTabbedPane.addTab("Entity", entityTab);
        entityTab.add(entityGraph, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        playerTab = new JPanel();
        playerTab.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        rootTabbedPane.addTab("Players", playerTab);
        onlinePlayerCount = new JLabel();
        onlinePlayerCount.setEnabled(true);
        onlinePlayerCount.setHorizontalAlignment(2);
        onlinePlayerCount.setText("Online: 0");
        playerTab.add(onlinePlayerCount, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JScrollPane scrollPane2 = new JScrollPane();
        playerTab.add(scrollPane2, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        playerTable = new JTable();
        playerTable.setAutoCreateRowSorter(true);
        scrollPane2.setViewportView(playerTable);
        pluginTab = new JPanel();
        pluginTab.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        rootTabbedPane.addTab("Plugins", pluginTab);
        final JScrollPane scrollPane3 = new JScrollPane();
        pluginTab.add(scrollPane3, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        pluginTable = new JTable();
        pluginTable.setCellSelectionEnabled(false);
        scrollPane3.setViewportView(pluginTable);
    }

    /**
     *
     */
    public JComponent $$$getRootComponent$$$() {
        return rootPane;
    }

    private void createUIComponents() {
        // Init the three graph
        ramGraph = new GraphPanel();
        ramGraph.setVariableExtreme(false);
        ramValues = new ArrayList<>();
        // Set the ram graph to 0
        for (int i = 0; i < RAM_VALUE_COUNT; i++) {
            ramValues.add(0);
        }
        ramGraph.setValues(ramValues);

        chunkGraph = new GraphPanel();
        chunkValues = new ArrayList<>();
        // Set the chunk graph to 0
        for (int i = 0; i < CHUNK_VALUE_COUNT; i++) {
            chunkValues.add(0);
        }
        chunkGraph.setValues(chunkValues);

        entityGraph = new GraphPanel();
        entityValues = new ArrayList<>();
        // Set the entity graph to 0
        for (int i = 0; i < ENTITY_VALUE_COUNT; i++) {
            entityValues.add(0);
        }
        entityGraph.setValues(entityValues);

        // Init the console
        consolePane = new ConsolePanel();
        consolePane.setBackground(new Color(0x131313));
        consolePane.setEditable(false);
    }

    public void appendTextToConsole(final String text) {
        SwingUtilities.invokeLater(() -> {
            consolePane.appendANSI(text);
            Document doc = consolePane.getDocument();
            consolePane.setCaretPosition(doc.getLength());
        });
    }

    private static class UneditableDefaultTableModel extends DefaultTableModel {
        public UneditableDefaultTableModel(String[][] data, String[] title) {
            super(data, title);
        }

        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    }

    private class CommandListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            var cmd = cmdInput.getText().trim();
            cmdInput.setText(""); // clear the input
            if (cmd.isEmpty()) return;

            appendTextToConsole(cmd + "\n"); // show what was run in the console
            var server = Server.getInstance();
            server.getScheduler().runLater(server, () -> Registries.COMMANDS.execute(Server.getInstance(), cmd));
        }
    }
}
