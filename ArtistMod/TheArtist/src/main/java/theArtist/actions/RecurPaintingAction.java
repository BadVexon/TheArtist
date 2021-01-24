package theArtist.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theArtist.cards.AbstractPaintingCard;

import java.util.Iterator;

public class RecurPaintingAction extends AbstractGameAction {
    private AbstractPlayer p;

    public RecurPaintingAction(int amount) {
        this.p = AbstractDungeon.player;// 15
        this.setValues(this.p, AbstractDungeon.player, amount);// 16
        this.actionType = ActionType.CARD_MANIPULATION;// 17
    }// 18

    public void update() {
        CardGroup group = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        for (AbstractCard q : this.p.discardPile.group) {
            if (q instanceof AbstractPaintingCard) {
                group.addToTop(q);
            }
        }
        if (this.p.hand.size() >= 10 || group.isEmpty()) {// 22
            this.isDone = true;// 23
        } else if (this.p.discardPile.size() == 1) {// 27
            AbstractCard card = (AbstractCard) group.group.get(0);// 28
            if (this.p.hand.size() < 10) {// 29
                this.p.hand.addToHand(card);// 30
                this.p.discardPile.removeCard(card);// 31
            }

            card.lighten(false);// 33
            this.p.hand.refreshHandLayout();// 34
            this.isDone = true;// 35
        } else if (this.duration == 0.5F) {// 39
            AbstractDungeon.gridSelectScreen.open(group, this.amount, "Choose a Painting to return to your hand", false);// 40
            this.tickDuration();// 41
        } else {
            if (AbstractDungeon.gridSelectScreen.selectedCards.size() != 0) {// 47
                Iterator var1 = AbstractDungeon.gridSelectScreen.selectedCards.iterator();// 48

                AbstractCard c;
                while (var1.hasNext()) {
                    c = (AbstractCard) var1.next();
                    if (this.p.hand.size() < 10) {// 49
                        this.p.hand.addToHand(c);// 50
                        this.p.discardPile.removeCard(c);// 51
                    }

                    c.lighten(false);// 53
                    c.unhover();// 54
                }

                AbstractDungeon.gridSelectScreen.selectedCards.clear();// 56
                this.p.hand.refreshHandLayout();// 57

                for (var1 = group.group.iterator(); var1.hasNext(); c.target_y = 0.0F) {// 59 62
                    c = (AbstractCard) var1.next();
                    c.unhover();// 60
                    c.target_x = (float) CardGroup.DISCARD_PILE_X;// 61
                }

                this.isDone = true;// 64
            }

            this.tickDuration();// 67
        }
    }// 24 36 42 68
}
