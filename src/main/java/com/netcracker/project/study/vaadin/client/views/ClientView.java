package com.netcracker.project.study.vaadin.client.views;

import com.github.appreciated.material.MaterialTheme;
import com.netcracker.project.study.model.client.Client;
import com.netcracker.project.study.model.driver.Driver;
import com.netcracker.project.study.model.driver.DriverStatusList;
import com.netcracker.project.study.model.driver.car.Car;
import com.netcracker.project.study.model.order.Order;
import com.netcracker.project.study.model.order.status.OrderStatus;
import com.netcracker.project.study.persistence.facade.impl.PersistenceFacade;
import com.netcracker.project.study.services.ClientService;
import com.netcracker.project.study.services.DriverService;
import com.netcracker.project.study.services.OrderService;
import com.netcracker.project.study.services.impl.UserDetailsServiceImpl;
import com.netcracker.project.study.vaadin.authorization.components.popups.ClientRegistration;
import com.netcracker.project.study.vaadin.client.components.grids.ClientCurrentOrderGrid;
import com.netcracker.project.study.vaadin.client.components.grids.ClientOrdersGrid;
import com.netcracker.project.study.vaadin.client.components.OrderMaker;
import com.netcracker.project.study.vaadin.client.events.RefreshClientViewEvent;
import com.netcracker.project.study.vaadin.client.events.SendClientMessage;
import com.netcracker.project.study.vaadin.client.popups.ClientUpdate;
import com.netcracker.project.study.vaadin.client.popups.DriverEvaluation;
import com.netcracker.project.study.vaadin.driver.components.views.OrdersViewForDrivers;
import com.netcracker.project.study.vaadin.driver.events.CancelClientOrderEvent;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.spring.annotation.*;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.vaadin.addons.Toast;
import org.vaadin.addons.ToastPosition;
import org.vaadin.addons.ToastType;
import org.vaadin.addons.Toastr;
import org.vaadin.addons.builder.ToastBuilder;
import org.vaadin.addons.builder.ToastOptionsBuilder;
import org.vaadin.spring.events.EventBus;
import org.vaadin.spring.events.annotation.EventBusListenerMethod;

import javax.annotation.PostConstruct;
import java.util.List;

@SpringView(name = ClientView.VIEW_NAME)
public class ClientView extends VerticalLayout implements View {

    public static final String VIEW_NAME = "client";

    @Autowired
    OrdersViewForDrivers ordersViewForDrivers;

    @Autowired
    private OrderMaker orderMaker;

    @Autowired
    ClientService clientService;

    @Autowired
    OrderService orderService;

    @Autowired
    DriverService driverService;

    @Autowired
    private ClientOrdersGrid clientOrdersGrid;

    @Autowired
    private ClientCurrentOrderGrid clientCurrentOrderGrid;

    @Autowired
    PersistenceFacade facade;

    @Autowired
    DaoAuthenticationProvider daoAuthenticationProvider;

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    ClientUpdate ClientWindow;

    @Autowired
    private EventBus.UIEventBus viewEventBus;

    @Autowired
    EventBus.ApplicationEventBus appEventBus;

    @Autowired
    private DriverEvaluation driverEvaluation;

    private Button newOrder, cancelOrder;

    Client client;

    private Window window;

    private Toastr toastr;

    private UI currentUi;

    List<Order> currentOrders ;

