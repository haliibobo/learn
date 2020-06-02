package com.github.haliibobo.learn.linux.shell;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * say something.
 *
 * @author lizibo
 * @version 1.0
 * @date 2019-05-27 17:09
 * @description describe what this class do
 */
public class ShellUtil {

    public static String linuxShellexec(final String[] args) {

        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<String> future= executor.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                InputStream is =null;
                InputStreamReader ir = null;
                BufferedReader br =null;
                String result;
                ProcessBuilder pb = new ProcessBuilder(args);
                pb.redirectErrorStream(true);
                try {
                    Process p = pb.start();
                    p.waitFor();
                    is =p.getInputStream();
                    ir = new InputStreamReader(is);
                    br = new BufferedReader(ir);
                    StringBuilder sb = new StringBuilder();
                    String line;
                    while ((line = br.readLine()) != null) {
                        sb.append(line).append("\n");
                    }
                    result = sb.toString();
                }
                catch (Exception e) {
                    e.printStackTrace();
                    result="linux exc fail";
                }finally {
                    try {
                        if (null != br) {
                            br.close();
                        }
                        if (null != ir) {
                            ir.close();
                        }
                        if (null != is) {
                            is.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                        result="linux exc fail";
                    }
                }
                if(result.contains("success")){
                    String s = result.substring(0,result.length()-1);
                    String diffPath =s.substring(s.lastIndexOf("\n"));
                    diffPath =diffPath.replaceAll("\n","");
                    return diffPath;
                }
                return result;
            }
        });
        try {
            return future.get(10, TimeUnit.HOURS);
        } catch (Exception e) {
            e.printStackTrace();
            return "linux exc timeout";
        } finally {
            future.cancel(true);
            executor.shutdown();
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i <1 ; i++) {
            String[] shellPath ={"/Users/lizibo/IdeaProjects/feeder-mr/src/main/assembly/bin/run-FeedMr-diff.sh",
                "/user/recsys/recpro/unifiedfeed/itemprofile/akhal_mark_sku/201905270622",
                "/user/recsys/recpro/unifiedfeed/itemprofile/akhal_mark_sku/201905280622",
                "/user/recsys/recpro/unifiedfeed/itemprofile/akhal_mark_sku/diff",
                "SOH","hdfs://ns1007"};
            String result = linuxShellexec(shellPath);
            System.out.println(result);
        }

    }

}
