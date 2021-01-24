package theArtist.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theArtist.AbstractCanvas;
import theArtist.ArtistMod;

public class RainbowReality extends AbstractArtistCard {

    public static final String ID = makeID(RainbowReality.class.getSimpleName());
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    private static final int COST = 1;

    public RainbowReality() {
        super(ID, COST, TYPE, RARITY, TARGET);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (ArtistMod.theCanvas.shapeList.isEmpty()) {
            atb(paint(AbstractCanvas.VexColor.RAINBOW, 2));
        } else {
            atb(paint(AbstractCanvas.VexColor.RAINBOW));
        }
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBaseCost(0);
            initializeDescription();
        }
    }
}