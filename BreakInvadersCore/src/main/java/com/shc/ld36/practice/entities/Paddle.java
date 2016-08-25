package com.shc.ld36.practice.entities;

import com.shc.ld36.practice.Resources;
import com.shc.silenceengine.graphics.Sprite;
import com.shc.silenceengine.input.Keyboard;
import com.shc.silenceengine.scene.components.SpriteComponent;
import com.shc.silenceengine.scene.entity.Entity2D;

/**
 * @author Sri Harsha Chilakapati
 */
public class Paddle extends Entity2D
{
    public Paddle()
    {
        position.set(100, 500);

        SpriteComponent spriteComponent = new SpriteComponent(new Sprite(Resources.Textures.PADDLE), Resources.Renderers.SPRITE);

        addComponent(spriteComponent);
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
