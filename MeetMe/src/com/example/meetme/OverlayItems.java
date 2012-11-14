package com.example.meetme;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;

import com.example.meetme.model.User;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.OverlayItem;

public class OverlayItems extends ItemizedOverlay {
	private ArrayList<OverlayItem> mOverlays = new ArrayList<OverlayItem>();
	private List<User> otherUsers;
	Context mContext;
	User me;

	public OverlayItems(Drawable defaultMarker, Context context, User me) {
		super(boundCenterBottom(defaultMarker));
		mContext = context;
		this.me = me;
		updateAllView();
	}

	public void addOverlay(OverlayItem overlay) {
		mOverlays.add(overlay);
		populate();
	}

	public void updateAllView() {
		this.mOverlays.clear();
		addUser(me);
		if (otherUsers != null) {
			for (User otherUser : otherUsers) {
				addUser(otherUser);				
			}
		}
	}

	public void addUser(User user) {
			int lat = (int) (user.getLatitude() * 1E6);
			int lng = (int) (user.getLongitude() * 1E6);
			GeoPoint userPosition = new GeoPoint(lat, lng);
			OverlayItem overlayUser = new OverlayItem(userPosition,
					user.getName(), "Status: " + user.getStatus());
			this.addOverlay(overlayUser);
	}

	@Override
	protected OverlayItem createItem(int i) {
		return mOverlays.get(i);
	}

	public void clearAllOverlays() {
		this.mOverlays.clear();
	}

	@Override
	public int size() {
		return mOverlays.size();
	}

	@Override
	protected boolean onTap(int index) {
		OverlayItem item = mOverlays.get(index);
		AlertDialog.Builder dialog = new AlertDialog.Builder(mContext);
		dialog.setTitle(item.getTitle());
		dialog.setMessage(item.getSnippet());
		dialog.show();
		return true;
	}

	public User getMe() {
		return me;
	}

	public void setMe(User me) {
		this.me = me;
	}

	public List<User> getOtherUsers() {
		return otherUsers;
	}

	public void setOtherUsers(List<User> otherUsers) {
		this.otherUsers = otherUsers;
	}

}
