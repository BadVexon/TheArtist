package theArtist;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Matrix4;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import theArtist.actions.PaintAction;
import theArtist.cards.AbstractPaintingCard;
import theArtist.relics.BlueBrush;
import theArtist.util.GainStatCurvy;
import theArtist.util.GainStatLine;
import theArtist.util.TextureLoader;

import java.awt.*;
import java.util.ArrayList;

import static com.badlogic.gdx.graphics.GL20.GL_DST_COLOR;
import static com.badlogic.gdx.graphics.GL20.GL_ZERO;

public class AbstractCanvas {

    public enum VexColor {
        RED, //176,18,10
        BLUE, //48,63,159
        GREEN, //66,189,65
        YELLOW, //255,235,59
        AQUA, //38,166,154
        MAGENTA, //136,14,79
        RAINBOW //???
    }

    private int maxLines = 36;
    private int stride = 360 / maxLines;
    private float offset = MathUtils.random(-180.0F, 180.0F);

    public Texture backgroundImg;
    public ArrayList<ColoredShape> shapeList;

    public boolean locked = false;

    public void unlock() {
        locked = false;
    }

    public AbstractCanvas() {
        shapeList = new ArrayList<>();
        backgroundImg = TextureLoader.getTexture(ArtistMod.makeCardPath("blankImage2.png"));
    }

