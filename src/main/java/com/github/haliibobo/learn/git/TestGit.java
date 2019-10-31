package com.github.haliibobo.learn.git;

import com.jcraft.jsch.Session;
import java.io.File;
import java.util.Iterator;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.PullCommand;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.internal.storage.file.FileRepository;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.transport.JschConfigSessionFactory;
import org.eclipse.jgit.transport.OpenSshConfig;
import org.eclipse.jgit.transport.SshSessionFactory;

/**
 * @author weiwenwen
 * @date 2019/10/14 18:44
 * @description TODO
 */
public class TestGit {

    public static void main(String[] args) throws Exception {
        File localDir = new File("docs");
        if (!localDir.exists()) {
            gitClone("****.git", localDir, "master");
        }
        gitPull(new File("docs"));
    }

    static void gitClone(String remoteUrl, File repoDir, String branchName) throws GitAPIException {
        JschConfigSessionFactory sessionFactory = new JschConfigSessionFactory() {
            public void configure(OpenSshConfig.Host hc, Session session) {
                session.setConfig("StrictHostKeyChecking", "no");
            }
        };

        SshSessionFactory.setInstance(sessionFactory);

        Git git = Git.cloneRepository()
            .setURI(remoteUrl)
            .setBranch(branchName)
            .setDirectory(repoDir)
            .call();
        System.out.println("Cloning from " + remoteUrl + " to " + git.getRepository());
    }

    static void gitPull(File repoDir) {
        File repoGitDir = new File(repoDir.getAbsolutePath() + "/.git");
        if (!repoGitDir.exists()) {
            throw new Error("Error! Not Exists : " + repoGitDir.getAbsolutePath());
        } else {
            JschConfigSessionFactory sessionFactory = new JschConfigSessionFactory() {
                public void configure(OpenSshConfig.Host hc, Session session) {
                    session.setConfig("StrictHostKeyChecking", "no");
                }
            };
            SshSessionFactory.setInstance(sessionFactory);
            Repository repo = null;
            try {
                repo = new FileRepository(repoGitDir.getAbsolutePath());
                Git git = new Git(repo);
                PullCommand pullCmd = git.pull();
                pullCmd.call();
                //System.out.println("commit log:" + git.getRepository().readCommitEditMsg());
                Iterable<RevCommit> log = git.log().call();
                for (RevCommit rev : log) {
                    System.out.println(rev.getCommitTime());
                    System.out.println(rev.getFullMessage());
                    System.out.println("---- ***** -----");
                }
                //System.out.println(
                //    "Pulled from remote repository to local repository at " + repo.getDirectory() +
                //        ", branch name is : " + git.getRepository().getBranch());
            } catch (Exception e) {
                System.out.println("pull git failed: " + repoGitDir.getAbsolutePath());
                e.printStackTrace();
            } finally {
                if (repo != null) {
                    repo.close();
                }
            }
        }
    }
}
