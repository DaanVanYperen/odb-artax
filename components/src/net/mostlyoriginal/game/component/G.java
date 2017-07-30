package net.mostlyoriginal.game.component;

/**
 * @author Daan van Yperen
 */
public class G {
    public static final boolean PRODUCTION = false;

    public static final boolean DEBUG_SKIP_INTRO = (!PRODUCTION && false);
    public static final boolean DEBUG_NO_MUSIC = (!PRODUCTION && false);
    public static final int DEBUG_STARTING_LEVEL = !PRODUCTION ? 1 : 1;
    public static final int CELL_SIZE = 16;

    public static final int SIMULATION_WIDTH = 220 + 50;
    public static final int SIMULATION_HEIGHT = 220 + 50;

    public static final int LAYER_PLAYER = 1000;
    public static final int LAYER_PLAYER_ROBOT = 900;
    public static final float PACER_FOLLOW_DISTANCE = 160;

    public static int CAMERA_ZOOM = 2;
    public static final int SCREEN_WIDTH = SIMULATION_WIDTH * 2 * CAMERA_ZOOM;
    private static int CARD_HEIGHT = 90;
    private static int MARGIN_BETWEEN_CARD_AND_SIM = 10;

    private static final int MARGIN_BETWEEN_SIM_AND_ROOF = 20;
    public static final int SCREEN_HEIGHT = (SIMULATION_HEIGHT + CARD_HEIGHT + MARGIN_BETWEEN_CARD_AND_SIM + MARGIN_BETWEEN_SIM_AND_ROOF) * CAMERA_ZOOM;
    public static int level = 1;
}
