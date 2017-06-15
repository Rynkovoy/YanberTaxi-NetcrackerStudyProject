package com.netcracker.project.study.vaadin.client.components.grids;

import com.github.appreciated.material.MaterialTheme;
import com.netcracker.project.study.model.client.Client;
import com.netcracker.project.study.model.order.Order;
import com.netcracker.project.study.model.order.OrderStatusEnum;
import com.netcracker.project.study.model.order.route.Route;
import com.netcracker.project.study.services.OrderService;
import com.netcracker.project.study.vaadin.driver.components.popup.OrderInfoPopUp;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.math.BigInteger;
import java.util.List;

@SpringComponent
public class ClientCurrentOrderGrid extends CustomComponent {

    @Autowired
    OrderService orderService;

    Client client;

    public void init() {
        VerticalLayout ord = currentOrder();
        setCompositionRoot(ord);
    }

    public void setClient(Client client) {this.client = client;}

    private VerticalLayout currentOrder() {
        List<Order> clientCurrentOrder = orderService.getCurrentOrderByClientId(client.getObjectId());
        Order currentOrder = clientCurrentOrder.size() >0 ? clientCurrentOrder.get(0) : null;

        MarginInfo margin = new MarginInfo(false, false, false, true);

        VerticalLayout currentOrderLayout = new VerticalLayout();
        currentOrderLayout.setDefaultComponentAlignment(Alignment.TOP_CENTER);

        if (currentOrder != null) {
            Label number = new Label("<b>№: </b>" + currentOrder.getObjectId(), ContentMode.HTML);
            Label driverName = new Label("<b>Driver: </b>" + (currentOrder.getDriverOnOrder() != null ? currentOrder.getDriverOnOrder().getFirstName() + " " +
                    currentOrder.getDriverOnOrder().getLastName() : "N/A"), ContentMode.HTML);
            driverName.setIcon(VaadinIcons.MALE);
            Label status = new Label("<b>Status: </b>" + OrderStatusEnum.getStatusValue(currentOrder.getStatus()), ContentMode.HTML);

            HorizontalLayout numAndStatus = new HorizontalLayout();
            numAndStatus.addComponents(number, status);

            VerticalLayout driverAndNumStat = new VerticalLayout();
            driverAndNumStat.addComponents(numAndStatus, driverName);
            driverAndNumStat.setComponentAlignment(numAndStatus, Alignment.TOP_CENTER);
            driverAndNumStat.setComponentAlignment(driverName, Alignment.TOP_CENTER);
            driverAndNumStat.setMargin(margin);

            HorizontalLayout allRoutes = new HorizontalLayout();
            allRoutes.setDefaultComponentAlignment(Alignment.TOP_CENTER);
            allRoutes.setMargin(margin);

            Label[] routes = getRoutes(currentOrder.getObjectId());

            VerticalLayout trio = new VerticalLayout();
            trio.addComponents(routes[0],routes[1]);
            try {
                if (routes[2] != null) trio.addComponent(routes[2]);
            } catch (IndexOutOfBoundsException ex) {}

            VerticalLayout pair = new VerticalLayout();
            try {
                if (routes[3] != null) pair.addComponent(routes[3]);
                if (routes[4] != null) pair.addComponent(routes[4]);
            } catch (IndexOutOfBoundsException ex) {}

            allRoutes.addComponents(trio, pair);

            currentOrderLayout.addComponents(driverAndNumStat, allRoutes);
            currentOrderLayout.setMargin(false);
        } else {
            Label message = new Label("<b>You have no active order </b>", ContentMode.HTML);
            message.addStyleName(MaterialTheme.LABEL_H2);
            currentOrderLayout.addComponent(message);
            currentOrderLayout.setComponentAlignment(message, Alignment.MIDDLE_CENTER);
        }

        return currentOrderLayout;
    }

    private Label[] getRoutes(BigInteger orderId){
        List<Route> routes = orderService.getRoutes(orderId);
        Label[] routesLables = new Label[routes.size()];

        for(int i = 0; i < routes.size(); i++){
            String value = "<b>Address " + (i+1) + ": </b>";
            if (i == 0) value = "<b>Starting Point (Address 1): </b>";
            if (i == routes.size() - 1) value = "<b>Destination (Address " + (routes.size() - 1) + "): </b>";
            Label label = new Label(value + routes.get(i).getCheckPoint(), ContentMode.HTML);
            label.setIcon(VaadinIcons.MAP_MARKER);
            if (i == 0) label.setIcon(VaadinIcons.HOME_O);
            if (i == routes.size() - 1) label.setIcon(VaadinIcons.FLAG_CHECKERED);
            routesLables[i] = label;
        }

        return routesLables;
    }
}