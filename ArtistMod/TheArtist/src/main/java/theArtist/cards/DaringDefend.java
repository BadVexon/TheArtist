package theArtist.cards;

import basemod.helpers.BaseModCardTags;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class DaringDefend extends AbstractArtistCard {

    public static final String ID = makeID(DaringDefend.class.getSimpleName());
    private static final CardRarity RARITY = CardRarity.BASIC;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    private static final int COST = 1;

    public DaringDefend() {
        super(ID, COST, TYPE, RARITY, TARGET);
        baseBlock = 5;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(blck());
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBlock(3);
            initializeDescription();
        }
    }
}