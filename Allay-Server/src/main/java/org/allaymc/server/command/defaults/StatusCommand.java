package org.allaymc.server.command.defaults;

import com.sun.jna.platform.win32.COM.WbemcliUtil;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.command.CommandSender;
import org.allaymc.api.command.SimpleCommand;
import org.allaymc.api.command.tree.CommandTree;
import org.allaymc.api.i18n.TrKeys;
import org.allaymc.api.server.Server;
import org.allaymc.api.utils.TextFormat;
import org.allaymc.api.world.Dimension;
import oshi.SystemInfo;
import oshi.util.platform.windows.WmiQueryHandler;

import java.io.File;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.allaymc.api.utils.MathUtils.round;

/**
 * Allay Project 2024/2/13
 *
 * @author daoge_cmd
 */
@Slf4j
public class StatusCommand extends SimpleCommand {

    protected static final String UPTIME_FORMAT = TextFormat.RED + "%d" + TextFormat.GOLD + " days " +
                                                  TextFormat.RED + "%d" + TextFormat.GOLD + " hours " +
                                                  TextFormat.RED + "%d" + TextFormat.GOLD + " minutes " +
                                                  TextFormat.RED + "%d" + TextFormat.GOLD + " seconds";
    protected static final Map<String, String> VM_VENDOR = new HashMap<>(10, 0.99f);
    protected static final Map<String, String> VM_MAC = new HashMap<>(10, 0.99f);
    protected static final SystemInfo SYSTEM_INFO = new SystemInfo();
    protected static final String[] VM_MODEL_ARRAY = new String[]
            {
                    "Linux KVM", "Linux lguest", "OpenVZ", "Qemu",
                    "Microsoft Virtual PC", "VMWare", "linux-vserver",
                    "Xen", "FreeBSD Jail", "VirtualBox", "Parallels",
                    "Linux Containers", "LXC", "Bochs"
            };

    static {
        // VM VENDOR
        VM_VENDOR.put("bhyve", "bhyve");
        VM_VENDOR.put("KVM", "KVM");
        VM_VENDOR.put("TCG", "QEMU");
        VM_VENDOR.put("Microsoft Hv", "Microsoft Hyper-V or Windows Virtual PC");
        VM_VENDOR.put("lrpepyh vr", "Parallels");
        VM_VENDOR.put("VMware", "VMware");
        VM_VENDOR.put("XenVM", "Xen HVM");
        VM_VENDOR.put("ACRN", "Project ACRN");
        VM_VENDOR.put("QNXQVMBSQG", "QNX Hypervisor");
        // VM MAC
        VM_MAC.put("00:50:56", "VMware ESX 3");
        VM_MAC.put("00:0C:29", "VMware ESX 3");
        VM_MAC.put("00:05:69", "VMware ESX 3");
        VM_MAC.put("00:03:FF", "Microsoft Hyper-V");
        VM_MAC.put("00:1C:42", "Parallels Desktop");
        VM_MAC.put("00:0F:4B", "Virtual Iron 4");
        VM_MAC.put("00:16:3E", "Xen or Oracle VM");
        VM_MAC.put("08:00:27", "VirtualBox");
        VM_MAC.put("02:42:AC", "Docker Container");
    }

    public StatusCommand() {
        super("status", TrKeys.A_COMMAND_STATUS_DESCRIPTION);
    }

