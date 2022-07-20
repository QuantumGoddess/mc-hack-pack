package net.fabricmc.MCHacks.hacks;

import net.fabricmc.MCHacks.MCHacks;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket.OnGroundOnly;
import net.minecraft.util.math.Vec3d;

public final class FlyHack extends Hack
{
	private boolean isFlying = false;
	private boolean pressed = false;
	int abilityResyncCountdown = 0;

	public FlyHack()
	{
		super("FlyHack", "A hack to fly");
		//setCategory(Category.MOVEMENT);
	}
	
	public void tick(MinecraftClient client)
	{
		ClientPlayerEntity player = client.player;

        if (abilityResyncCountdown > 0) {
            --abilityResyncCountdown;
        }

		if(!player.input.jumping)
			pressed = false;

		if (!pressed && player.input.jumping) {
			pressed = true;
			MCHacks.LOGGER.info("" + abilityResyncCountdown);
			if (!player.isSwimming() && abilityResyncCountdown > 0) {
				isFlying = !isFlying;
				abilityResyncCountdown = 0;
			}
			else if (abilityResyncCountdown == 0) {
				abilityResyncCountdown = 7;
			}
		}
		

		if(isFlying){		
			player.getAbilities().flying = false;
			player.airStrafingSpeed = 1.2f;
			
			player.setVelocity(0, 0, 0);
			Vec3d velocity = player.getVelocity();
			
			if(client.options.jumpKey.isPressed())
				player.setVelocity(velocity.add(0, 0.6f, 0));
			
			if(client.options.sneakKey.isPressed())
				player.setVelocity(velocity.subtract(0, 0.6f, 0));
			
			if(player.fallDistance <= (player.isFallFlying() ? 1 : 2))
				return;
			
			
		}

		if(player.isFallFlying() && player.isSneaking()
				&& !isFallingFastEnoughToCauseDamage(player))
				return;
			
			player.networkHandler.sendPacket(new OnGroundOnly(true));
	
	}
	
	private boolean isFallingFastEnoughToCauseDamage(ClientPlayerEntity player)
	{
		return player.getVelocity().y < -0.5;
	}
}