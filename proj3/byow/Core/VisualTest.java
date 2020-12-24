package byow.Core;

import byow.TileEngine.TERenderer;

import static byow.Core.Engine.HEIGHT;
import static byow.Core.Engine.WIDTH;

public class VisualTest {

    public static void testWorldWithHallways() {
        TERenderer ter = new TERenderer();
        System.out.println(System.currentTimeMillis());
        WorldGenerator wg = new WorldGenerator(WIDTH, HEIGHT,
                                               System.currentTimeMillis());
        wg.generateWorld();
        ter.initialize(WIDTH, HEIGHT);
        ter.renderFrame(wg._world._tiles);
    }

    public static void main(String[] args) {
        testWorldWithHallways();
    }
}
