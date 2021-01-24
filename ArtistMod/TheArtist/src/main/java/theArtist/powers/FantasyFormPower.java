package theArtist.powers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theArtist.ArtistMod;
import theArtist.cards.AbstractPaintingCard;
import theArtist.util.TextureLoader;

public class FantasyFormPower extends AbstractPower {
    public static final String POWER_ID = ArtistMod.makeID("FantasyFormPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private static final Texture tex84 = TextureLoader.getTexture("artistmodResources/images/powers/FantasyForm84.png");
    private static final Texture tex32 = TextureLoader.getTexture("artistmodResources/images/powers/FantasyForm32.png");

    public FantasyFormPower(int magic) {
        name = NAME;
        ID = POWER_ID;
        this.amount = magic;
        this.owner = AbstractDungeon.player;
        type = PowerType.BUFF;
        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);
        updateDescription();
    }

    @Override
    public void updateDescription() {
        if (amount == 1) {
            description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
        } else {
            description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[2];
        }
    }

    public void onCardDraw(AbstractCard card) {
        if (card instanceof AbstractPaintingCard){// 36
            this.flash();// 37
            AbstractDungeon.actionManager.addToBottom(new DrawCardAction(this.owner, this.amount));// 38
        }
    }// 40
}