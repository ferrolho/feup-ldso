cd /root/git/feup-ldso/project-app

# kill previous running app (if any)
kill $(cat target/universal/project-app-1.0-SNAPSHOT/RUNNING_PID)

git pull

./activator dist

cd target/universal/
rm -r project-app-1.0-SNAPSHOT
unzip project-app-1.0-SNAPSHOT.zip
cd project-app-1.0-SNAPSHOT/bin/

./project-app -Dplay.evolutions.db.default.autoApply=true -Dplay.crypto.secret="Hcey3kRQ@?=_S__2H7pvQ@mkxJ8kF`KAe7X<<K3:56@X^B>m/Owzevh<C@D`be/J" &