    protected static void printOperationSystemMemoryInfo(CommandSender sender) {
        sender.sendText(TextFormat.YELLOW + ">>> " + TextFormat.WHITE + "Operation System Memory Info" + TextFormat.YELLOW + " <<<" + TextFormat.RESET);
        var globalMemory = SYSTEM_INFO.getHardware().getMemory();
        var virtualMemory = globalMemory.getVirtualMemory();
        // Physical Memory
        var allPhysicalMemory = globalMemory.getTotal() / 1000;
        var usedPhysicalMemory = (globalMemory.getTotal() - globalMemory.getAvailable()) / 1000;
        var usage = (double) usedPhysicalMemory / allPhysicalMemory * 100;
        var usageColor = TextFormat.GREEN;
        if (usage > 85) usageColor = TextFormat.GOLD;
        sender.sendText(TextFormat.GOLD + "OS memory: ");
        sender.sendText(TextFormat.GOLD + "  Physical memory: " + TextFormat.GREEN + usageColor + toMB(usedPhysicalMemory) + " / " + toMB(allPhysicalMemory) + ". (" + round(usage, 2) + "%)");

        // Virtual Memory
        var allVirtualMemory = virtualMemory.getVirtualMax() / 1000;
        var usedVirtualMemory = virtualMemory.getVirtualInUse() / 1000;
        usage = (double) usedVirtualMemory / allVirtualMemory * 100;
        usageColor = TextFormat.GREEN;
        if (usage > 85) usageColor = TextFormat.GOLD;
        sender.sendText(TextFormat.GOLD + "  Virtual memory: " + TextFormat.GREEN + usageColor + toMB(usedVirtualMemory) + " / " + toMB(allVirtualMemory) + ". (" + round(usage, 2) + "%)");

        // Hardware
        var physicalMemories = globalMemory.getPhysicalMemory();
        if (!physicalMemories.isEmpty()) sender.sendText(TextFormat.GOLD + "  Hardware list: ");
        for (var each : physicalMemories) {
            sender.sendText(TextFormat.AQUA + "    " + each.getBankLabel() + " @ " + formatFreq(each.getClockSpeed()) + TextFormat.WHITE + " " + toMB(each.getCapacity() / 1000));
            sender.sendText(TextFormat.GRAY + "      " + each.getMemoryType() + ", " + each.getManufacturer());
        }
        sender.sendText("");
    }

    protected static void printCPUInfo(CommandSender sender) {
        var cpu = SYSTEM_INFO.getHardware().getProcessor();
        var processorIdentifier = cpu.getProcessorIdentifier();
        sender.sendText(TextFormat.YELLOW + ">>> " + TextFormat.WHITE + "CPU Info" + TextFormat.YELLOW + " <<<" + TextFormat.RESET);
        sender.sendText(TextFormat.GOLD + "CPU: " + TextFormat.AQUA + processorIdentifier.getName() + TextFormat.GRAY +
                          " (" + formatFreq(cpu.getMaxFreq()) + " baseline; " + cpu.getPhysicalProcessorCount() + " cores, " + cpu.getLogicalProcessorCount() + " logical cores)");
        sender.sendText(TextFormat.GOLD + "Thread count: " + TextFormat.GREEN + Thread.getAllStackTraces().size());
        sender.sendText(TextFormat.GOLD + "CPU Features: " + TextFormat.RESET + (processorIdentifier.isCpu64bit() ? "64bit, " : "32bit, ") +
                          processorIdentifier.getModel() + ", micro-arch: " + processorIdentifier.getMicroarchitecture());
        sender.sendText("");
    }

    protected static void printNetworkInfo(CommandSender sender) {
        try {
            var networkIFs = SYSTEM_INFO.getHardware().getNetworkIFs();
            if (networkIFs != null) {
                sender.sendText(TextFormat.YELLOW + ">>> " + TextFormat.WHITE + "Network Info" + TextFormat.YELLOW + " <<<" + TextFormat.RESET);
                sender.sendText(TextFormat.GOLD + "Network hardware list: ");
                ObjectArrayList<String> list;
                for (var networkIF : networkIFs) {
                    list = new ObjectArrayList<>(networkIF.getIPv4addr().length + networkIF.getIPv6addr().length);
                    list.addElements(0, networkIF.getIPv4addr());
                    list.addElements(list.size(), networkIF.getIPv6addr());
                    sender.sendText(TextFormat.AQUA + "  " + networkIF.getDisplayName());
                    sender.sendText(TextFormat.RESET + "    " + toKB(networkIF.getSpeed()) + "/s " + TextFormat.GRAY + String.join(", ", list));
                }
                sender.sendText("");
            }
        } catch (Exception ignored) {
            sender.sendText(TextFormat.RED + "    Failed to get network info.");
        }
    }

