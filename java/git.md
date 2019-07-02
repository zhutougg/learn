git branch --set-upstream-to=origin/dev 
git push origin HEAD -u
git push origin --delete
git branch -d 
git remote set-url origin git@gitlabb.ky-tech.com.cn:kye-devops/devops_font.git
git remote set-url origin http://yourname:password@bitbucket.org/yourname/project.git
mvn clean compile install -Dmaven.test.skip=true -U -e

git remote set-url origin http://taoxincheng:Tao111111@gitlab.ky-tech.com.cn/erp-coo/tos-basic.git

git push --set-upstream origin http://taoxincheng:Tao111111@gitlab.ky-tech.com.cn/erp-coo/tos-basic.git

切换分支
git checkout -b dev origin/release/caigou_v1.0
git checkout -b 本地分支名 origin/远程分支名

git push -u origin dev:release/caigou_v1.0
git push <远程主机名> <本地分支名>:<远程分支名>


1 git checkout -b 新分支名
执行该指令后，会在本地创建一个新分支，该分支是从当前分支上检出的，所以所有文件内容都和当前分支一模一样，这是正常的。创建成功后，将自动切换至新分支上


2 git push --set-upstream origin 分支名



查看谁修改了东西
git log --pretty=format:"%h - %an, %ar :%s"  --name-status --author=xx  --since="2008-10-01" --before="2008-11-01"
git show hash filename