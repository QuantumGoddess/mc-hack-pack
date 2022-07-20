package net.fabricmc.MCHacks.hacks;

import java.util.Objects;

public abstract class Hack
{
	private final String name;
	private final String description;
	//private Category category;
	
	private boolean enabled;
	
	public Hack(String name, String description)
	{
		this.name = Objects.requireNonNull(name);
		this.description = Objects.requireNonNull(description);
	}
	
	public final String getName()
	{
		return name;
	}
	
	public String getRenderName()
	{
		return name;
	}
	
	public final String getDescription()
	{
		return description;
	}
	
	// public final Category getCategory()
	// {
	// 	return category;
	// }
	
	// protected final void setCategory(Category category)
	// {
	// 	this.category = category;
	// }
	
	public final boolean isEnabled()
	{
		return enabled;
	}
	
	public final void setEnabled(boolean enabled)
	{
		if(this.enabled == enabled)
			return;
		
		this.enabled = enabled;
		
		if(enabled)
			onEnable();
		else
			onDisable();
	}
	
	public final String getState()
	{
		return enabled ? "Disable" : "Enable";
	}
	
	public final void toggle()
	{
		setEnabled(!enabled);
	}
	
	protected void onEnable()
	{
		
	}
	
	protected void onDisable()
	{
		
	}
}