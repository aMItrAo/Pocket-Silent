package in.co.sdsmobilelabs.pocketsilent;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class ServiceManager extends BroadcastReceiver {

	Context mContext;
	private final String BOOT_ACTION = "android.intent.action.BOOT_COMPLETED";

	@Override
	public void onReceive(Context context, Intent intent) {
		mContext = context;
		String action = intent.getAction();
		if (action.equalsIgnoreCase(BOOT_ACTION)) {
			startService();
		}
	}

	private void startService() {
		Intent mServiceIntent = new Intent();
		mServiceIntent
				.setAction("in.co.sdsmobilelabs.pocketsilent.MainActivity");
		mContext.startService(mServiceIntent);
	}
}