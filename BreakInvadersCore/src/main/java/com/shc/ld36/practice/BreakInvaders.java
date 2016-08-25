package com.shc.ld36.practice;

import com.shc.ld36.practice.states.LoadingState;
import com.shc.silenceengine.core.Game;
import com.shc.silenceengine.core.SilenceEngine;
import com.shc.silenceengine.graphics.opengl.GLContext;
import com.shc.silenceengine.input.Keyboard;

import static com.shc.silenceengine.graphics.IGraphicsDevice.Constants.*;

public class BreakInvaders extends Game
{
    @Override
    public void init()
    {
        SilenceEngine.display.setTitle("BreakInvaders: SilenceEngine v1.0.1");
        SilenceEngine.display.setSize(1280, 720);
        SilenceEngine.display.centerOnScreen();

        // Enable blending
        GLContext.enable(GL_BLEND);
        GLContext.blendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

        setGameState(new LoadingState(this));
    }

    @Override
    public void update(float deltaTime)
    {
        if (Keyboard.isKeyTapped(Keyboard.KEY_ESCAPE))
            SilenceEngine.display.close();
    }
}