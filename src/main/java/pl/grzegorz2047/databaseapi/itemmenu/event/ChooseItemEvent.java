package pl.grzegorz2047.databaseapi.itemmenu.event;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class ChooseItemEvent extends Event implements Cancellable {
    private final int slot;
    private Player player;
    private ItemStack clicked;
    private static final HandlerList handlers = new HandlerList();
    private int size;
    private String title;
    private boolean cancelled = false;
    Inventory inventory;

    public ChooseItemEvent(String title, int size, Inventory inventory, ItemStack clicked, Player p, int slot) {
        this.title = title;
        this.size = size;
        this.inventory = inventory;
        this.clicked = clicked;
        this.player = p;
        this.slot = slot;
    }

    public Player getPlayer() {
        return this.player;
    }

    public int getSize() {
        return this.size;
    }

    public String getTitle() {
        return this.title;
    }

    public Inventory getInventory() {
        return this.inventory;
    }

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    public boolean isCancelled() {
        return this.cancelled;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    public ItemStack getClicked() {
        return this.clicked;
    }

    public int getSlot() {
        return this.slot;
    }
}
