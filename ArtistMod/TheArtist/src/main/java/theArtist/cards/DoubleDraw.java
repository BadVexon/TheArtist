package theArtist.cards;

import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.AlwaysRetainField;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theArtist.actions.SpecificNonChosenDiscardPileToHandAction;

import java.util.ArrayList;

public class DoubleDraw extends AbstractArtistCard {

    public static final String ID = makeID(DoubleDraw.class.getSimpleName());
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    private static final int COST = 1;

    public DoubleDraw() {
        super(ID, COST, TYPE, RARITY, TARGET);
        baseDamage = 3;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(dmg(m, makeInfo(), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        atb(dmg(m, makeInfo(), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        atb(new AbstractGameAction() {
            @Override
            public void update() {
                ArrayList<AbstractCard> eligibleCards = new ArrayList<>();
                for (AbstractCard c : p.discardPile.group) {
                    if (c instanceof AbstractPaintingCard) {
                        eligibleCards.add(c);
                    }
                }
                if (!eligibleCards.isEmpty()) {
                    AbstractDungeon.actionManager.addToBottom(new SpecificNonChosenDiscardPileToHandAction(eligibleCards.get(AbstractDungeon.cardRandomRng.random(eligibleCards.size() - 1))));
                }
                this.isDone = true;
            }
        });
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(1);
                        selfRetain = true;
            this.rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}