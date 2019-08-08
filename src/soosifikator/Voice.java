package soosifikator;

import com.sun.speech.freetts.VoiceManager;


public class Voice {
	
	private String name;
	// create empty TTS
	private com.sun.speech.freetts.Voice voice;
    private VoiceManager voiceMN;

	
	public Voice (String name) {
		System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
		
		
		//testcode Ambrola
		//System.setProperty("freetts.voices", "de.dfki.lt.freetts.en.us.MbrolaVoiceDirectory");
		System.setProperty("mbrola.base", "C:\\Users\\Adrian\\eclipse-workspace\\mbrola");
		//voiceMN = VoiceManager.getInstance();
		//voice = voiceMN.getVoice("mbrola_us2");
		//testcode 

		this.name = name;
		this.voice = VoiceManager.getInstance().getVoice(this.name);
		this.voice.allocate();
		this.voice.setRate(100);
	}
	
	public void say(String something) {
		this.voice.speak(something);
	}
}
