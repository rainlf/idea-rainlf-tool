package com.rainlf.ui;

import com.intellij.icons.AllIcons;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.rainlf.component.eval.EvalManager;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * @author : rain
 * @date : 3/16/2022 2:28 PM
 */
public class MainWindow {
    private JPanel mainPanel;
    private JTabbedPane mainTabPane;
    private JList<String> licenseInfoList;
    private JTextArea helloWorldTextArea;
    private JButton resetEvalInfoButton;
    private JButton loadLicenseButton;
    private JPanel evalPane;
    private JPanel evalButtonPane;
    private JPanel helloPane;

    private Project project;
    private EvalManager evalManager;

    public MainWindow(Project project, EvalManager evalManager) {
        this.project = project;
        this.evalManager = evalManager;

        // init
        loadLicenseInfo();

        // listener
        loadLicenseButton.addActionListener(e -> loadLicenseInfo());
        resetEvalInfoButton.addActionListener(e -> resetEvalInfo());
    }

    private void loadLicenseInfo() {
        licenseInfoList.setListData(evalManager.loadLicenceInfo().toArray(new String[0]));
    }

    private void resetEvalInfo() {
        evalManager.resetEvalInfo();
        Messages.showMessageDialog(project, "Reset eval success", "Greeting", Messages.getInformationIcon());
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
