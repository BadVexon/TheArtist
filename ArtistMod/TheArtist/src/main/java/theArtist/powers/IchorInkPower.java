package theArtist.powers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.NonStackablePower;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theArtist.ArtistMod;
import theArtist.actions.PaintAction;
import theArtist.cards.AbstractArtistCard;
import theArtist.util.TextureLoader;

public class IchorInkPower extends AbstractPower {
    public static final String POWER_ID = ArtistMod.makeID("IchorInkPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private static final Texture tex84 = TextureLoader.getTexture("artistmodResources/images/powers/IchorInk84.png");
    private static final Texture tex32 = TextureLoader.getTexture("artistmodResources/images/powers/IchorInk32.png");

    public IchorInkPower(int amount) {
        name = NAME;
        ID = POWER_ID;
        this.amount = amount;
        this.owner = AbstractDungeon.player;
        type = PowerType.BUFF;
        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);
        updateDescription();
    }

    @Override
    public void updateDescription() {
        if (this.amount == 1) {
            this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
        } else {
            this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
        }
    }

    public int onAttacked(DamageInfo info, int damageAmount) {
        if (info.type != DamageInfo.DamageType.THORNS && info.type != DamageInfo.DamageType.HP_LOSS && info.owner != null && info.owner != this.owner && damageAmount > 0) {// 27
            this.flash();// 29

            for (int i = 0; i < this.amount; ++i) {// 30
                AbstractDungeon.actionManager.addToTop(new PaintAction(AbstractArtistCard.getRandomColor()));
            }
        }

        return damageAmount;// 34
    }
}