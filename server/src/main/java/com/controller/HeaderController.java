package com.controller;

import com.config.DateBaseConfig;
import com.config.DateConfig;
import com.message.HeaderMessage;
import com.entity.AccountEntity;
import com.models.Account;
import com.repository.AccountRepository;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.SQLException;

public class HeaderController {
    public void start(ObjectOutputStream out, ObjectInputStream in) throws IOException, ClassNotFoundException, SQLException {
        while (true){
            HeaderMessage headerMessage=(HeaderMessage)in.readObject();
            switch (headerMessage){
                case AUTORIZATION:autorization(in, out);break;
                case REGISTRATION:registration(in,out);break;
            }
        }
    }

    private void registration(ObjectInputStream in,ObjectOutputStream out) throws IOException, ClassNotFoundException, SQLException {
        Account account=(Account) in.readObject();
        if(AccountRepository.getAccountRepository()
                .findAccountByLogin(account.getLogin(), account.getPassword())){
            out.writeObject(HeaderMessage.ACCOUNT_IS_EXISTS);
        }
        else {
            account.setDate_registration(DateConfig.getDateConfigure().nowDate());
            account.setRole(AccountEntity.accountPositionUser);
            account.setStatus(AccountEntity.accoutnStatusUnblock);
            AccountRepository.getAccountRepository().registrationAccount(account,account.getMen(),account.getPassport());
            out.writeObject(HeaderMessage.SEC_AUT);
        }
    }

    private void autorization(ObjectInputStream in ,ObjectOutputStream out) throws IOException, ClassNotFoundException, SQLException {
        Account regAcc=(Account) in.readObject();
            Account account=AccountRepository.getAccountRepository()
                    .autorizationRequest(regAcc);
            if (account.getStatus()==null){
                out.writeObject(HeaderMessage.NOT_FOUND_ACC);
            }
            else {
                if(account.getStatus().equals(AccountEntity.accountStatusBlock)){
                    out.writeObject(HeaderMessage.BAN_STATUS);
                }
                else {
                    out.writeObject(HeaderMessage.SEC_AUT);
                    if(account.getRole().equals(AccountEntity.accountPositionAdmin)){
                        out.writeObject(HeaderMessage.ADMIN_ACC);
                        AdminController adminController=new AdminController();
                        adminController.start(out,in,account);
                    }
                    if(account.getRole().equals(AccountEntity.accountPositionUser)){
                        out.writeObject(HeaderMessage.USER_ACC);
                        UserController userController=new UserController();
                        userController.start(out,in,account);
                    }
                }
            }
    }
}

