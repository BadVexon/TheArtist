package theArtist.util;

import com.evacipated.cardcrawl.mod.bard.notes.*;
import com.megacrit.cardcrawl.cards.AbstractCard;

public class BardHelper {
    public static AbstractCard.CardTags attackTag() {
        return AttackNote.TAG;
    }

    public static AbstractCard.CardTags blockTag() {
        return BlockNote.TAG;
    }

    public static AbstractCard.CardTags buffTag() {
        return BuffNote.TAG;
    }

    public static AbstractCard.CardTags debuffTag() {
        return DebuffNote.TAG;
    }

    public static AbstractCard.CardTags wildTag() {
        return WildCardNote.TAG;
    }
}
