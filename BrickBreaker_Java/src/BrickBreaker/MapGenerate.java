package BrickBreaker;

import java.awt.*;

public class MapGenerate {
    public int map[][];
    public int brickWIdth;
    public int brickHeight;

    public MapGenerate(int row, int col) {
        map = new int[row][col];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                map[i][j] = 1; //detects if the brick has been intersected
            }
        }

        brickWIdth = 540 / col;
        brickHeight = 150 / row;
    }

    public void drawBrick(Graphics2D g) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] > 0) {
                    g.setColor(Color.yellow);
                    g.fillRect(j * brickWIdth + 80, i * brickHeight + 50, brickWIdth, brickHeight);

                    g.setStroke(new BasicStroke(3));
                    g.setColor(Color.black);
                    g.drawRect(j * brickWIdth + 80, i * brickHeight + 50, brickWIdth, brickHeight);

                }
            }
        }
    }

    public void setBrickValue(int value, int row, int col) {
        map[row][col] = value;
    }
}
