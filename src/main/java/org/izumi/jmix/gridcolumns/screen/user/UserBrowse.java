package org.izumi.jmix.gridcolumns.screen.user;

import io.jmix.ui.UiComponents;
import io.jmix.ui.component.Button;
import io.jmix.ui.component.Component;
import io.jmix.ui.component.DataGrid;
import io.jmix.ui.component.Label;
import org.izumi.jmix.gridcolumns.entity.User;
import io.jmix.ui.navigation.Route;
import io.jmix.ui.screen.*;
import org.springframework.beans.factory.annotation.Autowired;

@UiController("User.browse")
@UiDescriptor("user-browse.xml")
@LookupComponent("usersGrid")
@Route("users")
public class UserBrowse extends StandardLookup<User> {

    @Autowired
    private UiComponents uiComponents;

    @Autowired
    private DataGrid<User> usersGrid;

    private boolean generatedVisible = true;
    private boolean usernameVisible = true;

    @Subscribe("toggleGeneratedBtn")
    private void onToggleGeneratedBtnClick(Button.ClickEvent event) {
        generatedVisible = !generatedVisible;
        usersGrid.getColumnNN("generated").setVisible(generatedVisible);
    }

    @Subscribe("toggleUsernameBtn")
    private void onToggleUsernameBtnClick(Button.ClickEvent event) {
        usernameVisible = !usernameVisible;
        usersGrid.getColumnNN("username").setVisible(usernameVisible);
    }

    @Install(to = "usersGrid.generated", subject = "columnGenerator")
    private Component usersGridGeneratedColumnGenerator(DataGrid.ColumnGeneratorEvent<User> ignored) {
        final Label<String> label = uiComponents.create(Label.NAME);
        label.setValue("Generated column' value");
        return label;
    }
}