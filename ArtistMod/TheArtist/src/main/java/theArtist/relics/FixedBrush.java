package theArtist.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theArtist.AbstractCanvas;
import theArtist.ArtistMod;
import theArtist.actions.PaintAction;
import theArtist.util.TextureLoader;

import static theArtist.ArtistMod.makeRelicOutlinePath;
import static theArtist.ArtistMod.makeRelicPath;

public class FixedBrush extends CustomRelic {

    public static final String ID = ArtistMod.makeID("FixedBrush");
    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("BentBrush.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("Prayer_Bead.png"));

    public FixedBrush() {
        super(ID, IMG, OUTLINE, RelicTier.BOSS, LandingSound.MAGICAL);
    }

    public void atPreBattle() {
        this.flash();
        AbstractDungeon.actionManager.addToBottom(new PaintAction(AbstractCanvas.VexColor.RAINBOW, 2));
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public boolean canSpawn() {
        return AbstractDungeon.player.hasRelic(BrokenBrush.ID);
    }

    @Override
    public void obtain() {
        if (AbstractDungeon.player.hasRelic(BlueBrush.ID)) {
            for (int i = 0; i < AbstractDungeon.player.relics.size(); ++i) {
                if (AbstractDungeon.player.relics.get(i).relicId.equals(BlueBrush.ID)) {
                    instantObtain(AbstractDungeon.player, i, true);
                    break;
                }
            }
        } else {
            super.obtain();
        }
    }
}
