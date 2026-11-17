package dev.evokerking.evokerGUI.listeners;

import dev.evokerking.evokerGUI.menu.Menu;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public class inventory implements Listener {
    @EventHandler
    public void onClick(InventoryClickEvent e) {
        final Inventory clicked = e.getClickedInventory();

        if(clicked == null) return;

        if(!(clicked.getHolder() instanceof final Menu menu)) {
            //Checks if the inventory is a menu we made if not then we just return
            return;
        }

        //Else if it is one of our menu's then we just cancel the event
        e.setCancelled(true);
        menu.click((Player) e.getWhoClicked(), e.getSlot());
    }
}
