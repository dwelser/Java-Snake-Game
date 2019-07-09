package game;

import java.util.HashMap;
import java.util.Map;
import org.newdawn.slick.*;

public class Audio 
{
public static Map<String, Sound> soundMap = new HashMap<String, Sound>();
public static Map<String, Music> musicMap = new HashMap<String, Music>();

	public static void loadSounds()
	{
		try
		{
			soundMap.put("eating", new Sound("audio_resources/anaconda_eating.ogg"));
			soundMap.put("death", new Sound("audio_resources/anaconda_death.ogg"));
			musicMap.put("music", new Music("audio_resources/anaconda.ogg"));
		}
		catch(SlickException e)
		{
			e.printStackTrace();
		}
}
	public static Music getMusic(String key)
	{
		return musicMap.get(key);
	}
	public static Sound getSound(String key)
	{
		return soundMap.get(key);
	}

}
