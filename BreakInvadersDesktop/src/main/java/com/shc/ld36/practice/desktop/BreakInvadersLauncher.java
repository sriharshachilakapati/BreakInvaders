package com.shc.ld36.practice.desktop;

import com.shc.silenceengine.backend.lwjgl.LwjglRuntime;
import com.shc.ld36.practice.BreakInvaders;

public class BreakInvadersLauncher
{
    public static void main(String[] args)
    {
        LwjglRuntime.start(new BreakInvaders());
    }
}