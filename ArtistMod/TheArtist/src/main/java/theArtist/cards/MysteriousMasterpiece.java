package theArtist.cards;

import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.FleetingField;
import com.megacrit.cardcrawl.actions.unique.AddCardToDeckAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theArtist.ArtistMod.theCanvas;

public class MysteriousMasterpiece extends AbstractArtistCard {

    public static final String ID = makeID(MysteriousMasterpiece.class.getSimpleName());
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    private static final int COST = 4;

    public MysteriousMasterpiece() {
        super(ID, COST, TYPE, RARITY, TARGET);
        this.tags.add(CardTags.HEALING);
        FleetingField.fleeting.set(this, true);
        isEthereal = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new AddCardToDeckAction(theCanvas.dispense(false)));
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBaseCost(3);
            initializeDescription();
        }
    }
}