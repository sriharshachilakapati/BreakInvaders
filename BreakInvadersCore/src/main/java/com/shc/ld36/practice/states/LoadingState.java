package com.shc.ld36.practice.states;

import com.shc.ld36.practice.BreakInvaders;
import com.shc.ld36.practice.Resources;
import com.shc.silenceengine.audio.Sound;
import com.shc.silenceengine.core.Game;
import com.shc.silenceengine.core.GameState;
import com.shc.silenceengine.core.ResourceLoader;
import com.shc.silenceengine.core.SilenceEngine;
import com.shc.silenceengine.graphics.DynamicRenderer;
import com.shc.silenceengine.graphics.SpriteRenderer;
import com.shc.silenceengine.graphics.fonts.BitmapFont;
import com.shc.silenceengine.graphics.fonts.BitmapFontRenderer;
import com.shc.silenceengine.graphics.opengl.Primitive;
import com.shc.silenceengine.graphics.opengl.Texture;
import com.shc.silenceengine.graphics.programs.DynamicProgram;
import com.shc.silenceengine.io.FilePath;

/**
 * @author Sri Harsha Chilakapati
 */
public class LoadingState extends GameState
{
    private Game           gameInstance;
    private ResourceLoader resourceLoader;

    // Handles for resources to be fetched after loading is done
    private long ballID;
    private long paddleID;
    private long spaceShipID;
    private long bgID;

    private long soundThemeID;
    private long soundBounceID;
    private long soundHurtID;
    private long soundDeathID;

    private long fontID;

    public LoadingState(BreakInvaders gameInstance)
    {
        this.gameInstance = gameInstance;
        this.resourceLoader = new ResourceLoader();
    }

    @Override
    public void onEnter()
    {
        // Define the resources
        ballID = resourceLoader.define(Texture.class, FilePath.getResourceFile("assets/breakinvaders/textures/ball.png"));
        paddleID = resourceLoader.define(Texture.class, FilePath.getResourceFile("assets/breakinvaders/textures/paddle.png"));
        spaceShipID = resourceLoader.define(Texture.class, FilePath.getResourceFile("assets/breakinvaders/textures/space_ship.png"));
        bgID = resourceLoader.define(Texture.class, FilePath.getResourceFile("assets/breakinvaders/textures/background.png"));

        soundThemeID = resourceLoader.define(Sound.class, FilePath.getResourceFile("assets/breakinvaders/sounds/theme_loop.ogg"));
        soundBounceID = resourceLoader.define(Sound.class, FilePath.getResourceFile("assets/breakinvaders/sounds/bounce.ogg"));
        soundHurtID = resourceLoader.define(Sound.class, FilePath.getResourceFile("assets/breakinvaders/sounds/alien_hurt.ogg"));
        soundDeathID = resourceLoader.define(Sound.class, FilePath.getResourceFile("assets/breakinvaders/sounds/alien_death.ogg"));

        fontID = resourceLoader.define(BitmapFont.class, FilePath.getResourceFile("engine_resources/fonts/roboto32px.fnt"));

        DynamicProgram.create(program ->
        {
            Resources.Programs.DYNAMIC = program;
            program.use();

            Resources.Renderers.DYNAMIC = new DynamicRenderer(50);
            program.applyToRenderer(Resources.Renderers.DYNAMIC);

            SpriteRenderer.create(spriteRenderer ->
            {
                Resources.Renderers.SPRITE = spriteRenderer;

                BitmapFontRenderer.create(fontRenderer ->
                {
                    Resources.Renderers.FONT = fontRenderer;

                    // Start loading the resources
                    resourceLoader.start();
                });
            });
        });

        SilenceEngine.display.setTitle("BreakInvaders: Loading please wait...");
    }

    @Override
    public void update(float delta)
    {
        if (resourceLoader.isDone())
        {
            Resources.Textures.BALL = resourceLoader.get(ballID);
            Resources.Textures.PADDLE = resourceLoader.get(paddleID);
            Resources.Textures.SPACE_SHIP = resourceLoader.get(spaceShipID);
            Resources.Textures.BACKGROUND = resourceLoader.get(bgID);

            Resources.Sounds.THEME = resourceLoader.get(soundThemeID);
            Resources.Sounds.BOUNCE = resourceLoader.get(soundBounceID);
            Resources.Sounds.ALIEN_HURT = resourceLoader.get(soundHurtID);
            Resources.Sounds.ALIEN_DEATH = resourceLoader.get(soundDeathID);

            Resources.Fonts.MAIN = resourceLoader.get(fontID);

            Resources.Sounds.THEME.play(true);

            gameInstance.setGameState(new PlayState());
        }
    }

    @Override
    public void render(float delta)
    {
        if (!resourceLoader.isDone() && Resources.Renderers.DYNAMIC != null)
        {
            DynamicRenderer dynamicRenderer = Resources.Renderers.DYNAMIC;

            dynamicRenderer.begin(Primitive.TRIANGLE_STRIP);
            {
                dynamicRenderer.vertex(100, 100);
                dynamicRenderer.texCoord(0, 0);

                dynamicRenderer.vertex(200, 100);
                dynamicRenderer.texCoord(1, 0);

                dynamicRenderer.vertex(100, 200);
                dynamicRenderer.texCoord(0, 1);

                dynamicRenderer.vertex(200, 200);
                dynamicRenderer.texCoord(1, 1);
            }
            dynamicRenderer.end();
        }
    }
}
