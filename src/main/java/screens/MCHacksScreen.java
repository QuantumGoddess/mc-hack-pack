/*
 * Decompiled with CFR 0.1.1 (FabricMC 57d88659).
 */
package screens;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.MessageScreen;
import net.minecraft.client.gui.screen.OpenToLanScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.StatsScreen;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.gui.screen.multiplayer.MultiplayerScreen;
import net.minecraft.client.gui.screen.option.OptionsScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.realms.gui.screen.RealmsMainScreen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.quantumgoddess.mchackpack.MCHacks;

@Environment(value=EnvType.CLIENT)
public class MCHacksScreen
extends Screen {

    public MCHacksScreen() {
        super(Text.translatable("MCHacks.menu"));
    }

    @Override
    protected void init() {
        this.addDrawableChild(new ButtonWidget(this.width / 2 - 102, this.height / 4 + 24 + -16, 204, 20, Text.translatable("MCHacks.menuFly" + MCHacks.flyHack.getState()), button -> {
            MCHacks.flyHack.toggle();
            
        }));
        this.addDrawableChild(new ButtonWidget(this.width / 2 - 102, this.height / 4 + 48 + -16, 98, 20, Text.translatable("MCHacks.menuXray" + MCHacks.xray.getState()), button -> MCHacks.xray.toggle()));
        this.addDrawableChild(new ButtonWidget(this.width / 2 + 4, this.height / 4 + 48 + -16, 98, 20, Text.translatable("gui.stats"), button -> this.client.setScreen(new StatsScreen(this, this.client.player.getStatHandler()))));
        this.addDrawableChild(new ButtonWidget(this.width / 2 - 102, this.height / 4 + 96 + -16, 98, 20, Text.translatable("menu.options"), button -> this.client.setScreen(new OptionsScreen(this, this.client.options))));
        ButtonWidget buttonWidget2 = this.addDrawableChild(new ButtonWidget(this.width / 2 + 4, this.height / 4 + 96 + -16, 98, 20, Text.translatable("menu.shareToLan"), button -> this.client.setScreen(new OpenToLanScreen(this))));
        buttonWidget2.active = this.client.isIntegratedServerRunning() && !this.client.getServer().isRemote();
        MutableText text = this.client.isInSingleplayer() ? Text.translatable("menu.returnToMenu") : Text.translatable("menu.disconnect");
        this.addDrawableChild(new ButtonWidget(this.width / 2 - 102, this.height / 4 + 120 + -16, 204, 20, text, button -> {
            boolean bl = this.client.isInSingleplayer();
            boolean bl2 = this.client.isConnectedToRealms();
            button.active = false;
            this.client.world.disconnect();
            if (bl) {
                this.client.disconnect(new MessageScreen(Text.translatable("menu.savingLevel")));
            } else {
                this.client.disconnect();
            }
            TitleScreen titleScreen = new TitleScreen();
            if (bl) {
                this.client.setScreen(titleScreen);
            } else if (bl2) {
                this.client.setScreen(new RealmsMainScreen(titleScreen));
            } else {
                this.client.setScreen(new MultiplayerScreen(titleScreen));
            }
        }));
    }

    @Override
    public void tick() {
        super.tick();
    }

    @Override
    public void removed() {
        this.client.keyboard.setRepeatEvents(false);
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        this.renderBackground(matrices);
        MCHacksScreen.drawCenteredText(matrices, this.textRenderer, this.title, this.width / 2, 40, 0xFFFFFF);
        super.render(matrices, mouseX, mouseY, delta);
    }
}

