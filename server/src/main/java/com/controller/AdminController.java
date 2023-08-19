package com.controller;

import com.config.DateConfig;
import com.entity.AccountEntity;
import com.entity.RoomEntity;
import com.message.AdminMessage;
import com.message.HeaderMessage;
import com.models.Account;
import com.models.Employee;
import com.models.Room;
import com.repository.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.List;

public class AdminController {

    private void reviewInitialized(ObjectOutputStream out) throws SQLException, ClassNotFoundException, IOException {
        out.writeObject(ReviewRepository.getReviewRepository().findAll());
    }

    private void initializedRoom(ObjectOutputStream out) throws IOException, SQLException, ClassNotFoundException {
        out.writeObject(RoomRepository.getRoomRepository().getAllRoomAndPhoto());
    }

    private void iniitalizedEmployee(ObjectOutputStream out) throws SQLException, ClassNotFoundException, IOException {
        out.writeObject(EmployeeRepository.getEmployeeRepository().findAllEmployee());
    }

    private void initializedStatistic(ObjectOutputStream out) throws SQLException, ClassNotFoundException, IOException {
        System.out.println(AccountRepository.getAccountRepository().findAllAccount().size());
        System.out.println(RoomRepository.getRoomRepository().findByStatus(RoomEntity.statusOpen).size());
        System.out.println(RoomRepository.getRoomRepository().getAllRoomNoPhoto().size());
        double procentRoom=RoomRepository.getRoomRepository().findByStatus(RoomEntity.statusOpen).size()*100/RoomRepository.getRoomRepository().getAllRoomNoPhoto().size();
        System.out.println(procentRoom);
        out.writeObject(AccountRepository.getAccountRepository().findAllAccount().size());
        out.writeObject(0);
        out.writeObject(RoomRepository.getRoomRepository().getAllRoomNoPhoto().size());
        out.writeObject(new DecimalFormat("#0").format(procentRoom));
    }

    private void initializedAccount(Account account,ObjectOutputStream out) throws IOException, SQLException, ClassNotFoundException {
        List<Account>list=AccountRepository.getAccountRepository().findAllAccount();
        list.removeIf(account1 -> account1.getId()==account.getId() );
        out.writeObject(account);
        out.writeObject(list);
    }

    public void start(ObjectOutputStream out , ObjectInputStream in , Account account) throws IOException, ClassNotFoundException, SQLException {
        initializedRoom(out);
        initializedAccount(account, out);
        initializedStatistic(out);
        iniitalizedEmployee(out);
        reviewInitialized(out);
        while (true){
            AdminMessage adninMessage=(AdminMessage)in.readObject();
            switch (adninMessage)
            {
                case UPDATE_COMMENT:reviewInitialized(out);break;
                case REGISTRATION_EMPLOYEE:registrationEmployee(in);break;
                case REMOVE_EMPLOYEE:removeEmployee(in);break;
                case REPAIR_ROOM:updateStatusRoom(RoomEntity.statusRepair,in);break;
                case STOP_ROOM:updateStatusRoom(RoomEntity.statusClose,in);break;
                case OPEN_ROOM:updateStatusRoom(RoomEntity.statusOpen,in);break;
                case REMOVE_ROOM:removeRoom(out,in);break;
                case VIEW_ROOM:viewRoom(in, out);break;
                case ADD_EMOLOYEE:addEmployee(in);break;
                case UPDATE_ALL_ROOM:getAllRoom(out);break;
                case ADD_ROOM:addRoom(in);break;
                case ADD_ADMIN:addAdmin(in, out);break;
                case REMOVE_ACCOUNT:removeAccount(in,out,account);break;
                case СHANGE_PERS_ACC:changePersInfo(in,account);break;
                case BLOCK_ACC:blockAccount(in, out, account);break;
                case UNBLOCK_ACC:unBlockAccount(in, out, account);break;
                case EXIT:HeaderController headerController=new HeaderController();headerController.start(out, in);break;
            }
        }
    }

    private void registrationEmployee(ObjectInputStream in) throws IOException, ClassNotFoundException, SQLException {
        Employee employee=(Employee) in.readObject();
        employee.setDate_registration(DateConfig.getDateConfigure().nowDate());
        EmployeeRepository.getEmployeeRepository().registrationEmployee(employee);
    }

    private void removeEmployee(ObjectInputStream in) throws IOException, ClassNotFoundException, SQLException {
        EmployeeRepository.getEmployeeRepository().removeEmployee((Employee) in.readObject());
    }

