package com.shc.ld36.practice.html;

import com.google.gwt.core.client.EntryPoint;
import com.shc.silenceengine.backend.gwt.GwtRuntime;
import com.shc.ld36.practice.BreakInvaders;

public class BreakInvadersLauncher implements EntryPoint
{
    @Override
    public void onModuleLoad()
    {
        GwtRuntime.start(new BreakInvaders());
    }
}