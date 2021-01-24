package theArtist.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theArtist.AbstractCanvas;
import theArtist.actions.PaintAction;

import static theArtist.ArtistMod.theCanvas;

public class BanefulBucket extends AbstractArtistCard {

    public static final String ID = makeID(BanefulBucket.class.getSimpleName());
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    private static final int COST = 2;

    public BanefulBucket() {
        super(ID, COST, TYPE, RARITY, TARGET);
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (theCanvas.chromatic()) {
            for (AbstractCanvas.ColoredShape shape : theCanvas.shapeList) {
                addToBot(new PaintAction(shape.color));
            }
        }
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBaseCost(1);
            initializeDescription();
        }
    }
}