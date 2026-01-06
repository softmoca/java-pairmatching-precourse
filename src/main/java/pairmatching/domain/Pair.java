package pairmatching.domain;

import java.util.ArrayList;
import java.util.List;

public class Pair {

    private final List<String> names;

    public Pair(List<String> names) {
        this.names = names;
    }

    public int getCount() {
        return names.size();
    }

    public boolean isExist(String name1, String name2) {
        return names.contains(name1) && names.contains(name2);
    }

    public String getNameByIdx(int idx) {
        return names.get(idx);
    }

    public List<String> getNames() {
        return new ArrayList<>(names);
    }

}
