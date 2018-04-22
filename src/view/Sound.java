package view;

import java.io.FileInputStream;
import java.io.IOException;

import sun.audio.AudioData;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.audio.ContinuousAudioDataStream;

public class Sound {

    
        public static void main(String[] args){
        	   music();
    }
		private static void music() {
		    AudioPlayer MGP = AudioPlayer.player;  
	        AudioStream BGM;  
	        AudioData MD;  
	        ContinuousAudioDataStream loop = null;  

	        try {  
	            BGM = new AudioStream(new FileInputStream("./src/view/melody.mp3"));  
	            MD = BGM.getData();  
	            loop = new ContinuousAudioDataStream(MD);  
	        } catch(IOException error)  {  
	            System.out.println("Error!!!");  

	        }  
	        MGP.start(loop);  
			
		} 
}


