package com.shc.ld36.practice.entities;

import com.shc.ld36.practice.Resources;
import com.shc.silenceengine.graphics.Sprite;
import com.shc.silenceengine.math.geom2d.Rectangle;
import com.shc.silenceengine.scene.components.CollisionComponent2D;
import com.shc.silenceengine.scene.components.SpriteComponent;
import com.shc.silenceengine.scene.entity.Entity2D;

/**
 * @author Sri Harsha Chilakapati
 */
public class SpaceShip extends Entity2D
{
    public SpaceShip(float x, float y)
    {
        position.set(x, y);

        final float width = Resources.Textures.SPACE_SHIP.getWidth();
        final float height = Resources.Textures.SPACE_SHIP.getHeight();

        addComponent(new SpriteComponent(new Sprite(Resources.Textures.SPACE_SHIP), Resources.Renderers.SPRITE));
        addComponent(new CollisionComponent2D(Resources.CollisionTags.SPACE_SHIP, new Rectangle(width, height)));
    }
}
