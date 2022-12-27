package ru.job4j.question;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        Info rsl = new Info(0, 0, 0);
        Map<Integer, String> mapPrev = new HashMap<>();
        for (var user : previous) {
            mapPrev.put(user.getId(), user.getName());
        }
        Set<User> tempCur = new HashSet<>(current);
        var itCur = tempCur.iterator();
        while (itCur.hasNext()) {
            var userCur = itCur.next();
            if (mapPrev.containsKey(userCur.getId())
            && mapPrev.containsValue(userCur.getName())) {
                mapPrev.remove(userCur.getId());
                itCur.remove();
            } else if (mapPrev.containsKey(userCur.getId())) {
                mapPrev.remove(userCur.getId());
                itCur.remove();
                rsl.setChanged(rsl.getChanged() + 1);
            }
        }
        rsl.setDeleted(mapPrev.size());
        rsl.setAdded(tempCur.size());
        return rsl;
    }
}
