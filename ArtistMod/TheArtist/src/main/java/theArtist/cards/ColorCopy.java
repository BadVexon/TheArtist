package theArtist.cards;

import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.AlwaysRetainField;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theArtist.actions.ChooseToPaintAction;

import static theArtist.ArtistMod.theCanvas;

public class ColorCopy extends AbstractArtistCard {

    public static final String ID = makeID(ColorCopy.class.getSimpleName());
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    private static final int COST = 0;

    public ColorCopy() {
        super(ID, COST, TYPE, RARITY, TARGET);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (theCanvas.colorList().size() == 1) {
            atb(paint(theCanvas.colorList().get(0)));
        } else if (theCanvas.colorList().size() > 1) {
            atb(new ChooseToPaintAction(true, 1));
        }
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