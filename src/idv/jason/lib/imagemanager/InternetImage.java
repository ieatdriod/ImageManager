package idv.jason.lib.imagemanager;

import com.jason.lib.imagemanager.conn.HttpInvoker;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class InternetImage extends BaseImage{
	public static final String TAG = InternetImage.class.getSimpleName();
	private Context mContext;
	private String mUrl;
	private Bitmap mBitmap;
	
	public InternetImage(Context context, String url) {
		mContext = context;
		mUrl = url;
	}
	
	public Bitmap getBitmap() {
		if (mBitmap != null) {
			return mBitmap;
		}
		
		try {
			if (HttpInvoker.isNetworkAvailable(mContext)) {
				BitmapFactory.Options options = new BitmapFactory.Options();
				options.inDither = false;
				mBitmap = BitmapFactory.decodeStream(
						HttpInvoker.getInputStreamFromUrl(mUrl), null, options);
			}
		} catch (Exception e) {
			mBitmap = null;
			e.printStackTrace();
		}
		return mBitmap;
	}
	
	@Override
	public void setBitmap(Bitmap bm) {
		mBitmap = bm;
	}
}
