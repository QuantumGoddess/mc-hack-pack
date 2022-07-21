/*
 * Decompiled with CFR 0.1.1 (FabricMC 57d88659).
 */
package screens;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.util.math.MatrixStack;
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
        this.addDrawableChild(new ButtonWidget(this.width / 2 - 102, this.height / 4 + 24 + -16, 204, 20, Text.translatable("MCHacks.menuFly" + MCHacks.flyHack.getState()), button -> MCHacks.flyHack.toggle()));
        this.addDrawableChild(new ButtonWidget(this.width / 2 - 102, this.height / 4 + 48 + -16, 204, 20, Text.translatable("MCHacks.menuXray" + MCHacks.xray.getState()), button -> MCHacks.xray.toggle()));
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

