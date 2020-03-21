package de.garritfra.daheimkalender;

import android.content.Context;

import de.garritfra.daheimkalender.model.Challenge;
import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

public class ChallengeDAO {

    private Realm realm;

    public ChallengeDAO(Context context) {
        Realm.init(context);
        realm = Realm.getDefaultInstance().getDefaultInstance();
    }

    void createOne(final Challenge challenge) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.insert(challenge);
            }
        });
        close();
    }

    void createMultiple(final RealmList<Challenge> challenges) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.insert(challenges);
            }
        });
        close();
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

    void updateOne(final Challenge challenge) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.insertOrUpdate(challenge);
            }
        });
        close();
    }

    void updateMany(final RealmList<Challenge> challenges) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.insertOrUpdate(challenges);
            }
        });
        close();
    }

    void delete(final Challenge challenge) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                challenge.deleteFromRealm();
            }
        });
        close();
    }

    void deleteMany(final RealmList<Challenge> challenges) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                challenges.deleteAllFromRealm();
            }
        });
        close();
    }

    void deleteAll() {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.delete(Challenge.class);
            }
        });
        close();
    }

    void close() {
        realm.close();
    }
}
