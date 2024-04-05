package service;

import dao.DAOAccount;
import model.VerifyAccount;

public class VerifyAccountService {
    public VerifyAccount selectVerifyAccountByIdAccount(int idAccount) {
        return DAOAccount.selectVerifyAccountByIdAccount(idAccount);
    }

    public int updateStateVerify(VerifyAccount v) {
        return DAOAccount.updateStateVerify(v);
    }

    public int insertVerify(VerifyAccount v) {
        return DAOAccount.insertVerify(v);
    }
}
