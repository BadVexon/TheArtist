package theArtist.powers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.NonStackablePower;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theArtist.ArtistMod;
import theArtist.actions.PaintAction;
import theArtist.util.TextureLoader;

public class TreasureTrovePower extends AbstractPower  {
    public static final String POWER_ID = ArtistMod.makeID("TreasureTrovePower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private static final Texture tex84 = TextureLoader.getTexture("artistmodResources/images/powers/TreasureTrove84.png");
    private static final Texture tex32 = TextureLoader.getTexture("artistmodResources/images/powers/TreasureTrove32.png");

    public TreasureTrovePower(int amount) {
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

    public void atStartOfTurn() {
        if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {// 27
            this.flash();// 28

            for(int i = 0; i < this.amount; ++i) {// 29
                AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(AbstractDungeon.getCard(AbstractCard.CardRarity.UNCOMMON, AbstractDungeon.cardRandomRng).makeCopy(), 1, false));// 30 33 34
            }
        }

    }// 39
}