    private void updateStatusRoom(String status , ObjectInputStream in) throws IOException, ClassNotFoundException, SQLException {
        Room room=(Room) in.readObject();
        room.setStatus(status);
        RoomRepository.getRoomRepository().updateStatusByID(room);
    }

    private void removeRoom(ObjectOutputStream out, ObjectInputStream in) throws IOException, ClassNotFoundException, SQLException {
        RoomRepository.getRoomRepository().removeRoom((Room) in.readObject());
    }

    private void viewRoom(ObjectInputStream in,ObjectOutputStream out) throws IOException, ClassNotFoundException, SQLException {
        Room room=(Room) in.readObject();
        out.writeObject(RoomRepository.getRoomRepository().findById(room));
    }

    private void addEmployee(ObjectInputStream in) throws IOException, ClassNotFoundException, SQLException {
            Employee account=(Employee) in.readObject();
            account.setDate_registration(DateConfig.getDateConfigure().nowDate());
            EmployeeRepository.getEmployeeRepository().registrationEmployee(account);
    }

    private void unBlockAccount(ObjectInputStream in ,ObjectOutputStream out,Account account) throws IOException, SQLException, ClassNotFoundException {
        Account blockAcc=(Account) in.readObject();
        if(blockAcc.getId()==account.getId()){
            out.writeObject(HeaderMessage.YOU_ACCOUNT);
        }
        else {
            out.writeObject(HeaderMessage.OK);
            out.writeObject(AccountEntity.accoutnStatusUnblock);
            blockAcc.setStatus(AccountEntity.accoutnStatusUnblock);
            AccountRepository.getAccountRepository().changeStatus(blockAcc);
        }
    }

    private void blockAccount(ObjectInputStream in ,ObjectOutputStream out,Account account) throws IOException, SQLException, ClassNotFoundException {
        Account blockAcc=(Account) in.readObject();
        if(blockAcc.getId()==account.getId()){
            out.writeObject(HeaderMessage.YOU_ACCOUNT);
        }
        else {
            out.writeObject(HeaderMessage.OK);
            out.writeObject(AccountEntity.accountStatusBlock);
            blockAcc.setStatus(AccountEntity.accountStatusBlock);
            AccountRepository.getAccountRepository().changeStatus(blockAcc);
        }
    }

    private void changePersInfo(ObjectInputStream in, Account account) throws IOException, ClassNotFoundException, SQLException {
        Account newAcc=(Account) in.readObject();
        newAcc.setId(account.getId());
        newAcc.getMen().setId(account.getMen().getId());
        newAcc.getPassport().setId_passport(account.getPassport().getId_passport());
        AccountRepository.getAccountRepository().changeAccount(newAcc);
        MenRepository.getMenRepository().changeMen(newAcc.getMen());
        PassportRepository.getPassportRepository().changePassport(newAcc.getPassport());

    }

    private void removeAccount(ObjectInputStream in, ObjectOutputStream out,Account account) throws IOException, ClassNotFoundException, SQLException {
        Account ac=(Account) in.readObject();
        if(ac.getId()==account.getId()){
            out.writeObject(HeaderMessage.YOU_ACCOUNT);
        }
        else {
            out.writeObject(HeaderMessage.OK);
            AccountRepository.getAccountRepository().removeAccount(ac);
        }
    }

    private void addAdmin(ObjectInputStream in ,ObjectOutputStream out) throws IOException, ClassNotFoundException, SQLException {
        Account account=(Account) in.readObject();
        if(AccountRepository.getAccountRepository()
                .findAccountByLogin(account.getLogin(), account.getPassword())){
            out.writeObject(HeaderMessage.ACCOUNT_IS_EXISTS);
        }
        else {
            account.setDate_registration(DateConfig.getDateConfigure().nowDate());
            account.setRole(AccountEntity.accountPositionAdmin);
            account.setStatus(AccountEntity.accoutnStatusUnblock);
            AccountRepository.getAccountRepository().registrationAccount(account,account.getMen(),account.getPassport());
            out.writeObject(HeaderMessage.SEC_AUT);
        }
    }

    private void addRoom(ObjectInputStream in) throws IOException, ClassNotFoundException, SQLException {
        Room room=(Room) in.readObject();
        room.setStatus(RoomEntity.statusOpen);
        RoomRepository.getRoomRepository().registrationRoom(room);
    }

    private void getAllRoom(ObjectOutputStream out) throws SQLException, ClassNotFoundException, IOException {
        out.writeObject(RoomRepository.getRoomRepository().getAllRoomAndPhoto());
    }
}
