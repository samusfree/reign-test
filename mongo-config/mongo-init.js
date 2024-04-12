db = db.getSiblingDB('be-test-articles');

db.createCollection('articles');

db.createUser(
    {
        user: "betest",
        pwd: "password",
        roles: [
            {
                role: "readWrite",
                db: "be-test-articles"
            }
        ]
    }
);
