package com.shc.ld36.practice.states;

import com.shc.ld36.practice.BreakInvaders;
import com.shc.ld36.practice.Resources;
import com.shc.silenceengine.core.Game;
import com.shc.silenceengine.core.GameState;
import com.shc.silenceengine.core.ResourceLoader;
import com.shc.silenceengine.core.SilenceEngine;
import com.shc.silenceengine.graphics.DynamicRenderer;
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

    public LoadingState(BreakInvaders gameInstance)
    {
        this.gameInstance = gameInstance;
        this.resourceLoader = new ResourceLoader();
    }

    @Override
    public void onEnter()
    {
        // Define the resources
        ballID = resourceLoader.define(Texture.class, FilePath.getResourceFile("assets/breakinvaders/ball.png"));
        paddleID = resourceLoader.define(Texture.class, FilePath.getResourceFile("assets/breakinvaders/paddle.png"));
        spaceShipID = resourceLoader.define(Texture.class, FilePath.getResourceFile("assets/breakinvaders/space_ship.png"));

        DynamicProgram.create(program ->
        {
            Resources.Programs.DYNAMIC = program;
            program.use();

            Resources.Renderers.DYNAMIC = new DynamicRenderer(50);
            program.applyToRenderer(Resources.Renderers.DYNAMIC);

            // Start loading the resources
            resourceLoader.start();
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

            gameInstance.setGameState(new PlayState());
        }
    }

    @Override
    public void render(float delta)
    {
        // Draw the progress bar here
    }
}
