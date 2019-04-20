1.git diff head head~1 (暂存区和head不同)
	bbbbbbbb
  git diff b1 b2 -- 文件名 (比较分支bbbbbbbb)
  git diff  commithash1 commithash2
  bbbbbbbb
2.合并message git rebase -i父类   bbbbbbbb
  git pick 基于 s 废弃
  bbbbbbbb
3.git reset 暂存区从commit还原 暂存区添加到工作区 --hard 暂存区 工作区 都还原
  git checkout 工作区从暂存区还原 老的丢失
  bbbbbbbb
  丢弃某些commit
  git reset --hard gitlog hashcode
  
bbbbbbbb