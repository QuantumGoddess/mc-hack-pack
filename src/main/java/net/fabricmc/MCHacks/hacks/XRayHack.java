package net.fabricmc.MCHacks.hacks;

import net.fabricmc.MCHacks.MCHacksClient;

public final class XRayHack extends Hack
{
	
	public XRayHack()
	{
		super("XRayHack", "See through blocks");
		//setCategory(Category.MOVEMENT);
	}
	
	@Override
	public void onEnable(){
		MCHacksClient.CLIENTINSTANCE.worldRenderer.reload();

	}

	@Override
	public void onDisable(){
		MCHacksClient.CLIENTINSTANCE.worldRenderer.reload();

	}
}