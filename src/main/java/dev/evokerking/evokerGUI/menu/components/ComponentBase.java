package dev.evokerking.evokerGUI.menu.components;

import dev.evokerking.evokerGUI.menu.SimpleMenu;

public interface ComponentBase {
    void setProviderName(String name);

    String getProviderName();

    void apply(SimpleMenu menu);
}
