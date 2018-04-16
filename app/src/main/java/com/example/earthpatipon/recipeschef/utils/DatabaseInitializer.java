package com.example.earthpatipon.recipeschef.utils;

import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.earthpatipon.recipeschef.database.AppDatabase;
import com.example.earthpatipon.recipeschef.entity.Recipe;
import com.example.earthpatipon.recipeschef.entity.User;

import java.util.List;

public class DatabaseInitializer {

    private static final int DELAY_MILLIS = 500;

    public static void populateAsync(final AppDatabase db) {
        PopulateDbAsync task = new PopulateDbAsync(db);
        task.execute();
    }

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final AppDatabase mDb;

        PopulateDbAsync(AppDatabase db) {
            mDb = db;
        }

        @Override
        protected Void doInBackground(final Void... params) {
            populateWithTestData(mDb);
            return null;
        }

    }

    public static void populateSync(@NonNull final AppDatabase database) {
        populateWithTestData(database);
    }

//    private static void addRecipe(final AppDatabase db, final String id,
//                                final User user, final Book book, Date from, Date to) {
//        Loan loan = new Loan();
//        loan.id = id;
//        loan.bookId = book.id;
//        loan.userId = user.id;
//        loan.startTime = from;
//        loan.endTime = to;
//        db.loanModel().insertLoan(loan);
//    }

    private static void addUser(final AppDatabase db, final String username,
                                final String password) {
        User user = new User(username, password);
        db.userDao().insert(user);
    }

    private static void populateWithTestData(AppDatabase db) {
        db.recipeDao().deleteAll();
        db.userDao().deleteAll();

        // Add admin account
        addUser(db, "admin", "1234");

//        List<User> userList = db.userDao().getAll();
//        Log.d(DatabaseInitializer.TAG, "Rows Count: " + userList.size());

//        try {
//            // Loans are added with a delay, to have time for the UI to react to changes.
//
//            Date today = getTodayPlusDays(0);
//            Date yesterday = getTodayPlusDays(-1);
//            Date twoDaysAgo = getTodayPlusDays(-2);
//            Date lastWeek = getTodayPlusDays(-7);
//            Date twoWeeksAgo = getTodayPlusDays(-14);
//
//            addLoan(db, "1", user1, book1, twoWeeksAgo, lastWeek);
//            Thread.sleep(DELAY_MILLIS);
//            addLoan(db, "2", user2, book1, lastWeek, yesterday);
//            Thread.sleep(DELAY_MILLIS);
//            addLoan(db, "3", user2, book2, lastWeek, today);
//            Thread.sleep(DELAY_MILLIS);
//            addLoan(db, "4", user2, book3, lastWeek, twoDaysAgo);
//            Thread.sleep(DELAY_MILLIS);
//            addLoan(db, "5", user2, book4, lastWeek, today);
//            Log.d("DB", "Added loans");
//
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

}
