package com.example.hms1.utils.enums;

public enum SCENE_IDENTIFIER {
    HELLO("hello-view.fxml"),

    MAINPAGE("main-page.fxml"),

    MEDICPAGE("medic-page.fxml"),

    ER("er-view.fxml"),

    IC("ic-room.fxml"),
    MORGUE("morgue-room.fxml"),
    ADMIN("admin-view.fxml"),
    ERP("erp-view.fxml"),
    PUBLIV("public-view.fxml"),
    ICP("icp-view.fxml"),
    MORGUEP("morguep-view.fxml"),
    MEDIC("medic-login.fxml");

    public final String label;

    SCENE_IDENTIFIER( String label ) {
        this.label = label;
    }
}
