git branch --set-upstream-to=origin/dev 
git push origin HEAD -u
git push origin --delete
git branch -d 
git remote set-url origin git@gitlabb.ky-tech.com.cn:kye-devops/devops_font.git
git remote set-url origin http://yourname:password@bitbucket.org/yourname/project.git
mvn clean compile install -Dmaven.test.skip=true -U -e

git remote set-url origin http://taoxincheng:Tao111111@gitlab.ky-tech.com.cn/erp-coo/tos-basic.git


切换分支
git checkout -b dev origin/release/caigou_v1.0
git checkout -b 本地分支名 origin/远程分支名

git push -u origin dev:release/caigou_v1.0
git push <远程主机名> <本地分支名>:<远程分支名>