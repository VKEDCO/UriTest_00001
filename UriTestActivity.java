package org.vkedco.mobappdev.uri_test;

import java.util.List;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.widget.EditText;
import android.content.UriMatcher;
import android.content.res.Resources;

public class UriTestActivity extends Activity {
	
	EditText edTxtUriInfo = null;
	Resources mRes = null;
	
	// AUTHORITY & PATHS
	static final String AUTHORITY = "org.vkedco.mobappdev.content_providers.books";
	static final String BOOK_ID_PATH     = "book_title" + "/id";
	static final String BOOK_TITLE_PATH  = "book_title" + "/title";
	static final String BOOK_AUTHOR_PATH = "book_author" + "/author";
	static final String BOOK_COVER_PATH  = "book_cover" + "/cover_image";
	static final String SLASH_HASH       = "/#";
	static final String LOGTAG    = "URI_TEST";
	
	private static final UriMatcher mUriMatcher;
	
	// uri matching codes
	static final int CODE_BOOK_ID_ALL        = 0;
	static final int CODE_BOOK_ID_SINGLE     = 1;
	static final int CODE_BOOK_TITLE_ALL     = 2;
	static final int CODE_BOOK_TITLE_SINGLE  = 3;
	static final int CODE_BOOK_AUTHOR_ALL    = 4;
	static final int CODE_BOOK_AUTHOR_SINGLE = 5;
	static final int CODE_BOOK_COVER_ALL     = 6;
	static final int CODE_BOOK_COVER_SINGLE  = 7;
	
	// for debugging purposes only
	static final String CODE_BOOK_ID_ALL_NAME        = "CODE_BOOK_ID_ALL";
	static final String CODE_BOOK_ID_SINGLE_NAME     = "CODE_BOOK_ID_SINGLE";
	static final String CODE_BOOK_TITLE_ALL_NAME     = "CODE_BOOK_TITLE_ALL";
	static final String CODE_BOOK_TITLE_SINGLE_NAME  = "CODE_BOOK_TITLE_SINGLE";
	static final String CODE_BOOK_AUTHOR_ALL_NAME    = "CODE_BOOK_AUTHOR_ALL";
	static final String CODE_BOOK_AUTHOR_SINGLE_NAME = "CODE_BOOK_AUTHOR_SINGLE";
	static final String CODE_BOOK_COVER_ALL_NAME     = "CODE_BOOK_COVER_ALL";
	static final String CODE_BOOK_COVER_SINGLE_NAME  = "CODE_BOOK_COVER_SINGLE";
	
	// mime type constants
	static final String BOOK_TITLE_ID_ALL_MIME    = "vnd.android.cursor.dir/vnd.vkedco.mobappdev.book_id_all";
	static final String BOOK_TITLE_ID_SINGLE_MIME = "vnd.android.cursor.item/vnd.vkedco.mobappdev.book_id_single";

	static final String BOOK_TITLE_ALL_MIME    = "vnd.android.cursor.dir/vnd.vkedco.mobappdev.book_title_all";
	static final String BOOK_TITLE_SINGLE_MIME = "vnd.android.cursor.item/vnd.vkedco.mobappdev.book_title_single";

	static final String BOOK_AUTHOR_ALL_MIME    = "vnd.android.cursor.dir/vnd.vkedco.mobappdev.book_author_all";
	static final String BOOK_AUTHOR_SINGLE_MIME = "vnd.android.cursor.dir/vnd.vkedco.mobappdev.book_author_single";

	static final String BOOK_COVER_ALL_MIME    = "vnd.android.cursor.dir/vnd.vkedco.mobappdev.book_cover_all";
	static final String BOOK_COVER_SINGLE_MIME = "vnd.android.cursor.dir/vnd.vkedco.mobappdev.book_cover_single";