    public void init() {
        currentUi = getUI();
        initClient();
        clientCurrentOrderGrid.setClient(client);
        clientCurrentOrderGrid.init();
        orderMaker.setClient(client);
        clientOrdersGrid.setClient(client);
        clientOrdersGrid.init();

        toastr = new Toastr();
        addComponent(toastr);
        setComponentAlignment(toastr, Alignment.TOP_RIGHT);

        HorizontalLayout mainButtons = setMainButtons();
        addComponent(mainButtons);
        setComponentAlignment(mainButtons, Alignment.TOP_CENTER);

        HorizontalLayout historyOfClientOrders = new HorizontalLayout(clientOrdersGrid);
        historyOfClientOrders.setSizeFull();

        Panel ordersHistoryPanel = getPanel("History of your orders", historyOfClientOrders);
        ordersHistoryPanel.setIcon(VaadinIcons.RECORDS);

        HorizontalLayout clientCurrentOrder = new HorizontalLayout(clientCurrentOrderGrid);

        Panel currentOrderPanel = getPanel("Your current order", clientCurrentOrder);
        currentOrderPanel.setIcon(VaadinIcons.INFO_CIRCLE_O);

        HorizontalSplitPanel horizontalSplitPanel = new HorizontalSplitPanel();
        horizontalSplitPanel.addComponent(ordersHistoryPanel);
        horizontalSplitPanel.addComponent(currentOrderPanel);
        addComponent(horizontalSplitPanel);
        setComponentAlignment(horizontalSplitPanel, Alignment.MIDDLE_CENTER);

    }

    public void initClient() {
        this.client = userDetailsService.getCurrentUser();
    }

    private HorizontalLayout setMainButtons() {
        HorizontalLayout root = new HorizontalLayout();

        newOrder = new Button("Make new order", VaadinIcons.TAXI);
        newOrder.addStyleName(MaterialTheme.BUTTON_HUGE + " " + MaterialTheme.BUTTON_ICON_ALIGN_RIGHT + " "
                                + MaterialTheme.BUTTON_FRIENDLY);
        newOrder.setDescription("Press this button to make your order");
        Button.ClickListener orderCreaterListener = new NewOrderCreater();
        newOrder.addClickListener(orderCreaterListener);

        cancelOrder = new Button("Cancel the order", VaadinIcons.CLOSE_BIG);
        cancelOrder.addStyleName(MaterialTheme.BUTTON_HUGE + " " + MaterialTheme.BUTTON_ICON_ALIGN_RIGHT + " "
                                    + MaterialTheme.BUTTON_DANGER);
        cancelOrder.setDescription("Press this button to cancel your last order");
        Button.ClickListener orderCancelListener = new OrderCancelListener();
        cancelOrder.addClickListener(orderCancelListener);

        if (isActiveOrderExist()) {
            newOrder.setVisible(false);
            cancelOrder.setVisible(true);
        } else {
            newOrder.setVisible(true);
            cancelOrder.setVisible(false);
        }

        root.addComponent(newOrder);
        root.addComponent(cancelOrder);

        return root;
    }

    private Panel getPanel(String caption, Component component) {
        Panel panel = new Panel(caption);
        panel.setSizeFull();
        panel.setContent(component);

        return panel;
    }

    class NewOrderCreater implements Button.ClickListener {

        @Override
        public void buttonClick(Button.ClickEvent clickEvent) {
            initWindow("");
            window.setCaption(" Add information about your order");
            window.setIcon(VaadinIcons.KEYBOARD);
            window.setContent(orderMaker);
            UI.getCurrent().addWindow(window);
            orderMaker.enableCancelOrderButton(cancelOrder);
            orderMaker.disableNewOrderButton(newOrder);
            orderMaker.closeOrderMakerWindow(window);
            orderMaker.showSuccesToaster(toastr);
        }
    }

    class OrderCancelListener implements Button.ClickListener {

