package theArtist.patches;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.TipHelper;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import theArtist.ArtistMod;
import theArtist.TheArtist;

@SpirePatch(
        clz = AbstractRoom.class,
        method = "render"
)
public class TipRenderPatch {
    public static void Postfix(AbstractRoom __instance, SpriteBatch sb) {
        if ((AbstractDungeon.player instanceof TheArtist || ArtistMod.renderStuff) && AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT) {
            if (ArtistMod.newHitbox.hb.hovered && !AbstractDungeon.isScreenUp) {
                String body = "You can #yPaint Colors onto the Canvas. #pClick to drag. #pRight #pClick to preview the #yPainting or add it into your hand.";
                TipHelper.renderGenericTip(
                        (float) InputHelper.mX + 60.0F * Settings.scale, (float) InputHelper.mY - 50.0F * Settings.scale,
                        "Canvas",
                        body
                );
            }
        }
    }
}