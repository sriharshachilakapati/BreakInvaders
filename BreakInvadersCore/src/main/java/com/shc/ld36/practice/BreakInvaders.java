package com.shc.ld36.practice;

import com.shc.ld36.practice.states.LoadingState;
import com.shc.silenceengine.core.Game;
import com.shc.silenceengine.core.SilenceEngine;
import com.shc.silenceengine.input.Keyboard;

public class BreakInvaders extends Game
{
    @Override
    public void init()
    {
        SilenceEngine.display.setTitle("BreakInvaders: SilenceEngine v1.0.1");
        SilenceEngine.display.setSize(1280, 720);
        SilenceEngine.display.centerOnScreen();

        setGameState(new LoadingState(this));
    }

    @Override
    public void update(float deltaTime)
    {
        if (Keyboard.isKeyTapped(Keyboard.KEY_ESCAPE))
            SilenceEngine.display.close();
    }
}