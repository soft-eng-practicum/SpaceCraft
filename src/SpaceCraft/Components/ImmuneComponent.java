package SpaceCraft.Components;

import com.almasb.ents.component.BooleanComponent;

public class ImmuneComponent extends BooleanComponent
{
	public ImmuneComponent()
	{
		super(false);
	}

	/**
	 * @param playerImmune
	 */
	public ImmuneComponent(boolean playerImmune)
	{
		super(playerImmune);
	}
}
