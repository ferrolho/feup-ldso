# Table of contents

- [Feature branch workflow](#feature-branch-workflow)
  - [You are creating the branch and starting the feature implementation](#you-are-creating-the-branch-and-starting-the-feature-implementation)
  - [You are helping your colleague implementing a feature](#you-are-helping-your-colleague-implementing-a-feature)
- [Deploying to DigitalOcean](#deploying-to-digitalocean)


# Feature branch workflow

#### You are creating the branch and starting the feature implementation

- Create a branch for the US you will be implementing  
`git checkout -b US_2-01`

- Add new files/modifications/deletions to commit  
`git add .`

- Commit changes  
`git commit -m "Created DB table for Sorting Centers"`

- Push changes to feature branch  
`git push origin US_2-01`


#### You are helping your colleague implementing a feature

- Checkout the branch your colleague created  
`git checkout US_2-01`

- Pull the branch  
`git pull origin US_2-01`


# Deploying to DigitalOcean

```
ssh root@46.101.187.147
*enter password*

reboot

ssh root@46.101.187.147
*enter password*

# kill previous running app (if any)
kill $(cat target/universal/project-app-1.0-SNAPSHOT/RUNNING_PID)

git pull

./activator dist

cd target/universal/
rm -r project-app-1.0-SNAPSHOT
unzip project-app-1.0-SNAPSHOT.zip
cd project-app-1.0-SNAPSHOT/bin/

./project-app -Dplay.evolutions.db.default.autoApply=true -Dplay.crypto.secret="Hcey3kRQ@?=_S__2H7pvQ@mkxJ8kF`KAe7X<<K3:56@X^B>m/Owzevh<C@D`be/J" &

^D
```
