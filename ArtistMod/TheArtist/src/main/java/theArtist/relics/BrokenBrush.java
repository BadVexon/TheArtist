package theArtist.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theArtist.ArtistMod;
import theArtist.util.TextureLoader;

import static theArtist.ArtistMod.makeRelicOutlinePath;
import static theArtist.ArtistMod.makeRelicPath;

public class BrokenBrush extends CustomRelic {

    public static final String ID = ArtistMod.makeID("BrokenBrush");
    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("BentBrush.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("Prayer_Bead.png"));

    public BrokenBrush() {
        super(ID, IMG, OUTLINE, RelicTier.BOSS, LandingSound.FLAT);
    }

    public void onEquip() {
        ++AbstractDungeon.player.energy.energyMaster;// 37
    }// 38

    public void onUnequip() {
        --AbstractDungeon.player.energy.energyMaster;// 42
    }// 43

    public void onPlayerEndTurn() {
        this.flash();
        ArtistMod.theCanvas.dispense(true);
    }

    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}