	// let's create a UriMatcher and add a few URIs to it
	static {
		mUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

		// content://org.vkedco.mobappdev.content_providers.books/book_title/id
		mUriMatcher.addURI(AUTHORITY, BOOK_ID_PATH, CODE_BOOK_ID_ALL);
		// content://org.vkedco.mobappdev.content_providers.books/book_title/id/#
		mUriMatcher.addURI(AUTHORITY, BOOK_ID_PATH + SLASH_HASH, CODE_BOOK_ID_SINGLE);

		// content://org.vkedco.mobappdev.content_providers.books/book_title/title
		mUriMatcher.addURI(AUTHORITY, BOOK_TITLE_PATH, CODE_BOOK_TITLE_ALL);
		// content://org.vkedco.mobappdev.content_providers.books/book_title/title/#
		mUriMatcher.addURI(AUTHORITY, BOOK_TITLE_PATH + SLASH_HASH, CODE_BOOK_TITLE_SINGLE);

		// content://org.vkedco.mobappdev.content_providers.books/book_author/author
		mUriMatcher.addURI(AUTHORITY, BOOK_AUTHOR_PATH, CODE_BOOK_AUTHOR_ALL);
		// content://org.vkedco.mobappdev.content_providers.books/book_author/author/#
		mUriMatcher.addURI(AUTHORITY, BOOK_AUTHOR_PATH + SLASH_HASH,
				CODE_BOOK_AUTHOR_SINGLE);

		// content://org.vkedco.mobappdev.content_providers.books/book_cover_image/cover_image
		mUriMatcher.addURI(AUTHORITY, BOOK_COVER_PATH, CODE_BOOK_COVER_ALL);
		// content://org.vkedco.mobappdev.content_providers.books/book_cover_image/cover_image/#
		mUriMatcher.addURI(AUTHORITY, BOOK_COVER_PATH + SLASH_HASH, CODE_BOOK_COVER_SINGLE);
	}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edTxtUriInfo = (EditText) findViewById(R.id.edTxtUriInfo);
        mRes = getResources();
        
        String uri_1 = getUriInfo(Uri.parse(mRes.getString(R.string.test_uri_1)));
        String uri_2 = getUriInfo(Uri.parse(mRes.getString(R.string.test_uri_2)));
        String uri_3 = getUriInfo(Uri.parse(mRes.getString(R.string.test_uri_3)));
        String uri_4 = getUriInfo(Uri.parse(mRes.getString(R.string.test_uri_4)));
        String uri_5 = getUriInfo(Uri.parse(mRes.getString(R.string.test_uri_5)));
        
        edTxtUriInfo.setText(uri_1 + uri_2 + uri_3 + uri_4 + uri_5);
        //edTxtUriInfo.setEnabled(false);
        
        // do testing and Log.d results
        testLogMatchUri(mRes);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    static void logMatchUri(Uri uri) {
    	int uri_match_code = mUriMatcher.match(uri);
    	switch ( uri_match_code ) {
    	case CODE_BOOK_ID_ALL: 
    		logUriMatchInfo(uri, uri_match_code, CODE_BOOK_ID_ALL_NAME);
    		break;
    	case CODE_BOOK_ID_SINGLE:
    		logUriMatchInfo(uri, uri_match_code, CODE_BOOK_ID_SINGLE_NAME);
    		break;
    	case CODE_BOOK_TITLE_ALL: 
    		logUriMatchInfo(uri, uri_match_code, CODE_BOOK_TITLE_ALL_NAME);
    		break;
    	case CODE_BOOK_TITLE_SINGLE: 
    		logUriMatchInfo(uri, uri_match_code, CODE_BOOK_TITLE_SINGLE_NAME);
    		break;
    	case CODE_BOOK_AUTHOR_ALL: 
    		logUriMatchInfo(uri, uri_match_code, CODE_BOOK_AUTHOR_ALL_NAME);
    		break;
    	case CODE_BOOK_AUTHOR_SINGLE: 
    		logUriMatchInfo(uri, uri_match_code, CODE_BOOK_AUTHOR_SINGLE_NAME);
    		break;
    	case CODE_BOOK_COVER_ALL: 
    		logUriMatchInfo(uri, uri_match_code, CODE_BOOK_COVER_ALL_NAME);
    		break;
    	case CODE_BOOK_COVER_SINGLE: 
    		logUriMatchInfo(uri, uri_match_code, CODE_BOOK_COVER_SINGLE_NAME);
    		break;
    	}
    }
    
