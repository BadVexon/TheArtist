package theArtist.cards;

import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.AlwaysRetainField;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theArtist.actions.DamagePerExhaustedAction;
import theArtist.actions.ExhaustConditionalCardsAction;

public class ArtisticAgony extends AbstractArtistCard {

    public static final String ID = makeID(ArtisticAgony.class.getSimpleName());
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    private static final int COST = 3;

    public ArtisticAgony() {
        super(ID, COST, TYPE, RARITY, TARGET);
        this.exhaust = true;
        baseDamage = 6;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        ExhaustConditionalCardsAction exhaustAction = new ExhaustConditionalCardsAction(blah -> blah instanceof AbstractPaintingCard);

        atb(exhaustAction);
        atb(new DamagePerExhaustedAction(p, exhaustAction, this.baseDamage, this.damageTypeForTurn));
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
                        selfRetain = true;
            this.rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}