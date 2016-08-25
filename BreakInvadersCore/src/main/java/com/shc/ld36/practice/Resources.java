package com.shc.ld36.practice;

import com.shc.silenceengine.audio.Sound;
import com.shc.silenceengine.collision.CollisionTag;
import com.shc.silenceengine.graphics.DynamicRenderer;
import com.shc.silenceengine.graphics.SpriteRenderer;
import com.shc.silenceengine.graphics.fonts.BitmapFont;
import com.shc.silenceengine.graphics.fonts.BitmapFontRenderer;
import com.shc.silenceengine.graphics.opengl.Texture;
import com.shc.silenceengine.graphics.programs.DynamicProgram;

/**
 * @author Sri Harsha Chilakapati
 */
public final class Resources
{
    public final static class Textures
    {
        public static Texture BACKGROUND;
        public static Texture SPACE_SHIP;
        public static Texture PADDLE;
        public static Texture BALL;
    }

    public final static class Sounds
    {
        public static Sound THEME;
        public static Sound BOUNCE;
        public static Sound ALIEN_HURT;
        public static Sound ALIEN_DEATH;
    }

    public final static class Renderers
    {
        public static DynamicRenderer    DYNAMIC;
        public static SpriteRenderer     SPRITE;
        public static BitmapFontRenderer FONT;
    }

    public final static class Fonts
    {
        public static BitmapFont MAIN;
    }

    public final static class Programs
    {
        public static DynamicProgram DYNAMIC;
    }

    public final static class CollisionTags
    {
        public static final CollisionTag SPACE_SHIP = new CollisionTag();
        public static final CollisionTag PADDLE     = new CollisionTag();
        public static final CollisionTag BALL       = new CollisionTag();
    }
}
