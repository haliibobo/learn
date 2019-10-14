package com.github.haliibobo.learn.git;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;

/**
 * say something.
 *
 * @author lizibo
 * @version 1.0
 * @date 2019-10-14 20:39
 * @description describe what this class do
 */
public class GitTest2 {

    public static void main(String[] args) throws IOException, GitAPIException {
        FileRepositoryBuilder builder = new FileRepositoryBuilder();
        Repository repo = builder.setGitDir(new File("localrepositary"+"\\.git")).setMustExist(true).build();
        Git git = new Git(repo);
        Iterable<RevCommit> log = git.log().call();
        for (Iterator<RevCommit> iterator = log.iterator(); iterator.hasNext();) {
            RevCommit rev = iterator.next();
            System.out.println(rev.getFullMessage());
        }
    }

}