        @Override
        public void buttonClick(Button.ClickEvent clickEvent) {
            List<Order> orderList = orderService.getActiveOrdersByClientId(client.getObjectId());
            if (orderList.size() > 0) {
                Order currentOrder = null;
                for (Order order : orderList) {
                    currentOrder = order;
                    orderService.changeStatus(OrderStatus.CANCELED, order.getObjectId());
                    if (currentOrder.getDriverOnOrder() != null){
                        driverService.changeStatus(DriverStatusList.FREE, currentOrder.getDriverOnOrder());
                    }
                }
                Toast cancelOrderToast = ToastBuilder.of(ToastType.Info, "<b>Order " + currentOrder.getName() + " was canceled</b> ")
                        .caption("Infomation")
                        .options(ToastOptionsBuilder.having()
                                .closeButton(true)
                                .debug(false)
                                .progressBar(true)
                                .preventDuplicates(true)
                                .position(ToastPosition.Top_Full_Width)
                                .timeOut(10000)
                                .build())
                        .build();
                toastr.toast(cancelOrderToast);

                newOrder.setVisible(true);
                cancelOrder.setVisible(false);

                clientOrdersGrid.init();
                clientCurrentOrderGrid.init();
                appEventBus.publish(this, new CancelClientOrderEvent(currentOrder.getObjectId()));
            } else {
                Toast cancelPerformingOrderToast = ToastBuilder.of(ToastType.Warning, "<b>You can't cancel performing order</b> ")
                    .caption("Attention")
                    .options(ToastOptionsBuilder.having()
                            .preventDuplicates(true)
                            .position(ToastPosition.Top_Right)
                            .build())
                     .build();
                toastr.toast(cancelPerformingOrderToast);
            }
        }
    }

    private boolean isActiveOrderExist() {
        List<Order> orderList = orderService.getActiveOrdersByClientId(client.getObjectId());
        List<Order> orderPerformingList = orderService.getPerformingOrdersByClientId(client.getObjectId());
        if (orderList.size() > 0 || orderPerformingList.size() > 0) return true;

        return false;
    }

    private void initWindow(String text){
        window = new Window(" Information");
        window.setResizable(false);
        window.setIcon(VaadinIcons.INFO_CIRCLE);
        window.center();
        window.setModal(true);
        VerticalLayout verticalLayout = new VerticalLayout();
        Label content = new Label(text, ContentMode.HTML);
        verticalLayout.addComponent(content);
        window.setContent(verticalLayout);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        init();
    }


    @PostConstruct
    public void afterPropertiesSet() {
        viewEventBus.subscribe(this,true);

    }

    @EventBusListenerMethod
    public void closeSymmUsageWindow(RefreshClientViewEvent event) {
        currentOrders = orderMaker.getOrdersList();
        if (currentOrders == null) {
            return;
        }
        for (Order currentOrder:currentOrders) {
            if (currentOrder.getObjectId().equals(event.getOrderId())) {
                clientOrdersGrid.init();
                clientCurrentOrderGrid.init();
                driverEvaluation.init(event.getOrderId());
                newOrder.setVisible(true);
                cancelOrder.setVisible(false);
                if (!currentUi.getWindows().contains(driverEvaluation)) {
                    currentUi.addWindow(driverEvaluation);
                }
            }
        }
    }

    @EventBusListenerMethod
    public void sendMessageWindow(SendClientMessage event) {
        currentOrders = orderMaker.getOrdersList();
        if (currentOrders == null) {
            return;
        }
        for (Order currentOrder:currentOrders) {
            if (currentOrder.getObjectId().equals(event.getOrderId())) {
                clientOrdersGrid.init();
                clientCurrentOrderGrid.init();
                /*Window message = new Window();
                VerticalLayout verticalLayout = new VerticalLayout();
                verticalLayout.setWidth(400, Unit.PIXELS);
                verticalLayout.setMargin(true);
                verticalLayout.setSpacing(true);
                TextField text = new TextField();
                text.setData("Your order is already accepted");
                text.setReadOnly(true);
                verticalLayout.addComponent(text);
                message.setModal(true);
                message.setResizable(false);
                currentUi.addWindow(message);
                message.setContent(verticalLayout);*/
                Driver driver = event.getDriver();
                String driverName = driver.getLastName() + " " + driver.getFirstName();

                Car car = event.getCar();
                String carName = car.getMakeOfCar() + " " + car.getModelType();

                toastr.toast(ToastBuilder.success("Your order has been accepted by " + driverName +
                         ". " + "Car: " + carName).build());
            }
        }

    }
}
