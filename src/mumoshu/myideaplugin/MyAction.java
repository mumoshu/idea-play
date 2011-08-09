package mumoshu.myideaplugin;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.ui.Messages;

/**
 * Created by IntelliJ IDEA.
 * User: mumoshu
 * Date: 11/08/09
 * Time: 23:35
 * To change this template use File | Settings | File Templates.
 */
public class MyAction extends AnAction {
    public void actionPerformed(AnActionEvent e) {
        Messages.showDialog("message", "title", new String[] {}, 0, 0, null);
    }
}
