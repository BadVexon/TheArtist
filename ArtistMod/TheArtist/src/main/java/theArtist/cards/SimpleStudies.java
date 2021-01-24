package theArtist.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theArtist.ArtistMod.theCanvas;

public class SimpleStudies extends AbstractArtistCard {

    public static final String ID = makeID(SimpleStudies.class.getSimpleName());
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    private static final int COST = 1;

    public SimpleStudies() {
        super(ID, COST, TYPE, RARITY, TARGET);
        baseBlock = 8;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(blck());
        if (theCanvas.chromatic()) {
            AbstractDungeon.actionManager.addToBottom(shuffleIn(theCanvas.dispense(false)));
        }
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBlock(2);
            upgradeMagicNumber(1);
            initializeDescription();
        }
    }
}