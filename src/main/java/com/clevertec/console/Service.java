package com.clevertec.console;

public class Service {
    private static final MenuMain menuMain = MenuMain.getInstance();
    public static void main(String[] args) {
        InitializationManager.initialize();
        MenuMain.showMainMenu();

    }
}
