package theArtist.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theArtist.AbstractCanvas;

public class DeepDream extends AbstractArtistCard {

    public static final String ID = makeID(DeepDream.class.getSimpleName());
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    private static final int COST = 3;

    public DeepDream() {
        super(ID, COST, TYPE, RARITY, TARGET);
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(paint(AbstractCanvas.VexColor.RED));
        atb(paint(AbstractCanvas.VexColor.BLUE));
        atb(paint(AbstractCanvas.VexColor.YELLOW));
        atb(paint(AbstractCanvas.VexColor.GREEN));
        atb(paint(AbstractCanvas.VexColor.AQUA));
        atb(paint(AbstractCanvas.VexColor.MAGENTA));
        atb(paint(AbstractCanvas.VexColor.RAINBOW));
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            isInnate = true;
            rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}