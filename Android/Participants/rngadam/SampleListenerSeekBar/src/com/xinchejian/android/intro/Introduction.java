package com.xinchejian.android.intro;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.SeekBar;

public class Introduction extends Activity {
    private EditText valueEditText;
	private SeekBar progressSeekBar;

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        valueEditText = (EditText) findViewById(R.id.value); 
        progressSeekBar = (SeekBar) findViewById(R.id.progress);
        progressSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
			
			public void onStopTrackingTouch(SeekBar seekBar) {
			}
			
			public void onStartTrackingTouch(SeekBar seekBar) {
			}
			
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				valueEditText.setText("Current: " + progress);
			}
		});
    }
}