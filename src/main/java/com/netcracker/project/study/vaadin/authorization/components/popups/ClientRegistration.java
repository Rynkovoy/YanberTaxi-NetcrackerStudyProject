package com.netcracker.project.study.vaadin.authorization.components.popups;

import com.google.common.collect.ImmutableList;
import com.netcracker.project.study.model.Role;
import com.netcracker.project.study.model.client.Client;
import com.netcracker.project.study.model.user.User;
import com.netcracker.project.study.persistence.facade.UserFacade;
import com.netcracker.project.study.persistence.facade.impl.PersistenceFacade;
import com.netcracker.project.study.services.ClientService;
import com.netcracker.project.study.services.impl.UserDetailsServiceImpl;
import com.netcracker.project.study.vaadin.authorization.page.AuthorizationPage;
import com.netcracker.project.study.vaadin.common.components.PhoneField;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.ViewScope;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.vaadin.addons.Toastr;
import org.vaadin.addons.builder.ToastBuilder;

import java.math.BigInteger;

@SpringComponent
@Scope(value = "prototype")
public class ClientRegistration extends Window{

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    PersistenceFacade persistenceFacade;

    @Autowired
    UserFacade userFacade;

    private Toastr toastr;

    public ClientRegistration() {
        VerticalLayout root = genRootLayout();
        root.addComponent(genFields());
        toastr = new Toastr();
        root.addComponent(toastr);
        center();
        setIcon(VaadinIcons.USER);
        setModal(true);
        setResizable(false);
        setCaption(" Client registration");
        setContent(root);
    }

    private VerticalLayout genRootLayout() {
        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.setWidth(600, Unit.PIXELS);
        verticalLayout.setMargin(true);
        verticalLayout.setSpacing(true);
        return verticalLayout;
    }

    private Component genFields() {
        HorizontalLayout horizontalLayout = new HorizontalLayout();

        VerticalLayout verticalLayout2 = new VerticalLayout();
        PhoneField phone = new PhoneField("Phone number");
        PasswordField password1 = new PasswordField("Password");
        PasswordField password2 = new PasswordField("Confirm password");
        Button cancel = new Button("Cancel", VaadinIcons.EXIT);
        cancel.setWidth(100, Unit.PIXELS);
        verticalLayout2.addComponents(phone, password1, password2, cancel);

        VerticalLayout verticalLayout1 = new VerticalLayout();
        TextField lastName = new TextField("Last name");
        TextField firstName = new TextField("Last name");
        TextField middleName = new TextField("Middle name");
        Button ok = new Button("Ok", VaadinIcons.USER_CHECK);
        ok.setWidth(100, Unit.PIXELS);
        verticalLayout1.addComponents(lastName, firstName, middleName, ok);
        verticalLayout1.setComponentAlignment(ok, Alignment.MIDDLE_RIGHT);

        ok.addClickListener(event -> {
            if (lastName.isEmpty()
                    || firstName.isEmpty()
                    || middleName.isEmpty()
                    || phone.isEmpty()
                    || password1.isEmpty()
                    || password2.isEmpty()) {
                toastr.toast(ToastBuilder.error("All fields must be filled!").build());
                return;
            }
            if (!password1.getValue().equals(password2.getValue())) {
                toastr.toast(ToastBuilder.error("Passwords don't match!").build());
                password1.clear();
                password2.clear();
                return;
            }
            if (userDetailsService.isClientLoginExist(phone.getValue())) {
                toastr.toast(ToastBuilder.error("This phone number already exists!").build());
                return;
            }

            Client client = new Client();
            client.setFirstName(firstName.getValue());
            client.setLastName(lastName.getValue());
            client.setMiddleName(middleName.getValue());
            client.setPhoneNumber(phone.getValue());
            client.setPoints(BigInteger.valueOf(0));
            persistenceFacade.create(client);

            User user = new User();
            user.setObjectId(client.getObjectId());
            user.setUsername(client.getPhoneNumber());
            user.setPassword(password2.getValue());
            user.setEnabled(true);
            user.setAuthorities(ImmutableList.of(Role.ROLE_CLIENT));
            userFacade.createUser(user);
            toastr.toast(ToastBuilder.success(
                    "You are successful registrated!\nEnter you login and password to continue the work.")
                    .build());
            close();
        });

        cancel.addClickListener(event -> {
            close();
        });

        horizontalLayout.addComponents(verticalLayout2, verticalLayout1);

        return horizontalLayout;
    }

    @Deprecated
    private Component genButtons() {
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.setSizeFull();
        //horizontalLayout.setDefaultComponentAlignment(Alignment.BOTTOM_RIGHT);

        Button ok = new Button("Ok", VaadinIcons.USER_CHECK);
        ok.setWidth(100, Unit.PIXELS);
        Button cancel = new Button("Cancel", VaadinIcons.EXIT);
        cancel.setWidth(100, Unit.PIXELS);

        horizontalLayout.addComponents(cancel, ok);

        horizontalLayout.setExpandRatio(cancel, 0.8f);
        horizontalLayout.setExpandRatio(ok, 0.2f);

        return horizontalLayout;
    }
}
