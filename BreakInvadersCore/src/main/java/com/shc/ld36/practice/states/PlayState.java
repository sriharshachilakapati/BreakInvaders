package com.shc.ld36.practice.states;

import com.shc.ld36.practice.Resources;
import com.shc.ld36.practice.entities.Ball;
import com.shc.ld36.practice.entities.Paddle;
import com.shc.silenceengine.collision.broadphase.DynamicTree2D;
import com.shc.silenceengine.collision.colliders.SceneCollider2D;
import com.shc.silenceengine.core.GameState;
import com.shc.silenceengine.core.SilenceEngine;
import com.shc.silenceengine.graphics.cameras.OrthoCam;
import com.shc.silenceengine.graphics.fonts.BitmapFont;
import com.shc.silenceengine.graphics.fonts.BitmapFontRenderer;
import com.shc.silenceengine.graphics.opengl.GLContext;
import com.shc.silenceengine.scene.Scene2D;

/**
 * @author Sri Harsha Chilakapati
 */
public class PlayState extends GameState
{
    private OrthoCam        camera;
    private static Scene2D  scene;
    private SceneCollider2D collider;
    
    public static Paddle    paddle;
    public static Ball      ball;
    
    @Override
    public void onEnter()
    {
        SilenceEngine.display.setTitle("BreakInvaders: Get set Go!!!");

        scene = new Scene2D();
        collider = new SceneCollider2D(new DynamicTree2D());
        collider.setScene(scene);

        paddle = new Paddle();
        ball = new Ball();
        
        scene.entities.add(paddle);
        scene.entities.add(ball);

        collider.register(Resources.CollisionTags.BALL, Resources.CollisionTags.SPACE_SHIP);
        collider.register(Resources.CollisionTags.BALL, Resources.CollisionTags.PADDLE);

        camera = new OrthoCam();
        resized();
    }

    @Override
    public void update(float delta)
    {
        scene.update(delta);
        collider.checkCollisions();
    }

    @Override
    public void render(float delta)
    {
        Resources.Renderers.SPRITE.begin();
        scene.render(delta);
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
