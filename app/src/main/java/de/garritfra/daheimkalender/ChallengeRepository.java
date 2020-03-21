package de.garritfra.daheimkalender;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

import java.util.Date;
import java.util.List;

import de.garritfra.daheimkalender.model.Challenge;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmList;
import io.realm.RealmResults;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ChallengeRepository {

    private static ChallengeRepository sharedInstance = new ChallengeRepository();

    ArrayList<Challenge> challenges = new ArrayList<Challenge>();

    RealmConfiguration config = new RealmConfiguration.Builder()
            .name("default.realm")
            .schemaVersion(1)
            .deleteRealmIfMigrationNeeded()
            .build();

    private Realm realm;

    private final String challengeBaseURL = "https://e3bzj7x3ck.execute-api.eu-west-1.amazonaws.com/v1/challenges";

    public ChallengeRepository() {
        super();
        realm = Realm.getInstance(config);
    }

    public static ChallengeRepository getInstance() {
        if (sharedInstance == null) {
            sharedInstance = new ChallengeRepository();
            return sharedInstance;
        } else {
            return sharedInstance;
        }
    }

    public ArrayList<Challenge> getChallenges() {
        return challenges;
    }

    public void update(final OnUpdateFinishedListener listener) {


        final OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://e3bzj7x3ck.execute-api.eu-west-1.amazonaws.com/v1/challenges")
                .build();

        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.e("E", String.valueOf(e.getStackTrace()));
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull final Response response) throws IOException {
                final Challenge[] fetchedChallenges = new Gson().fromJson(response.body().string(), Challenge[].class);

                new Handler(Looper.getMainLooper()).post(new Runnable() { // <-- if you are not on UI thread and want to go there
                    @Override
                    public void run() {
                        for (Challenge challenge : fetchedChallenges) {
                            updateOne(challenge);
                            challenges.add(challenge);
                            Log.d("CHALLENGE", challenge.toString());
                        }

                        listener.onUpdateFinished();
                    }
                });


            }
        });
    }

    void createOne(final Challenge challenge) throws Error {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.insertOrUpdate(challenge);
            }
        });
    }

    void createMultiple(final RealmList<Challenge> challenges) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.insertOrUpdate(challenges);
            }
        });
    }

    Challenge readOneById(int id) {
        return realm.where(Challenge.class).equalTo("id", id).findFirst();
    }

    RealmResults<Challenge> readManyByIds(String[] ids) {
        return realm.where(Challenge.class).findAll();
    }

    RealmResults<Challenge> readAll() {
        return realm.where(Challenge.class).findAll();
    }

    RealmResults<Challenge> getChallengesBefore(Date date) {
        return realm.where(Challenge.class).lessThan("dateStart", date).findAll();
    }

    Challenge getTodaysChallenge() {
        return realm.where(Challenge.class).equalTo("dateStart", new Date()).findFirst();
    }

    void updateOne(final Challenge challenge) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.insertOrUpdate(challenge);
            }
        });
    }

    void updateMany(final RealmList<Challenge> challenges) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.insertOrUpdate(challenges);
            }
        });
    }

    void delete(final Challenge challenge) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                challenge.deleteFromRealm();
            }
        });
    }

    void deleteMany(final RealmList<Challenge> challenges) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                challenges.deleteAllFromRealm();
            }
        });
    }

    void deleteAll() {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.delete(Challenge.class);
            }
        });
    }

    void close() {
        realm.close();
    }

    public interface OnUpdateFinishedListener {

        // this can be any type of method
        void onUpdateFinished();
    }
}
