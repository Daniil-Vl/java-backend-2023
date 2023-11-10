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

    private final HashMap<String, String> map;

    public DiskMap() {
        this.map = new HashMap<>();
    }

    public DiskMap(Map<String, String> map) {
        this.map = new HashMap<>(map);
    }

    /**
     * Returns the number of key-value mappings in this map.  If the
     * map contains more than {@code Integer.MAX_VALUE} elements, returns
     * {@code Integer.MAX_VALUE}.
     *
     * @return the number of key-value mappings in this map
     */
    @Override
    public int size() {
        return this.map.size();
    }

    /**
     * Returns {@code true} if this map contains no key-value mappings.
     *
     * @return {@code true} if this map contains no key-value mappings
     */
    @Override
    public boolean isEmpty() {
        return this.map.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return this.map.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return this.map.containsValue(value);
    }

    @Override
    public String get(Object key) {
        return this.map.get(key);
    }

    @Nullable
    @Override
    public String put(String key, String value) {
        return this.map.put(key, value);
    }

    @Override
    public String remove(Object key) {
        return this.map.remove(key);
    }

    @Override
    public void putAll(@NotNull Map<? extends String, ? extends String> m) {
        this.map.putAll(m);
    }

    @Override
    public void clear() {
        this.map.clear();
    }

    @NotNull
    @Override
    public Set<String> keySet() {
        return this.map.keySet();
    }

    @NotNull
    @Override
    public Collection<String> values() {
        return this.map.values();
    }

    @NotNull
    @Override
    public Set<Entry<String, String>> entrySet() {
        return this.map.entrySet();
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        for (Map.Entry<String, String> elem : this.map.entrySet()) {
            out.writeBytes(elem.getKey() + ":" + elem.getValue() + "\n");
        }
        out.close();
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException {
        String line = in.readLine();
        String key;
        String value;
        this.map.clear();
        while (line != null) {
            key = line.split(":")[0];
            value = line.split(":")[1];
            this.map.put(key, value);
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

    @Override public String toString() {
        return map.toString();
    }

    @Override public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DiskMap diskMap = (DiskMap) o;
        return this.map.equals(diskMap.map);
    }

    @Override
    public int hashCode() {
        return this.map.hashCode();
    }
}
