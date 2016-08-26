package com.shc.ld36.practice.entities;

import com.shc.ld36.practice.Resources;
import com.shc.silenceengine.core.SilenceEngine;
import com.shc.silenceengine.graphics.Sprite;
import com.shc.silenceengine.input.Keyboard;
import com.shc.silenceengine.math.geom2d.Rectangle;
import com.shc.silenceengine.scene.components.CollisionComponent2D;
import com.shc.silenceengine.scene.components.SpriteComponent;
import com.shc.silenceengine.scene.entity.Entity2D;

/**
 * @author Sri Harsha Chilakapati
 */
public class Paddle extends Entity2D
{
    public Paddle()
    {
        position.set(SilenceEngine.display.getWidth() / 2 - 24, 650);

        final float width = Resources.Textures.PADDLE.getWidth();
        final float height = Resources.Textures.PADDLE.getHeight();

        addComponent(new SpriteComponent(new Sprite(Resources.Textures.PADDLE), Resources.Renderers.SPRITE));
        addComponent(new CollisionComponent2D(Resources.CollisionTags.PADDLE, new Rectangle(width, height)));
    }

    @Override
    protected void onUpdate(float deltaTime)
    {
        if (Keyboard.isKeyDown(Keyboard.KEY_LEFT))
            position.x -= 4;

        if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT))
            position.x += 4;
    }
}
