package dev.evokerking.evokerGUI.listeners;

import dev.evokerking.evokerGUI.menu.Menu;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public final class InventoryListener implements Listener {
    @EventHandler
    public void onClick(InventoryClickEvent event) {
        final Inventory clicked = event.getClickedInventory();

        if (clicked == null) {
            return;
        }

        if (!(clicked.getHolder() instanceof final Menu menu)) {
            return;
        }

        event.setCancelled(true);
        menu.click((Player) event.getWhoClicked(), event.getSlot());
    }
}
