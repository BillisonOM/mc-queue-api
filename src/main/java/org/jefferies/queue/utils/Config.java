package org.jefferies.queue.utils;

import lombok.Getter;
import org.bukkit.*;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Config implements ConfigurationSection {

    private File file;
    @Getter
    private YamlConfiguration config;

    public Config(String name, JavaPlugin plugin) {
        file = new File(plugin.getDataFolder(), name);
        if (!file.exists()) {
            file.getParentFile().mkdir();
            plugin.saveResource(name, true);
        }
        config = new YamlConfiguration();
        try {
            config.load(file);
        } catch (IOException | InvalidConfigurationException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public Set<String> getKeys(boolean b) {
        return config.getKeys(b);
    }

    @Override
    public Map<String, Object> getValues(boolean b) {
        return config.getValues(b);
    }

    @Override
    public boolean contains(String s) {
        return config.contains(s);
    }

    @Override
    public boolean isSet(String s) {
        return config.isSet(s);
    }

    @Override
    public String getCurrentPath() {
        return config.getCurrentPath();
    }

    @Override
    public String getName() {
        return config.getName();
    }

    @Override
    public Configuration getRoot() {
        return config.getRoot();
    }

    @Override
    public ConfigurationSection getParent() {
        return config.getParent();
    }

    @Override
    public Object get(String s) {
        return config.get(s);
    }

    @Override
    public Object get(String s, Object o) {
        return config.get(s, o);
    }

    @Override
    public void set(String s, Object o) {
        config.set(s, o);
    }

    @Override
    public ConfigurationSection createSection(String s) {
        return config.createSection(s);
    }

    @Override
    public ConfigurationSection createSection(String s, Map<?, ?> map) {
        return config.createSection(s, map);
    }

    @Override
    public String getString(String s) {
        return ChatColor.translateAlternateColorCodes('&', config.getString(s));
    }

    @Override
    public String getString(String s, String s1) {
        return config.getString(s, s1);
    }

    @Override
    public boolean isString(String s) {
        return config.isString(s);
    }

    @Override
    public int getInt(String s) {
        return config.getInt(s);
    }

    @Override
    public int getInt(String s, int i) {
        return config.getInt(s, i);
    }

    @Override
    public boolean isInt(String s) {
        return config.isInt(s);
    }

    @Override
    public boolean getBoolean(String s) {
        return config.getBoolean(s);
    }

    @Override
    public boolean getBoolean(String s, boolean b) {
        return config.getBoolean(s, b);
    }

    @Override
    public boolean isBoolean(String s) {
        return config.isBoolean(s);
    }

    @Override
    public double getDouble(String s) {
        return config.getDouble(s);
    }

    @Override
    public double getDouble(String s, double v) {
        return config.getDouble(s, v);
    }

    @Override
    public boolean isDouble(String s) {
        return config.isDouble(s);
    }

    @Override
    public long getLong(String s) {
        return config.getLong(s);
    }

    @Override
    public long getLong(String s, long l) {
        return config.getLong(s, l);
    }

    @Override
    public boolean isLong(String s) {
        return config.isLong(s);
    }

    @Override
    public List<?> getList(String s) {
        return config.getList(s);
    }

    @Override
    public List<?> getList(String s, List<?> list) {
        return config.getList(s, list);
    }

    @Override
    public boolean isList(String s) {
        return config.isList(s);
    }

    @Override
    public List<String> getStringList(String s) {
        return config.getStringList(s);
    }

    @Override
    public List<Integer> getIntegerList(String s) {
        return config.getIntegerList(s);
    }

    @Override
    public List<Boolean> getBooleanList(String s) {
        return config.getBooleanList(s);
    }

    @Override
    public List<Double> getDoubleList(String s) {
        return config.getDoubleList(s);
    }

    @Override
    public List<Float> getFloatList(String s) {
        return config.getFloatList(s);
    }

    @Override
    public List<Long> getLongList(String s) {
        return config.getLongList(s);
    }

    @Override
    public List<Byte> getByteList(String s) {
        return config.getByteList(s);
    }

    @Override
    public List<Character> getCharacterList(String s) {
        return config.getCharacterList(s);
    }

    @Override
    public List<Short> getShortList(String s) {
        return config.getShortList(s);
    }

    @Override
    public List<Map<?, ?>> getMapList(String s) {
        return config.getMapList(s);
    }

    @Override
    public Vector getVector(String s) {
        return config.getVector(s);
    }

    @Override
    public Vector getVector(String s, Vector vector) {
        return config.getVector(s, vector);
    }

    @Override
    public boolean isVector(String s) {
        return config.isVector(s);
    }

    @Override
    public OfflinePlayer getOfflinePlayer(String s) {
        return config.getOfflinePlayer(s);
    }

    @Override
    public OfflinePlayer getOfflinePlayer(String s, OfflinePlayer offlinePlayer) {
        return config.getOfflinePlayer(s, offlinePlayer);
    }

    @Override
    public boolean isOfflinePlayer(String s) {
        return config.isOfflinePlayer(s);
    }

    @Override
    public ItemStack getItemStack(String s) {
        return config.getItemStack(s);
    }

    @Override
    public ItemStack getItemStack(String s, ItemStack itemStack) {
        return config.getItemStack(s, itemStack);
    }

    @Override
    public boolean isItemStack(String s) {
        return config.isItemStack(s);
    }

    @Override
    public Color getColor(String s) {
        return config.getColor(s);
    }

    @Override
    public Color getColor(String s, Color color) {
        return config.getColor(s, color);
    }

    @Override
    public boolean isColor(String s) {
        return config.isColor(s);
    }

    @Override
    public ConfigurationSection getConfigurationSection(String s) {
        return config.getConfigurationSection(s);
    }

    @Override
    public boolean isConfigurationSection(String s) {
        return config.isConfigurationSection(s);
    }

    @Override
    public ConfigurationSection getDefaultSection() {
        return config.getDefaultSection();
    }

    @Override
    public void addDefault(String s, Object o) {
        config.addDefault(s, o);
    }

    public void save() {
        try {
            config.save(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setLocation(String path, Location location) {
        config.set(path + ".X", location.getX());
        config.set(path + ".Y", location.getY());
        config.set(path + ".Z", location.getZ());
        config.set(path + ".WORLD", location.getWorld().getName());
        config.set(path + ".YAW", location.getYaw());
        config.set(path + ".PITCH", location.getPitch());
    }

    public Location getLocation(String path) {
        return new Location(Bukkit.getWorld(config.getString(path + ".WORLD")), config.getDouble(path + ".X"), config.getDouble(path + ".Y"), config.getDouble(path + ".Z"), config.getLong(path + ".YAW"), config.getLong(path + ".PITCH"));
    }

}

