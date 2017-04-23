package net.mostlyoriginal.game.component;

import com.artemis.Component;
import com.badlogic.gdx.math.MathUtils;

/**
 * @author Daan van Yperen
 */
public class Star extends Component {
    public Star() {
    }
    public final String animId[] = new String[6];
    public final float speedFactor = 1f + MathUtils.random(-0.2f,0.5f);
    public float blinkTimer = MathUtils.random(0f,10f);

    public void set(int kind) {
        animId[0] = "star-" + kind + "-0";
        animId[1] = "star-" + kind + "-1";
        animId[2] = "star-" + kind + "-2";
        animId[3] = "star-" + kind + "-3";
        animId[4] = "star-" + kind + "-4";
        animId[5] = "star-" + kind + "-5";
    }
}
