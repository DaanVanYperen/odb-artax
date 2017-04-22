package net.mostlyoriginal.game.system.planet;

import com.artemis.E;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.TextureData;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import net.mostlyoriginal.api.system.core.PassiveSystem;
import net.mostlyoriginal.game.component.Planet;
import net.mostlyoriginal.game.component.PlanetCell;
import net.mostlyoriginal.game.system.planet.cells.LavaCellSimulator;
import net.mostlyoriginal.game.system.planet.cells.StaticCellSimulator;
import net.mostlyoriginal.game.system.view.GameScreenAssetSystem;

/**
 * @author Daan van Yperen
 */
public class PlanetCreationSystem extends PassiveSystem {

    GameScreenAssetSystem gameScreenAssetSystem;

    @Override
    protected void initialize() {
        super.initialize();
        Planet planet = E.E()
                .planet()
                .pos(0, 0)
                .getPlanet();

        populate(planet);
    }

    private void populate(Planet planet) {
        TextureData textureData = ((TextureRegion) gameScreenAssetSystem.get("dancingman").getKeyFrame(0)).getTexture().getTextureData();
        textureData.prepare();
        Pixmap pixmap = textureData.consumePixmap();
        for (int y = 0; y < Planet.SIMULATION_HEIGHT; y++) {
            for (int x = 0; x < Planet.SIMULATION_WIDTH; x++) {
                planet.grid[y][x] = formCell(planet, y, x, pixmap.getPixel(x, y));
            }
        }
        pixmap.dispose();
    }

    Color c = new Color();

    private PlanetCell formCell(Planet planet, int y, int x, int color) {

        PlanetCell cell = new PlanetCell();
        cell.x = x;
        cell.y = y;
        cell.color = color;
        c.set(color);

        if (c.r > 0.4f && c.g < 0.9f && c.b < 0.1f) {
            cell.type = PlanetCell.CellType.LAVA;
        }

        return cell;
    }
}
