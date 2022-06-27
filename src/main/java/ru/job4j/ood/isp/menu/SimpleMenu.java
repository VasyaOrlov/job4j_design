package ru.job4j.ood.isp.menu;

import java.util.*;

public class SimpleMenu implements Menu {

    private final List<MenuItem> rootElements = new ArrayList<>();

    @Override
    public boolean add(String parentName, String childName, ActionDelegate actionDelegate) {
        boolean rsl = false;
        boolean checkChild = findItem(childName).isEmpty();
        if (checkChild && Objects.equals(parentName, Menu.ROOT)) {
            MenuItem item = new SimpleMenuItem(childName, actionDelegate);
            rootElements.add(item);
            rsl = true;
        } else if (checkChild) {
            Optional<ItemInfo> parent = findItem(parentName);
            if (parent.isPresent()) {
                parent.map(x -> x.menuItem.getChildren())
                        .ifPresent(x -> x.add(new SimpleMenuItem(childName, actionDelegate)));
                rsl = true;
            }
        }
        return rsl;
    }

    @Override
    public Optional<MenuItemInfo> select(String itemName) {
        Optional<MenuItemInfo> menuItemInfo = Optional.empty();
        Optional<ItemInfo> optionalItemInfo = findItem(itemName);
        if (optionalItemInfo.isPresent()) {
            ItemInfo itemInfo = optionalItemInfo.get();
            menuItemInfo = Optional.of(new MenuItemInfo(itemInfo.menuItem, itemInfo.number));
        }
        return menuItemInfo;
    }

    @Override
    public Iterator<MenuItemInfo> iterator() {
        return new MenuItemInfoIterator();
    }

    private Optional<ItemInfo> findItem(String name) {
        DFSIterator dit = new DFSIterator();
        Optional<ItemInfo> rsl = Optional.empty();
        while (dit.hasNext()) {
            ItemInfo itemInfo = dit.next();
            if (name.equals(itemInfo.menuItem.getName())) {
                rsl = Optional.of(itemInfo);
                break;
            }
        }
        return rsl;
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

    private class MenuItemInfoIterator implements Iterator<MenuItemInfo> {

        Deque<MenuItem> stack = new LinkedList<>();

        Deque<String> numbers = new LinkedList<>();

        MenuItemInfoIterator() {
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
        public MenuItemInfo next() {
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
            return new MenuItemInfo(current, lastNumber);
        }
    }
}