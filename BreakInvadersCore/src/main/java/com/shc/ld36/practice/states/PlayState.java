package com.shc.ld36.practice.states;

import com.shc.ld36.practice.Resources;
import com.shc.silenceengine.core.GameState;
import com.shc.silenceengine.core.SilenceEngine;
import com.shc.silenceengine.graphics.DynamicRenderer;
import com.shc.silenceengine.graphics.cameras.OrthoCam;
import com.shc.silenceengine.graphics.opengl.GLContext;
import com.shc.silenceengine.graphics.opengl.Primitive;

/**
 * @author Sri Harsha Chilakapati
 */
public class PlayState extends GameState
{
    private OrthoCam camera;

    @Override
    public void onEnter()
    {
        SilenceEngine.display.setTitle("BreakInvaders: Get set Go!!!");

        camera = new OrthoCam();
        resized();
    }

    @Override
    public void render(float delta)
    {
        DynamicRenderer dynamicRenderer = Resources.Renderers.DYNAMIC;

        Resources.Textures.BALL.bind(0);
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

    @Override
    public void resized()
    {
        camera.initProjection(SilenceEngine.display.getWidth(), SilenceEngine.display.getHeight());
        camera.apply();

        GLContext.viewport(0, 0, SilenceEngine.display.getWidth(), SilenceEngine.display.getHeight());
    }
}
