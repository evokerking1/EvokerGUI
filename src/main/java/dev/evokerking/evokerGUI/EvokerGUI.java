package dev.evokerking.evokerGUI;

import dev.evokerking.evokerGUI.listeners.InventoryListener;
import org.bukkit.plugin.Plugin;

public final class EvokerGUI {
    private static boolean initialized = false;

    private EvokerGUI() {
        throw new UnsupportedOperationException("EvokerGUI is a utility class and cannot be instantiated.");
    }

    /**
     * Initializes EvokerGUI and registers its listeners.
     *
     * Call this once from your plugin's onEnable method:
     *
     * EvokerGUI.init(this);
     *
     * @param plugin the plugin using EvokerGUI
     */
    public static void init(Plugin plugin) {
        if (initialized) {
            return;
        }

        plugin.getServer().getPluginManager().registerEvents(new InventoryListener(), plugin);
        initialized = true;
    }

    /**
     * Returns whether EvokerGUI has already been initialized.
     *
     * @return true if initialized, otherwise false
     */
    public static boolean isInitialized() {
        return initialized;
    }
}
