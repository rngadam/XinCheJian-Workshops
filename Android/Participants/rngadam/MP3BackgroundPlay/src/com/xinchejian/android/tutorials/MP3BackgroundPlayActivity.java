package com.xinchejian.android.tutorials;

import android.app.Activity;
import android.media.AudioManager;
import android.media.SoundPool;
import android.media.SoundPool.OnLoadCompleteListener;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;

public class MP3BackgroundPlayActivity extends Activity {
	private static final int SOUND_POOL_SUCCESS = 0;
	private static final int PLAYBACK_RATE = 1;
	private static final int LOOP_FOREVER = -1;
	//Clips from: 
	//http://www.partnersinrhyme.com/soundfx/car_sounds/car_motorcycle-idling_wav.shtml
	protected static final int PRIORITY = 0;
	private Button playButton;
	private SoundPool soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
	private int idleSoundId = -1;
	private int cruiseSoundId = -1;
	private int up2SpeedSoundId = -1;
	private int streamId = -1;
	private SeekBar motorControl;
	private int lastSpeed;
	private String TAG = MP3BackgroundPlayActivity.class.getCanonicalName();
	private boolean accelerating;


	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		soundPool.setOnLoadCompleteListener(new OnLoadCompleteListener() {
			public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
				if(status != SOUND_POOL_SUCCESS) {
					notifyUser("Load of " + sampleId + " failed");
					return;
				}
				if(idleSoundId != -1 && cruiseSoundId != 1 && up2SpeedSoundId != -1) {
					playButton.setEnabled(true);
					playButton.setText("Press to play");
				}
			}});
		idleSoundId = soundPool.load(this, R.raw.idle, 1);
		cruiseSoundId = soundPool.load(this, R.raw.cruisen, 1);
		up2SpeedSoundId = soundPool.load(this, R.raw.up2speed, 1);

		playButton = (Button) findViewById(R.id.playButton);
		playButton.setEnabled(false);
		playButton.setText("Waiting for sound clips to load");
		playButton.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				synchronized(soundPool) { // it's possible to get multiple clicks... make sure we process one at a time
					if(streamId == -1) {
						// there was no stream playing so start playing
						playButton.setText("Playing, click to stop");
						play(idleSoundId);
						motorControl.setEnabled(true);					
					// stream was playing so we to stop it
					} else {
						motorControl.setEnabled(false);	
						soundPool.stop(streamId);
						streamId = -1;
						playButton.setText("Start playing");
					}
				}
			}});

		motorControl = (SeekBar) findViewById(R.id.motoControl);
		motorControl.setEnabled(false);
		motorControl.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

			public void onStopTrackingTouch(SeekBar seekBar) {
				accelerating = false;
				if(lastSpeed == 0) {
					play(idleSoundId);			
				} else {
					play(cruiseSoundId);
				}
			}

			public void onStartTrackingTouch(SeekBar seekBar) {
				if(!accelerating) {
					play(up2SpeedSoundId);
					accelerating = true;
				}
			}

			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				lastSpeed = progress;
				float volume = calcVolume();
				soundPool.setVolume(streamId, volume, volume);

			}
		});
	}
	
	private void play(int clip) {
		// max simultaneous sound is one
		Log.d(TAG , "Playing " + clip);
		float volume;
		if(idleSoundId == clip) {
			volume = 0.5f;
		} else {
			volume = calcVolume();
			
		}
		synchronized(soundPool) {
			streamId = soundPool.play(clip, volume, volume, PRIORITY, LOOP_FOREVER, PLAYBACK_RATE);
		}
	}

	private float calcVolume() {
		float volume;
		volume = ((float)lastSpeed)/100.0f;
		if(volume > 1.0f) {
			volume = 1.0f;
		}
		return volume;
	}
	
	protected void notifyUser(String string) {
		Toast.makeText(this, string, Toast.LENGTH_LONG).show();
	}
}