package com.shc.ld36.practice.android;

import com.shc.ld36.practice.BreakInvaders;
import com.shc.silenceengine.backend.android.AndroidRuntime;
import com.shc.silenceengine.backend.android.AndroidLauncher;

/**
 * @author Sri Harsha Chilakapati
 */
public class BreakInvadersLauncher extends AndroidLauncher
{
    @Override
    public void launchGame()
    {
        AndroidRuntime.start(new BreakInvaders());
    }
}