package com.shawckz.dankboard.frontend;

import org.bukkit.ChatColor;

public enum FLabel {

    ENDER_PEARL("&cEnderPearl&7: &6", FLabelType.TIMER),
    SPAWN_TAG("&aSpawn Tag&7: &6", FLabelType.TIMER),
    ARMOR_CLASS("&9Armor Class&7: &6", FLabelType.LABEL);
    //Put labels here, feel free to move the ones above as they are examples ^


    private final String key;
    private final FLabelType labelType;

    FLabel(String key, FLabelType labelType) {
        this.key = key;
        this.labelType = labelType;
    }

    public String getKey() {
        return ChatColor.translateAlternateColorCodes('&', key);
    }

    public FLabelType getLabelType() {
        return labelType;
    }
}
