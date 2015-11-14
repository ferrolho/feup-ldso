# Deploying to DigitalOcean

```
ssh root@46.101.187.147
*enter password*

reboot

ssh root@46.101.187.147
*enter password*

cd git/feup-ldso/
git pull

cd project-app/
./activator dist

cd target/universal/
ls
rm -r project-app-1.0-SNAPSHOT
unzip project-app-1.0-SNAPSHOT.zip
cd project-app-1.0-SNAPSHOT/bin/
ls
rm ../RUNNING_PID

./project-app -Dhttp.port=80 -Dplay.evolutions.db.default.autoApply=true -Dplay.crypto.secret="Hcey3kRQ@?=_S__2H7pvQ@mkxJ8kF`KAe7X<<K3:56@X^B>m/Owzevh<C@D`be/J"

^Z

jobs
%1 &
jobs

^D
```
