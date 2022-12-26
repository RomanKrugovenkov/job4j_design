package ru.job4j.question;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        Info rsl = new Info(0, 0, 0);
        Set<User> tempPrev = new HashSet<>(previous);
        Set<User> tempCur = new HashSet<>(current);
        tempPrev.removeAll(current);
        tempCur.removeAll(previous);
        var itPrev = tempPrev.iterator();
        var itCur = tempCur.iterator();
        while (itPrev.hasNext()) {
            var userPr = itPrev.next();
            while (itCur.hasNext()) {
                var userCur = itCur.next();
                if (Objects.equals(userPr, userCur)) {
                    itCur.remove();
                    itPrev.remove();
                } else if (userPr.getId() == userCur.getId()) {
                    rsl.setChanged(rsl.getChanged() + 1);
                    itPrev.remove();
                    itCur.remove();
                }
            }
        }
        rsl.setDeleted(tempPrev.size());
        rsl.setAdded(tempCur.size());
        return rsl;
    }
}
