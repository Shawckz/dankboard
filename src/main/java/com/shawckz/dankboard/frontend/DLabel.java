package com.shawckz.dankboard.frontend;

import org.bukkit.ChatColor;

public enum DLabel {

    ENDER_PEARL("&cEnderPearl&7: &6", DLabelType.TIMER),
    SPAWN_TAG("&aSpawn Tag&7: &6", DLabelType.TIMER),
    ARMOR_CLASS("&9Armor Class&7: &6", DLabelType.LABEL);
    //Put labels here, feel free to move the ones above as they are examples ^


    private final String key;
    private final DLabelType labelType;

    DLabel(String key, DLabelType labelType) {
        this.key = key;
        this.labelType = labelType;
    }

    public String getKey() {
        return ChatColor.translateAlternateColorCodes('&', key);
    }

    public DLabelType getLabelType() {
        return labelType;
    }
}
