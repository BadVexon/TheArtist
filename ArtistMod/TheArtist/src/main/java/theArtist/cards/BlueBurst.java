package theArtist.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theArtist.AbstractCanvas;

public class BlueBurst extends AbstractArtistCard {

    public static final String ID = makeID(BlueBurst.class.getSimpleName());
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    private static final int COST = 1;

    public BlueBurst() {
        super(ID, COST, TYPE, RARITY, TARGET);
        baseBlock = 6;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(blck());
        atb(paint(AbstractCanvas.VexColor.BLUE));
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBlock(3);
            initializeDescription();
        }
    }

}