package git;

/**
 * @author KCWang
 * @version 1.0  git 相关管理
 * @date 2023/7/9 下午12:01
 *
 *  Git 常用命令
 * 1) git config --global user.name 用户名              《设置用户签名》
 * 2) git config --global user.email 邮箱               《设置用户签名》
 * 3) git init                                          《初始化本地库，管理项目》
 * 4) git status      《查看本地库状态》
 * 6) git add 文件名     《添加到暂存区》
 * 7) git rm --cached 文件名      《将暂存区里的文件删除，工作区里还是有的》
 * 8) git commit -m "日志信息" 文件名     《提交的本地库》
 * 9) git reflog     《查看版本信息》
 * 10) git log      《查看版本详细信息》
 * 11) git reset --hard 版本号     《版本穿梭，回到任意版本(如git reset --hard 9aa3670)》
 * 12) git branch 分支名      《创建分支》
 * 13) git branch -v      《查看分支》
 * 14) git checkout 分支名    《更换分支》
 * 15) git merge 分支名     《把指定的分支和并到当前分支上》
 * 16) git remote -v       《查看当前所有远程地址别名》
 * 17) git remote add 别名 远程地址    《创建远程地址别名》
 * 18) git push 别名 分支        《将本地仓库内容推送到远程仓库(或者 git push 远程地址 分支)》
 * 19) git pull 别名 分支      《将远程仓库内容拉取到本地仓库》
 * 20) git clone 远程地址    《将远程仓库内容克隆到本地仓库》
 *
 * Github :  用户名  KCWang152253     密码 wangcheng152253
 * Token:  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
 * 不过期的Token值：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
 *
 * Git 产生密钥
 *         cd ~/.ssh
 *         ssh-keygen -t rsa -C 2747962529@qq.com
 *         Generating public/private rsa key pair.
 *         cat ~/.ssh/id_rsa.pub
 *
 *
 *
 *
 * 使用Git将本地文件提交到远程仓库
 *      1、（先进入项目文件夹）通过命令  git init  把这个目录变成git可以管理的仓库
 *      2、把文件添加到版本库中，使用命令 git add . 添加到暂存区里面去，不要忘记后面的小数点“.”，意为添加文件夹下的所有文件
 *      3、用命令 git commit告诉Git，把文件提交到仓库。引号内为提交说明：git commit -m 'initialize the project20230710'
 *      4、关联到远程库git remote add origin 你的远程库地址  git remote add origin https://github.com/zhangsan/project.git
 *          git remote add origin  https://github.com/KCWang152253/javaWeb.git
 *          git remote add origin https://github.com/KCWang152253/JavvaNote.git
 *      5、git pull 获取远程库与本地同步合并(如果远程库不为空必须做这一步，否则后面的提交会失败)  git pull --rebase origin master
 *      6、把本地库的内容推送到远程，使用 git push命令，实际上是把当前分支master推送到远程。执行此命令后会要求输入用户名、密码，验证通过后即开始上传。
 *      7、补充命令（第6步失效）
 *          git remote set-url origin https://<your_token>@github.com/<USERNAME>/<REPO>.git
 *         <your_token>：换成你自己得到的token
 *         <USERNAME>：是你自己github的用户名
 *         <REPO>：是你的仓库名称
 *         不过期的token值：
 *              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
 *              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
 *              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
 *              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
 *
 *         git remote set-url origin https://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@github.com/KCWang152253/JavvaNote.git
 *         git remote set-url origin https://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@github.com/KCWang152253/JavvaNote.git
 *         git remote set-url origin https://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@github.com/KCWang152253/JavvaNote.git
 *         git remote set-url origin https://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@github.com/KCWang152253/JavvaNote.git
 *         git remote set-url origin https://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@github.com/KCWang152253/javaWeb.git
 *
 *
 */
public class Git {

}
