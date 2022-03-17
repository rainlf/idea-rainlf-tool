package com.rainlf.component.eval;

import com.intellij.openapi.application.PathManager;
import com.intellij.openapi.util.io.FileUtil;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author : rain
 * @date : 3/16/2022 4:28 PM
 */
public class EvalManager {
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public List<String> loadLicenceInfo() {
        List<String> licenceInfoList = new ArrayList<>();
        File evalDir = getEvalDir();
        if (evalDir.exists()) {
            File[] files = evalDir.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.getName().endsWith(".key")) {
                        licenceInfoList.add(String.format("License: %s Expire: %s", file.getName(), getExpireDate(file)));
                    }
                }
            }
        }
        return licenceInfoList;
    }

    public void resetEvalInfo() {
        File evalDir = getEvalDir();
        if (evalDir.exists()) {
            File[] files = evalDir.listFiles();
            if (files != null) {
                for (File file : files) {
                    resetEvalFile(file);
                }
            }
        }
    }

    private File getEvalDir() {
        return new File(PathManager.getConfigPath(), "eval");
    }

    private String getExpireDate(File file) {
        try {
            DataInputStream dis = new DataInputStream(new FileInputStream(file));
            Date expireDate = new Date(~dis.readLong() + 2592000000L);
            return sdf.format(expireDate);
        } catch (IOException e) {
            throw new RuntimeException("getExpireDate failed", e);
        }
    }

    private void resetEvalFile(File file) {
        boolean success = FileUtil.delete(file);
        if (!success) {
            try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(file))) {
                dos.writeLong(~System.currentTimeMillis());
            } catch (IOException e) {
                throw new RuntimeException("update eval file failed", e);
            }
        }
    }
}
