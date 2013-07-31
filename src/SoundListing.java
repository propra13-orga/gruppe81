import java.io.*;
import javax.sound.sampled.*;

public class SoundListing
{
//========================================================SoundListing=======
private static AudioInputStream ais;
private static AudioFormat format;
private static Clip clip;

public static void playSampleFile ( String name ) throws Exception
	{
	//+++++++++
	ais =  AudioSystem.getAudioInputStream(new File(name) );
	format = ais.getFormat();
	
	if((format.getEncoding() == AudioFormat.Encoding.ULAW) || (format.getEncoding() == AudioFormat.Encoding.ALAW)) 
		{
		AudioFormat tmp = new AudioFormat
						(  
						AudioFormat.Encoding.PCM_SIGNED,
						format.getSampleRate(),
						format.getSampleSizeInBits() * 2,
						format.getChannels(),
						format.getFrameSize() * 2,
						format.getFrameRate(),
						true
						);
		ais = AudioSystem.getAudioInputStream(tmp, ais);
		format = tmp;
		}

	// Clip erzeugen und offnen
	DataLine.Info info = new DataLine.Info (Clip.class, format, ( (int) ais.getFrameLength() * format.getFrameSize() ) );

	clip = (Clip)AudioSystem.getLine(info);
	clip.open(ais);

	//Clip abspielen
	clip.start();


//	clip.stop();
//	clip.close();
	}

}




