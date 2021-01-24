package theArtist.cards;

import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.AlwaysRetainField;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theArtist.AbstractCanvas;
import theArtist.actions.PaintAction;

import static theArtist.ArtistMod.theCanvas;

public class ClearChroma extends AbstractArtistCard {

    public static final String ID = makeID(ClearChroma.class.getSimpleName());
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    private static final int COST = 1;

    public ClearChroma() {
        super(ID, COST, TYPE, RARITY, TARGET);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (theCanvas.clear()) {
            atb(paint(getRandomColor()));
        }
        if (theCanvas.chromatic()) {
            for (AbstractCanvas.VexColor c : theCanvas.colorList()) {
                atb(new PaintAction(c));
            }
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