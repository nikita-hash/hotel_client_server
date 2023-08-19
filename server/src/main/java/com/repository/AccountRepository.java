package com.repository;

import com.config.DateBaseConfig;
import com.models.Account;
import com.models.Men;
import com.models.Passport;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountRepository extends DateBaseConfig {
    private static AccountRepository AccountRepository = new AccountRepository();

    public  static AccountRepository getAccountRepository(){
        return AccountRepository;
    }

    private List<Account> listAccount(ResultSet resultSet) throws SQLException, ClassNotFoundException {
        List<Account>list=new ArrayList<>();
        while (resultSet.next()){
            Account account=new Account();
            account.setId(resultSet.getInt("id"));
            account.setRole(resultSet.getString("position"));
            account.setStatus(resultSet.getString("status"));
            account.setPassword(resultSet.getString("password"));
            account.setLogin(resultSet.getString("login"));
            account.setDate_registration(resultSet.getString("date_registration"));
            account.setAvatar(resultSet.getString("avatar"));
            account.setMen(MenRepository.getMenRepository().findByAccountId(account.getId()));
            account.setPassport(PassportRepository.getPassportRepository().findByIdAccount(account.getId()));
            account.setNetwork(NeworkRepository.getNeworkRepository().findByIdAccount(account.getId()));
            list.add(account);
        }
        return list;
    }



    public Account autorizationRequest(Account account) throws SQLException, ClassNotFoundException {
        String find_user="SELECT * FROM hotel.account  WHERE account.login = "+"'"+account.getLogin()+"'"+" AND account.password = "+"'"+
                account.getPassword()+"'";
        PreparedStatement preparedStatement=getDbconnection().prepareStatement(find_user);
        ResultSet res = preparedStatement.executeQuery();
        Account user=new Account();
        while (res.next()){
            user.setId(res.getInt("id"));
            user.setStatus(res.getString("status"));
            user.setRole(res.getString("position"));
            user.setAvatar(res.getString("avatar"));
            user.setDate_registration(res.getString("date_registration"));
            user.setLogin(res.getString("login"));
            user.setPassword(res.getString("password"));
            user.setMen(MenRepository.getMenRepository().findByAccountId(user.getId()));
            user.setPassport(PassportRepository.getPassportRepository().findByIdAccount(user.getId()));
            user.setNetwork(NeworkRepository.getNeworkRepository().findByIdAccount(user.getId()));
        }
        return user;
    }

    public List<Account> findAllAccount() throws SQLException, ClassNotFoundException {
        String request="select * from hotel.account";
        PreparedStatement preparedStatement= getDbconnection().prepareStatement(request);
        ResultSet resultSet=preparedStatement.executeQuery();
        return listAccount(resultSet);
    }

    public Account findById(int id) throws SQLException, ClassNotFoundException {
        String reqiest="select * from hotel.account where hotel.account.id = "+id;
        PreparedStatement preparedStatement= getDbconnection().prepareStatement(reqiest);
        ResultSet resultSet=preparedStatement.executeQuery();
        Account account=new Account();
        while (resultSet.next()){
            account.setId(resultSet.getInt("id"));
            account.setRole(resultSet.getString("position"));
            account.setStatus(resultSet.getString("status"));
            account.setPassword(resultSet.getString("password"));
            account.setLogin(resultSet.getString("login"));
            account.setDate_registration(resultSet.getString("date_registration"));
            account.setAvatar(resultSet.getString("avatar"));
            account.setMen(MenRepository.getMenRepository().findByAccountId(account.getId()));
            account.setPassport(PassportRepository.getPassportRepository().findByIdAccount(account.getId()));
        }
        return account;
    }

    public boolean findAccountByLogin(String login , String password) throws SQLException, ClassNotFoundException {
        String request="SELECT * FROM hotel.account  WHERE hotel.account.login = "+"'"+login+"'"+" AND account.password = "+"'"+
                password+"'";
        PreparedStatement statement=getDbconnection().prepareStatement(request);
        ResultSet resault=statement.executeQuery();
        if(resault.next()){
            return true;
        }
        return false;
    }

    public void registrationAccount(Account account, Men men, Passport passport) throws SQLException, ClassNotFoundException {
        String insert_user="INSERT INTO hotel.account( login, password, status, position, date_registration, avatar) "+"VALUES(?,?,?,?,?,?)";
        PreparedStatement statement_account=getDbconnection().prepareStatement(insert_user,PreparedStatement.RETURN_GENERATED_KEYS);
        statement_account.setString(1,account.getLogin());
        statement_account.setString(2,account.getPassword());
        statement_account.setString(3,account.getStatus());
        statement_account.setString(4,account.getRole());
        statement_account.setString(5,account.getDate_registration());
        statement_account.setString(6,account.getAvatar());
        statement_account.executeUpdate();
        ResultSet res=statement_account.getGeneratedKeys();
        res.next();
        String insert_personal_info="INSERT INTO hotel.men( id_account, name, last_name, patronymic, phone) "+"VALUES(?,?,?,?,?)";
        PreparedStatement statement_personal_info=getDbconnection().prepareStatement(insert_personal_info);
        men.setAccount(account);
        men.getAccount().setId(res.getInt(1));
        statement_personal_info.setInt(1,men.getAccount().getId());
        statement_personal_info.setString(2,men.getName());
        statement_personal_info.setString(3,men.getLast_name());
        statement_personal_info.setString(4,men.getPatronymic());
        statement_personal_info.setString(5,men.getPhone());
        statement_personal_info.executeUpdate();
        String insert_passport="insert into hotel.pasport(id_account,id,seria_number) VALUES(?,?,?)";
        PreparedStatement statement_passport_info=getDbconnection().prepareStatement(insert_passport);
        statement_passport_info.setInt(1,res.getInt(1));
        statement_passport_info.setString(2,passport.getId_passport());
        statement_passport_info.setString(3,passport.getSeria_number());
        statement_passport_info.executeUpdate();
    }

    public void removeAccount(Account account) throws SQLException, ClassNotFoundException {
        String request="delete from hotel.account where hotel.account.id ="+account.getId();
        PreparedStatement preparedStatement= getDbconnection().prepareStatement(request);
        preparedStatement.executeUpdate();
    }

    public void changeStatus(Account account) throws SQLException, ClassNotFoundException {
        String request="UPDATE hotel.account set hotel.account.status = '"+account.getStatus()+"' where hotel.account.id = "+account.getId();
        PreparedStatement preparedStatement= getDbconnection().prepareStatement(request);
        preparedStatement.executeUpdate();
    }

    public void changeAccount(Account account) throws SQLException, ClassNotFoundException {
        String request="update hotel.account set hotel.account.avatar = '"+account.getAvatar()+"', hotel.account.login = '"
                +account.getLogin()+"', hotel.account.password = '"+account.getPassword()+"' where hotel.account.id = "+account.getId();
        PreparedStatement preparedStatement= getDbconnection().prepareStatement(request);
        preparedStatement.executeUpdate();
    }
}
