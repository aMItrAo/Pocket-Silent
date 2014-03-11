package in.co.sdsmobilelabs.pocketsilent;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity implements SensorEventListener {
	private SensorManager mSensorManager;
	private Sensor mProximity, mGravity;
	TextView tv1, tv2, tv3, tv4;
	float fx = 0;
	float fy = 0;
	float fz = 0;
	int proximity = 0;

	@Override
	public final void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		tv1 = (TextView) findViewById(R.id.textView1);
		tv2 = (TextView) findViewById(R.id.textView2);
		tv3 = (TextView) findViewById(R.id.textView3);
		tv4 = (TextView) findViewById(R.id.textView4);
		mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		mGravity = mSensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY);
		mProximity = mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
	}

	@Override
	public final void onAccuracyChanged(Sensor sensor, int accuracy) {
	}

	@Override
	public final void onSensorChanged(SensorEvent event) {
		Sensor sensor = event.sensor;
		if (sensor.equals(mProximity)) {
			proximity = (int) event.values[0];
			tv4.setText(Integer.toString(proximity));
		} else {
			fx = event.values[0];
			tv1.setText(Float.toString(fx));
			fy = event.values[1];
			tv2.setText(Float.toString(fy));
			fz = event.values[2];
			tv3.setText(Float.toString(fz));
		}
		AudioManager am = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
		int x = am.getRingerMode();
		if (proximity == 0) {
			if (fy > 6.0 || fy < -6.0) {
				am.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
			}
		} else {
			am.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		mSensorManager.registerListener(this, mProximity,
				SensorManager.SENSOR_DELAY_NORMAL);
		mSensorManager.registerListener(this, mGravity,
				SensorManager.SENSOR_DELAY_NORMAL);
	}

	@Override
	protected void onPause() {
		super.onPause();
		mSensorManager.unregisterListener(this);
	}
}