package edu.hw6.task1;

import java.io.Externalizable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class DiskMap implements Map<String, String>, Externalizable {

    private final HashMap<String, String> delegatedMap;

    public DiskMap() {
        delegatedMap = new HashMap<>();
    }

    public DiskMap(Map<String, String> map) {
        this.delegatedMap = new HashMap<>(map);
    }

    @Override
    public int size() {
        return delegatedMap.size();
    }

    @Override
    public boolean isEmpty() {
        return delegatedMap.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return delegatedMap.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return delegatedMap.containsValue(value);
    }

    @Override
    public String get(Object key) {
        return delegatedMap.get(key);
    }

    @Nullable
    @Override
    public String put(String key, String value) {
        return delegatedMap.put(key, value);
    }

    @Override
    public String remove(Object key) {
        return delegatedMap.remove(key);
    }

    @Override
    public void putAll(@NotNull Map<? extends String, ? extends String> m) {
        delegatedMap.putAll(m);
    }

    @Override
    public void clear() {
        delegatedMap.clear();
    }

    @NotNull
    @Override
    public Set<String> keySet() {
        return delegatedMap.keySet();
    }

    @NotNull
    @Override
    public Collection<String> values() {
        return delegatedMap.values();
    }

    @NotNull
    @Override
    public Set<Entry<String, String>> entrySet() {
        return delegatedMap.entrySet();
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        for (Map.Entry<String, String> elem : delegatedMap.entrySet()) {
            out.writeBytes(elem.getKey() + ":" + elem.getValue() + "\n");
        }
        out.close();
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException {
        delegatedMap.clear();
        String line = in.readLine();
        while (line != null) {
            String[] keyValue = line.split(":");
            this.delegatedMap.put(keyValue[0], keyValue[1]);
            line = in.readLine();
        }
        in.close();
    }

    public void saveToFile(File file) throws IOException {
        // Creates new file, if it doesn't exist yet
        this.writeExternal(new ObjectOutputStream(new FileOutputStream(file)));
    }

    public void loadFromFile(File file) throws IOException {
        this.readExternal(new ObjectInputStream(new FileInputStream(file)));
    }

    @Override
    public String toString() {
        return delegatedMap.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DiskMap diskMap = (DiskMap) o;
        return delegatedMap.equals(diskMap.delegatedMap);
    }

    @Override
    public int hashCode() {
        return delegatedMap.hashCode();
    }
}
