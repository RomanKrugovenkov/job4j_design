package ru.job4j.ood.isp.menu;

import java.util.*;

public class SimpleMenu implements Menu {

    private final List<MenuItem> rootElements = new ArrayList<>();

    private Optional<ItemInfo> findItem(String name) {
        DFSIterator dfsIterator = new DFSIterator();
        while (dfsIterator.hasNext()) {
            ItemInfo item = dfsIterator.next();
            if (name.equals(item.menuItem.getName())) {
                return Optional.of(item);
            }
        }
        return Optional.empty();
    }

    @Override
    public boolean add(String parentName, String childName, ActionDelegate actionDelegate) {
        boolean rsl = true;
        if (parentName.equals(ROOT)) {
            rootElements.add(new SimpleMenuItem(childName, actionDelegate));
        } else if (findItem(parentName).isPresent()) {
            var parentItemInfo = findItem(parentName).get();
            parentItemInfo.menuItem.getChildren().add(new SimpleMenuItem(childName, actionDelegate));
        } else {
            rsl = false;
        }
        return rsl;
    }

    @Override
    public Optional<MenuItemInfo> select(String itemName) {
        Optional<MenuItemInfo> rsl = Optional.empty();
        if (findItem(itemName).isPresent()) {
            var itemInfo = findItem(itemName).get();
            rsl = Optional.of(new MenuItemInfo(itemInfo.menuItem, itemInfo.number));
        }
        return rsl;
    }

    @Override
    public Iterator<MenuItemInfo> iterator() {
        DFSIterator dfsIterator = new DFSIterator();
        List<MenuItemInfo> list = new ArrayList<>();
        while (dfsIterator.hasNext()) {
            var itemInfo = dfsIterator.next();
            list.add(new MenuItemInfo(itemInfo.menuItem, itemInfo.number));
        }
        return list.iterator();
    }

    private static class SimpleMenuItem implements MenuItem {

        private String name;
        private List<MenuItem> children = new ArrayList<>();
        private ActionDelegate actionDelegate;

        public SimpleMenuItem(String name, ActionDelegate actionDelegate) {
            this.name = name;
            this.actionDelegate = actionDelegate;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public List<MenuItem> getChildren() {
            return children;
        }

        @Override
        public ActionDelegate getActionDelegate() {
            return actionDelegate;
        }
    }

    private class DFSIterator implements Iterator<ItemInfo> {

        Deque<MenuItem> stack = new LinkedList<>();
        Deque<String> numbers = new LinkedList<>();

        DFSIterator() {
            int number = 1;
            for (MenuItem item : rootElements) {
                stack.addLast(item);
                numbers.addLast(String.valueOf(number++).concat("."));
            }
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public ItemInfo next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            MenuItem current = stack.removeFirst();
            String lastNumber = numbers.removeFirst();
            List<MenuItem> children = current.getChildren();
            int currentNumber = children.size();
            for (var i = children.listIterator(children.size()); i.hasPrevious();) {
                stack.addFirst(i.previous());
                numbers.addFirst(lastNumber.concat(String.valueOf(currentNumber--)).concat("."));
            }
            return new ItemInfo(current, lastNumber);
        }

    }

    private class ItemInfo {

        MenuItem menuItem;
        String number;

        public ItemInfo(MenuItem menuItem, String number) {
            this.menuItem = menuItem;
            this.number = number;
        }
    }
}
