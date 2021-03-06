package com.netcracker.project.study.vaadin.admin.views;

import com.netcracker.project.study.vaadin.admin.components.grids.ClientsGrid;
import com.netcracker.project.study.vaadin.admin.components.logo.Copyright;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Label;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;

@SpringView(name = ClientsView.VIEW_NAME)
public class ClientsView extends VerticalLayout implements View {

    public static final String VIEW_NAME = "clients";

    @Autowired
    private ClientsGrid clientsGrid;

    /*@Autowired
    EventBus.UIEventBus eventBus;*/

    void init() {
        TabSheet tabSheet = getTabSheet();
        tabSheet.setSizeFull();
        tabSheet.addStyleName(ValoTheme.TABSHEET_FRAMED);
        tabSheet.addStyleName(ValoTheme.TABSHEET_PADDED_TABBAR);
        addComponent(tabSheet);
    }

    private TabSheet getTabSheet() {
        TabSheet tabSheet = new TabSheet();
        tabSheet.setSizeFull();
        tabSheet.addTab(getControlTab(), "All clients");

        return tabSheet;
    }

    private VerticalLayout getControlTab() {
        VerticalLayout controlLayout = new VerticalLayout();
        controlLayout.addComponent(clientsGrid);
        return controlLayout;
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        clientsGrid.init();
        clientsGrid.refreshGrid();
        init();
    }
}