    protected static void printOperationSystemAndJVMInfo(CommandSender sender) {
        var os = SYSTEM_INFO.getOperatingSystem();
        var mxBean = ManagementFactory.getRuntimeMXBean();
        sender.sendText(TextFormat.YELLOW + ">>> " + TextFormat.WHITE + "OS & JVM Info" + TextFormat.YELLOW + " <<<" + TextFormat.RESET);
        sender.sendText(TextFormat.GOLD + "OS: " + TextFormat.AQUA + os.getFamily() + " " + os.getManufacturer() + " " +
                          os.getVersionInfo().getVersion() + " " + os.getVersionInfo().getCodeName() + " " + os.getBitness() + "bit, " +
                          "build " + os.getVersionInfo().getBuildNumber());
        sender.sendText(TextFormat.GOLD + "JVM: " + TextFormat.AQUA + mxBean.getVmName() + " " + mxBean.getVmVendor() + " " + mxBean.getVmVersion());
        try {
            var vm = detectVM();
            if (vm == null) sender.sendText(TextFormat.GOLD + "Virtual environment: " + TextFormat.GREEN + "no");
            else sender.sendText(TextFormat.GOLD + "Virtual environment: " + TextFormat.YELLOW + "yes (" + vm + ")");
        } catch (Exception ignore) {}
        sender.sendText("");
    }

    protected static void printWorldInfo(CommandSender sender) {
        for (var world : Server.getInstance().getWorldPool().getWorlds().values()) {
            sender.sendText(TextFormat.GREEN + "---- " + TextFormat.WHITE + "World " + world.getWorldData().getName() + " Status" + TextFormat.GREEN + " ----");
            sender.sendText(TextFormat.GOLD + "TPS: " + TextFormat.WHITE + world.getTps());
            sender.sendText(TextFormat.GOLD + "MSPT: " + TextFormat.WHITE + world.getMSPT());
            sender.sendText(TextFormat.GOLD + "TickUsage: " + TextFormat.WHITE + world.getTickUsage() * 100f + "%");
            world.getDimensions().values().forEach(dimension -> printDimensionInfo(sender, dimension));
        }
    }

    protected static void printDimensionInfo(CommandSender sender, Dimension dimension) {
        sender.sendText(TextFormat.GOLD + "DimensionId " + TextFormat.WHITE + dimension.getDimensionInfo().dimensionId());
        sender.sendText(TextFormat.GOLD + "Chunk count: " + TextFormat.WHITE + dimension.getChunkService().getLoadedChunks().size());
        sender.sendText(TextFormat.GOLD + "Entity count: " + TextFormat.WHITE + dimension.getEntityCount());
        sender.sendText(TextFormat.GOLD + "BlockEntity count: " + TextFormat.WHITE + dimension.getBlockEntityCount());
    }

    protected static void printUpTimeInfo(CommandSender sender) {
        var time = System.currentTimeMillis() - Server.getInstance().getStartTime();
        sender.sendText(TextFormat.GOLD + "Uptime: " + formatUptime(time));
    }

    protected static void printMemoryUsageInfo(CommandSender sender) {
        var runtime = Runtime.getRuntime();
        var totalMB = round(((double) runtime.totalMemory()) / 1024 / 1024, 2);
        var usedMB = round((double) (runtime.totalMemory() - runtime.freeMemory()) / 1024 / 1024, 2);
        var maxMB = round(((double) runtime.maxMemory()) / 1024 / 1024, 2);
        var usage = usedMB / maxMB * 100;

        var usageColor = TextFormat.GREEN;
        if (usage > 85) usageColor = TextFormat.GOLD;

        sender.sendText(TextFormat.GOLD + "Used VM memory: " + usageColor + usedMB + " MB. (" + round(usage, 2) + "%)");
        sender.sendText(TextFormat.GOLD + "Total VM memory: " + TextFormat.RED + totalMB + " MB.");
        sender.sendText(TextFormat.GOLD + "Maximum JVM memory: " + TextFormat.RED + maxMB + " MB.");
    }

    protected static void printOnlinePlayerInfo(CommandSender sender) {
        var server = Server.getInstance();
        var playerColor = TextFormat.GREEN;
        if (((float) server.getOnlinePlayerCount() / (float) server.getNetworkServer().getMaxPlayerCount()) > 0.85) {
            playerColor = TextFormat.GOLD;
        }

        sender.sendText(TextFormat.GOLD + "Players: " + playerColor + server.getOnlinePlayers().size() + TextFormat.GREEN + " online, " +
                          TextFormat.RED + server.getNetworkServer().getMaxPlayerCount() + TextFormat.GREEN + " max. ");
    }

    protected static String toKB(long bytes) {
        return round((bytes / 1024d * 1000), 2) + " KB";
    }

    protected static String toMB(long bytes) {
        return round((bytes / 1024d / 1024 * 1000), 2) + " MB";
    }

