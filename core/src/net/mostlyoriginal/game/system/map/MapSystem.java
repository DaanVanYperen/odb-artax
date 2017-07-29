package net.mostlyoriginal.game.system.map;

import com.artemis.BaseSystem;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.utils.Array;
import net.mostlyoriginal.api.system.core.PassiveSystem;
import net.mostlyoriginal.api.utils.MapMask;
import net.mostlyoriginal.game.component.G;

/**
 * @author Daan van Yperen
 */
public class MapSystem extends BaseSystem {

    public TiledMap map;
    private int width;
    private int height;
    private Array<TiledMapTileLayer> layers;
    private boolean isSetup;

    private EntitySpawnerSystem entitySpawnerSystem;

    @Override
    protected void initialize() {
        map = new TmxMapLoader().load("map1.tmx");

        layers = new Array<TiledMapTileLayer>();
        for (MapLayer rawLayer : map.getLayers()) {
            layers.add((TiledMapTileLayer) rawLayer);
        }
        width = layers.get(0).getWidth();
        height = layers.get(0).getHeight();
    }

    public MapMask getMask(String property) {
        return new MapMask(height, width, G.CELL_SIZE, G.CELL_SIZE, layers, property);
    }

    /**
     * Spawn map entities.
     */
    protected void setup() {
        for (TiledMapTileLayer layer : layers) {
            for (int ty = 0; ty < height; ty++) {
                for (int tx = 0; tx < width; tx++) {
                    final TiledMapTileLayer.Cell cell = layer.getCell(tx, ty);
                    if (cell != null) {
                        final MapProperties properties = cell.getTile().getProperties();
                        if (properties.containsKey("entity")) {
                            entitySpawnerSystem.spawnEntity(tx * G.CELL_SIZE, ty * G.CELL_SIZE, properties);
                            layer.setCell(tx, ty, null);
                        }
                    }
                }
            }
        }
    }

    @Override
    protected void processSystem() {
        if (!isSetup) {
            isSetup = true;
            setup();
        }
    }

}