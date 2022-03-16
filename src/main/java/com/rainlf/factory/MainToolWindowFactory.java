package com.rainlf.factory;

import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import com.rainlf.component.eval.EvalManager;
import com.rainlf.ui.MainWindow;
import org.jetbrains.annotations.NotNull;

/**
 * @author : rain
 * @date : 3/16/2022 3:27 PM
 */
public class MainToolWindowFactory implements ToolWindowFactory, DumbAware {
    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
        MainWindow mainWindow = new MainWindow(project, new EvalManager());
        ContentFactory contentFactory = ContentFactory.SERVICE.getInstance();
        Content content = contentFactory.createContent(mainWindow.getMainPanel(), "", false);
        toolWindow.getContentManager().addContent(content);
    }
}
