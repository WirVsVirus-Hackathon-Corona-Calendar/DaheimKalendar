package de.garritfra.daheimkalender;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import de.garritfra.daheimkalender.model.Challenge;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmList;
import io.realm.RealmResults;
import io.realm.Sort;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ChallengeRepository {

    private static ChallengeRepository sharedInstance = new ChallengeRepository();

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

    public Challenge getNextAvailableChallenge() {
        Challenge lastCompleted = realm.where(Challenge.class).equalTo("isCompleted", true).sort("order", Sort.DESCENDING).findFirst();
        if (lastCompleted != null) {
            // check if day has passed since last completed
            Calendar completionDate = Calendar.getInstance();
            completionDate.setTimeInMillis(lastCompleted.getCompletionDate());
            Calendar now = Calendar.getInstance();
            now.setTimeInMillis(new Date().getTime());
            if (completionDate.get(Calendar.YEAR) == now.get(Calendar.YEAR) && completionDate.get(Calendar.MONTH) == now.get(Calendar.MONTH) && completionDate.get(Calendar.DAY_OF_MONTH) == now.get(Calendar.DAY_OF_MONTH)) {
                return realm.where(Challenge.class).equalTo("isCompleted", false).sort("order", Sort.ASCENDING).findFirst();
            } else {
                return null;
            }
        } else {
            // no completed challenges yet, so we return the first one
            return realm.where(Challenge.class).sort("order", Sort.ASCENDING).findFirst();
        }
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
                        deleteAll();

                        if (fetchedChallenges != null) {
                            for (Challenge challenge : fetchedChallenges) {
                                updateOne(challenge);
                                Log.d("CHALLENGE", challenge.toString());
                            }
                        }

                        listener.onUpdateFinished();
                    }
                });


            }
        });
    }

    public void createOne(final Challenge challenge) throws Error {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.insertOrUpdate(challenge);
            }
        });
    }

    public void createMultiple(final RealmList<Challenge> challenges) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.insertOrUpdate(challenges);
            }
        });
    }

    public Challenge readOneById(String id) {
        return realm.where(Challenge.class).equalTo("id", id).findFirst();
    }

    public RealmResults<Challenge> readManyByIds(String[] ids) {
        return realm.where(Challenge.class).findAll();
    }

    public RealmResults<Challenge> readAll() {
        return realm.where(Challenge.class).findAll();
    }

    public void updateOne(final Challenge challenge) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.insertOrUpdate(challenge);
            }
        });
    }

    public void updateMany(final RealmList<Challenge> challenges) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.insertOrUpdate(challenges);
            }
        });
    }

    public void delete(final Challenge challenge) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                challenge.deleteFromRealm();
            }
        });
    }

    public void deleteMany(final RealmList<Challenge> challenges) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                challenges.deleteAllFromRealm();
            }
        });
    }

    public void deleteAll() {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.delete(Challenge.class);
            }
        });
    }

    public void close() {
        realm.close();
    }

    public interface OnUpdateFinishedListener {

        // this can be any type of method
        void onUpdateFinished();
    }

    public void setCompleted(final Challenge challenge, final Boolean completed) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                challenge.setCompleted(completed);
            }
        });
    }

    public void setCompletionDate(final Challenge challenge, final Date completionDate) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                challenge.setCompletionDate(completionDate.getTime());
            }
        });
    }

    public void setImagePath(final Challenge challenge, final String imagePath) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                challenge.setImagePath(imagePath);
            }
        });
    }

    public void setTutorialSteps(final Challenge challenge, List<String> tutorialSteps) {
        final RealmList<String> realmList = new RealmList<String>();
        for (String step : tutorialSteps) {
            realmList.add(step);
        }
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                challenge.setTutorialSteps(realmList);
            }
        });
    }

    public void setResources(final Challenge challenge, List<String> resources) {
        final RealmList<String> realmList = new RealmList<String>();
        for (String resource : resources) {
            realmList.add(resource);
        }
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                challenge.setResources(realmList);
            }
        });
    }
}