    public void paint(VexColor color) {
        ColoredShape shape = new ColoredShape(color);
        shapeList.add(shape);
        for (int i = 0; i < maxLines; i++) {
            AbstractDungeon.effectList.add(new GainStatLine(shape.location.x + ArtistMod.newHitbox.hb.x, shape.location.y + ArtistMod.newHitbox.hb.y, shape.hex.cpy(), ((stride * i) + MathUtils.random(-stride, stride) + offset)));
            if (i % 2 == 0) {
                AbstractDungeon.effectList.add(new GainStatCurvy(shape.location.x + ArtistMod.newHitbox.hb.x, shape.location.y + ArtistMod.newHitbox.hb.y, shape.hex.cpy()));
            }
        }
        if (AbstractDungeon.player.hasRelic(BlueBrush.ID)) {
            BlueBrush r = (BlueBrush) AbstractDungeon.player.getRelic(BlueBrush.ID);
            if (!BlueBrush.usedThisCombat) {
                r.flash();// 35
                r.stopPulse();
                AbstractDungeon.actionManager.addToTop(new PaintAction(color));
                AbstractDungeon.actionManager.addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, r));// 38
                BlueBrush.usedThisCombat = true;
            }
        }
    }

    public boolean chromatic() {
        int i = 0;
        ArrayList<VexColor> blahList = new ArrayList<>();
        for (ColoredShape shape : this.shapeList) {
            if (!blahList.contains(shape.color)) {
                i++;
                blahList.add(shape.color);
            }
        }
        return i == 2;
    }

    public boolean clear() {
        for (ColoredShape q : this.shapeList) {
            return false;
        }
        return true;
    }

    public ArrayList<VexColor> colorList() {
        ArrayList<VexColor> blah = new ArrayList<>();
        for (ColoredShape shape : this.shapeList) {
            if (!blah.contains(shape.color)) {
                blah.add(shape.color);
            }
        }
        return blah;
    }

    public void render(SpriteBatch sb, float x, float y) {
        Color whiteColor = Color.WHITE.cpy();
        for (ColoredShape q : shapeList) {
            whiteColor.r *= 0.75F;
            whiteColor.b *= 0.75F;
            whiteColor.g *= 0.75F;
        }
        sb.setColor(whiteColor);
        sb.draw(backgroundImg, x, y, backgroundImg.getWidth() / 2F, backgroundImg.getHeight() / 2F, backgroundImg.getWidth(), backgroundImg.getHeight(), 1f, 1f, 0, 0, 0, backgroundImg.getWidth(), backgroundImg.getHeight(), false, false);
        for (ColoredShape blah : shapeList) {
            sb.setColor(blah.hex.cpy());
            sb.draw(blah.texture, x + blah.location.x, y + blah.location.y, blah.texture.getWidth() / 2F, blah.texture.getHeight() / 2F, blah.texture.getWidth(), blah.texture.getHeight(), blah.scaleX, blah.scaleY, blah.rotation, 0, 0, blah.texture.getWidth(), blah.texture.getHeight(), false, false);
        }
        sb.setColor(Color.WHITE.cpy());
        sb.draw(ImageMaster.CARD_FRAME_SKILL_COMMON_L, x - 15, y - 35);
        sb.draw(ImageMaster.CARD_BANNER_COMMON_L, x - 75, y + 300);
        FontHelper.renderFontCentered(sb, FontHelper.SCP_cardTitleFont_small, ArtistMod.curName, x + (backgroundImg.getWidth() / 2F), y + backgroundImg.getHeight() + 25, Settings.CREAM_COLOR, Settings.scale);// 1333
    }

    public AbstractCard dispense(boolean purge) {
        int red = 0;
        int blue = 0;
        int green = 0;
        int yellow = 0;
        int aqua = 0;
        int magenta = 0;
        int brown = 0;
        int purple = 0;
        int black = 0;
        int rainbow = 0;
        for (ColoredShape q : shapeList) {
            switch (q.color) {
                case RED:
                    red++;
                    break;
                case BLUE:
                    blue++;
                    break;
                case GREEN:
                    green++;
                    break;
                case YELLOW:
                    yellow++;
                    break;
                case AQUA:
                    aqua++;
                    break;
                case MAGENTA:
                    magenta++;
                    break;
                case RAINBOW:
                    rainbow++;
                    break;
            }
        }

        FrameBuffer frameBuffer = new FrameBuffer(Pixmap.Format.RGBA8888, backgroundImg.getWidth(), backgroundImg.getHeight(), false);
        SpriteBatch spriteBatch = new SpriteBatch();
        Matrix4 matrix = new Matrix4();
        matrix.setToOrtho2D(0, 0, backgroundImg.getWidth(), backgroundImg.getHeight());
        spriteBatch.setProjectionMatrix(matrix);

        frameBuffer.begin();
        spriteBatch.begin();

        Color whiteColor = Color.WHITE.cpy();
        whiteColor.r *= 0.75F;
        whiteColor.b *= 0.75F;
        whiteColor.g *= 0.75F;
        spriteBatch.setColor(whiteColor);
        spriteBatch.draw(backgroundImg, 0, 0, backgroundImg.getWidth() / 2F, backgroundImg.getHeight() / 2F, backgroundImg.getWidth(), backgroundImg.getHeight(), 1, 1, 0, 0, 0, backgroundImg.getWidth(), backgroundImg.getHeight(), false, false);

        for (ColoredShape blah : shapeList) {
            spriteBatch.setColor(blah.hex.cpy());
            spriteBatch.draw(blah.texture, blah.location.x, blah.location.y, blah.texture.getWidth() / 2F, blah.texture.getHeight() / 2F, blah.texture.getWidth(), blah.texture.getHeight(), blah.scaleX, blah.scaleY, blah.rotation, 0, 0, blah.texture.getWidth(), blah.texture.getHeight(), false, false);
        }
        spriteBatch.setColor(Color.WHITE.cpy());
        int wah = spriteBatch.getBlendDstFunc();
        int blah = spriteBatch.getBlendSrcFunc();
        spriteBatch.setBlendFunction(GL_DST_COLOR, GL_ZERO);
        if (red > 0) {
            spriteBatch.draw(TextureLoader.getTexture("artistmodResources/images/ui/AttackMask_p.png"), 0, 0, backgroundImg.getWidth() / 2F, backgroundImg.getHeight() / 2F, backgroundImg.getWidth(), backgroundImg.getHeight(), 1, 1, 0, 0, 0, backgroundImg.getWidth(), backgroundImg.getHeight(), false, false);
        } else {
            spriteBatch.draw(TextureLoader.getTexture("artistmodResources/images/ui/SkillMask_p.png"), 0, 0, backgroundImg.getWidth() / 2F, backgroundImg.getHeight() / 2F, backgroundImg.getWidth(), backgroundImg.getHeight(), 1, 1, 0, 0, 0, backgroundImg.getWidth(), backgroundImg.getHeight(), false, false);
        }

        spriteBatch.setBlendFunction(blah, wah);

        spriteBatch.end();
        frameBuffer.end();

        FrameBuffer frameBuffer2 = new FrameBuffer(Pixmap.Format.RGBA8888, backgroundImg.getWidth() / 2, backgroundImg.getHeight() / 2, false);
        frameBuffer2.begin();
        spriteBatch.begin();
        spriteBatch.draw(frameBuffer.getColorBufferTexture(), 0, 0, backgroundImg.getWidth() / 2F, backgroundImg.getHeight() / 2F, backgroundImg.getWidth(), backgroundImg.getHeight(), 1, 1, 0, 0, 0, backgroundImg.getWidth(), backgroundImg.getHeight(), false, false);
        spriteBatch.end();
        frameBuffer2.end();

        System.out.println(red + " " + blue + " " + green + " " + yellow + " " + aqua + " " + magenta + " " + brown + " " + purple + " " + black + " " + rainbow);
        if (purge) {
            shapeList.clear();
            backgroundImg = TextureLoader.getTexture(ArtistMod.makeCardPath("blankImage2.png"));
        }
        return new AbstractPaintingCard(frameBuffer2.getColorBufferTexture(), red, blue, green, yellow, aqua, magenta, brown, purple, black, rainbow, purge);
    }

    public class ColoredShape {
        public VexColor color;
        public Point location;
        public Texture texture;
        public float rotation;
        public float scaleX;
        public float scaleY;
        public com.badlogic.gdx.graphics.Color hex;

        public ColoredShape(VexColor co) {
            color = co;
            location = new Point((MathUtils.random(0, 300)), (MathUtils.random(0, 250)));
            scaleX = MathUtils.random(0.75F, 1.25F);
            scaleY = MathUtils.random(0.75F, 1.5F);
            texture = TextureLoader.getTexture("artistmodResources/images/ui/Drawing" + MathUtils.random(8, 18) + ".png");
            rotation = MathUtils.random(360F);
            switch (co) {
                case RED:
                    hex = new com.badlogic.gdx.graphics.Color(0.690F, 0.070F, 0.039F, 1F);
                    break;
                case BLUE:
                    hex = new com.badlogic.gdx.graphics.Color(0.188F, 0.247F, 0.623F, 1F);
                    break;
                case GREEN:
                    hex = new com.badlogic.gdx.graphics.Color(0.258F, 0.741F, 0.254F, 1F);
                    break;
                case YELLOW:
                    hex = new com.badlogic.gdx.graphics.Color(1f, 0.921f, 0.231f, 1F);
                    break;
                case AQUA:
                    hex = new com.badlogic.gdx.graphics.Color(0.149f, 0.650f, 0.603f, 1F);
                    break;
                case MAGENTA:
                    hex = new com.badlogic.gdx.graphics.Color(0.533f, 0.054f, 0.309f, 1F);
                    break;
                case RAINBOW:
                    hex = ArtistMod.rainbow;
                    break;
            }
        }

        public void update() {
            if (this.color == VexColor.RAINBOW) {
                this.hex = ArtistMod.rainbow;
            }
        }
    }
}
