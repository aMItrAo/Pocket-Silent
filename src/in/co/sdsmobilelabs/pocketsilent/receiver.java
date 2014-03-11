package in.co.sdsmobilelabs.pocketsilent;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.AudioManager;

public class receiver extends BroadcastReceiver implements SensorEventListener {

	private SensorManager mSensorManager;
	@SuppressWarnings("unused")
	private Sensor mProximity, mGravity;
	float fx = 0;
	float fy = 0;
	float fz = 0;
	int proximity = 0;
	AudioManager am;
	static int x;

	@SuppressLint("InlinedApi")
	@Override
	public void onReceive(Context context, Intent arg1) {
		mSensorManager = (SensorManager) context
				.getSystemService(Context.SENSOR_SERVICE);
		mGravity = mSensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY);
		mProximity = mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
		am = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
	}

	@Override
	public void onAccuracyChanged(Sensor arg0, int arg1) {

	}

	@Override
	public void onSensorChanged(SensorEvent event) {

		Sensor sensor = event.sensor;
		if (sensor.equals(mProximity)) {
			proximity = (int) event.values[0];
		} else {
			fx = event.values[0];
			fy = event.values[1];
			fz = event.values[2];
		}
		if (fy > 6.0) {
			if (proximity == 0) {
				am.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
			}
		} else {
			am.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
		}

	}

}
