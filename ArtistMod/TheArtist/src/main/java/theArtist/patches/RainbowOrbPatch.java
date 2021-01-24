package theArtist.patches;

import basemod.ReflectionHacks;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import theArtist.ArtistMod;
import theArtist.TheArtist;

@SpirePatch(
        clz = EnergyPanel.class,
        method = "render"
)
public class RainbowOrbPatch {
    public static void Prefix(EnergyPanel __instance, SpriteBatch sb) {
        if (AbstractDungeon.player instanceof TheArtist) {
            ReflectionHacks.setPrivate(__instance, EnergyPanel.class, "energyVfxColor", ArtistMod.rainbow.cpy());
        }
    }
}