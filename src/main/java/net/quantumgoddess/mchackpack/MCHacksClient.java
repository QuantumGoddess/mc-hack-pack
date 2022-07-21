package net.quantumgoddess.mchackpack;

import java.util.ArrayList;
import java.util.Arrays;

import org.lwjgl.glfw.GLFW;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import screens.MCHacksScreen;

public class MCHacksClient implements ClientModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	private static KeyBinding keyBinding;


	public static ArrayList<Block> oreBlocks = new ArrayList<>(Arrays.asList(
		Blocks.IRON_ORE, 
		Blocks.COAL_ORE, 
		Blocks.COPPER_ORE,
		Blocks.REDSTONE_ORE, 
		Blocks.LAPIS_ORE, 
		Blocks.GOLD_ORE, 
		Blocks.DIAMOND_ORE
	));

	public static final MinecraftClient CLIENTINSTANCE = MinecraftClient.getInstance();

	

	@Override
	public void onInitializeClient() {
		
		// TODO Auto-generated method stub
		keyBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
    		"key.MCHacks.openGUI", // The translation key of the keybinding's name
			InputUtil.Type.KEYSYM, // The type of the keybinding, KEYSYM for keyboard, MOUSE for mouse.
			GLFW.GLFW_KEY_V, // The keycode of the key
			"category.MCHacks.ControlCategory" // The translation key of the keybinding's category.
		));

		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			while (keyBinding.wasPressed()) {
				openMCHacksMenu(client);
			}
			
			if(MCHacks.flyHack.isEnabled()){
				MCHacks.flyHack.tick(client);
			}
		});

		
	}
	

	public void openMCHacksMenu(MinecraftClient client) {
        if (client.currentScreen != null) {
            return;
        }
        client.setScreen(new MCHacksScreen());
    }
}



