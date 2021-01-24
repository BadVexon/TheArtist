package theArtist.cards;

import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.AlwaysRetainField;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theArtist.actions.RecurPaintingAction;

public class RaringRepaint extends AbstractArtistCard {

    public static final String ID = makeID(RaringRepaint.class.getSimpleName());
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    private static final int COST = 1;

    public RaringRepaint() {
        super(ID, COST, TYPE, RARITY, TARGET);
        baseBlock = 4;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(blck());
        atb(new RecurPaintingAction(1));
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBlock(1);
                        selfRetain = true;
            this.rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}