package com.shc.ld36.practice.entities;

import com.shc.ld36.practice.Resources;
import com.shc.ld36.practice.states.PlayState;
import com.shc.silenceengine.core.SilenceEngine;
import com.shc.silenceengine.graphics.Sprite;
import com.shc.silenceengine.input.Keyboard;
import com.shc.silenceengine.scene.components.SpriteComponent;
import com.shc.silenceengine.scene.entity.Entity2D;

public class Ball extends Entity2D
{
	private final int width = 24;
	private int motionX;
	private int motionY;
	
    public Ball()
    {
    	motionX = 0;
    	motionY = 4;
    	
        position.set(SilenceEngine.display.getWidth() / 2 - width, 650);

        SpriteComponent spriteComponent = new SpriteComponent(new Sprite(Resources.Textures.BALL), Resources.Renderers.SPRITE);

        addComponent(spriteComponent);
    }

    @Override
    protected void onUpdate(float deltaTime)
    {
    	if (position.y + width < 0) {
    		
    		if (Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
    			
    			position.x = PlayState.paddle.position.x;
    			position.y = PlayState.paddle.position.y - width;
    		}
    	}
    		
    	else {
    		
    		position.y = position.y - motionY;
    		position.x = position.x - motionX;
    	}
        
    }
}
