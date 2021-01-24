package theArtist.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import theArtist.ArtistMod;
import theArtist.util.TextureLoader;

import static theArtist.ArtistMod.makeRelicOutlinePath;
import static theArtist.ArtistMod.makeRelicPath;

public class BlueBrush extends CustomRelic {

    public static boolean usedThisCombat = false;

    public static final String ID = ArtistMod.makeID("BlueBrush");
    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("BentBrush.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("Prayer_Bead.png"));

    public BlueBrush() {
        super(ID, IMG, OUTLINE, RelicTier.STARTER, LandingSound.FLAT);
    }

    public void atPreBattle() {
        usedThisCombat = false;// 25
        this.pulse = true;// 26
        this.beginPulse();// 27
    }

    public void onVictory() {
        stopPulse();
    }

    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}
