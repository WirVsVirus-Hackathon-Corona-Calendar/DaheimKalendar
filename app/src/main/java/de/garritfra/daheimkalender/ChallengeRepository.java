package de.garritfra.daheimkalender;

import java.util.Date;

import de.garritfra.daheimkalender.model.Challenge;
import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

public class ChallengeRepository {

    private static ChallengeRepository sharedInstance = new ChallengeRepository();

    private Realm realm;

    public ChallengeRepository() {
        super();
        realm = Realm.getDefaultInstance().getDefaultInstance();
    }

    public static ChallengeRepository getInstance() {
        if (sharedInstance == null) {
            sharedInstance = new ChallengeRepository();
            return sharedInstance;
        } else {
            return sharedInstance;
        }
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
}
