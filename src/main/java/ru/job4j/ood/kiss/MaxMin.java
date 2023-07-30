package ru.job4j.ood.kiss;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MaxMin {
    public <T> T max(List<T> value, Comparator<T> comparator) {
        List<T> list = sort(value, comparator);
        return list.get(list.size() - 1);
    }

    public <T> T min(List<T> value, Comparator<T> comparator) {
        List<T> list = sort(value, comparator);
        return list.get(0);
    }

    private <T> List<T> sort(List<T> value, Comparator<T> comparator) {
        List<T> list = new ArrayList<>();
        list.add(value.get(0));
        for (int i = 1; i < value.size(); i++) {
            for (int j = 0; j < list.size(); j++) {
                int comp = comparator.compare(value.get(i), list.get(j));
                if (comp < 0) {
                    list.add(j, value.get(i));
                    break;
                } else if (comp == 0) {
                    list.add(j + 1, value.get(i));
                    break;
                } else if (j == list.size() - 1) {
                    list.add(value.get(i));
                }
            }
        }
        return list;
    }
}
