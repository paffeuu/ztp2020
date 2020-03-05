package ztp.lista1.view;

import ztp.lista1.controller.ViewListener;
import ztp.lista1.model.Model;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class View {
    private final Model model;
    private Set<ViewListener> listeners;

    public View(Model model) {
        this.model = model;
        this.listeners = new HashSet<>();
    }

    public void showMainView() {
        System.out.println("Dzień dobry");
        System.out.println("Lista dostępnych opcji:");
        System.out.println("[1] Wyświetl listę kursów");
        System.out.println("[2] Wyświetl listę studentów");
    }

    private String readInput() {
        String line = null;
        try(Scanner scanner = new Scanner(System.in)) {
            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
            }
        }
        return line;
    }

    public void addActionListener(ViewListener newViewListener) {
        listeners.add(newViewListener);
    }

    private void notifyActionListeners() {
        this.listeners.forEach(viewListener -> viewListener.actionPerformed(1, ""));
    }


}
