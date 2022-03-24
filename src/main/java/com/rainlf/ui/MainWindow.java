package com.rainlf.ui;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.rainlf.component.encode.EncodeManager;
import com.rainlf.component.encode.EncodeManagerI;
import com.rainlf.component.eval.EvalManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author : rain
 * @date : 3/16/2022 2:28 PM
 */
public class MainWindow {
    private final Project project;
    private final EvalManager evalManager;
    private final EncodeManagerI encodeManager;

    private JPanel mainPanel;
    private JTabbedPane mainTabPane;

    /**
     * eval
     */
    private JPanel evalPane;
    private JList<String> licenseInfoList;
    private JButton resetEvalInfoButton;
    private JButton loadLicenseButton;
    private JPanel evalButtonPane;

    /**
     * encode
     */
    private JPanel encodePane;
    private JTextArea inputText;
    private JTextArea md5Text;
    private JTextArea urlText;
    private JTextArea base64Text;
    private JTextArea unicodeText;
    private JButton encodeButton;
    private JButton decodeButton;


    public MainWindow(Project project, EvalManager evalManager, EncodeManagerI encodeManager) {
        this.project = project;
        this.evalManager = evalManager;
        this.encodeManager = encodeManager;

        /* init */
        initEvalPane();
        initEncodePane();

        /* listener */
        // eval
        loadLicenseButton.addActionListener(e -> clickLoadResetInfoButton());
        resetEvalInfoButton.addActionListener(e -> clickResetEvalButton());

        encodeButton.addActionListener(e -> clickEncodeButton());
        decodeButton.addActionListener(e -> clickDecodeButton());
    }


    /**
     * eval
     */
    private void initEvalPane() {
        clickLoadResetInfoButton();
    }

    private void clickLoadResetInfoButton() {
        licenseInfoList.setListData(new String[0]);
        licenseInfoList.setListData(evalManager.loadLicenceInfo().toArray(new String[0]));
    }

    private void clickResetEvalButton() {
        evalManager.resetEvalInfo();
        Messages.showMessageDialog(project, "Reset eval success", "Greeting", Messages.getInformationIcon());
    }

    /**
     * encode
     */
    private void initEncodePane() {
    }

    private void clickEncodeButton() {
        md5Text.setText(encodeManager.md5Encrypt(inputText.getText()));
        urlText.setText(encodeManager.urlEncode(inputText.getText()));
        base64Text.setText(encodeManager.base64Encode(inputText.getText()));
        unicodeText.setText(encodeManager.unicodeEncode(inputText.getText()));
    }

    private void clickDecodeButton() {
        md5Text.setText(encodeManager.md5Encrypt(inputText.getText()));
        urlText.setText(encodeManager.urlDecode(inputText.getText()));
        base64Text.setText(encodeManager.base64Decode(inputText.getText()));
        unicodeText.setText(encodeManager.unicodeDecode(inputText.getText()));
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
