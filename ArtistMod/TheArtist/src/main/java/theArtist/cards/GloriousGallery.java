package theArtist.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theArtist.actions.SpecificNonChosenDiscardPileToHandAction;
import theArtist.actions.SpecificNonChosenDrawPileToHandAction;

public class GloriousGallery extends AbstractArtistCard {

    public static final String ID = makeID(GloriousGallery.class.getSimpleName());
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    private static final int COST = 1;

    public GloriousGallery() {
        super(ID, COST, TYPE, RARITY, TARGET);
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new AbstractGameAction() {
            @Override
            public void update() {
                for (AbstractCard c : AbstractDungeon.player.drawPile.group) {
                    if (c instanceof AbstractPaintingCard) {
                        atb(new SpecificNonChosenDrawPileToHandAction(c));
                    }
                }
                for (AbstractCard c : AbstractDungeon.player.discardPile.group) {
                    if (c instanceof AbstractPaintingCard) {
                        atb(new SpecificNonChosenDiscardPileToHandAction(c));
                    }
                }
                this.isDone = true;
            }
        });
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBaseCost(0);
            initializeDescription();
        }
    }
}