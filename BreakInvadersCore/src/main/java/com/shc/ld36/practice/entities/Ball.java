package com.shc.ld36.practice.entities;

import com.shc.ld36.practice.Resources;
import com.shc.ld36.practice.states.PlayState;
import com.shc.silenceengine.core.SilenceEngine;
import com.shc.silenceengine.graphics.Sprite;
import com.shc.silenceengine.input.Keyboard;
import com.shc.silenceengine.math.Vector2;
import com.shc.silenceengine.math.geom2d.Rectangle;
import com.shc.silenceengine.scene.components.CollisionComponent2D;
import com.shc.silenceengine.scene.components.SpriteComponent;
import com.shc.silenceengine.scene.entity.Entity2D;

public class Ball extends Entity2D
{
    private final static int RADIUS = 24;

    private boolean dead;

    private Vector2 speed = new Vector2();

    private CollisionComponent2D collisionComponent;

    public Ball()
    {
        reSpawn();

        final float width = Resources.Textures.BALL.getWidth();
        final float height = Resources.Textures.BALL.getHeight();

        addComponent(new SpriteComponent(new Sprite(Resources.Textures.BALL), Resources.Renderers.SPRITE));
        addComponent(collisionComponent =
                new CollisionComponent2D(Resources.CollisionTags.BALL, new Rectangle(width, height), this::onCollision));
    }

    private void reSpawn()
    {
        speed.set(4, -4);
        position.set(PlayState.PADDLE.position.x, 600);
    }

    private void onCollision(Entity2D self, CollisionComponent2D other)
    {
        if (other.entity instanceof Paddle)
        {
            speed.y *= -1;
            position.y += speed.y;
        }

        else if (other.entity instanceof SpaceShip)
        {
            final Rectangle sBounds = collisionComponent.polygon.getBounds();
            final Rectangle oBounds = other.polygon.getBounds();

            final float iWidth = oBounds.getIntersectionWidth(sBounds);
            final float iHeight = oBounds.getIntersectionHeight(sBounds);

            if (iWidth > iHeight)
            {
                speed.y *= -1;
                position.y += speed.y;
            }

            if (iHeight > iWidth)
            {
                speed.x *= -1;
                position.x += speed.x;
            }

            PlayState.SCENE.entities.remove(other.entity);
        }
    }

    @Override
    protected void onUpdate(float deltaTime)
    {
        if (dead)
        {
            if (Keyboard.isKeyTapped(Keyboard.KEY_LEFT) || Keyboard.isKeyTapped(Keyboard.KEY_RIGHT))
            {
                dead = false;
                reSpawn();
            }
            else
                return;
        }

        position.add(speed);

        if (position.x >= SilenceEngine.display.getWidth() - RADIUS || position.x - RADIUS <= 0)
        {
            speed.x *= -1;
            position.x += speed.x;
        }

        if (position.y - RADIUS <= 0)
        {
            speed.y *= -1;
            position.y += speed.y;
        }

        if (position.y >= SilenceEngine.display.getHeight() - RADIUS)
        {
            dead = true;
            position.y = SilenceEngine.display.getHeight() + 2 * RADIUS;
        }
    }
}
