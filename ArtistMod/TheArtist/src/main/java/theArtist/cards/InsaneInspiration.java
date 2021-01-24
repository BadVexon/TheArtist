package theArtist.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.PlayTopCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theArtist.actions.ReplayThisAction;

public class InsaneInspiration extends AbstractArtistCard {

    public static final String ID = makeID(InsaneInspiration.class.getSimpleName());
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    private static final int COST = 1;

    public InsaneInspiration() {
        super(ID, COST, TYPE, RARITY, TARGET);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractCard c = this;
        atb(new AbstractGameAction() {
            @Override
            public void update() {
                atb(new PlayTopCardAction(AbstractDungeon.getRandomMonster(), false));
                if (AbstractDungeon.player.drawPile.getTopCard() instanceof AbstractPaintingCard) {
                    atb(new ReplayThisAction(m, c));
                }
                isDone = true;
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