package repository;

import entity.Town;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TownsRepository {
    private final Map<String, Town> towns;

    public TownsRepository() {
        this.towns = new HashMap<>();
    }

    public List<Town> findAll() {
        return new ArrayList<>(towns.values());
    }

    public void save(Town town) {
        towns.put(town.getId(), town);
    }

    public void remove(String id) {
        towns.remove(id);
    }

    public Town findOne(String id) {
        return towns.get(id);
    }
}
