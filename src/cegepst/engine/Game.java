package cegepst.engine;

import java.awt.*;

public abstract class Game {

    private static final int SLEEP = 25;
    private long before;
    private RenderingEngine renderingEngine;
    private boolean playing = true;

    public Game() {
        renderingEngine = new RenderingEngine();
    }

    public abstract void initialize();
    public abstract void conclude();
    public abstract void update();
    public abstract void draw(Buffer buffer);

    public void start() {
        initialize();
        run();
        conclude();
    }

    public void stop() {
        playing = false;
    }

    private void run() {
        renderingEngine.start();
        updateSyncTime();
        while (playing) {
            update();
            draw(renderingEngine.getRenderingBuffer());
            renderingEngine.renderBufferOnScreen();
            sleep();
        }
        renderingEngine.stop();
    }



    private void sleep() {
        try {
            Thread.sleep(getSleepTime());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        updateSyncTime();
    }

    private void updateSyncTime() {
        before = System.currentTimeMillis();
    }

    private long getSleepTime() {
        long sleep = SLEEP - (System.currentTimeMillis() - before);
        if (sleep < 0) {
            sleep = 4;
        }
        return sleep;
    }
}
