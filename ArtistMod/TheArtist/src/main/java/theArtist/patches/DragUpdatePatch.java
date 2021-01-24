package theArtist.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import theArtist.AbstractCanvas;
import theArtist.TheArtist;

import static theArtist.ArtistMod.*;

@SpirePatch(
        clz = EnergyPanel.class,
        method = "update"
)
public class DragUpdatePatch {
    public static void Prefix(EnergyPanel __instance) {
        if ((AbstractDungeon.player instanceof TheArtist || renderStuff) && AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT) {
            for (AbstractCanvas.ColoredShape coloredShape : theCanvas.shapeList) {
                coloredShape.update();
            }
            newHitbox.dragUpdate();
            newHitbox.update();
        }
    }
}