    // test calls to matchUri
    static void testLogMatchUri(Resources res) {
    	logMatchUri(Uri.parse(res.getString(R.string.uri_book_title_id_all)));
        logMatchUri(Uri.parse(res.getString(R.string.uri_book_title_id_1)));
        logMatchUri(Uri.parse(res.getString(R.string.uri_book_title_id_2)));
        
        logMatchUri(Uri.parse(res.getString(R.string.uri_book_title_all)));
        logMatchUri(Uri.parse(res.getString(R.string.uri_book_title_10)));
        logMatchUri(Uri.parse(res.getString(R.string.uri_book_title_20)));
        
        logMatchUri(Uri.parse(res.getString(R.string.uri_book_author_all)));
        logMatchUri(Uri.parse(res.getString(R.string.uri_book_author_30)));
        logMatchUri(Uri.parse(res.getString(R.string.uri_book_author_40)));
    }
    
    // log info about the matching uri
    static void logUriMatchInfo(Uri uri, int matchCode, String matchCodeName) {
    	Log.d(LOGTAG, "uri=" + uri.toString() + " code=" + matchCode + " code_name=" + matchCodeName);
    	StringBuilder sb = new StringBuilder("");
    	List<String> pathSegments = uri.getPathSegments();
    	for(String segment : pathSegments)
    		sb.append(segment + ";");
    	Log.d(LOGTAG, "path_segments=" + sb.toString());
    	for(int i = 0; i < pathSegments.size(); i++) {
    		Log.d(LOGTAG, "path_segment[" + i + "]=" + pathSegments.get(i));
    	}
    }
    
    // convert uri into string
    static String getUriInfo(Uri uri) {
    	StringBuilder sb = new StringBuilder("");
    	sb.append("\n==================\n");
    	sb.append("URI: " + uri.toString() + "\n");
    	sb.append("SCHEME: " + uri.getScheme() + "\n");
    	sb.append("AUTHORITY: " + uri.getAuthority() + "\n");
    	sb.append("PATH=" + uri.getPath() + "\n");
    	sb.append("PATH SEGMENTS:\n");
    	List<String> pathSegments = uri.getPathSegments();
    	for(int i = 0; i < pathSegments.size(); i++) {
    		sb.append("path_sgmnt[" + i + "]=" + pathSegments.get(i) + "\n");
    	}
    	sb.append("PORT: " + uri.getPort() + "\n");
    	sb.append("QUERY: " + uri.getQuery() + "\n");
    	sb.append("TITLE_PARAM: " + uri.getQueryParameter("title") + "\n");
    	sb.append("AUTHOR_PARAM: " + uri.getQueryParameter("author") + "\n");
    	sb.append("MIME: " + getUriMIME(uri));
    	return sb.toString();
    }
    
    static String getUriMIME(Uri uri) {
    	int uri_match_code = mUriMatcher.match(uri);
    	switch ( uri_match_code ) {
    	case CODE_BOOK_ID_ALL: 
    		return UriTestActivity.BOOK_AUTHOR_ALL_MIME;
    	case CODE_BOOK_ID_SINGLE:
    		return UriTestActivity.BOOK_AUTHOR_SINGLE_MIME;
    	case CODE_BOOK_TITLE_ALL: 
    		return UriTestActivity.BOOK_TITLE_ALL_MIME;
    	case CODE_BOOK_TITLE_SINGLE: 
    		return UriTestActivity.BOOK_TITLE_SINGLE_MIME;
    	case CODE_BOOK_AUTHOR_ALL: 
    		return UriTestActivity.BOOK_AUTHOR_ALL_MIME;
    	case CODE_BOOK_AUTHOR_SINGLE: 
    		return UriTestActivity.BOOK_AUTHOR_SINGLE_MIME;
    	case CODE_BOOK_COVER_ALL: 
    		return UriTestActivity.BOOK_COVER_ALL_MIME;
    	case CODE_BOOK_COVER_SINGLE: 
    		return UriTestActivity.BOOK_COVER_ALL_MIME;
    	}
    	return "NONE";
    }
    
    
}
