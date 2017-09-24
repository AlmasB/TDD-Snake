package com.almasb.snake;

import com.almasb.fxgl.app.DSLKt;
import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.settings.GameSettings;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

import static com.almasb.fxgl.app.DSLKt.onKeyDown;

/**
 * @author Almas Baimagambetov (almaslvl@gmail.com)
 */
public class SnakeApp extends GameApplication {

    private static final int GAME_SIZE = 24;
    private static final int TILE_SIZE = 30;

    @Override
    protected void initSettings(GameSettings settings) {
        settings.setCloseConfirmation(false);
        settings.setProfilingEnabled(false);
        settings.setMenuEnabled(false);
        settings.setIntroEnabled(false);
        settings.setFullScreen(false);

        settings.setWidth(TILE_SIZE * GAME_SIZE);
        settings.setHeight(TILE_SIZE * GAME_SIZE);
    }

    private Game game;

    @Override
    protected void initInput() {
        onKeyDown(KeyCode.W, "UP", () -> game.setDirection(Direction.UP));
        onKeyDown(KeyCode.S, "DOWN", () -> game.setDirection(Direction.DOWN));
        onKeyDown(KeyCode.A, "LEFT", () -> game.setDirection(Direction.LEFT));
        onKeyDown(KeyCode.D, "RIGHT", () -> game.setDirection(Direction.RIGHT));
    }

    @Override
    protected void initGame() {
        game = new Game(GAME_SIZE);
    }

    private double t = 0;

    @Override
    protected void onUpdate(double tpf) {
        t += tpf;

        if (t > 0.1) {
            t = 0;
            game.update();

            if (game.isOver()) {
                getDisplay().showMessageBox("Game over", this::exit);
            }
        }

        render();
    }

    private void render() {
        GraphicsContext g = getGameScene().getGraphicsContext();

        g.setFill(Color.BLUE);
        game.getSnake().getBody().forEach(p -> {
            g.fillRect(p.getX() * TILE_SIZE, p.getY() * TILE_SIZE, TILE_SIZE, TILE_SIZE);
        });

        g.setFill(Color.YELLOW);
        g.fillRect(game.getFood().getPosition().getX() * TILE_SIZE, game.getFood().getPosition().getY() * TILE_SIZE, TILE_SIZE, TILE_SIZE);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