    protected static String formatFreq(long hz) {
        if (hz >= 1000000000) {
            return String.format("%.2fGHz", hz / 1000000000.0);
        } else if (hz >= 1000 * 1000) {
            return String.format("%.2fMHz", hz / 1000000.0);
        } else if (hz >= 1000) {
            return String.format("%.2fKHz", hz / 1000.0);
        } else {
            return String.format("%dHz", hz);
        }
    }

    protected static String formatUptime(long uptime) {
        long days = TimeUnit.MILLISECONDS.toDays(uptime);
        uptime -= TimeUnit.DAYS.toMillis(days);
        long hours = TimeUnit.MILLISECONDS.toHours(uptime);
        uptime -= TimeUnit.HOURS.toMillis(hours);
        long minutes = TimeUnit.MILLISECONDS.toMinutes(uptime);
        uptime -= TimeUnit.MINUTES.toMillis(minutes);
        long seconds = TimeUnit.MILLISECONDS.toSeconds(uptime);
        return String.format(UPTIME_FORMAT, days, hours, minutes, seconds);
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    protected static String detectVM() {
        var hardware = SYSTEM_INFO.getHardware();

        // CPU model detection
        var vmVendor = VM_VENDOR.get(hardware.getProcessor().getProcessorIdentifier().getVendor().trim());
        if (vmVendor != null) return vmVendor;

        // MAC address detection
        var networkIFs = hardware.getNetworkIFs();
        for (var nif : networkIFs) {
            var mac = nif.getMacaddr().toUpperCase();
            var oui = mac.length() > 7 ? mac.substring(0, 8) : mac;
            var vmMac = VM_MAC.get(oui);
            if (vmMac != null) return vmMac;
        }

        // Model detection
        var model = hardware.getComputerSystem().getModel();
        for (var vm : VM_MODEL_ARRAY) {
            if (model.contains(vm)) return vm;
        }

        var manufacturer = hardware.getComputerSystem().getManufacturer();
        if ("Microsoft Corporation".equals(manufacturer) && "Virtual Machine".equals(model)) {
            return "Microsoft Hyper-V";
        }

        // Memory manufacturer detection
        if (hardware.getMemory().getPhysicalMemory().getFirst().getManufacturer().equals("QEMU")) {
            return "QEMU";
        }

        // Check Windows system parameters
        // WMI virtual machine query only on Windows
        if (System.getProperties().getProperty("os.name").toUpperCase().contains("WINDOWS")) {
            var computerSystemQuery = new WbemcliUtil.WmiQuery("Win32_ComputerSystem", ComputerSystemEntry.class);
            var result = WmiQueryHandler.createInstance().queryWMI(computerSystemQuery);
            var tmp = result.getValue(ComputerSystemEntry.HYPERVISORPRESENT, 0);
            if (tmp != null && tmp.toString().equals("true")) {
                return "Hyper-V";
            }
        } else {
            // Check for Docker container
            // Docker check only on non-Windows systems
            var file = new File("/.dockerenv");
            if (file.exists()) return "Docker Container";

            var cgroupFile = new File("/proc/1/cgroup");
            if (cgroupFile.exists()) {
                try (var lineStream = Files.lines(cgroupFile.toPath())) {
                    var searchResult = lineStream.filter(line -> line.contains("docker") || line.contains("lxc"));
                    if (searchResult.findAny().isPresent()) return "Docker Container";
                } catch (IOException e) {
                    log.error("Error while checking docker", e);
                }
            }
        }

        return null;
    }

    @Override
    public void prepareCommandTree(CommandTree tree) {
        tree.getRoot()
                .bool("full", false)
                .optional()
                .exec(context -> {
                    boolean full = context.getResult(0);
                    var sender = context.getSender();
                    sender.sendText(TextFormat.GREEN + "---- " + TextFormat.WHITE + "Server Status" + TextFormat.GREEN + " ----");
                    printUpTimeInfo(sender);
                    printMemoryUsageInfo(sender);
                    printOnlinePlayerInfo(sender);
                    printWorldInfo(sender);
                    if (full) {
                        printOperationSystemAndJVMInfo(sender);
                        printNetworkInfo(sender);
                        printCPUInfo(sender);
                        printOperationSystemMemoryInfo(sender);
                    }
                    return context.success();
                });
    }

    protected enum ComputerSystemEntry {
        HYPERVISORPRESENT
    }
}
