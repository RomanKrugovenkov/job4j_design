package ru.job4j.ood.isp.menu;

public class MenuOutPrinter implements MenuPrinter {

    @Override
    public void print(Menu menu) {
        var iteratorMII = menu.iterator();
        while (iteratorMII.hasNext()) {
            var item = iteratorMII.next();
            for (int i = item.getNumber().length() - 2; i > 0; i--) {
                System.out.print(" ");
            }
            System.out.println(item.getNumber() + item.getName());
        }

    }
}
