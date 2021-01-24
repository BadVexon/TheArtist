package theArtist.util;

import basemod.ReflectionHacks;
import basemod.abstracts.CustomEnergyOrb;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import theArtist.ArtistMod;

public class RainbowOrb extends CustomEnergyOrb {
    public RainbowOrb(String[] orbTexturePaths, String orbVfxPath, float[] layerSpeeds) {
        super(orbTexturePaths, orbVfxPath, layerSpeeds);
    }

    public void renderOrb(SpriteBatch sb, boolean enabled, float current_x, float current_y) {
        sb.setColor(ArtistMod.rainbow);// 107
        int i;
        if (enabled) {// 108
            for (i = 0; i < this.energyLayers.length; ++i) {// 109
                sb.draw(this.energyLayers[i], current_x - 64.0F, current_y - 64.0F, 64.0F, 64.0F, 128.0F, 128.0F, (float) ReflectionHacks.getPrivateStatic(CustomEnergyOrb.class, "ORB_IMG_SCALE"), (float) ReflectionHacks.getPrivateStatic(CustomEnergyOrb.class, "ORB_IMG_SCALE"), this.angles[i], 0, 0, 128, 128, false, false);// 110
            }
        } else {
            for (i = 0; i < this.noEnergyLayers.length; ++i) {// 121
                sb.draw(this.noEnergyLayers[i], current_x - 64.0F, current_y - 64.0F, 64.0F, 64.0F, 128.0F, 128.0F, (float) ReflectionHacks.getPrivateStatic(CustomEnergyOrb.class, "ORB_IMG_SCALE"), (float) ReflectionHacks.getPrivateStatic(CustomEnergyOrb.class, "ORB_IMG_SCALE"), this.angles[i], 0, 0, 128, 128, false, false);// 122
            }
        }

        sb.draw(this.baseLayer, current_x - 64.0F, current_y - 64.0F, 64.0F, 64.0F, 128.0F, 128.0F, (float) ReflectionHacks.getPrivateStatic(CustomEnergyOrb.class, "ORB_IMG_SCALE"), (float) ReflectionHacks.getPrivateStatic(CustomEnergyOrb.class, "ORB_IMG_SCALE"), 0.0F, 0, 0, 128, 128, false, false);// 133
    }// 142
}
