package com.xinchejian.android.rngadam.robot;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.SeekBar;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.xinchejian.android.bluetooth.Bluetooth;
import com.xinchejian.android.bluetooth.RobotControl;

public class RobotUserInterface extends Activity {
    private Bluetooth bluetooth;
	private RobotControl robotControl;
	private static final String arduinoAddress = "00:11:03:14:02:02";
	private Toast unableToConnectBluetoothToast;
	private ToggleButton bluetoothConnectionStatusToggle;
	private ToggleButton deviceConnectedStatusToggle;
	private int updownValue = RobotControl.UPDOWN_DEFAULT_POS;
	private int sidewaysValue = RobotControl.SIDEWAYS_DEFAULT_POS;
	private int prevUpDownValue =  0;
	private int prevSidewaysValue = 0;
	
	private final Handler handlerTimer = new Handler();
	private final Runnable taskTimerUpdate = new Runnable() {
		public void run() {
			if(updownValue != prevUpDownValue) {
				robotControl.sendCommandString(RobotControl.UPDOWN_AXIS, updownValue);
				prevUpDownValue = updownValue;
			}
			if(prevSidewaysValue != sidewaysValue) {
			    robotControl.sendCommandString(RobotControl.SIDEWAYS_AXIS, sidewaysValue);
			    prevSidewaysValue = sidewaysValue;
				
			}
			if(!bluetooth.isConnected()) {
				unableToConnectBluetoothToast.show();
			} 
			bluetoothConnectionStatusToggle.setChecked(bluetooth.checkBluetoothAvailable());
			deviceConnectedStatusToggle.setChecked(bluetooth.isConnected());
	        handlerTimer.postDelayed(this, 300);   			
		}
	};
	private SeekBar upDownSeekBar;
	private SeekBar sidewaysSeekBar;


	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        unableToConnectBluetoothToast = Toast.makeText(this, "Unable to connect to bluetooth", 500);
        bluetoothConnectionStatusToggle = (ToggleButton) findViewById(R.id.bluetoothConnectionStatus);
        deviceConnectedStatusToggle = (ToggleButton) findViewById(R.id.bluetoothConnected);
        upDownSeekBar = (SeekBar) findViewById(R.id.UpDownSeekBar);
        sidewaysSeekBar = (SeekBar) findViewById(R.id.SidewaysSeekBar);
        upDownSeekBar.setProgress(updownValue);
        sidewaysSeekBar.setProgress(sidewaysValue);
        upDownSeekBar.setMax(RobotControl.UPDOWN_MAX_POS);
        sidewaysSeekBar.setMax(RobotControl.SIDEWAYS_MAX_POS);
        upDownSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
			public void onProgressChanged(final SeekBar seekBar,
					final int progress, final boolean fromUser) {
				if (fromUser) {
					if (progress < RobotControl.UPDOWN_MIN_POS) {
						updownValue = RobotControl.UPDOWN_MIN_POS;
					} else {
						updownValue = progress;
					}
				}
			}

			public void onStartTrackingTouch(SeekBar seekBar) {
			}

			public void onStopTrackingTouch(SeekBar seekBar) {
			}

		});
        sidewaysSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
			public void onProgressChanged(final SeekBar seekBar,
					final int progress, final boolean fromUser) {
				if (fromUser) {
					if (progress < RobotControl.SIDEWAYS_MIN_POS) {
						sidewaysValue = RobotControl.SIDEWAYS_MIN_POS;
					} else {
						sidewaysValue = progress;
					}
				}
			}

			public void onStartTrackingTouch(SeekBar seekBar) {
			}

			public void onStopTrackingTouch(SeekBar seekBar) {
			}
        });
        
        bluetooth = new Bluetooth(arduinoAddress);
        robotControl = new RobotControl(bluetooth);
		
        handlerTimer.postDelayed(taskTimerUpdate, 300);  
        
    }
}