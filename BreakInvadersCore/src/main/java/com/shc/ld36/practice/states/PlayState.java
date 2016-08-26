package com.shc.ld36.practice.states;

import com.shc.ld36.practice.Resources;
import com.shc.ld36.practice.entities.Ball;
import com.shc.ld36.practice.entities.Paddle;
import com.shc.ld36.practice.entities.SpaceShip;
import com.shc.silenceengine.collision.broadphase.DynamicTree2D;
import com.shc.silenceengine.collision.colliders.SceneCollider2D;
import com.shc.silenceengine.core.GameState;
import com.shc.silenceengine.core.SilenceEngine;
import com.shc.silenceengine.graphics.DynamicRenderer;
import com.shc.silenceengine.graphics.cameras.OrthoCam;
import com.shc.silenceengine.graphics.fonts.BitmapFont;
import com.shc.silenceengine.graphics.fonts.BitmapFontRenderer;
import com.shc.silenceengine.graphics.opengl.GLContext;
import com.shc.silenceengine.graphics.opengl.Primitive;
import com.shc.silenceengine.input.Touch;
import com.shc.silenceengine.scene.Scene2D;

import static com.shc.silenceengine.input.Keyboard.*;

/**
 * @author Sri Harsha Chilakapati
 */
public class PlayState extends GameState
{
    public static Scene2D SCENE;

    private boolean fakedKeyInput;

    public static Paddle PADDLE;
    public static Ball   BALL;

    private OrthoCam        camera;
    private SceneCollider2D collider;

    @Override
    public void onEnter()
    {
        SilenceEngine.display.setTitle("BreakInvaders: Get set Go!!!");

        SCENE = new Scene2D();
        collider = new SceneCollider2D(new DynamicTree2D());
        collider.setScene(SCENE);

        PADDLE = new Paddle();
        BALL = new Ball();

        SCENE.entities.add(PADDLE);
        SCENE.entities.add(BALL);

        for (int i = 1; i < 12; i++)
            SCENE.entities.add(new SpaceShip(i++ * Resources.Textures.SPACE_SHIP.getWidth() / 1.5f, 100));

        collider.register(Resources.CollisionTags.BALL, Resources.CollisionTags.SPACE_SHIP);
        collider.register(Resources.CollisionTags.BALL, Resources.CollisionTags.PADDLE);

        camera = new OrthoCam();
        resized();
    }

    @Override
    public void update(float delta)
    {
        // Send fake touch to key events
        if (Touch.isFingerDown(Touch.FINGER_0))
        {
            fakedKeyInput = true;

            // Get the x coordinate of the touch
            final float x = Touch.getFingerPosition(Touch.FINGER_0).x;

            // If the right side of the screen is touched, generate KEY_RIGHT event
            if (x > SilenceEngine.display.getWidth() / 2)
            {
                SilenceEngine.input.postKeyEvent(KEY_RIGHT, true);
                SilenceEngine.input.postKeyEvent(KEY_LEFT, false);
            }
            else
            {
                SilenceEngine.input.postKeyEvent(KEY_RIGHT, false);
                SilenceEngine.input.postKeyEvent(KEY_LEFT, true);
            }
        }
        else if (fakedKeyInput)
        {
            SilenceEngine.input.postKeyEvent(KEY_RIGHT, false);
            SilenceEngine.input.postKeyEvent(KEY_LEFT, false);
            SilenceEngine.input.postKeyEvent(KEY_SPACE, false);

            fakedKeyInput = false;
        }

        SCENE.update(delta);
        collider.checkCollisions();
    }

    @Override
    public void render(float delta)
    {
        DynamicRenderer dynamicRenderer = Resources.Renderers.DYNAMIC;
        Resources.Textures.BACKGROUND.bind(0);
        Resources.Programs.DYNAMIC.use();
        dynamicRenderer.begin(Primitive.TRIANGLE_FAN);
        {
            dynamicRenderer.vertex(0, 0);
            dynamicRenderer.texCoord(0, 0);

            dynamicRenderer.vertex(SilenceEngine.display.getWidth(), 0);
            dynamicRenderer.texCoord(1, 0);

            dynamicRenderer.vertex(SilenceEngine.display.getWidth(), SilenceEngine.display.getHeight());
            dynamicRenderer.texCoord(1, 1);

            dynamicRenderer.vertex(0, SilenceEngine.display.getHeight());
            dynamicRenderer.texCoord(0, 1);
        }
        dynamicRenderer.end();

        Resources.Renderers.SPRITE.begin();
        SCENE.render(delta);
        Resources.Renderers.SPRITE.end();

        BitmapFontRenderer fontRenderer = Resources.Renderers.FONT;
        BitmapFont font = Resources.Fonts.MAIN;

        fontRenderer.begin();
        {
            fontRenderer.render(font, "FPS: " + SilenceEngine.gameLoop.getFPS(), 10, 10);
            fontRenderer.render(font, "\nUPS: " + SilenceEngine.gameLoop.getUPS(), 10, 10);
        }
        fontRenderer.end();
    }

    @Override
    public void resized()
    {
        camera.initProjection(SilenceEngine.display.getWidth(), SilenceEngine.display.getHeight());
        camera.apply();

        GLContext.viewport(0, 0, SilenceEngine.display.getWidth(), SilenceEngine.display.getHeight());
    }
}
