package com.shc.ld36.practice.entities;

import com.shc.ld36.practice.Resources;
import com.shc.silenceengine.core.SilenceEngine;
import com.shc.silenceengine.graphics.Sprite;
import com.shc.silenceengine.math.Vector2;
import com.shc.silenceengine.math.geom2d.Rectangle;
import com.shc.silenceengine.scene.components.CollisionComponent2D;
import com.shc.silenceengine.scene.components.SpriteComponent;
import com.shc.silenceengine.scene.entity.Entity2D;

public class Ball extends Entity2D
{
    private final static int WIDTH = 24;

    private Vector2 speed = new Vector2();

    public Ball()
    {
        speed.set(4, 4);
        position.set(SilenceEngine.display.getWidth() / 2 - WIDTH, 600);

        final float width = Resources.Textures.BALL.getWidth();
        final float height = Resources.Textures.BALL.getHeight();

        addComponent(new SpriteComponent(new Sprite(Resources.Textures.BALL), Resources.Renderers.SPRITE));
        addComponent(new CollisionComponent2D(Resources.CollisionTags.BALL, new Rectangle(width, height), this::onCollision));
    }

    private void onCollision(Entity2D self, CollisionComponent2D other)
    {
        if (other.entity instanceof Paddle)
        {
            speed.y *= -1;
            position.y += speed.y;
        }
    }

    @Override
    protected void onUpdate(float deltaTime)
    {
        position.add(speed);

        if (position.x >= SilenceEngine.display.getWidth() - WIDTH || position.x - WIDTH <= 0)
        {
            speed.x *= -1;
            position.x += speed.x;
        }

        if (position.y - WIDTH <= 0)
        {
            speed.y *= -1;
            position.y += speed.y;
        }
    }
}
