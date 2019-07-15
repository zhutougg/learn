
//克隆分支
git clone http://taoxincheng:Tao111111@gitlab.ky-tech.com.cn/erp-coo/tos-hr.git
//删除本地分支
git branch -d 

mvn clean compile install -Dmaven.test.skip=true -U -e

//更改remote url 账号密码
git remote set-url origin http://taoxincheng:Tao111111@gitlab.ky-tech.com.cn/erp-coo/tos-depot.git



切换分支remote已经有的
git checkout -b dev origin/caigou_v1.0
git checkout -b 本地分支名 origin/远程分支名

git push -u origin dev:caigou_v1.0
git push <远程主机名> <本地分支名>:<远程分支名>


1 git checkout -b 新分支名
执行该指令后，会在本地创建一个新分支，该分支是从当前分支上检出的，所以所有文件内容都和当前分支一模一样，这是正常的。创建成功后，将自动切换至新分支上


2 git push --set-upstream origin 分支名



查看谁修改了东西
git log --pretty=format:"%h - %an, %ar :%s"  --name-status --author=xx  --since="2008-10-01" --before="2008-11-01"
git show hash filename