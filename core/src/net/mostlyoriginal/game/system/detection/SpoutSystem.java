package net.mostlyoriginal.game.system.detection;

import com.artemis.Aspect;
import com.artemis.E;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import net.mostlyoriginal.api.component.basic.Pos;
import net.mostlyoriginal.game.component.Spout;
import net.mostlyoriginal.game.system.common.FluidIteratingSystem;

/**
 * @author Daan van Yperen
 */
public class SpoutSystem extends FluidIteratingSystem {

    private ParticleSystem particleSystem;

    public SpoutSystem() {
        super(Aspect.all(Spout.class, Pos.class));
    }

    Vector2 v2 = new Vector2();

    @Override
    protected void process(E e) {
        e.spoutAge(e.spoutAge() + world.delta);
        if (e.spoutAge() >= e.spoutCooldown() + e.spoutSprayDuration()) {
            e.spoutSprayCooldown(0);
            e.spoutAge(0);
        }
        if (e.spoutAge() >= e.spoutCooldown()) {
            e.spoutSprayCooldown(e.spoutSprayCooldown() - world.delta);
            if (e.spoutSprayCooldown() <= 0) {
                e.spoutSprayCooldown(e.spoutSprayInterval());
                float angle = e.spoutAngle() + MathUtils.random(-2f,2f);
                v2.set(10,0).setAngle(angle).add(e.posX() + e.boundsCx(),e.posY() + e.boundsCy());

                particleSystem.acid(v2.x, v2.y, angle, e.spoutAngle() == 90 ? 50 : 20);
            }
        }
    }
}
