package com.rainlf.component.eval;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.application.PathManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.util.io.FileUtil;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author : rain
 * @date : 3/16/2022 4:28 PM
 */
public class EvalManager {
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
                    FileUtil.delete(file);
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
            return expireDate.toString();
        } catch (IOException e) {
            throw new RuntimeException("getExpireDate failed", e);
        }
    }
}
