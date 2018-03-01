package com.archer.lib.ec.database;

import android.content.Context;

import org.greenrobot.greendao.database.Database;

/**
 * Created by Archer on 2018/2/24.
 */

public class DatabaseManager {
    private DaoSession mDaoSession;
    private UserProfileDao mDao;

    private DatabaseManager() {
    }

    private static final class Holder {
        private static final DatabaseManager INSTANCE = new DatabaseManager();
    }

    public static DatabaseManager getInstance() {
        return Holder.INSTANCE;
    }

    public DatabaseManager init(Context context) {
        initDao(context);
        return this;
    }

    private void initDao(Context context) {
        final ReleaseOpenHelper helper = new ReleaseOpenHelper(context, "fast-ec.db");
        final Database db = helper.getWritableDb();
        mDaoSession = new DaoMaster(db).newSession();
        mDao = mDaoSession.getUserProfileDao();
    }

    public final UserProfileDao getDao() {
        return mDao;
    }
}
